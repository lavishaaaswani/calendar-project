import java.io.*;
import java.net.HttpURLConnection;   
import java.net.URL;                 
import java.util.Properties;
import java.util.Base64;             
import org.apache.poi.ss.usermodel.*;      
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
import org.json.JSONArray;          
import org.json.JSONObject;         

public class test {
    public static void main(String[] args) {
        try {
            Properties config = new Properties();
            config.load(new FileInputStream("config.properties"));

            String email = config.getProperty("email");
            String apiToken = config.getProperty("apiToken");
            String baseUrl = config.getProperty("baseUrl");
            String[] subCalendarIds = config.getProperty("subCalendarIds").split(",");

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Calendar Events");
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.CORAL.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            
            Row headerRow = sheet.createRow(0);
            Cell c0 = headerRow.createCell(0); c0.setCellValue("Resource Name"); c0.setCellStyle(headerStyle);
            Cell c1 = headerRow.createCell(1); c1.setCellValue("Start Date");     c1.setCellStyle(headerStyle);
            Cell c2 = headerRow.createCell(2); c2.setCellValue("End Date");       c2.setCellStyle(headerStyle);
            Cell c3 = headerRow.createCell(3); c3.setCellValue("Event Type");     c3.setCellStyle(headerStyle);

            int rowIndex = 1;

            for (int i = 0; i < 4; i++) {
               sheet.autoSizeColumn(i);
            }

            for (String subCalendarId : subCalendarIds) {
                String apiUrl = baseUrl + "/wiki/rest/calendar-services/1.0/calendar/events.json?subCalendarId=" +
                                subCalendarId + "&start=2025-02-28T18:30:00Z&end=2025-08-31T18:30:00Z";
                HttpURLConnection con = (HttpURLConnection) new URL(apiUrl).openConnection();

                con.setRequestMethod("GET");

                String auth = email + ":" + apiToken;
                String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
                con.setRequestProperty("Authorization", "Basic " + encodedAuth);

                int responseCode = con.getResponseCode();
                if (responseCode == 200) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    StringBuilder content = new StringBuilder();
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    in.close();
                    con.disconnect();

                    JSONObject responseJson = new JSONObject(content.toString());
                    JSONArray events = responseJson.getJSONArray("events");

                    for (int i = 0; i < events.length(); i++) {
                        JSONObject event = events.getJSONObject(i);
                        //if (!title.toLowerCase().contains("Lavisha")) continue;

                        Row row = sheet.createRow(rowIndex++);
                        row.createCell(0).setCellValue(event.optString("title"));
                        row.createCell(1).setCellValue(event.optString("start"));
                        row.createCell(2).setCellValue(event.optString("end"));
                        row.createCell(3).setCellValue(event.optString("eventType"));
                    }
                } else {
                    System.out.println("Failed to fetch events from: " + subCalendarId + ". HTTP Code: " + responseCode);
                }
            }

            FileOutputStream fileOut = new FileOutputStream("calendar_events.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();

            System.out.println("Events written to: calendar_events.xlsx");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


import java.io.BufferedReader;//to efficiently read the incoming data line by line 
import java.io.InputStreamReader;//need to convert bytes → characters
import java.net.HttpURLConnection;// to send a request and receive using http
import java.net.URL;// to create url object to connect to web address
import java.util.Base64;//to encode token and email and for token authentication

public class CalendarAPI {

    public static void main(String[] args) {
        try {
            // Define the API URL (example for Leave calendar)
            String[] subCalendarIds = {"s1", "s2","s3"} ;
                    
            for (String subCalendarId : subCalendarIds) {
                String apiUrl = "apiurl"
                        + subCalendarId
                        + "&start=2025-02-28T18:30:00Z&end=2025-08-31T18:30:00Z";//T is Seperator and Z for UTC time zone 

            //Create the connection to web address(call an api in java)
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Add Authorization (username + API token)
            String email = "enter your email address"; 
            String apiToken ="enter your api token";
                                  // generated from Atlassian account
            String auth = email + ":" + apiToken;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

            con.setRequestProperty("Authorization", "Basic " + encodedAuth);//header(http request part)

            //Read the response
            int responseCode = con.getResponseCode();
            if (responseCode == 200) {//200 is if it is successfull
                System.out.println("API Response:");

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine).append("\n");
                }
                in.close();//closing  input and  connection
                con.disconnect();
                // Print the raw JSON response
                System.out.println(content.toString());
            } else {
                System.out.println("Failed. HTTP Response Code: " + responseCode);
            }
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

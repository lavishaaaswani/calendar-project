# 📅 Calendar Event Export Tool

This tool retrieves calendar events from **Confluence** and exports them into an **Excel (.xlsx)** file.

---

## ✅ Prerequisites

Before you begin, make sure you have:

- ✅ Java **17 or above** installed
- ✅ API token from your Atlassian (Confluence) account
- ✅ SubCalendar ID(s) from your Confluence calendar
- ✅ Email ID linked to your Confluence account
- ✅ Internet access to fetch calendar data

---

## 📦 Installation Steps

1. **Clone or download** this repository (zip or Git).
2. Ensure the following files and folders are present:
   - `CalendarEventExporter.java` (main Java file)
   - `config.properties` (your configuration file)
   - `lib/` folder with Apache POI and org.json JARs
   - *(Optional)* `run.command` for Mac/Linux quick start

3. **Install Java 17+**  
   Download Java from [Oracle’s official page](https://www.oracle.com/java/technologies/javase-downloads.html)

---

## ⚙️ Configure `config.properties`

Create or edit the `config.properties` file with your own credentials and details:

```properties
email = your_email@yourdomain.com
apiToken = your_api_token_from_Confluence
baseUrl = https://yourcompany.atlassian.net
subCalendarIds = calendarId1,calendarId2
```
🔐 How to Get Your API Token
Visit: https://id.atlassian.com/manage/api-tokens

Click on Create API token

Copy the generated token.

Paste it into your config.properties file under the apiToken field like so:

📅 How to Get SubCalendar ID(s)
Open your Confluence Calendar in the browser

Open Developer Tools (F12 or Cmd+Opt+I)

Go to the Network tab

Reload the calendar page

Look for requests like:calendar/events.json?subCalendarId=xyz123
Copy the subCalendarId and paste it into config.properties

▶️ How to Run the Program
On Mac/Linux
Use the terminal or double-click run.command (if available):

bash
Copy
Edit
./run.command
On Windows
Open Command Prompt / Terminal

Navigate to the project folder:

bash
Copy
Edit
cd path\to\your\project
Run the program:

bash
Copy
Edit
java -cp "lib/*;." CalendarEventExporter
✅ Expected Output
If successful, you will see:

css
Copy
Edit
HTTP 200 OK
Events written to: calendar_events.xlsx
A new file named calendar_events.xlsx will be created in the same folder.
🎉 You're Done!
You’ve successfully installed and run the Calendar Event Export Tool.
Your Excel output can now be used for reporting, documentation, or analysis!



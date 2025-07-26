📅 Calendar Event Export Tool
This tool allows you to export calendar events from Atlassian Confluence into a neatly formatted Excel (.xlsx) file. It's designed to make event data portable, analyzable, and easy to document.
🚀 Features
Extract events from one or more Confluence sub-calendars.
Save results in an Excel file (calendar_events.xlsx).
Easy configuration using a config.properties file.
Cross-platform support (Mac, Linux, Windows).
✅ Prerequisites
Java 17 or higher is installed: https://www.oracle.com/java/technologies/javase-downloads.html
API token for your Atlassian (Confluence) account.
SubCalendar ID(s) from your Confluence calendar.
Email ID linked to your Atlassian account.
Internet access to fetch calendar data.
📁 Project Structure
project-folder/ ├── CalendarEventExporter.java      # Main program ├── config.properties               # User configuration ├── lib/                            # Folder with dependencies (Apache POI, org.json JARs) └── run.command                     # Mac/Linux startup script (optional)
⚙️ Configuration (config.properties)
Create or edit config.properties in the root folder:
email = your_email@yourdomain.com apiToken = your_api_token_from_Confluence baseUrl = https://yourcompany.atlassian.net subCalendarIds = calendarId1,calendarId2
🔐 Get Your API Token
1. Go to: https://id.atlassian.com/manage/api-tokens
2. Click Create API token
3. Copy and paste it into config.properties.
📘 Find SubCalendar IDs
Open your Confluence calendar in a browser
Open Developer Tools (F12) → go to the Network tab
Reload the calendar
Look for a request like: calendar/events.json?subCalendarId=xyz123
Copy each subCalendarId and paste into config.properties
▶️ How to Run
💻 On Mac/Linux
Make run.command executable if needed:
chmod +x run.command ./run.command
🖥 On Windows
cd path\to\project-folder java -cp "lib/*;." CalendarEventExporter
Replace `;` with `:` if on Mac/Linux.
✅ Expected Output
If successful, you’ll see:  HTTP 200 OK Events written to: calendar_events.xlsx
An Excel file named calendar_events.xlsx will be created in the project directory.
🛠 Troubleshooting
**404 or 401 Errors?** – Double-check your API token, email, and base URL in config.properties.
**Empty Excel file?** – Ensure you’re using the correct subCalendarIds and that those calendars have upcoming events.
**Java Not Found?** – Make sure Java 17+ is installed and added to your system’s PATH.
📦 You're Done!
You've successfully installed and run the Calendar Event Export Tool! Use the generated Excel file for reporting, documentation, or further analysis.
📄 License
MIT License (add if applicable)

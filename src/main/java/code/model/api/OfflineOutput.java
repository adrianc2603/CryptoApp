package code.model.api;

public class OfflineOutput implements OutputAPI {

    @Override
    public String sendReport(String reportData) {
        if (reportData == null || reportData.isEmpty()) {
            return "Cannot send a null or empty message";
        }
        return "{" +
                "  \"account_sid\": \"ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\"," +
                "  \"api_version\": \"2010-04-01\"," +
                "  \"body\": \"" + reportData + "\"," +
                "  \"date_created\": \"Thu, 30 Jul 2015 20:12:31 +0000\"," +
                "  \"date_sent\": \"Thu, 30 Jul 2015 20:12:33 +0000\"," +
                "  \"date_updated\": \"Thu, 30 Jul 2015 20:12:33 +0000\"," +
                "  \"direction\": \"outbound-api\"," +
                "  \"error_code\": null," +
                "  \"error_message\": null," +
                "  \"from\": \"+14158141829\"," +
                "  \"messaging_service_sid\": \"MGXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\"," +
                "  \"num_media\": \"0\"," +
                "  \"num_segments\": \"1\"," +
                "  \"price\": null," +
                "  \"price_unit\": null," +
                "  \"sid\": \"SMXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\"," +
                "  \"status\": \"sent\"," +
                "  \"subresource_uris\": {" +
                "    \"media\": \"/2010-04-01/Accounts/ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Messages/SMXXXXXXXXXXXXXX" +
                "XXXXXXXXXXXXXXXXXX/Media.json\"" +
                "  }," +
                "  \"to\": \"+123456789\"," +
                "  \"uri\": \"/2010-04-01/Accounts/ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Messages/SMXXXXXXXXXXXXXXXXXX" +
                "XXXXXXXXXXXXXX.json\"" +
                "}";
    }

}

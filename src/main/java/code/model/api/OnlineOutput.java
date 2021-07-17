package code.model.api;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class OnlineOutput implements OutputAPI {

    private final String accountSID;
    private final String authToken;
    private final String phoneNumberFrom;
    private final String phoneNumberTo;

    public OnlineOutput() {
        JSONParser parser = new JSONParser();
        JSONObject config;
        JSONObject twilioConfig;
        try {
            config = (JSONObject) parser.parse(new FileReader("src/main/resources/config.json"));
            twilioConfig = (JSONObject) config.get("twilio");
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not load api keys from configuration file");
        }

        this.accountSID = (String) twilioConfig.get("accountSid");
        this.authToken = (String) twilioConfig.get("authToken");
        this.phoneNumberFrom = (String) twilioConfig.get("phoneNumberFrom");
        this.phoneNumberTo = (String) twilioConfig.get("phoneNumberTo");
    }

    @Override
    public String sendReport(String reportData) {
        if (reportData == null || reportData.isEmpty()) {
            return "Cannot send a null or empty message";
        }

        /*
         * Please note the below code was influenced by the following website:
         * https://zetcode.com/java/getpostrequest/
         */

        StringBuilder builder = new StringBuilder();

        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("To", this.phoneNumberTo));
        parameters.add(new BasicNameValuePair("From", this.phoneNumberFrom));
        parameters.add(new BasicNameValuePair("Body", reportData));

        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            var request = new HttpPost("https://" + this.accountSID + ":" + this.authToken +
                    "@api.twilio.com/2010-04-01/Accounts/" + this.accountSID + "/Messages.json");
            request.setHeader("User-Agent", "Java client");
            request.setHeader("Content-Type", "application/x-www-form-urlencoded");
            request.setEntity(new UrlEncodedFormEntity(parameters));

            CloseableHttpResponse response = client.execute(request);
            var bufReader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            String output;
            while ((output = bufReader.readLine()) != null) {
                builder.append(output);
                builder.append(System.lineSeparator());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Something went wrong. Please try again";
        }

        return builder.toString();

        /*
         * End of code block
         */
    }

}

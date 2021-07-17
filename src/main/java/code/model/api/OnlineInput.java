package code.model.api;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class OnlineInput implements InputAPI {

    private final String key;

    public OnlineInput() {
        JSONParser parser = new JSONParser();
        JSONObject config;
        JSONObject coinConfig;
        try {
            config = (JSONObject) parser.parse(new FileReader("src/main/resources/config.json"));
            coinConfig = (JSONObject) config.get("coinmarketcap");
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not load api keys from configuration file");
        }

        this.key = (String) coinConfig.get("key");
    }

    @Override
    public String getListOfCryptocurrencies() {
        return accessAPI("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest");
    }

    @Override
    public String getInfoPanelOnCryptoCurrency(String symbol) {
        if (symbol == null || symbol.isEmpty()) {
            return "You must enter all parameters";
        }
        return accessAPI("https://pro-api.coinmarketcap.com/v1/cryptocurrency/info?symbol=" + symbol);
    }

    @Override
    public String convert(String baseCurrency, String targetCurrency, String amount) {
        if (baseCurrency == null || baseCurrency.isEmpty() || targetCurrency == null || targetCurrency.isEmpty() ||
                amount == null || amount.isEmpty()) {
            return "You must enter all parameters";
        }
        try {
            Double.parseDouble(amount);
        }
        catch (Exception e) {
            return "Invalid amount entered. Try again";
        }
        return accessAPI("https://pro-api.coinmarketcap.com/v1/tools/price-conversion?amount=" + amount +
                "&symbol=" + baseCurrency + "&convert=" + targetCurrency);
    }

    /*
     * Please note the below code was influenced by the following website:
     * https://zetcode.com/java/getpostrequest/
     */

    /**
     * Send a get request to the given url and retrieve its output
     * @param url The url to send GET request to
     * @return The output of the request
     */
    private String accessAPI(String url) {
        StringBuilder builder = new StringBuilder();

        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            var request = new HttpGet(url);
            request.setHeader("User-Agent", "Java client");
            request.setHeader("Content-Type", "application/x-www-form-urlencoded");
            request.setHeader("X-CMC_PRO_API_KEY", key);

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
    }

    /*
     * End of code block
     */
}

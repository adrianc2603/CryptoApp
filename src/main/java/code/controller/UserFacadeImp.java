package code.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import code.model.api.InputAPI;
import code.model.api.OutputAPI;
import code.model.database.Database;

public class UserFacadeImp implements UserFacade {

    private final InputAPI input;
    private final OutputAPI output;
    private final Database database;
    private final ThreadHelper threadHelper;
    private int balance;
    private String baseCurrency = null;

    public UserFacadeImp(InputAPI i, OutputAPI o, Database d, ThreadHelper helper) {
        this.input = i;
        this.output = o;
        this.database = d;
        this.threadHelper = helper;
    }

    @Override
    public String getListOfCryptocurrencies() {
        return condenseCryptocurrencyList(input.getListOfCryptocurrencies());
    }

    @Override
    public String getInfoPanelOnCryptoCurrency(String cryptocurrency, UserRequest requestType, boolean sendingReport) {
        if (cryptocurrency == null || cryptocurrency.isEmpty()) {
            return "You must enter all parameters";
        }
        cryptocurrency = cryptocurrency.toUpperCase();
        if (requestType == UserRequest.REPORT_NEW || requestType == UserRequest.INFO_NEW) {
            String info = input.getInfoPanelOnCryptoCurrency(cryptocurrency);
            String condensedOutput = condenseCryptocurrencyInfo(info, cryptocurrency, sendingReport);
            if (condensedOutput.equals("Something went wrong")) {
                return condensedOutput;
            }
            database.addToDatabase(cryptocurrency, info);
            return condensedOutput;
        }
        return condenseCryptocurrencyInfo(database.getFromDatabase(cryptocurrency), cryptocurrency, sendingReport);
    }

    @Override
    public String convert(String currency1, String currency2, String amount) {
        if (currency1 == null || currency1.isEmpty() || currency2 == null || currency2.isEmpty() || amount == null ||
                amount.isEmpty()) {
            return "You must enter all parameters";
        }
        double amountDouble;
        try {
            amountDouble = Double.parseDouble(amount);
        }
        catch (Exception e) {
            return "Invalid amount entered. Try again";
        }
        if (amountDouble > this.balance) {
            return "The given amount is above the balance set at application launch.\n" +
                    "The balance is " + this.balance;
        }
        currency1 = currency1.toUpperCase();
        currency2 = currency2.toUpperCase();
        String conversion1 =  condenseConversionMessage(input.convert(currency1, currency2, amount), currency2);
        String conversion2 =  condenseConversionMessage(input.convert(currency2, currency1, amount), currency1);
        if (conversion1.equals("Something went wrong") || conversion2.equals("Something went wrong")) {
            return "Something went wrong";
        }
        this.baseCurrency = currency1;
        return conversion1 + "\n\n" + conversion2 + "\n\n" +
                "The base currency has been set to " + currency1;
    }

    @Override
    public String sendReport(String cryptoCurrency, UserRequest requestType) {
        if (cryptoCurrency == null || cryptoCurrency.isEmpty()) {
            return "You must enter all parameters";
        }
        cryptoCurrency = cryptoCurrency.toUpperCase();
        String report = getInfoPanelOnCryptoCurrency(cryptoCurrency, requestType, true);
        if (report.equals("Something went wrong")) {
            return report;
        }
        return condenseReportSentMessage(output.sendReport(report));
    }

    @Override
    public boolean isThereCachedInfo(String cryptocurrency) {
        if (cryptocurrency == null || cryptocurrency.isEmpty()) {
            return false;
        }
        cryptocurrency = cryptocurrency.toUpperCase();
        return !(database.getFromDatabase(cryptocurrency) == null);
    }

    @Override
    public void createThread(UserRequest request, String[] container) {
        this.threadHelper.setUserFacade(this);
        this.threadHelper.setRequestType(request);
        this.threadHelper.setParameterContainer(container);
        Thread t = new Thread(this.threadHelper);
        t.start();
    }

    @Override
    public String setBalance(String balance) {
        if (balance == null || balance.isEmpty()) {
            return "You must enter all parameters";
        }
        int balanceInt;
        try {
            balanceInt = Integer.parseInt(balance);
        }
        catch (Exception e) {
            return "Balance must be an integer";
        }
        if (balanceInt < 1 || balanceInt > 1000) {
            return "Balance must be from 1 to 1000";
        }
        this.balance = balanceInt;
        return "Balance has been set to " + this.balance;
    }

    /**
     * Extract the key details from the JSON string and return them as a new string
     * @param s The JSON string containing the API-returned list of cryptocurrencies
     * @return A condensed list of cryptocurrencies
     */
    private String condenseCryptocurrencyList(String s) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject config = (JSONObject) parser.parse(s);
            JSONArray bodyArray = (JSONArray) config.get("data");
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < bodyArray.size(); i++) {
                JSONObject obj = (JSONObject) bodyArray.get(i);
                builder.append("ID: " + obj.get("id").toString() + "\n");
                builder.append("Name: " + obj.get("name").toString() + "\n");
                builder.append("Symbol: " + obj.get("symbol").toString() + "\n");
                builder.append("Slug: " + obj.get("slug").toString() + "\n");
                builder.append("\n");
            }
            return builder.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Something went wrong";
        }
    }

    /**
     * Extract the key details from the JSON string and return them as a new string
     * @param s The JSON string containing the API-returned information of the given cryptocurrency
     * @param cryptoCurrency The symbol of the Cryptocurrency the JSON string contains information on
     * @param sendingReport Indicates whether the cryptocurrency info is being sent as a report or not
     * @return A condensed list of information of the given cryptocurrency
     */
    private String condenseCryptocurrencyInfo(String s, String cryptoCurrency, boolean sendingReport) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject config = (JSONObject) parser.parse(s);
            JSONObject dataConfig = (JSONObject) config.get("data");
            JSONObject statusConfig = (JSONObject) config.get("status");
            JSONObject bodyConfig = (JSONObject) dataConfig.get(cryptoCurrency);
            StringBuilder builder = new StringBuilder();
            builder.append("ID: " + bodyConfig.get("id").toString() + "\n");
            builder.append("Name: " + bodyConfig.get("name").toString() + "\n");
            builder.append("Symbol: " + bodyConfig.get("symbol").toString() + "\n");
            builder.append("Category: " + bodyConfig.get("category").toString() + "\n");
            builder.append("Slug: " + bodyConfig.get("slug").toString() + "\n");
            builder.append("Description: " + bodyConfig.get("description").toString() + "\n");
            builder.append("Date Added: " + bodyConfig.get("date_added").toString() + "\n");

            // Only add URLs if the report isn't being sent (output has character limit)
            if (!sendingReport) {
                builder.append("Logo: " + bodyConfig.get("logo").toString() + "\n");
                JSONObject urlsConfig = (JSONObject) bodyConfig.get("urls");
                JSONArray websiteUrl = (JSONArray) urlsConfig.get("website");
                JSONArray technicalDocUrl = (JSONArray) urlsConfig.get("technical_doc");
                JSONArray explorerURL = (JSONArray) urlsConfig.get("explorer");
                JSONArray sourceCodeUrl = (JSONArray) urlsConfig.get("source_code");
                if (websiteUrl.size() >= 1) {
                    builder.append("Website: " + websiteUrl.get(0).toString() + "\n");
                }
                else {
                    builder.append("Website: EMPTY\n");
                }
                if (technicalDocUrl.size() >= 1) {
                    builder.append("Technical Document: " + technicalDocUrl.get(0).toString() + "\n");
                }
                else {
                    builder.append("Technical Document: EMPTY\n");
                }
                if (explorerURL.size() >= 1) {
                    builder.append("Explorer: " + explorerURL.get(0).toString() + "\n");
                }
                else {
                    builder.append("Explorer: EMPTY\n");
                }
                if (sourceCodeUrl.size() >= 1) {
                    builder.append("Source Code: " + sourceCodeUrl.get(0).toString() + "\n");
                }
                else {
                    builder.append("Source Code: EMPTY\n");
                }
            }

            // If sending report, add conversion of this.balance from this.baseCurrency to new cryptocurrency
            else {
                String conversionMessage = "No Base Currency Set to Convert From";
                if (this.baseCurrency != null) {
                    conversionMessage = condenseConversionMessage(input.convert(this.baseCurrency, cryptoCurrency,
                            String.valueOf(this.balance)), cryptoCurrency);
                    if (conversionMessage.equals("Something went wrong")) {
                        conversionMessage = "Couldn't convert from " + this.baseCurrency + " to " + cryptoCurrency;
                    }
                }
                builder.append(conversionMessage + "\n");
            }

            builder.append("Timestamp: " + statusConfig.get("timestamp").toString() + "\n");
            builder.append("\n");
            return builder.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Something went wrong";
        }
    }

    /**
     * Extract the key details from the JSON string and return them as a new string
     * @param s The JSON string containing the API-returned conversion between currencies
     * @param targetCurrency The target currency of the conversion
     * @return A condensed string showing the conversion between currencies
     */
    private String condenseConversionMessage(String s, String targetCurrency) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject config = (JSONObject) parser.parse(s);
            JSONObject dataConfig = (JSONObject) config.get("data");
            JSONObject quoteConfig = (JSONObject) dataConfig.get("quote");
            JSONObject priceConfig = (JSONObject) quoteConfig.get(targetCurrency);
            StringBuilder builder = new StringBuilder();
            builder.append(dataConfig.get("amount").toString() + " ");
            builder.append(dataConfig.get("name") + " is worth ");
            builder.append(priceConfig.get("price").toString() + " ");
            builder.append(targetCurrency);
            return builder.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Something went wrong";
        }
    }

    /**
     * Extract the key details from the JSON string and return them as a new string
     * @param s The JSON string containing the API-returned message shown once a message is sent
     * @return A condensed string showing the message has been sent to a number
     */
    private String condenseReportSentMessage(String s) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject config = (JSONObject) parser.parse(s);
            StringBuilder builder = new StringBuilder();
            builder.append("Status: " + config.get("status") + "\n");
            builder.append("To: " + config.get("to") + "\n");
            builder.append("Body:\n" + config.get("body") + "\n");
            return builder.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Something went wrong";
        }
    }
}
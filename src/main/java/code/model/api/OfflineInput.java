package code.model.api;

public class OfflineInput implements InputAPI {

    @Override
    public String getListOfCryptocurrencies() {
        return "{" +
                "\"data\": [" +
                "{" +
                "\"id\": 1," +
                "\"name\": \"Bitcoin\"," +
                "\"symbol\": \"BTC\"," +
                "\"slug\": \"bitcoin\"," +
                "\"cmc_rank\": 5," +
                "\"num_market_pairs\": 500," +
                "\"circulating_supply\": 16950100," +
                "\"total_supply\": 16950100," +
                "\"max_supply\": 21000000," +
                "\"last_updated\": \"2018-06-02T22:51:28.209Z\"," +
                "\"date_added\": \"2013-04-28T00:00:00.000Z\"," +
                "\"tags\": [" +
                "\"mineable\"" +
                "]," +
                "\"platform\": null," +
                "\"quote\": {" +
                "\"USD\": {" +
                "\"price\": 9283.92," +
                "\"volume_24h\": 7155680000," +
                "\"percent_change_1h\": -0.152774," +
                "\"percent_change_24h\": 0.518894," +
                "\"percent_change_7d\": 0.986573," +
                "\"market_cap\": 158055024432," +
                "\"last_updated\": \"2018-08-09T22:53:32.000Z\"" +
                "}," +
                "\"BTC\": {" +
                "\"price\": 1," +
                "\"volume_24h\": 772012," +
                "\"percent_change_1h\": 0," +
                "\"percent_change_24h\": 0," +
                "\"percent_change_7d\": 0," +
                "\"market_cap\": 17024600," +
                "\"last_updated\": \"2018-08-09T22:53:32.000Z\"" +
                "}" +
                "}" +
                "}," +
                "{" +
                "\"id\": 1027," +
                "\"name\": \"Ethereum\"," +
                "\"symbol\": \"ETH\"," +
                "\"slug\": \"ethereum\"," +
                "\"num_market_pairs\": 6360," +
                "\"circulating_supply\": 16950100," +
                "\"total_supply\": 16950100," +
                "\"max_supply\": 21000000," +
                "\"last_updated\": \"2018-06-02T22:51:28.209Z\"," +
                "\"date_added\": \"2013-04-28T00:00:00.000Z\"," +
                "\"tags\": [" +
                "\"mineable\"" +
                "]," +
                "\"platform\": null," +
                "\"quote\": {" +
                "\"USD\": {" +
                "\"price\": 1283.92," +
                "\"volume_24h\": 7155680000," +
                "\"percent_change_1h\": -0.152774," +
                "\"percent_change_24h\": 0.518894," +
                "\"percent_change_7d\": 0.986573," +
                "\"market_cap\": 158055024432," +
                "\"last_updated\": \"2018-08-09T22:53:32.000Z\"" +
                "}," +
                "\"ETH\": {" +
                "\"price\": 1," +
                "\"volume_24h\": 772012," +
                "\"percent_change_1h\": 0," +
                "\"percent_change_24h\": 0," +
                "\"percent_change_7d\": 0," +
                "\"market_cap\": 17024600," +
                "\"last_updated\": \"2018-08-09T22:53:32.000Z\"" +
                "}" +
                "}" +
                "}" +
                "]," +
                "\"status\": {" +
                "\"timestamp\": \"2018-06-02T22:51:28.209Z\"," +
                "\"error_code\": 0," +
                "\"error_message\": \"\"," +
                "\"elapsed\": 10," +
                "\"credit_count\": 1" +
                "}" +
                "}";
    }

    @Override
    public String getInfoPanelOnCryptoCurrency(String symbol) {
        if (symbol == null || symbol.isEmpty()) {
            return "You must enter all parameters";
        }
        return "{\"status\":{\"timestamp\":\"2021-05-30T07:37:13.997Z\",\"" + 
        "error_code\":0,\"" +
        "error_message\":null,\"" + 
        "elapsed\":11,\"" + 
        "credit_count\":1,\"" + 
        "notice\":null},\"" + 
        "data\":{\"" + symbol + "\":{\"id\":1,\"" +
        "name\":\"" + symbol + "\",\"" +
        "symbol\":\"" + symbol + " \",\"" +
        "category\":\"coin\",\"" +
        "description\":\"" + symbol + " is a cryptocurrency . Users are able to generate " + symbol + "  through the" +
                " process of mining. " + symbol + " has a current supply of 18,721,681. The last known price of " +
                symbol + " is 35,774.32442755 USD and is down -2.04 over the last 24 hours. It is currently trading " +
                "on 9728 active market(s) with $45,724,775,307.89 traded over the last 24 hours. More information " +
                "can be found at https://" + symbol + ".org/.\",\"" +
        "slug\":\"" + symbol + "\",\"" +
        "logo\":\"https://s2.coinmarketcap.com/static/img/coins/64x64/1.png\",\"" +
        "subreddit\":\"" + symbol + "\",\"" +
        "notice\":\"\",\"" +
        "tags\":[\"mineable\",\"" +
        "pow\",\"" +
        "sha-256\",\"" +
        "store-of-value\",\"" +
        "state-channels\",\"" +
        "coinbase-ventures-portfolio\",\"" +
        "three-arrows-capital-portfolio\",\"" +
        "polychain-capital-portfolio\",\"" +
        "binance-labs-portfolio\",\"" +
        "arrington-xrp-capital\",\"" +
        "blockchain-capital-portfolio\",\"" +
        "boostvc-portfolio\",\"" +
        "cms-holdings-portfolio\",\"" +
        "dcg-portfolio\",\"" +
        "dragonfly-capital-portfolio\",\"" +
        "electric-capital-portfolio\",\"" +
        "fabric-ventures-portfolio\",\"" +
        "framework-ventures\",\"" +
        "galaxy-digital-portfolio\",\"" +
        "huobi-capital\",\"" +
        "alameda-research-portfolio\",\"" +
        "a16z-portfolio\",\"" +
        "1confirmation-portfolio\",\"" +
        "winklevoss-capital\",\"" +
        "usv-portfolio\",\"" +
        "placeholder-ventures-portfolio\",\"" +
        "pantera-capital-portfolio\",\"" +
        "multicoin-capital-portfolio\",\"" +
        "paradigm-xzy-screener\"],\"" +
        "tag-names\":[\"Mineable\",\"" +
        "PoW\",\"" +
        "SHA-256\",\"" +
        "Store of Value\",\"" +
        "State channels\",\"" +
        "Coinbase Ventures Portfolio\",\"" +
        "Three Arrows Capital Portfolio\",\"" +
        "Polychain Capital Portfolio\",\"" +
        "Binance Labs Portfolio\",\"" +
        "Arrington XRP capital\",\"" +
        "Blockchain Capital Portfolio\",\"" +
        "BoostVC Portfolio\",\"" +
        "CMS Holdings Portfolio\",\"" +
        "DCG Portfolio\",\"" +
        "DragonFly Capital Portfolio\",\"" +
        "Electric Capital Portfolio\",\"" +
        "Fabric Ventures Portfolio\",\"" +
        "Framework Ventures\",\"" +
        "Galaxy Digital Portfolio\",\"" +
        "Huobi Capital\",\"" +
        "Alameda Research Portfolio\",\"" +
        "A16Z Portfolio\",\"" +
        "1Confirmation Portfolio\",\"" +
        "Winklevoss Capital\",\"" +
        "USV Portfolio\",\"" +
        "Placeholder Ventures Portfolio\",\"" +
        "Pantera Capital Portfolio\",\"" +
        "Multicoin Capital Portfolio\",\"" +
        "Paradigm XZY Screener\"],\"" +
        "tag-groups\":[\"OTHER\",\"" +
        "CONSENSUS_ALGORITHM\",\"" +
        "CONSENSUS_ALGORITHM\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\",\"" +
        "PROPERTY\"],\"" +
        "urls\":{\"website\":[\"https://" + symbol + ".org/\"],\"" +
        "twitter\":[],\"" +
        "message_board\":[\"https://" + symbol + "talk.org\"],\"" +
        "chat\":[],\"" +
        "explorer\":[\"https://blockchain.coinmarketcap.com/chain/" + symbol + "\",\"" +
        "https://blockchain.info/\",\"" +
        "https://live.blockcypher.com/" + symbol + " /\",\"" +
        "https://blockchair.com/" + symbol + "\",\"" +
        "https://explorer.via" + symbol + " .com/" + symbol + " \"],\"" +
        "reddit\":[\"https://reddit.com/r/" + symbol + "\"],\"" +
        "technical_doc\":[\"https://" + symbol + ".org/" + symbol + ".pdf\"],\"" +
        "source_code\":[\"https://github.com/" + symbol + "/\"],\"" +
        "announcement\":[]},\"" +
        "platform\":null,\"" +
        "date_added\":\"2013-04-28T00:00:00.000Z\",\"" +
        "twitter_username\":\"\",\"" +
        "is_hidden\":0}}}\n";
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
        return "{" +
                "\"data\": {" +
                "\"symbol\": \"" + baseCurrency + "\"," +
                "\"id\": \"1\"," +
                "\"name\": \"" + baseCurrency + "\"," +
                "\"amount\": " + amount + "," +
                "\"last_updated\": \"2018-06-06T08:04:36.000Z\"," +
                "\"quote\": {" +
                "\"" + targetCurrency + "\": {" +
                "\"price\": 381442," +
                "\"last_updated\": \"2018-06-06T08:06:51.968Z\"" +
                "}" +
                "}" +
                "}," +
                "\"status\": {" +
                "\"timestamp\": \"2021-05-20T05:52:01.571Z\"," +
                "\"error_code\": 0," +
                "\"error_message\": \"\"," +
                "\"elapsed\": 10," +
                "\"credit_count\": 1" +
                "}" +
                "}";
    }
}

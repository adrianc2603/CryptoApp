package code.controller;

import code.model.api.InputAPI;
import code.model.api.OutputAPI;
import code.model.database.Database;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserFacadeImpTest {

    private UserFacade facade;
    private InputAPI input;
    private OutputAPI output;
    private Database database;
    private ThreadHelper threadHelper;

    private String cryptocurrencyList() {
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

    private String cryptocurrencyInfo(String symbol) {
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
                "description\":\"" + symbol + " is a cryptocurrency . Users are able to generate " + symbol + "  " +
                "through the process of mining. " + symbol + " has a current supply of 18,721,681. The last known " +
                "price of " + symbol + " is 35,774.32442755 USD and is down -2.04 over the last 24 hours. It is " +
                "currently trading on 9728 active market(s) with $45,724,775,307.89 traded over the last 24 hours. " +
                "More information can be found at https://" + symbol + ".org/.\",\"" +
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

    private String convert(String baseCurrency, String targetCurrency, String amount) {
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

    private String sendReport(String reportData) {
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
                "    \"media\": \"/2010-04-01/Accounts/ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Messages/SMXXXXXXXXXXXXX" +
                "XXXXXXXXXXXXXXXXXXX/Media.json\"" +
                "  }," +
                "  \"to\": \"+123456789\"," +
                "  \"uri\": \"/2010-04-01/Accounts/ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX/Messages/SMXXXXXXXXXXXXXXXXX" +
                "XXXXXXXXXXXXXXX.json\"" +
                "}";
    }

    @Before
    public void setup() {
        input = mock(InputAPI.class);
        output = mock(OutputAPI.class);
        database = mock(Database.class);
        threadHelper = mock(ThreadHelper.class);
        facade = new UserFacadeImp(input, output, database, threadHelper);
    }

    @Test
    public void testGetListofCryptocurrencies() {
        when(input.getListOfCryptocurrencies()).thenReturn(cryptocurrencyList());
        assertNotNull(facade.getListOfCryptocurrencies());
        verify(input, times(1)).getListOfCryptocurrencies();
    }

    @Test
    public void testGetInfo() {
        when(input.getInfoPanelOnCryptoCurrency("BTC")).thenReturn(cryptocurrencyInfo("BTC"));
        assertNotNull(facade.getInfoPanelOnCryptoCurrency("BTC", UserRequest.INFO_NEW,
                false));
        verify(input, times(1)).getInfoPanelOnCryptoCurrency("BTC");
    }

    @Test
    public void testGetInfoNullSymbol() {
        String actual = facade.getInfoPanelOnCryptoCurrency(null, UserRequest.INFO_NEW,
                false);
        assertNotNull(actual);
        assertEquals("You must enter all parameters", actual);
        verify(input, never()).getInfoPanelOnCryptoCurrency(anyString());
    }

    @Test
    public void testGetInfoEmptySymbol() {
        String actual = facade.getInfoPanelOnCryptoCurrency("", UserRequest.INFO_NEW, false);
        assertNotNull(actual);
        assertEquals("You must enter all parameters", actual);
        verify(input, never()).getInfoPanelOnCryptoCurrency(anyString());
    }

    @Test
    public void testConversion() {
        facade.setBalance("1000");
        when(input.convert("USD", "BTC", "500")).thenReturn(convert("USD",
                "BTC", "500"));
        assertNotNull(facade.convert("USD", "BTC", "500"));
        verify(input, times(1)).convert("USD", "BTC", "500");
    }

    @Test
    public void testConversionNullBase() {
        String actual = facade.convert(null, "BTC", "500");
        assertNotNull(actual);
        assertEquals("You must enter all parameters", actual);
        verify(input, never()).convert(anyString(), anyString(), anyString());
    }

    @Test
    public void testConversionEmptyBase() {
        String actual = facade.convert("", "BTC", "500");
        assertNotNull(actual);
        assertEquals("You must enter all parameters", actual);
        verify(input, never()).convert(anyString(), anyString(), anyString());
    }

    @Test
    public void testConversionNullTarget() {
        String actual = facade.convert("USD", null, "500");
        assertNotNull(actual);
        assertEquals("You must enter all parameters", actual);
        verify(input, never()).convert(anyString(), anyString(), anyString());
    }

    @Test
    public void testConversionEmptyTarget() {
        String actual = facade.convert("USD", "", "500");
        assertNotNull(actual);
        assertEquals("You must enter all parameters", actual);
        verify(input, never()).convert(anyString(), anyString(), anyString());
    }

    @Test
    public void testConversionNullAmount() {
        String actual = facade.convert("USD", "BTC", null);
        assertNotNull(actual);
        assertEquals("You must enter all parameters", actual);
        verify(input, never()).convert(anyString(), anyString(), anyString());
    }

    @Test
    public void testConversionEmptyAmount() {
        String actual = facade.convert("USD", "BTC", "");
        assertNotNull(actual);
        assertEquals("You must enter all parameters", actual);
        verify(input, never()).convert(anyString(), anyString(), anyString());
    }

    @Test
    public void testConversionBadAmount() {
        String actual = facade.convert("USD", "BTC", "Five Hundred");
        assertNotNull(actual);
        assertEquals("Invalid amount entered. Try again", actual);
        verify(input, never()).convert(anyString(), anyString(), anyString());
    }

    @Test
    public void testSendReport() {
        when(input.getInfoPanelOnCryptoCurrency("BTC")).thenReturn(cryptocurrencyInfo("BTC"));
        String report = facade.getInfoPanelOnCryptoCurrency("BTC", UserRequest.REPORT_NEW,
                true);
        when(output.sendReport(report)).thenReturn(sendReport(report));
        assertNotNull(facade.sendReport("BTC", UserRequest.REPORT_NEW));
        verify(output, times(1)).sendReport(report);
    }

    @Test
    public void testSendReportNullReport() {
        String actual = facade.sendReport(null, UserRequest.REPORT_NEW);
        assertNotNull(actual);
        assertEquals("You must enter all parameters", actual);
        verify(output, never()).sendReport(anyString());
    }

    @Test
    public void testSendReportEmptyReport() {
        String actual = facade.sendReport("", UserRequest.REPORT_NEW);
        assertNotNull(actual);
        assertEquals("You must enter all parameters", actual);
        verify(output, never()).sendReport(anyString());
    }

    @Test
    public void testGetCryptocurrencyListThenInfo() {

        // Get Cryptocurrency List
        when(input.getListOfCryptocurrencies()).thenReturn(cryptocurrencyList());
        String currencyList = facade.getListOfCryptocurrencies();
        verify(input, times(1)).getListOfCryptocurrencies();
        assertNotNull(currencyList);

        // Get Info on Cryptocurrency
        when(input.getInfoPanelOnCryptoCurrency("BTC")).thenReturn(cryptocurrencyInfo("BTC"));
        String currencyInfo = facade.getInfoPanelOnCryptoCurrency("BTC", UserRequest.INFO_NEW,
                false);
        verify(input, times(1)).getInfoPanelOnCryptoCurrency("BTC");
        assertNotNull(currencyInfo);
    }

    @Test
    public void testGetInfoTwoCryptocurrenciesThenConvertBothWays() {

        // Get Info on Cryptocurrency
        when(input.getInfoPanelOnCryptoCurrency("BTC")).thenReturn(cryptocurrencyInfo("BTC"));
        String currencyInfo1 = facade.getInfoPanelOnCryptoCurrency("BTC", UserRequest.INFO_NEW,
                false);
        verify(input, times(1)).getInfoPanelOnCryptoCurrency("BTC");
        assertNotNull(currencyInfo1);

        // Get Info on Cryptocurrency
        when(input.getInfoPanelOnCryptoCurrency("ETH")).thenReturn(cryptocurrencyInfo("ETH"));
        String currencyInfo2 = facade.getInfoPanelOnCryptoCurrency("ETH", UserRequest.INFO_NEW,
                false);
        verify(input, times(1)).getInfoPanelOnCryptoCurrency("ETH");
        assertNotNull(currencyInfo2);

        // Convert 1 --> 2
        facade.setBalance("1000");
        when(input.convert("BTC", "ETH", "500")).thenReturn(convert("BTC",
                "ETH", "500"));
        String conversion1 = facade.convert("BTC", "ETH", "500");
        verify(input, times(1)).convert("BTC", "ETH", "500");
        assertNotNull(conversion1);

        // Convert 2 --> 1
        when(input.convert("ETH", "BTC", "50")).thenReturn(convert("ETH",
                "BTC", "500"));
        String conversion2 = facade.convert("ETH", "BTC", "50");
        verify(input, times(1)).convert("ETH", "BTC", "50");
        assertNotNull(conversion2);
    }

    @Test
    public void testGetInfoTwoCryptocurrenciesSendTwoReports() {

        // Get Info on Cryptocurrency
        when(input.getInfoPanelOnCryptoCurrency("BTC")).thenReturn(cryptocurrencyInfo("BTC"));
        String currencyInfo1 = facade.getInfoPanelOnCryptoCurrency("BTC", UserRequest.INFO_NEW,
                true);
        verify(input, times(1)).getInfoPanelOnCryptoCurrency("BTC");
        assertNotNull(currencyInfo1);

        // Get Info on Cryptocurrency
        when(input.getInfoPanelOnCryptoCurrency("ETH")).thenReturn(cryptocurrencyInfo("ETH"));
        String currencyInfo2 = facade.getInfoPanelOnCryptoCurrency("ETH", UserRequest.INFO_NEW,
                true);
        verify(input, times(1)).getInfoPanelOnCryptoCurrency("ETH");
        assertNotNull(currencyInfo2);

        // Send report 1
        when(output.sendReport(currencyInfo1)).thenReturn(sendReport(currencyInfo1));
        String reportOutput1 = facade.sendReport("BTC", UserRequest.REPORT_NEW);
        verify(output, times(1)).sendReport(currencyInfo1);
        assertNotNull(reportOutput1);

        // Send report 2
        when(output.sendReport(currencyInfo2)).thenReturn(sendReport(currencyInfo2));
        String reportOutput2 = facade.sendReport("ETH", UserRequest.REPORT_NEW);
        verify(output, times(1)).sendReport(currencyInfo2);
        assertNotNull(reportOutput2);
    }

    @Test
    public void testGetCryptocurrencyListBadJSON() {
        when(input.getListOfCryptocurrencies()).thenReturn("Not JSON");
        assertEquals("Something went wrong", facade.getListOfCryptocurrencies());
    }

    @Test
    public void testGetCryptocurrencyInfoBadJSON() {
        when(input.getInfoPanelOnCryptoCurrency("BTC")).thenReturn("Not JSON");
        assertEquals("Something went wrong", facade.getInfoPanelOnCryptoCurrency("BTC",
                UserRequest.REPORT_NEW, false));
    }

    @Test
    public void testConvertBadJson() {
        facade.setBalance("1000");
        when(input.convert("BTC", "ETH", "60")).thenReturn("Not JSON");
        assertEquals("Something went wrong", facade.convert("BTC", "ETH", "60"));
    }

    @Test
    public void testSendReportBadJSON() {
        when(input.getInfoPanelOnCryptoCurrency("BTC")).thenReturn(cryptocurrencyInfo("BTC"));
        String report = facade.getInfoPanelOnCryptoCurrency("BTC", UserRequest.REPORT_NEW,
                true);
        when(output.sendReport(report)).thenReturn("Not JSON");
        assertEquals("Something went wrong", facade.sendReport("BTC", UserRequest.REPORT_NEW));
    }

    @Test
    public void testGetCachedInfo() {
        when(database.getFromDatabase("BTC")).thenReturn(cryptocurrencyInfo("BTC"));
        assertNotNull(facade.getInfoPanelOnCryptoCurrency("BTC", UserRequest.INFO_CACHED,
                false));
        verify(input, never()).getInfoPanelOnCryptoCurrency("BTC");
    }

    @Test
    public void testGetNewInfoStoresinCache() {
        assertFalse(facade.isThereCachedInfo("BTC"));
        when(input.getInfoPanelOnCryptoCurrency("BTC")).thenReturn(cryptocurrencyInfo("BTC"));
        facade.getInfoPanelOnCryptoCurrency("BTC", UserRequest.INFO_NEW, false);
        when(database.getFromDatabase("BTC")).thenReturn("Not null");
        assertTrue(facade.isThereCachedInfo("BTC"));
        verify(database, times(1)).addToDatabase("BTC", cryptocurrencyInfo("BTC"));
    }

    @Test
    public void testGetNewThenCachedInfo() {
        when(input.getInfoPanelOnCryptoCurrency("BTC")).thenReturn(cryptocurrencyInfo("BTC"));
        String newInfo = facade.getInfoPanelOnCryptoCurrency("BTC", UserRequest.INFO_NEW,
                false);
        when(database.getFromDatabase("BTC")).thenReturn(cryptocurrencyInfo("BTC"));
        String cachedInfo = facade.getInfoPanelOnCryptoCurrency("BTC", UserRequest.INFO_CACHED,
                false);
        assertEquals(newInfo, cachedInfo);
    }

    @Test
    public void testNewCachedNewInfo() {
        when(input.getInfoPanelOnCryptoCurrency("BTC")).thenReturn(cryptocurrencyInfo("BTC"));
        facade.getInfoPanelOnCryptoCurrency("BTC", UserRequest.INFO_NEW, false);
        when(database.getFromDatabase("BTC")).thenReturn(cryptocurrencyInfo("BTC"));
        facade.getInfoPanelOnCryptoCurrency("BTC", UserRequest.INFO_CACHED, false);
        facade.getInfoPanelOnCryptoCurrency("BTC", UserRequest.INFO_NEW, false);
        verify(database, times(1)).getFromDatabase("BTC");
        verify(database, times(2)).addToDatabase(eq("BTC"), anyString());
    }

    @Test
    public void testSendReportCachedInfo() {
        when(database.getFromDatabase("BTC")).thenReturn(cryptocurrencyInfo("BTC"));
        String report = facade.getInfoPanelOnCryptoCurrency("BTC", UserRequest.REPORT_CACHED,
                true);
        when(output.sendReport(report)).thenReturn(sendReport(report));
        assertNotNull(facade.sendReport("BTC", UserRequest.REPORT_CACHED));
        verify(output, times(1)).sendReport(report);
        verify(input, never()).getInfoPanelOnCryptoCurrency("BTC");
        verify(database, never()).addToDatabase(eq("BTC"), anyString());
    }

    @Test
    public void testSendReportNewInfoUpdatedCache() {
        when(input.getInfoPanelOnCryptoCurrency("BTC")).thenReturn(cryptocurrencyInfo("BTC"));
        String report = facade.getInfoPanelOnCryptoCurrency("BTC", UserRequest.REPORT_NEW,
                true);
        verify(database, times(1)).addToDatabase("BTC", cryptocurrencyInfo("BTC"));
        verify(database, never()).getFromDatabase("BTC");
        when(output.sendReport(report)).thenReturn(sendReport(report));
        when(database.getFromDatabase("BTC")).thenReturn(cryptocurrencyInfo("BTC"));
        assertNotNull(facade.sendReport("BTC", UserRequest.REPORT_CACHED));
        verify(database, times(1)).getFromDatabase("BTC");
        verify(output, times(1)).sendReport(report);
        verify(database, times(1)).getFromDatabase("BTC");
        when(database.getFromDatabase("BTC")).thenReturn(cryptocurrencyInfo("BTC"));
        String cached = facade.getInfoPanelOnCryptoCurrency("BTC", UserRequest.INFO_CACHED,
                true);
        assertEquals(cached, report);
    }

    @Test
    public void testGetListWithThread() {
        facade.createThread(UserRequest.LIST, null);
        verify(threadHelper, times(1)).setUserFacade(facade);
        verify(threadHelper, times(1)).setRequestType(UserRequest.LIST);
        verify(threadHelper, times(1)).setParameterContainer(null);
    }

    @Test
    public void testGetInfoWithThread() {
        String[] container = {"BTC"};
        facade.createThread(UserRequest.INFO_NEW, container);
        facade.createThread(UserRequest.INFO_CACHED, container);
        verify(threadHelper, times(2)).setUserFacade(facade);
        verify(threadHelper, times(2)).setParameterContainer(container);
        verify(threadHelper, times(1)).setRequestType(UserRequest.INFO_NEW);
        verify(threadHelper, times(1)).setRequestType(UserRequest.INFO_CACHED);
    }

    @Test
    public void testConvertWithThread() {
        String[] container = {"BTC", "USD", "30"};
        facade.createThread(UserRequest.CONVERT, container);
        verify(threadHelper, times(1)).setUserFacade(facade);
        verify(threadHelper, times(1)).setRequestType(UserRequest.CONVERT);
        verify(threadHelper, times(1)).setParameterContainer(container);
    }

    @Test
    public void testSendReportWithThread() {
        String[] container = {"BTC"};
        facade.createThread(UserRequest.REPORT_NEW, container);
        facade.createThread(UserRequest.REPORT_CACHED, container);
        verify(threadHelper, times(2)).setUserFacade(facade);
        verify(threadHelper, times(2)).setParameterContainer(container);
        verify(threadHelper, times(1)).setRequestType(UserRequest.REPORT_NEW);
        verify(threadHelper, times(1)).setRequestType(UserRequest.REPORT_CACHED);
    }

    /*
    The following tests were added as part of my exam work
     */

    @Test
    public void testStoreValidBalance() {
        assertEquals("Balance has been set to 500", facade.setBalance("500"));
        assertEquals("Balance has been set to 1000", facade.setBalance("1000"));
        assertEquals("Balance has been set to 1", facade.setBalance("1"));
    }

    @Test
    public void testStoreBalanceNotInteger() {
        assertEquals("Balance must be an integer", facade.setBalance("500.1"));
    }

    @Test
    public void testStoreBalanceWord() {
        assertEquals("Balance must be an integer", facade.setBalance("ThreeHundred"));
    }

    @Test
    public void testStoreBalanceZero() {
        assertEquals("Balance must be from 1 to 1000", facade.setBalance("0"));
    }

    @Test
    public void testStoreBalanceAThousandAndOne() {
        assertEquals("Balance must be from 1 to 1000", facade.setBalance("1001"));
    }

    @Test
    public void testStoreBalanceNegative() {
        assertEquals("Balance must be from 1 to 1000", facade.setBalance("-1"));
    }

    @Test
    public void testStoreBalanceNullAmount() {
        assertEquals("You must enter all parameters", facade.setBalance(null));
    }

    @Test
    public void testStoreBalanceEmptyAmount() {
        assertEquals("You must enter all parameters", facade.setBalance(""));
    }

    @Test
    public void testConvertAboveBalance() {
        facade.setBalance("122");
        String expected = "The given amount is above the balance set at application launch.\nThe balance is 122";
        assertEquals(expected, facade.convert("BTC", "USD", "123"));
    }

    @Test
    public void testConvertEqualToBalance() {
        facade.setBalance("123");
        when(input.convert("BTC", "USD", "123")).thenReturn(convert("BTC",
                "USD", "123"));
        when(input.convert("USD", "BTC", "123")).thenReturn(convert("US" +
                "D", "BTC", "123"));
        String conversion = facade.convert("USD", "BTC", "123");
        assertTrue(conversion.contains("123 BTC is worth"));
        assertTrue(conversion.contains("123 USD is worth"));
    }

    @Test
    public void testConvertBelowBalance() {
        facade.setBalance("130");
        when(input.convert("BTC", "USD", "123")).thenReturn(convert("BTC",
                "USD", "123"));
        when(input.convert("USD", "BTC", "123")).thenReturn(convert("USD",
                "BTC", "123"));
        String conversion = facade.convert("USD", "BTC", "123");
        assertTrue(conversion.contains("123 BTC is worth"));
        assertTrue(conversion.contains("123 USD is worth"));
    }

    @Test
    public void testSendReportNoBaseCurrencySet() {
        when(input.getInfoPanelOnCryptoCurrency("BTC")).thenReturn(cryptocurrencyInfo("BTC"));
        String report = facade.getInfoPanelOnCryptoCurrency("BTC", UserRequest.REPORT_NEW,
                true);
        when(output.sendReport(report)).thenReturn(sendReport(report));
        assertTrue(facade.sendReport("BTC", UserRequest.REPORT_NEW).contains("No Base Currency Set to" +
                " Convert From"));
    }

    @Test
    public void testSendReportBaseCurrencyDifferent() {
        facade.setBalance("1000");
        when(input.convert("ETH","BTC", "1000")).thenReturn(convert("ETH",
                "BTC", "1000"));
        when(input.convert("BTC","ETH", "1000")).thenReturn(convert("BTC",
                "ETH", "1000"));
        facade.convert("ETH", "BTC", "1000");
        when(input.getInfoPanelOnCryptoCurrency("BTC")).thenReturn(cryptocurrencyInfo("BTC"));
        String report = facade.getInfoPanelOnCryptoCurrency("BTC", UserRequest.REPORT_NEW,
                true);
        when(output.sendReport(report)).thenReturn(sendReport(report));
        String sendReportOutput = facade.sendReport("BTC", UserRequest.REPORT_NEW);
        assertFalse(sendReportOutput.contains("No Base Currency Set to Convert From"));
        assertTrue(sendReportOutput.contains("1000 ETH is worth"));
    }

    @Test
    public void testSendReportBaseCurrencySame() {
        facade.setBalance("530");
        when(input.convert("BTC","BTC", "530")).thenReturn(convert("BTC",
                "BTC", "530"));
        facade.convert("BTC", "BTC", "530");
        when(input.getInfoPanelOnCryptoCurrency("BTC")).thenReturn(cryptocurrencyInfo("BTC"));
        String report = facade.getInfoPanelOnCryptoCurrency("BTC", UserRequest.REPORT_NEW,
                true);
        when(output.sendReport(report)).thenReturn(sendReport(report));
        String sendReportOutput = facade.sendReport("BTC", UserRequest.REPORT_NEW);
        assertFalse(sendReportOutput.contains("No Base Currency Set to Convert From"));
        assertTrue(sendReportOutput.contains("530 BTC is worth"));
    }
}
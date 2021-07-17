package code.controller;

public interface UserFacade {

    /**
     * Obtain a list of cryptocurrencies from the CoinMarketCap API
     *
     * @return The list of cryptocurrencies,
     */
    String getListOfCryptocurrencies();

    /**
     * Obtain an info panel on a cryptocurrency
     *
     * @param cryptocurrency The symbol of the cryptocurrency to display an info panel on. May not be null or empty
     * @param requestType    Indicates whether the user would like to obtain fresh info from the API or the cached info
     * @param sendingReport Indicates whether the cryptocurrency info is being sent as a report or not
     * @return The info panel on the given cryptocurrency
     */
    String getInfoPanelOnCryptoCurrency(String cryptocurrency, UserRequest requestType, boolean sendingReport);

    /**
     * Given two currencies and an amount obtain the conversion either way. Also set one of the currencies as the base
     * currency for future use
     *
     * @param currency1 The first currency of the conversion. May not be null or empty. Will be set as the base currency
     * @param currency2 The second currency of the conversion. May not be null or empty
     * @param amount    The base currency amount to convert to the target currency. May not be null or empty.
     *                  Must be able to convert to a Double. Must not exceed the balance set at application launch
     * @return Information regarding the conversion from currency1 to currency2, and currency2 to currency1
     */
    String convert(String currency1, String currency2, String amount);

    /**
     * Send data on a given cryptocurrency
     *
     * @param cryptoCurrency The cryptocurrency to send information on. May not be null or empty
     * @param requestType    Indicates whether the user would like to send fresh info from the API or the cached info
     * @return A message indicating the report has been sent to the given phone number
     */
    String sendReport(String cryptoCurrency, UserRequest requestType);

    /**
     * Check whether there is cached data on the given cryptocurrency in the local SQLite database
     *
     * @param cryptocurrency The symbol of the cryptocurrency to check storage status on
     * @return True if there is cached info on the given cryptocurrency. False otherwise
     */
    boolean isThereCachedInfo(String cryptocurrency);

    /**
     * Create a new Thread to perform the API request
     * @param request The API request to be performed
     * @param container Contains all necessary parameters for the request. Will be null if no parameters
     *                  are required
     */
    void createThread(UserRequest request, String[] container);

    /**
     * Set the balance of the app, which is the maximum a user can choose to enter as the amount for a conversion
     * @param balance The balance to be set. Must be able to convert to an integer. Must be from 1 and 1000. May not be
     *                null or empty
     * @return A string indicating the balance has been stored, or an error has occured
     */
    String setBalance(String balance);
}
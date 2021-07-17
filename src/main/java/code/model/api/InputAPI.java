package code.model.api;

public interface InputAPI {

    /**
     * Obtain a list of cryptocurrencies
     * @return The list of cryptocurrencies
     */
    String getListOfCryptocurrencies();

    /**
     * Obtain an info panel on a cryptocurrency
     * @param symbol The symbol of the cryptocurrency to display an info panel on. May not be null or empty
     * @return The info panel on the given cryptocurrency
     */
    String getInfoPanelOnCryptoCurrency(String symbol);

    /**
     * Given a base currency and an amount obtain the conversion to a target currency
     * @param baseCurrency The base currency to convert from. May not be null or empty
     * @param targetCurrency The target currency to convert to. May not be null or empty
     * @param amount The base currency amount to convert to the target currency. May not be null or empty.
     *               Must be able to convert to a Double
     * @return Information regarding the conversion from baseCurrency to targetCurrency
     */
    String convert(String baseCurrency, String targetCurrency, String amount);
}

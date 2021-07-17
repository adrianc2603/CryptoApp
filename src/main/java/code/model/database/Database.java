package code.model.database;

public interface Database {

    /**
     * Add to the database the cryptocurrency symbol and cryptocurrency info.
     * Update the cryptocurrency info in the database if there is already a matching entry
     * @param symbol The cryptocurrency symbol to store
     * @param info The cryptocurrency info to store
     */
    void addToDatabase(String symbol, String info);

    /**
     * Retrieve the cryptocurrency info from the database
     * @param symbol The cryptocurrency symbol corresponding to the required info
     * @return The info if an entry with the given symbol exists. Null otherwise
     */
    String getFromDatabase(String symbol);
}

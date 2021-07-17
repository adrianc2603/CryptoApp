package code.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseImp implements Database {

    private final Connection connection;
    private final Statement statement;

    /*
     * Please note the below code was influenced by the following website:
     * https://github.com/xerial/sqlite-jdbc
     */

    public DatabaseImp(String file) {
        try {
            // Create connection and table
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + file);
            this.statement = this.connection.createStatement();
            this.statement.executeUpdate("create table if not exists cryptotable (symbol string, info string)");
        }
        catch (Exception e) {
            throw new RuntimeException("Couldn't load database");
        }
    }

    @Override
    public void addToDatabase(String symbol, String info) {
        String sql = "insert into cryptotable values('" + symbol + "', '" + info + "')";

        // Replace what is stored in cache if something already exists
        if (getFromDatabase(symbol) != null) {
            sql = "update cryptotable set info = '" + info + "' where symbol = '" + symbol + "'";
        }

        try {
            this.statement.executeUpdate(sql);
        }
        catch (Exception e) {
            throw new RuntimeException("Couldn't add to database");
        }
    }

    @Override
    public String getFromDatabase(String symbol) {
        try {
            ResultSet rs = statement.executeQuery("select * from cryptotable where symbol = '" + symbol + "'");
            return rs.getString("info");
        }
        catch (Exception e) {
            return null; // Return null if entry is not in database
        }
    }

    /*
     * End of code block
     */
}
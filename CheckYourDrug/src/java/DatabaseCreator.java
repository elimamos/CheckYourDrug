
import java.sql.*;

public class DatabaseCreator {
    // JDBC driver name and database URL

     static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static String DB_URL = "https://node48511-przetwarzaie.unicloud.pl";

    static final String USER = "root";
    static final String PASS = "U3czgEov5w";
    static final String DB_NAME = "CheckYourDrug";

    public void databaseCreate() {

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //   Database creation 
            System.out.println("Creating database...");
            stmt = conn.createStatement();
            String sql = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");

            DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
            System.out.println("Connecting to selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //  Table creation - drugs
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();

            sql = "CREATE TABLE IF NOT EXISTS drugs"
                    + "(ID int NOT NULL AUTO_INCREMENT, "
                    + " name VARCHAR(255) NOT NULL, "
                    + " substance VARCHAR(255), "
                    + " similar VARCHAR(255), "
                    + " price VARCHAR(255),"
                    + " PRIMARY KEY (ID))";

            stmt.executeUpdate(sql);
            System.out.println("Created table drugs in given database...");

            //  Table creation - missingDrugs
            System.out.println("Creating table missingDrugs in given database...");
            stmt = conn.createStatement();

            sql = "CREATE TABLE IF NOT EXISTS missingDrugs"
                    + "(ID int NOT NULL, "
                    + " name VARCHAR(255) NOT NULL)";

            stmt.executeUpdate(sql);
            System.out.println("Created table missingDrugs in given database...");

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseAddToTable {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static String DB_URL = "jdbc:mysql://localhost:3306/checkyourdrug";

    static final String USER = "root";
    static final String PASS = "";
    static final String DB_NAME = "baza";
    
    public void databaseTable(String name, String substance, String similar, Double price)
    {
        Connection conn = null;
        Statement stmt = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("polaczenie z baza");
            //ResultSet md = conn.getMetaData().getCatalogs();
            stmt=conn.createStatement();
            String sql = "INSERT INTO drugs (ID, name, substance, similar, price)" + "VALUES (null, '"+name+"', '"+substance+"', '"+similar+"', '"+price+"')";
            stmt.executeUpdate(sql);
            System.out.println("dodano nowe rekordy do bazy");

        }catch(Exception e){
            System.out.println("blad "+e);
        } 
        
    }
}

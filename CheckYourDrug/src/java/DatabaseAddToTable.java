
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseAddToTable {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static String DB_URL = "https://node48511-przetwarzaie.unicloud.pl";

    static final String USER = "root";
    static final String PASS = "U3czgEov5w";
    static final String DB_NAME = "CheckYourDrug";
    
    public void databaseTable(String name, String substance, String similar, String price)
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
           System.out.println(sql);
           // stmt.executeUpdate(sql);
           if(stmt.execute(sql)){
            System.out.println("dodano nowe rekordy do bazy");
           }
           else{
            System.out.println("NIE dodano nowe rekordy do bazy");}

        }catch(Exception e){
            System.out.println("blad "+e);
        } 
        
    }
}


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseAddToTable {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String USER = "root";
    static final String PASS = "U3czgEov5w";
    static final String DB_NAME = "CheckYourDrug";
    static final String DB_URL = "jdbc:mysql://node48511-przetwarzaie.unicloud.pl/" + DB_NAME;

    
    public void databaseTable(String name, String substance, String similar, String price)
    {
        Connection conn = null;
        Statement stmt = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            System.out.println("Connecting to database...");
            
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Database connected");
            stmt=conn.createStatement();
            String sqlTableDrugs = "INSERT INTO drugs (name, substance, similar, price)" + "VALUES ('"+name+"', '"+substance+"', '"+similar+"', '"+price+"')";
            String sqlTableMissingDrugs = "DELETE FROM missingDrugs WHERE name='"+name+"'";

            stmt.executeUpdate(sqlTableDrugs); 
            stmt.executeUpdate(sqlTableMissingDrugs);
           if(stmt.execute(sqlTableDrugs)){
            System.out.println("ADDED");
           }
           else{
            System.out.println("NOT ADDED");}
           
           if(stmt.execute(sqlTableMissingDrugs)){
            System.out.println("DELETED");
           }
           else{
            System.out.println("NOT DELETED");}

        }catch(Exception e){
            System.out.println("ERROR "+e);
        } 
        
    }
}

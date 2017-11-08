
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.soap.AddressingFeature;

public class DatabaseTakeFromTable {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static String DB_URL = "jdbc:mysql://localhost:3306/checkyourdrug";

    static final String USER = "root";
    static final String PASS = "";
    static final String DB_NAME = "baza";
    
    public List<Database> databaseTable()
    {
        Connection conn = null;
        Statement stmt = null;
        
        List<Database> list = new ArrayList<Database>();
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("polaczenie z baza");
            String sql = "SELECT * FROM drugs";
            stmt=conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                int ID = rs.getInt("ID");
                String name = rs.getString("name");
                String substance = rs.getString("substance");
                String similar = rs.getString("similar");
                Double price = rs.getDouble("price");
                
                list.add(new Database(ID, name, substance, similar, price));
            }
            
            System.out.println("wyswietlono baze");

        }catch(Exception e){
            System.out.println("blad "+e);
        } 
        return list;
    }
}
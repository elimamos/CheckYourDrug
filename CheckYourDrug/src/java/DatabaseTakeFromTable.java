
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseTakeFromTable {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static String DB_URL = "jdbc:mysql://localhost:3306/checkyourdrug";

    static final String USER = "root";
    static final String PASS = "";
    static final String DB_NAME = "CheckYourDrug";
    
    public List<Database> databaseTable(String table)
    {
        Connection conn = null;
        Statement stmt = null;
        
        List<Database> list = new ArrayList<Database>();
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT * FROM "+table+"";
            stmt=conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            switch(table){
                case "drugs":{
                    while(rs.next()){
                int ID = rs.getInt("ID");
                String name = rs.getString("name");
                String substance = rs.getString("substance");
                String similar = rs.getString("similar");
                String price = rs.getString("price");
                
                list.add(new Database(ID, name, substance, similar, price));
            }
                    break;
                }
                case "missingdrugs":{
                    while(rs.next()){
                int ID = rs.getInt("ID");
                String name = rs.getString("name");
                
                list.add(new Database(ID, name, "", "", ""));
            }
                    break;
                }
            }


        }catch(Exception e){
            System.out.println("Error "+e);
        } 
        return list;
    }

}


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.*;



public class CheckDrug extends HttpServlet {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    static final String USER = "root";
    static final String PASS = "U3czgEov5w";
    static final String DB_NAME = "CheckYourDrug";
    static String DB_URL = "jdbc:mysql://node48511-przetwarzaie.unicloud.pl/"+DB_NAME; 

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
         
        String drugName = request.getParameter("name");
        drugName = drugName.trim(); 
        System.out.println(drugName);
          
        
        Connection conn = null;
        Statement stmt = null;
       
        JSONObject obj = new JSONObject();
             
        try{
            Class.forName("com.mysql.jdbc.Driver");
          
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection established!");
        
            stmt=conn.createStatement();
            String sql = "SELECT name,substance,similar,price FROM drugs WHERE name='"+drugName+"'";
            System.out.println(sql);
            
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){   
               String name = rs.getString("name");
               String substance = rs.getString("substance");
               String similiar = rs.getString("similar");
               String price = rs.getString("price");
     
               obj.put("name",name);
               obj.put("substance",substance);
               obj.put("similar",similiar);
               obj.put("price",price);
               
               response.setCharacterEncoding("utf8");
               response.setContentType("application/json");
               response.getWriter().write(obj.toString());

            }else{   
                sql = "SELECT name FROM missingDrugs WHERE name='"+drugName+"'";
                System.out.println(sql);
            
                rs = stmt.executeQuery(sql);
                if(rs.next()){      
                    String noresultinfo = "Sorry! Missing drug! We've already added your request to the Missing Drug Database and out stuff wil take care of it in soon future."; 
                    response.getWriter().print(noresultinfo);                    
                }else{
                    sql = "INSERT INTO missingDrugs (name) VALUES ('"+drugName+"')" ;
                    stmt.executeUpdate(sql);
                    String noresultinfo = "Sorry! Missing drug!"; 
                    response.getWriter().print(noresultinfo);
                }
                
            
            }   
        }catch(Exception e){
            System.out.println("Error occured! "+e);
        } 
        
    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Servlet for manage data from android device";
    }

    
}

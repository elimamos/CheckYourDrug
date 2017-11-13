import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class AdminPage extends HttpServlet{
    
    String name;
    String substance;
    String similar;
    String price;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter(); 
    List<Database> list = new ArrayList<Database>(); 
        DatabaseTakeFromTable dcTakeFromTable = new DatabaseTakeFromTable(); 
        //list = dcTakeFromTable.databaseTable(); 
        list=dcTakeFromTable.databaseTable("drugs"); 
        out.print("<h3>List of drugs in database</h3>"); 
        out.print("<div>"); 
        for(Database db : list){ 
                out.print(db.name+"; "); 
        } 
        out.print("</div>"); 
        list=dcTakeFromTable.databaseTable("missingdrugs"); 
        out.print("<h3>List of missing drugs in database</h3>"); 
        out.print("<div>"); 
        for(Database db : list){ 
                out.print(db.name+"; "); 
        } 
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        name = request.getParameter("name").trim();
        substance = request.getParameter("substance").trim();
        similar = request.getParameter("similar").trim();
        price = request.getParameter("price").trim();
        if(name==null||substance==null||price==null){
        //HERE ARGUMENTS NULL
        } else if(name.equals("")||substance.equals("")||price.equals("")){
        //HERE ARGUMENTS EMPTY
        }else{
     
       Pattern pattern = Pattern.compile("\\d+[\\.\\,]?\\d{0,2}");
       if(pattern.matcher(price).matches()){
            if (price.contains(",")) {
              price= price.replace(',', '.');
              System.out.print(price);
              
            }
         DatabaseAddToTable dbAddToTable = new DatabaseAddToTable();
        dbAddToTable.databaseTable(name, substance, similar, price);
        response.sendRedirect("/CheckYourDrug/adminAccount.jsp");
       }
       else{
           
           System.out.print("WRONG ARGUMENT - PRICE");
          }
    }
 }
}

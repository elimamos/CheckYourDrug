


import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class CheckPassword extends HttpServlet{
    
    String name;
    String substance;
    String similar;
    String price;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
       }
       else{
           
           System.out.print("WRONG ARUMNET - PRICE");
          }
    }
 }
}

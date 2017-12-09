import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DatabaseAccess extends HttpServlet {

    List<Database> list = new ArrayList<Database>();
   public void init(){   
       
       //stworzenie bazy danych jak nie istnieje
        DatabaseCreator dbCreator = new DatabaseCreator();
        dbCreator.databaseCreate();
        
        //dodanie rzeczy do bazy
        DatabaseAddToTable dbTable = new DatabaseAddToTable();
        //dbTable.databaseTable();
        
        //pobranie danych z bazy do listy
        DatabaseTakeFromTable dcTakeFromTable = new DatabaseTakeFromTable();
        list=dcTakeFromTable.databaseTable("drugs");
    };
   
   // REST 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        DatabaseTakeFromTable dcTakeFromTable = new DatabaseTakeFromTable();
        list=dcTakeFromTable.databaseTable("drugs");
        PrintWriter out = response.getWriter();
        
        out.print("<table class='table is-striped'><tbody><tr><th>Name</th><th>Substance</th><th>Similar</th><th>Price</th></tr>"); 
        for(Database db : list){
                out.print("<tr>"
                        + "<td>"+db.name+"</td>"
                        + "<td>"+db.substance+"</td>"
                        + "<td>"+db.similar+"</td>"
                        + "<td>"+db.price+"</td>"
                        + "</tr>");
                out.print("</br>");
        }
        out.print("</tbody></table>"); 
    }
    

    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        String nameOfLogin="admin";
        String nameOfPassword="admin";
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if(nameOfLogin.equals(login) && nameOfPassword.equals(password)){
            response.sendRedirect("/adminAccount.jsp");
        }
        else{
            response.sendRedirect("/index.jsp");
        }
        doGet(request, response);
        }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // super.doOptions(req, resp); 
}   
    
    
}

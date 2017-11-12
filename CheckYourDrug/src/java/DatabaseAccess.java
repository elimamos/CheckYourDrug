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
        list=dcTakeFromTable.databaseTable();
    };
   
   // REST 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        DatabaseTakeFromTable dcTakeFromTable = new DatabaseTakeFromTable();
        list=dcTakeFromTable.databaseTable();
        PrintWriter out = response.getWriter();
        
        out.print("<div><table class='table is-striped'><tbody><tr><th>Nazwa</th><th>Substancja czynna</th><th>Inna nazwa</th><th>Cena</th></tr>"); 
        for(Database db : list){
                out.print("<tr>"
                        + "<td>"+db.name+"</td>"
                        + "<td>"+db.substance+"</td>"
                        + "<td>"+db.similar+"</td>"
                        + "<td>"+db.price+"</td>"
                        + "</tr>");
                out.print("</br>");
        }
        out.print("</tbody></table></div>"); 
    }
    

    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        String nameOfLogin="admin";
        String nameOfPassword="admin";
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if(nameOfLogin.equals(login) && nameOfPassword.equals(password)){
            request.getRequestDispatcher("/adminAccount.jsp").forward(request, response);
        }
        else{
            //response.sendRedirect("/index.jsp");
            request.getRequestDispatcher("/checkLogin.jsp").forward(request, response);
        }
        doGet(request, response);
        }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // super.doOptions(req, resp); 
}   
    
    
}

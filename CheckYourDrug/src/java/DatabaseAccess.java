import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;


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
        Random r = new Random();
        String[] table = {"#ccffcc", "#ffffcc", "#ffcccc", "#cce6ff", "#ffcccc", "#ffccff"};
        PrintWriter out = response.getWriter();
        
        for(Database db : list){
           
           int rr=r.nextInt(6);
                out.print("<div class=\"one\" style=\"background-color: "+table[rr]+"; width: auto; height: auto; margin: auto; font-size: x-large;\">"
                        + "<b>"+db.name+"</b></br>"
                        + "<p style=\"border-bottom-style: dotted; border-color: white; text-align: center; color: black\">Substance:</p>"
                        + db.substance+"</br>"
                        + "<p style=\"border-bottom-style: dotted; border-color: white; text-align: center; color: black\">Similar:</p>"
                        + db.similar+"</br>"
                        + "<p style=\"border-bottom-style: dotted; border-color: white; text-align: center; color: black\">Price:</p>"
                        + db.price+"</br>"
                        + "</div>");
                out.print("</br>");
        }
      
        //request.getRequestDispatcher("adminAccount.jsp").forward(request, response);
    
    }
       

    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        String nameOfLogin="admin";
        String nameOfPassword="admin";
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if(nameOfLogin.equals(login) && nameOfPassword.equals(password)){
            response.sendRedirect("/CheckYourDrug/adminAccount.jsp");
        }
        else{
            response.sendRedirect("/CheckYourDrug/index.jsp");
        }
        }
   
}

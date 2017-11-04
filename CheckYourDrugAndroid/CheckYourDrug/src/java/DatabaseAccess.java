import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DatabaseAccess extends HttpServlet {

   public void init(){                  
        DatabaseCreator dbCreator = new DatabaseCreator();
        dbCreator.databaseCreate();
    };
   
   
   // REST 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
    
    }
       

    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException,
        IOException {
        doGet(request, response);
        }
}

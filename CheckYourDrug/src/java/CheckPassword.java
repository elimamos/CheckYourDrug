
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckPassword extends HttpServlet{
    
    String name;
    String substance;
    String similar;
    Double price;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        name = request.getParameter("name");
        substance = request.getParameter("substance");
        similar = request.getParameter("similar");
        price = Double.parseDouble(request.getParameter("price"));
        
        DatabaseAddToTable dbAddToTable = new DatabaseAddToTable();
        dbAddToTable.databaseTable(name, substance, similar, price);
    }
}

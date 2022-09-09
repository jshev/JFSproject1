import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ViewEmpsServlet extends HttpServlet {
    LoginDao loginDao = LoginDaoFactory.getLoginDao();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        List<Login> employees = loginDao.getAllLogin();
        for (Login employee: employees) {
            out.println("<div>");
            out.println(employee);
            out.println("</div>");
        }
    }
}

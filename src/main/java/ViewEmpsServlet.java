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

        out.println("<head>");
        out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css\"\n" +
                "          integrity=\"sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N\" crossorigin=\"anonymous\">\n");
        out.println("</head>");
        out.println("<body>");

        for (Login employee: employees) {
            /*out.println("<div>");
            out.println(employee);
            out.println("</div>");*/
            out.println("<div class=\"card\">");
            out.println("<div class=\"card-body\">\n");
            out.println(employee);
            out.println("</div>");
            out.println("</div>");
        }

        out.println("</body>");
    }
}

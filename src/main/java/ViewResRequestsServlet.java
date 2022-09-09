import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ViewResRequestsServlet extends HttpServlet {

    RequestDao requestDao = RequestDaoFactory.getRequestDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        List<Request> requests = requestDao.getRequestsByStatus("approved");
        for (Request request1: requests) {
            out.println("<div>");
            out.println(request1);
            out.println("</div>");
        }

        List<Request> requests2 = requestDao.getRequestsByStatus("denied");
        for (Request request1: requests2) {
            out.println("<div>");
            out.println(request1);
            out.println("</div>");
        }
    }

}

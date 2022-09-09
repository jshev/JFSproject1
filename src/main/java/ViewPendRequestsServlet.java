import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ViewPendRequestsServlet extends HttpServlet {
    RequestDao requestDao = RequestDaoFactory.getRequestDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        List<Request> requests = requestDao.getRequestsByStatus("pending");
        for (Request request1: requests) {
            out.println("<div>");
            out.println(request1);
            out.println("<form action='ApproveRequestServlet' method='post'>\n" +
                    "    <input type='hidden' name='requestId' value='"+request1.getRequestId()+"'/>\n" +
                    "    <input type='submit' value='Approve'>\n" +
                    "</form>\n" +
                    "<form action='DenyRequestServlet' method='post'>\n" +
                    "    <input type='hidden' name='requestId' value='"+request1.getRequestId()+"'/>\n" +
                    "    <input type='submit' value='Deny'>\n" +
                    "</form>");
            out.println("</div>");
        }
    }
}

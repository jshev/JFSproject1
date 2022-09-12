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

        out.println("<head>");
        out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css\"\n" +
                "          integrity=\"sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N\" crossorigin=\"anonymous\">\n");
        out.println("</head>");
        out.println("<body>");

        for (Request request1: requests) {
            out.println("<div class=\"card\">");
            out.println("<div class=\"card-body\">\n");
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
            out.println("</div>");
        }

        out.println("</body>");
    }
}

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DenyRequestServlet extends HttpServlet {
    RequestDao requestDao = RequestDaoFactory.getRequestDao();
    int requestId;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        requestId = Integer.parseInt(request.getParameter("requestId"));
        Request request1 = requestDao.getRequestById(requestId);
        request1.setStatus("denied");
        requestDao.updateRequest(request1);
        response.sendRedirect("ViewPendRequestsServlet");
    }
}

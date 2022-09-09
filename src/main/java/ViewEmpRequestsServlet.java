import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ViewEmpRequestsServlet extends HttpServlet {
    LoginDao loginDao = LoginDaoFactory.getLoginDao();
    RequestDao requestDao = RequestDaoFactory.getRequestDao();
    int loginId;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("loginId")) {
                    loginId = parseInt(cookie.getValue());
                }
            }
        }

        Login login = loginDao.getLoginById(loginId);

        List<Request> requests = requestDao.getRequestsBySubmitter(login.getName());
        for (Request request1: requests) {
            out.println("<div>");
            out.println(request1);
            out.println("</div>");
        }
    }
}

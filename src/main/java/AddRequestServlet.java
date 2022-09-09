import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Integer.parseInt;

public class AddRequestServlet extends HttpServlet {

    LoginDao loginDao = LoginDaoFactory.getLoginDao();
    RequestDao requestDao = RequestDaoFactory.getRequestDao();
    int loginId;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<form action='AddRequestServlet' method='post'>\n" +
                "    <div>\n" +
                "        <label for='description'>Description</label>\n" +
                "        <input type='text' name='description' id='description'>\n" +
                "    </div>\n" +
                "    <div>\n" +
                "        <label for='amount'>Amount</label>\n" +
                "        <input type='text' name='amount' id='amount'>\n" +
                "    </div>\n" +
                "    <div>\n" +
                "        <input type='submit' value='Submit'>\n" +
                "    </div>\n" +
                "</form>");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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

        Request request1 = new Request();
        request1.setSubmitter(login.getName());
        request1.setDescription(request.getParameter("description"));
        request1.setAmount(Integer.parseInt(request.getParameter("amount")));
        request1.setStatus("pending");

        requestDao.addRequest(request1);
        request.getRequestDispatcher("empHome.html").include(request,response);

    }

}

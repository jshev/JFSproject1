import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Integer.parseInt;

public class UpdateEmpServlet extends HttpServlet {

    LoginDao loginDao = LoginDaoFactory.getLoginDao();
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

        out.println("<form action='UpdateEmpServlet' method='post'>\n" +
                "    <div>\n" +
                "        <label for='name'>Name</label>\n" +
                "        <input type='text' id='name' name='name' value='"+login.getName()+"'>\n" +
                "    </div>\n" +
                "    <div>\n" +
                "        <label for='email'>Email</label>\n" +
                "        <input type='text' id='email' name='email' value='"+login.getEmail()+"'>\n" +
                "    </div>\n" +
                "    <div>\n" +
                "        <label for='username'>Username</label>\n" +
                "        <input type='text' id='username' name='username' value='"+login.getUsername()+"'>\n" +
                "    </div>\n" +
                "    <div>\n" +
                "        <label for='password'>Password</label>\n" +
                "        <input type='text' id='password' name='password' value='"+login.getPassword()+"'>\n" +
                "    </div>\n" +
                "    <div>\n" +
                "        <input type='submit' value='Save'>\n" +
                "    </div>\n" +
                "</form>");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        Login login = loginDao.getLoginById(loginId);
        login.setName(request.getParameter("name"));
        login.setEmail(request.getParameter("email"));
        login.setUsername(request.getParameter("username"));
        login.setPassword(request.getParameter("password"));

        loginDao.updateLogin(login);
        request.getRequestDispatcher("empHome.html").include(request,response);
    }


}

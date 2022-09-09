import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    LoginDao loginDao = LoginDaoFactory.getLoginDao();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher("index.html").include(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int userId = loginDao.getLoginIdByCredentials(username, password);
        if (userId == 0) {
            out.println("Unsuccessful login...");
            request.getRequestDispatcher("index.html").include(request,response);
        } else {
            //out.println("UserID = " + userId);
            Login user = loginDao.getLoginById(userId);
            if (user.getRole().equals("manager")) {
                //out.println("Successful manager login!");
                //out.println("Hello, " + user.getName() + "!");
                Cookie idCookie = new Cookie("loginId", String.valueOf(userId));
                response.addCookie(idCookie);
                request.getRequestDispatcher("manHome.html").include(request,response);
            } else {
                //out.println("Successful employee login!");
                //out.println("Hello, " + user.getName() + "!");
                Cookie idCookie = new Cookie("loginId", String.valueOf(userId));
                response.addCookie(idCookie);
                request.getRequestDispatcher("empHome.html").include(request,response);
            }
        }

    }
}

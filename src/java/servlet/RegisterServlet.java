package servlet;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (req.getParameter("username"));
        String password = req.getParameter("password");
        UserDao userDao = new UserDao();
        if (userDao.getUser(username, password) == null) {
            new UserDao().add(username, password);
            resp.sendRedirect("login.jsp");
        } else {

            resp.getWriter().println("该用户已存在,请更换用户名");
            resp.getWriter().println("<h2><a href=\"register.jsp\">我要注册</a></h2>");
        }

    }
}

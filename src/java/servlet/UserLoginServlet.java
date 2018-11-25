package servlet;

import bean.User;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new UserDao().getUser(username, password);
//        System.out.println(username + password + user);

        if (null != user) {
            req.getSession().setAttribute("user", user);
            Cookie usernameCookie = new Cookie("username", user.getName());
            Cookie passwordCookie = new Cookie("password", user.getPassword());
            usernameCookie.setMaxAge(864000);
            passwordCookie.setMaxAge(864000);//设置最大生存期限为10天
            resp.addCookie(usernameCookie);
            resp.addCookie(passwordCookie);
            resp.sendRedirect("/listProduct");
        } else {
            resp.getWriter().println("<h1 align=\"center\" style=\"color:green\"> 用户名或密码错误 </h1>");
            resp.getWriter().println("<h1 align=\"center\" style=\"color:green\"> <a href=\"login.jsp\">我要登陆</a> </h1>");
        }
    }
}

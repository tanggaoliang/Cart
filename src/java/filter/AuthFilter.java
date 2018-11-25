package filter;

import bean.User;
import dao.UserDao;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String uri = request.getRequestURI();
        if (uri.contains("login") || uri.contains("register")) {
//            System.out.println("filter  zheng zai 登录");
            chain.doFilter(req, resp);
            return;
        }
        //通过session设置user
        User user = (User) request.getSession().getAttribute("user");

        if (user != null) {
//            System.out.println("filter session"+user.toString());
            chain.doFilter(req, resp);
            return;
        }
        String username = "";
        String password = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    username = URLDecoder.decode(c.getValue(), "utf-8");
                } else if (c.getName().equals("password")) {
                    password = URLDecoder.decode(c.getValue(), "utf-8");
                }
            }
            user = new UserDao().getUser(username, password);
            if (null != user) {
                request.getSession().setAttribute("user", user);
//                System.out.println("filter cookie 读取 user ");
                chain.doFilter(req, resp);
                return;
            }
        }
//        System.out.println("filter to login");
        response.sendRedirect("login.jsp");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}


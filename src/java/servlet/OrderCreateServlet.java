package servlet;

import bean.Order;
import bean.OrderItem;
import bean.User;
import dao.OrderDao;
import dao.OrderItemDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderCreateServlet")
public class OrderCreateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (null == user) {
            resp.sendRedirect("/login.jsp");
            return;
        }
        Order order = new Order(user);
        new OrderDao().insert(order);
        List<OrderItem> orderItemList = (List<OrderItem>) req.getSession().getAttribute("OrderItemList");
        int pay = 0;
        for (OrderItem item : orderItemList) {
            pay += item.getProduct().getPrice() * item.getNum();
            item.setOrder(order);
            new OrderItemDao().insert(item);
        }
        orderItemList.clear();
        resp.getWriter().println("订单创建成功,一共" + pay + "元.");

    }
}

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "OrderItemListServlet")
public class OrderItemListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean history = Boolean.parseBoolean(req.getParameter("history"));
        if (history) {
            User user = (User) req.getSession().getAttribute("user");
            List<OrderItem> orderItemList = new ArrayList<>();
            List<Order> orderList = new OrderDao().getOrderList(user.getId());
//            System.out.println("orderList is "+orderList.toString());
//            到这成功
            for (Order order : orderList) {
                for (OrderItem item : new OrderItemDao().getOrderListItem(order.getId())) {
//                    System.out.println("item is"+item.toString());
                    orderItemList.add(item);
                }

            }
            req.setAttribute("OrderItemList", orderItemList);
//            System.out.println(orderItemList.get(0).getProduct().getName()+orderItemList.get(0).getNum());
//            System.out.println("OrderItemList:"+Arrays.toString(orderItemList.toArray()));
            req.getRequestDispatcher("listOrderHistory.jsp").forward(req, resp);
        } else
            req.getRequestDispatcher("listOrderItem.jsp").forward(req, resp);
    }
}

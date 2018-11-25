package servlet;

import bean.OrderItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "OrderItemDeleteServlet")
public class OrderItemDeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pid = Integer.parseInt(req.getParameter("pid"));
//        System.out.println(pid+" is pid");

        List<OrderItem> OrderItemList = (List<OrderItem>) req.getSession().getAttribute("OrderItemList");
//        System.out.println(Arrays.toString(OrderItemList.toArray()));
        for (OrderItem item : OrderItemList) {
            if (item.getProduct().getId() == pid) {
//                System.out.println("准备删除pid"+pid);
                OrderItemList.remove(item);
//                System.out.println(OrderItemList.toString());
                break;
            }
        }

        resp.sendRedirect("/listOrderItem");
    }
}

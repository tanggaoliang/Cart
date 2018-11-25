package servlet;

import bean.OrderItem;
import bean.Product;
import com.sun.org.apache.xpath.internal.operations.Or;
import dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderItemAddServlet")
public class OrderItemAddServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int num = Integer.parseInt(req.getParameter("num"));
        int pid = Integer.parseInt(req.getParameter("pid"));
        boolean replace = Boolean.parseBoolean(req.getParameter("replace"));
        Product product = new ProductDao().getProduct(pid);
        OrderItem orderItem = new OrderItem(product, num);
        List<OrderItem> OrderItemList = (List<OrderItem>) req.getSession().getAttribute("OrderItemList");
        if (null == OrderItemList) {
            OrderItemList = new ArrayList<OrderItem>();
//            System.out.println("没有商品列表");
        }
        //是否是同一件商品
        boolean found = false;
        for (OrderItem item : OrderItemList) {
            if (item.getProduct().getId() == orderItem.getProduct().getId()) {
                if (replace) {
                    item.setNum(orderItem.getNum());
//                    System.out.println("ajax"+item.getProduct().getId()+item.getNum());
                } else {
                    item.setNum(orderItem.getNum() + item.getNum());
                }
                found = true;
//                System.out.println("找到相同商品:" + product.getName());
                break;
            }
        }
        if (!found) {
//            System.out.println("没有找到相同商品:" + product.getName());
            OrderItemList.add(orderItem);
        }
        req.getSession().setAttribute("OrderItemList", OrderItemList);
        resp.sendRedirect("/listProduct");


//        resp.sendRedirect("/listOrderItem");
    }

}
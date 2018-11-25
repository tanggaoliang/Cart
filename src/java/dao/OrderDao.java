package dao;

import bean.Order;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    public void insert(Order order) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=utf8", "root", "admin");
            String sql = "insert into order_ values(null,?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, order.getUser().getId());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                order.setId(id);
            }
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Order> getOrderList(int uid) {
        List<Order> orderList = new ArrayList<>();
        Order order = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=utf8", "root", "admin");
            String sql = " select id from order_  where uid = ? ; ";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, uid);
//            System.out.println(ps.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
//                System.out.println("user id is " + id);
                order = new Order(id, new UserDao().getUser(uid));
//                System.out.println(order.toString());
                orderList.add(order);
            }
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(orderList.toString());
        return orderList;

    }

    public Order getOrder(int id) {
        Order order = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=utf8", "root", "admin");
            String sql = "select uid from order_  where id =?;";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
//            System.out.println("getOrder"+ps.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int uid = rs.getInt(1);
                order = new Order(id, new UserDao().getUser(uid));
            }
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;

    }


}

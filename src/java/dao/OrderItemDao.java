package dao;

import bean.OrderItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDao {
    public void insert(OrderItem orderItem) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=utf8", "root", "admin");
            String sql = "insert into orderitem values(null,?,?,?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, orderItem.getProduct().getId());
            ps.setInt(2, orderItem.getNum());
            ps.setInt(3, orderItem.getOrder().getId());
            ps.execute();
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<OrderItem> getOrderListItem(int oid) {
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=utf8", "root", "admin");
            String sql = "select * from orderitem where oid=?;";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, oid);
//            System.out.println(ps.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int pid = rs.getInt(2);
                int num = rs.getInt(3);
                orderItem = new OrderItem(id, new ProductDao().getProduct(pid), num, new OrderDao().getOrder(oid));
//                System.out.println("orderItem " + orderItem.toString());
                orderItemList.add(orderItem);
            }
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderItemList;
    }

    public OrderItem getOrder(int id) {
        OrderItem orderItem = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=utf8", "root", "admin");
            String sql = "select * from orderitem where id=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeQuery();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int pid = rs.getInt(2);
                int num = rs.getInt(3);
                int oid = rs.getInt(4);
                orderItem = new OrderItem(id, new ProductDao().getProduct(pid), num, new OrderDao().getOrder(oid));
            }
            ps.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderItem;
    }
}

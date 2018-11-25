package dao;

import bean.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public Product getProduct(int id) {
        Product product = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=utf8", "root", "admin");
            String sql = "select * from product where id =?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString(2);
                Float price = rs.getFloat(3);
                product = new Product(id, name, price);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> listProduct() {
        List<Product> products = new ArrayList<Product>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?charsetEncoding=UTF-8", "root", "admin");
            String sql = "select * from product order by id desc";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                float price = rs.getFloat(3);
                Product product = new Product(id, name, price);
//                System.out.println(product.toString());
                products.add(product);
            }
            ps.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }


}

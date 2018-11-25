package dao;

import bean.User;

import java.sql.*;

public class UserDao {
    public void add(String username, String password) {

        String sql = "insert into user values(null ,?,?)";
        try (Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=utf8", "root", "admin"); PreparedStatement ps = c.prepareStatement(sql);) {
            try {
                ps.setString(1, username);
                ps.setString(2, password);
                ps.execute();
                System.out.println("add user" + ps.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String name, String password) {
        User user = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=utf8", "root", "admin");
            String sql = "select * from user where name = ? and password = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                user = new User(id, name, password);
            }
            ps.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUser(int id) {
        User user = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=utf8", "root", "admin");
            String sql = "select * from user where id= ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString(2);
                String password = rs.getString(3);
                user = new User(id, name, password);
            }
            ps.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}

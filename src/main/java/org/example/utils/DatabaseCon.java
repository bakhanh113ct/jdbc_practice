package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseCon {
    Connection con = null;

    public DatabaseCon() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/user", "root", "1234");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUser(String query, String username, String password) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
//                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "NOT FOUND";
    }

    public boolean setUser(String query, String username, String password) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
//            Statement stmt = con.createStatement();
            int row = stmt.executeUpdate();
            System.out.println("row effected: " + row);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (Exception e) {
            }
        }
        return false;
    }
}

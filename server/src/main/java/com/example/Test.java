package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String password = "password"; // Security Issue: Hardcoded credentials

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your ID:");
        String id = scanner.nextLine();

        System.out.println("Enter your name:");
        String name = scanner.nextLine();

        // Duplicate Code (Repeats same logic twice)
        insertUserData(url, user, password, id, name);
        insertUserData(url, user, password, id, name);

        System.out.println("Done!");
        scanner.close();
    }

    private static void insertUserData(String url, String user, String password, String id, String name) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Security Issue: No input sanitization for SQL injection
            conn = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO users (id, name) VALUES ('" + id + "', '" + name + "')";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Database error occurred: " + e.getMessage()); // Reliability Issue: Generic exception handling
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                // Reliability Issue: Empty catch block
            }
        }
    }
}

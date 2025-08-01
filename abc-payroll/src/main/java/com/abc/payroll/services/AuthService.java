package com.abc.payroll.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;

public class AuthService {
    private Connection connection;

    public AuthService(Connection connection) {
        this.connection = connection;
    }

    public boolean authenticate(String username, String password) {
        try {
            String testHash = BCrypt.hashpw("admin123", BCrypt.gensalt());
            boolean testResult = BCrypt.checkpw("admin123", testHash);
            System.out.println("BCrypt self-test: " + testResult);
            
            String sql = "SELECT password_hash FROM users WHERE username = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username.trim());
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String storedHash = rs.getString("password_hash").trim();
                System.out.println("Stored hash: " + storedHash);
                return BCrypt.checkpw(password.trim(), storedHash);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
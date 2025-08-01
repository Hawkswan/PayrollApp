package com.abc.payroll.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class AuthService {
    private final Connection connection;

    public AuthService(Connection connection) {
        this.connection = connection;
        BCrypt.gensalt();
    }

    public boolean authenticate(String username, String password) {
        if (username == null || password == null) {
            return false;
        }

        try {
            String storedHash = getPasswordHash(username.trim());
            if (storedHash == null) {
                return false;
            }

            storedHash = storedHash.replace("\\$", "$");
            
            if (!(storedHash != null && storedHash.startsWith("$2a$") && storedHash.length() == 60)) {
                System.err.println("Invalid BCrypt hash format for user: " + username);
                return false;
            }

            return BCrypt.checkpw(password.trim(), storedHash);
        } catch (Exception e) {
            System.err.println("Authentication error for user: " + username);
            e.printStackTrace();
            return false;
        }
    }

    private String getPasswordHash(String username) throws SQLException {
        String sql = "SELECT password_hash FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getString("password_hash") : null;
        }
    }

    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }
}
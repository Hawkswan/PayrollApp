package com.abc.payroll;

import com.abc.payroll.services.AuthService;
import com.abc.payroll.services.DatabaseService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.io.File; // Add this import

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Starting application...");
            
            // Get absolute path to database
            String dbPath = new File("payroll.db").getAbsolutePath();
            System.out.println("Database path: " + dbPath);
            
            // Establish connection (remove duplicate declaration)
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            System.out.println("Database connected");
            
            AuthService authService = new AuthService(connection);
            DatabaseService dbService = new DatabaseService(connection);
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            
            System.out.println("Attempting login with:");
            System.out.println("Username: '" + username + "'");
            System.out.println("Password: '" + password + "'");
            
            if (authService.authenticate(username, password)) {
                System.out.println("Login successful!");
                dbService.getAllEmployees().forEach(emp -> 
                    System.out.println(emp.getFirstName() + " " + emp.getLastName()));
            } else {
                System.out.println("Invalid credentials");
            }
            
        } catch (Exception e) {
            System.err.println("Application error:");
            e.printStackTrace();
        }
    }
}
package com.abc.payroll.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.abc.payroll.models.Employee;

public class DatabaseService {
    private Connection connection;

    public DatabaseService(Connection connection) {
        this.connection = connection;
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT id, first_name, last_name, email, position, salary, hire_date FROM employees";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setEmail(rs.getString("email"));
                emp.setPosition(rs.getString("position"));
                emp.setSalary(rs.getDouble("salary"));
                
                // Handle SQLite date format (stored as TEXT)
                Date hireDate = rs.getDate("hire_date");
                if (hireDate == null && rs.getString("hire_date") != null) {
                    hireDate = Date.valueOf(rs.getString("hire_date"));
                }
                emp.setHireDate(hireDate);
                
                employees.add(emp);
            }
        }
        return employees;
    }

    public Employee getEmployeeById(int id) throws SQLException {
        String sql = "SELECT id, first_name, last_name, email, position, salary, hire_date FROM employees WHERE id = ?";
        Employee emp = null;
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setEmail(rs.getString("email"));
                emp.setPosition(rs.getString("position"));
                emp.setSalary(rs.getDouble("salary"));
                
                // Handle SQLite date format
                Date hireDate = rs.getDate("hire_date");
                if (hireDate == null && rs.getString("hire_date") != null) {
                    hireDate = Date.valueOf(rs.getString("hire_date"));
                }
                emp.setHireDate(hireDate);
            }
        }
        return emp;
    }
}
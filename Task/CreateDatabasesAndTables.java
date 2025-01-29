package com.JdbcEx;

import java.sql.*;

public class CreateDatabasesAndTables {

    public static void main(String[] args) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a connection to the database (replace with your credentials)
            String url = "jdbc:mysql://localhost:3306/"; 
            String user = "root";
            String password = "9Y@swanth5";
            Connection con = DriverManager.getConnection(url, user, password);

            // Create a statement object
            Statement stmt = con.createStatement();

            // Create a new database (if it doesn't exist)
            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS my_new_database";
            stmt.executeUpdate(createDatabaseQuery);

            // Use the newly created database
            String useDatabaseQuery = "USE my_new_database";
            stmt.executeUpdate(useDatabaseQuery);

            // Create a table (if it doesn't exist)
            String createTableQuery = "CREATE TABLE IF NOT EXISTS customers (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255)," +
                    "email VARCHAR(255))";
            stmt.executeUpdate(createTableQuery);

            System.out.println("Database and table created successfully!");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/tradb",
                            System.getenv("USERNAME"),
                            System.getenv("PASSWORD")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            Statement statement = connection.createStatement();
            statement.execute("select * from student;");
            ResultSet rs = statement.getResultSet();

            while(rs.next()) {

                int id = (rs.getInt("id"));
                String name = (rs.getString("name"));
                String email = (rs.getString("email"));

                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
            }
            //update record
            int idToUpdate= 2 ;
            String email= "wasan@gmail.com";

            statement.executeUpdate("UPDATE student SET email = '" +email + "' WHERE id = "+ idToUpdate);
            System.out.println("student updated");

            //insert record
            

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
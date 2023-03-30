package org.example;

import java.sql.*;

/**
 * A simple program that demonstrates basic CRUD (Create, Read, Update, Delete) operations
 * using JDBC to interact with a MySQL database.
 */
public class Main {
    /**
     * The main method that runs the program.
     *
     * @param args The command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        try {
            // Establish a connection to the database using JDBC
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/tradb",  // JDBC URL for the database
                        System.getenv("USERNAME"),
                        System.getenv("PASSWORD")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Create a statement object to execute SQL statements
            Statement statement = connection.createStatement();

            // Read (select) data from the database
            statement.execute("SELECT * FROM student;");
            ResultSet rs = statement.getResultSet();

            // Print out the results of the query
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
            }

            // Update data in the database
            int idToUpdate = 2;
            String newEmail = "wasan@gmail.com";

            statement.executeUpdate("UPDATE student SET email = '" + newEmail + "' WHERE id = " + idToUpdate);
            System.out.println("Student updated");

            // Insert data into the database
            String newName = "Salim";
            String newEmail2 = "salim@gmail.com";

            statement.executeUpdate("INSERT INTO student (name, email) VALUES ('" + newName + "', '" + newEmail2 + "')");
            System.out.println("New student inserted");

            // Delete data from the database
            int idToDelete = 4;

            statement.executeUpdate("DELETE FROM student WHERE id = " + idToDelete);
            System.out.println("Student deleted");

            // Close the resources (result set, statement, and connection)
            rs.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

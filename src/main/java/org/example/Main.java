/**

 A simple program that demonstrates basic CRUD (Create, Read, Update, Delete) operations
 using JDBC to interact with a MySQL database.
 */
package org.example;
import java.sql.*;
import java.util.ArrayList;

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
                        "jdbc:mysql://localhost:3306/tradb", // JDBC URL for the database
                        System.getenv("USERNAME"),
                        System.getenv("PASSWORD")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Create a statement object to execute SQL statements
            Statement statement = connection.createStatement();

            // Execute the SQL query to read data from the student table
            statement.execute("SELECT * FROM student;");
            ResultSet rs = statement.getResultSet();

            // Create an ArrayList to store the student objects
            ArrayList<Student> studentList = new ArrayList();

            // Loop through the result set and create a new student object for each row of data
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int age = rs.getInt("age");

                // Create a new student object and add it to the ArrayList
                Student newStudent = new Student(id, name, email, age);
                studentList.add(newStudent);
            }

            // Print out the student list
            for(Student currentStudent : studentList)
                System.out.println("ID: " + currentStudent.getId() + ", Name: " + currentStudent.getName()
                        + ", Email: " + currentStudent.getEmail() + " ,Age: " + currentStudent.getAge());

            // Close the resources (result set, statement, and connection)
            rs.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

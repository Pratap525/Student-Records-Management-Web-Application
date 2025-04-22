package com.example.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.example.model.Student;

public class StudentDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/school_db";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root123";

    private static final String INSERT_STUDENT = "INSERT INTO students (name, age, address) VALUES (?, ?, ?)";
    private static final String SELECT_STUDENT = "SELECT * FROM students WHERE id = ?";
    private static final String SELECT_ALL_STUDENTS = "SELECT * FROM students";
    private static final String UPDATE_STUDENT = "UPDATE students SET name = ?, age = ?, address = ? WHERE id = ?";
    private static final String DELETE_STUDENT = "DELETE FROM students WHERE id = ?";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Insert Student
    public void insertStudent(Student student) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setString(3, student.getAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get Student by ID
    public Student getStudentById(int id) {
        Student student = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                student = new Student(id, name, age, address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    // Get All Students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                students.add(new Student(id, name, age, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Update Student
    public boolean updateStudent(Student student) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setString(3, student.getAddress());
            preparedStatement.setInt(4, student.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    // Delete Student
    public boolean deleteStudent(int id) {
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }
}
package com.collegemanagement.repository;

import com.collegemanagement.model.*;
import com.collegemanagement.controller.HomeController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//  JDBC API contains the package: java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class StudentRepo extends BaseRepository{
	private Connection _conn = null; 
    private CourseRepo courseRep = null;

    public List<Student> fetchAll() throws SQLException {
		_conn = openConnection();
        List<Student> students = new ArrayList<>();
        Statement stmt = _conn.createStatement();
        String queryString = "SELECT * FROM student;";
        ResultSet resultSet = stmt.executeQuery(queryString);
        while (resultSet.next()) {
            Student stud = new Student(
                resultSet.getInt("id"),
                resultSet.getString("firstname"),
                resultSet.getString("lastname")
            );
            courseRep = new CourseRepo();
            Course crs = courseRep.fetch(resultSet.getInt("course_id"));
            stud.setCourse(crs);
            students.add(stud);
        }
        resultSet.close();
        stmt.close();
        closeConnection(_conn);
        return students;
    }

    public Student fetch(int id) throws SQLException {
        _conn = openConnection();
        List<Student> students = new ArrayList<>();
        Statement stmt = _conn.createStatement();
        String queryString = "SELECT * FROM student WHERE id=" + id;
        ResultSet resultSet = stmt.executeQuery(queryString);
        while (resultSet.next()) {
            Student stud = new Student(
                resultSet.getInt("id"),
                resultSet.getString("firstname"),
                resultSet.getString("lastname")
            );
            courseRep = new CourseRepo();
            Course crs = courseRep.fetch(resultSet.getInt("course_id"));
            stud.setCourse(crs);
            students.add(stud);
        }
        resultSet.close(); 
        stmt.close();
        closeConnection(_conn);
        return students.get(0);
    }

    public void create(String firstName, String lastName, int courseId) throws SQLException {
        _conn = openConnection();
        Statement st = _conn.createStatement();
        String sqlString = "INSERT INTO student VALUES (default, \"" + 
            firstName + "\", \"" + lastName + "\", " + courseId + ");" ;
        st.executeUpdate(sqlString);
        st.close();
        closeConnection(_conn);
    }

    public void edit(int studentId, String firstName, String lastName, int courseId) throws SQLException {
        _conn = openConnection();
        Statement st = _conn.createStatement();
        String sqlString = String.format("UPDATE student SET "
        + "firstname=\"%s\", lastname=\"%s\", course_id=%d where id=%d",
        firstName, lastName, courseId, studentId
        );
        st.executeUpdate(sqlString);
        st.close();
        closeConnection(_conn);
    }

    public void delete(int id) throws SQLException {
        _conn = openConnection();
        baseDelete(_conn, "student", id);
        closeConnection(_conn);
    }

    public List<Student> fetchByCourse(int course_id) throws SQLException {
        _conn = openConnection();
        List<Student> students = new ArrayList<>();
        Statement stmt = _conn.createStatement();
        String queryString = "SELECT * FROM student where course_id=" + course_id;
        ResultSet resultSet = stmt.executeQuery(queryString);
        while (resultSet.next()) {
            Student stud = new Student(
                resultSet.getInt("id"),
                resultSet.getString("firstname"),
                resultSet.getString("lastname")
            );
            courseRep = new CourseRepo();
            Course crs = courseRep.fetch(course_id);
            stud.setCourse(crs);
            students.add(stud);
        }
        resultSet.close();
        stmt.close();
        closeConnection(_conn);
        return students;
    }

}
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

public class CourseRepo extends BaseRepository{
	private Connection _conn = null; 

    public List<Course> fetchAll() throws SQLException {
		_conn = openConnection();
        List<Course> courses = new ArrayList<>();
        Statement stmt = _conn.createStatement();
        String queryString = "SELECT * FROM course";
        ResultSet resultSet = stmt.executeQuery(queryString);
        while (resultSet.next()) {
            Course crs = new Course(
                resultSet.getInt("id"),
                resultSet.getString("name")
            );
            courses.add(crs);
        }
        resultSet.close();
        stmt.close();
        closeConnection(_conn);
        return courses;
    }

    public Course fetch(int id) throws SQLException {
        _conn = openConnection();
        List<Course> courses = new ArrayList<>();
        Statement stmt = _conn.createStatement();
        String queryString = "SELECT * FROM course WHERE id=" + id;
        ResultSet resultSet = stmt.executeQuery(queryString);
        while (resultSet.next()) {
            Course stud = new Course(
                resultSet.getInt("id"),
                resultSet.getString("name")
            );
            courses.add(stud);
        }
        resultSet.close();
        stmt.close();
        closeConnection(_conn);
        return courses.get(0);
    }

    public List<Course> fetchBySubject(int subjectId) throws SQLException {
        // Return a list of Course that contain a Subject
        _conn = openConnection();
        Statement stmt = _conn.createStatement();
        List<Course> courses = new ArrayList<>();
        String csrQuery = "SELECT * FROM course_subject_relation WHERE subject_id=" 
            + subjectId;
        ResultSet resultSet = stmt.executeQuery(csrQuery);
        while (resultSet.next()) {
            Course course = fetch(resultSet.getInt("course_id"));
            courses.add(course);
        }
        resultSet.close();
        stmt.close();
        return courses;
    }

    public Course fetchByStudent(int studentId) throws SQLException {
        _conn = openConnection();
        Statement stmt = _conn.createStatement();
        StudentRepo studRep = new StudentRepo();
        Student student = studRep.fetch(studentId);
        return student.course;
    }

}
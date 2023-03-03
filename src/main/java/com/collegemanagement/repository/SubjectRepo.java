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

public class SubjectRepo extends BaseRepository{
	private Connection _conn = null;

    public List<Subject> fetchAll() throws SQLException {
		_conn = openConnection();
        List<Subject> subjects = new ArrayList<>();
        Statement stmt = _conn.createStatement();
        String queryString = "SELECT * FROM subject;";
        ResultSet resultSet = stmt.executeQuery(queryString);
        while (resultSet.next()) {

            Instructor currentInstructor = null;
            InstructorRepo instRepo = new InstructorRepo();
            currentInstructor = instRepo.fetch(resultSet.getInt("instructor_id"));

            Subject subj = new Subject(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("credit"),
                currentInstructor
            );
            subjects.add(subj);
        }
        resultSet.close(); 
        stmt.close();
        closeConnection(_conn);
        return subjects;
    }

    public Subject fetch(int id) throws SQLException {
		_conn = openConnection();
        List<Subject> subjects = new ArrayList<>();
        Statement stmt = _conn.createStatement();
        String queryString = "SELECT * FROM subject WHERE id=" + id;
        ResultSet resultSet = stmt.executeQuery(queryString);
        while (resultSet.next()) {

            Instructor currentInstructor = null;
            InstructorRepo instRepo = new InstructorRepo();
            currentInstructor = instRepo.fetch(resultSet.getInt("instructor_id"));

            Subject subj = new Subject(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("credit"),
                currentInstructor
            );
            subjects.add(subj);
        }
        resultSet.close(); 
        stmt.close();
        closeConnection(_conn);
        return subjects.get(0);
    }

    public List<Subject> fetchByCourse(int courseId) throws SQLException {
        _conn = openConnection();
        Statement stmt = _conn.createStatement();
        List<Subject> subjects = new ArrayList<>();

        String csrQuery = "SELECT * FROM course_subject_relation WHERE course_id=" 
            + courseId;
        ResultSet resultSet = stmt.executeQuery(csrQuery);
        while (resultSet.next()) {
            Subject sbj = fetch(resultSet.getInt("subject_id"));
            subjects.add(sbj);
        }
        resultSet.close();
        stmt.close();
        closeConnection(_conn);
        return subjects;
    }
    
    public List<Subject> fetchByInstructor(int instructorId) throws SQLException {
        _conn = openConnection();
        Statement stmt = _conn.createStatement();
        List<Subject> subjects = new ArrayList<>();
        InstructorRepo irep;

        String csrQuery = "SELECT * FROM subject WHERE instructor_id=" 
            + instructorId;
        ResultSet resultSet = stmt.executeQuery(csrQuery);
        while (resultSet.next()) {
            irep = new InstructorRepo();
            Subject sbj = new Subject(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("credit"),
                irep.fetch(resultSet.getInt("instructor_id"))
            );
            subjects.add(sbj);
        }
        resultSet.close();
        stmt.close();
        closeConnection(_conn);
        return subjects;
    }

    public Subject fetchByName(String subjectName) throws SQLException {
        _conn = openConnection();
        Statement stmt = _conn.createStatement();
        List<Subject> subjects = new ArrayList<>();
        InstructorRepo irep = new InstructorRepo();;

        String sqlString = "SELECT * FROM subject WHERE name=\"" 
            + subjectName.toLowerCase() + "\"";
        ResultSet resultSet = stmt.executeQuery(sqlString);
        while (resultSet.next()) {
            Subject sbj = new Subject(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("credit"),
                irep.fetch(resultSet.getInt("instructor_id"))
            );
            subjects.add(sbj);
        }
        resultSet.close();
        stmt.close();
        closeConnection(_conn);
        return subjects.get(0);
    }

}
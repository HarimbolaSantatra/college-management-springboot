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

public class InstructorRepo extends BaseRepository{
	private Connection _conn = null; 

    public List<Instructor> fetchAll() throws SQLException {
		_conn = openConnection();
        List<Instructor> instors = new ArrayList<>();
        Statement stmt = _conn.createStatement();
        String queryString = "SELECT * FROM instructor;";
        ResultSet resultSet = stmt.executeQuery(queryString);
        while (resultSet.next()) {
            Instructor inst = new Instructor(
                resultSet.getInt("id"),
                resultSet.getString("name")
            );
            instors.add(inst);
        }
        resultSet.close(); 
        stmt.close();
        closeConnection(_conn);
        return instors;
    }

    public Instructor fetch(int _id) throws SQLException {
		_conn = openConnection();
        List<Instructor> instors = new ArrayList<>();
        Statement stmt = _conn.createStatement();
        String queryString = "SELECT * FROM instructor where id=" + _id;
        ResultSet resultSet = stmt.executeQuery(queryString);
        while (resultSet.next()) {
            Instructor inst = new Instructor(
                resultSet.getInt("id"),
                resultSet.getString("name")
            );
            instors.add(inst);
        }
        resultSet.close(); 
        stmt.close();
        closeConnection(_conn);
        return instors.get(0);
    }

}
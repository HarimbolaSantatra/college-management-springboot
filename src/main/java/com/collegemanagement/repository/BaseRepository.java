package com.collegemanagement.repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class BaseRepository{

    public Connection openConnection() throws SQLException {
    	Connection con;
    	try {
    		con = DriverManager.getConnection(
        		"jdbc:mysql://127.0.0.1:3306/collegemanagement", "root", "root");
    	}
    	catch(SQLException ex) {
    		con = null;
    	}
    	return con;
    }

    public void closeConnection(Connection con) throws SQLException {
    	if(!con.isClosed()) {    		
    		con.close();
    	}
    }

	// Nataoko oe baseDelete @zay tsy mifangaro @le delete() ny child
	public void baseDelete (Connection con, String tableName, int id) throws SQLException {
        Statement st = con.createStatement();
        String sqlString = String.format("DELETE FROM %s WHERE id=%d", tableName, id);
        st.executeUpdate(sqlString);
        st.close();
	}

}
package com.collegemanagement.repository;

import com.collegemanagement.model.*;
import com.collegemanagement.controller.HomeController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class CourseInfoRepo extends BaseRepository{
	private Connection _conn = null;
}
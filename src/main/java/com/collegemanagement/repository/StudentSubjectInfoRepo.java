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

public class StudentSubjectInfoRepo extends BaseRepository{
    // SSI: StudentSubjectInfo
	private Connection _conn = null; 

    public List<StudentSubjectInfo> fetchStudentSSI(int studentId) throws SQLException {
        _conn = openConnection();
        Statement stmt = _conn.createStatement();

        // fetch student
        StudentRepo studRep = new StudentRepo();
        Student student = studRep.fetch(studentId);
        
        // Fetch Student's course
        Course course = student.course;

        // SubjectRepo
        SubjectRepo subjectRep = new SubjectRepo();

        List<StudentSubjectInfo> ssiList = new ArrayList<>();
        String queryString = "SELECT * FROM student_subject_info WHERE student_id=" + studentId ;
        ResultSet resultSet = stmt.executeQuery(queryString);
        while (resultSet.next()) {
            StudentSubjectInfo ssi = new StudentSubjectInfo(
                resultSet.getInt("id"),
                course,
                subjectRep.fetch(resultSet.getInt("subject_id") ),
                student
            );
            ssi.setGrade(resultSet.getInt("grade"));
            ssiList.add(ssi);
        }
        resultSet.close();
        stmt.close();
        closeConnection(_conn);
        return ssiList;
    }
}
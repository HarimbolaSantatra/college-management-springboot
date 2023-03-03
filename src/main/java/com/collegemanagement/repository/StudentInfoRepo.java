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
import java.util.Map;

import com.collegemanagement.model.*;

public class StudentInfoRepo extends BaseRepository{
    private Connection _conn = null;

	public StudentInfo fetchStudentInfo(int studentId) throws SQLException {
        StudentSubjectInfoRepo ssiRep = new StudentSubjectInfoRepo();
        List<StudentSubjectInfo> ssi = ssiRep.fetchStudentSSI(studentId);
        StudentInfo studInfo = new StudentInfo(ssi.get(0).id, ssi.get(0).student);
        studInfo.setStudentSubjectInfo(ssi);
        return studInfo;
    }
    
    public void editStudentInfo(int studentId, Map<String, String> subjectNotePair) throws SQLException {
        _conn = openConnection();
        Statement stmt = _conn.createStatement();
        
        SubjectRepo subjectRepo = new SubjectRepo();
        Subject subject;
        String sqlString;
        int grade;
        String colName;
        for(Map.Entry pair : subjectNotePair.entrySet()){
            grade = Integer.parseInt((String) pair.getValue());
            colName = (String) pair.getKey();
            subject = subjectRepo.fetchByName(colName);
            sqlString = "UPDATE student_subject_info SET grade=" + grade 
            + " WHERE student_id=" + studentId
            + " AND subject_id=" + subject.id ;
            stmt.executeUpdate(sqlString);
        }
        
        stmt.close();
        closeConnection(_conn);
    }

}
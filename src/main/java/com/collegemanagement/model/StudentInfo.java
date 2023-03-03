package com.collegemanagement.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import java.sql.SQLException;

import com.collegemanagement.repository.StudentSubjectInfoRepo;
import com.collegemanagement.repository.CourseRepo;

public class StudentInfo{
    public int id;
    public Student student;
    public Course course;
    public List<StudentSubjectInfo> studentSubjectInfos;

    public StudentInfo(int id, Student student) throws SQLException{
        this.id = id;
        this.student = student;
        CourseRepo courseRepo = new CourseRepo();
        this.course = courseRepo.fetch(student.course.id);
    }

    public void setStudentSubjectInfo(List<StudentSubjectInfo> studentSubjectInfos){
        this.studentSubjectInfos = studentSubjectInfos;
    }

    public float getAverageGrade(){
        return StudentSubjectInfo.computeAverageGrade(this.studentSubjectInfos);
    }

    
}

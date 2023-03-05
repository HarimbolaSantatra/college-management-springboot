package com.collegemanagement.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

public class CourseInfo{
    public int id;
    public Course course;
    public List<Subject> subjects;
    public List<StudentInfo> studentInfos;

    public CourseInfo(int id, Course course){
        this.id = id;
        this.course = course;
    }

    public void setStudentInfos(List<StudentInfo> studentInfos){
        this.studentInfos = studentInfos;
    }

    public void setSubjectList(List<Subject> subjects){
        this.subjects = subjects;
    }
}

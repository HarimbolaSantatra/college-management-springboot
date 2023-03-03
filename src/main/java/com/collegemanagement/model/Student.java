package com.collegemanagement.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.collegemanagement.controller.HomeController;
import com.collegemanagement.repository.StudentRepo;

public class Student{
    public int id;
    public String firstName, lastName;
    public Course course;
    public Student(int _id, String _firstName, String _lastName){
        this.id = _id;
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.course = null;
    }

    public void setCourse(Course _course){
        this.course = _course;
    }
    
    public String fullName(){
        return this.firstName + " " + this.lastName;
    }

    public String getCourseName(){
        return this.course.name;
    }
}
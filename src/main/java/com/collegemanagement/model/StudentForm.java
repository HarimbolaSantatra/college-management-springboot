package com.collegemanagement.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.collegemanagement.controller.HomeController;
import com.collegemanagement.repository.StudentRepo;

public class StudentForm{
    public String firstName, lastName;
    public int courseId;
    public void setParam(String _firstName, String _lastName, int _courseId){
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.courseId = _courseId;
    }
}
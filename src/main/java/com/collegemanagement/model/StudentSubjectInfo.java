package com.collegemanagement.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.collegemanagement.controller.HomeController;
import com.collegemanagement.repository.StudentRepo;

public class StudentSubjectInfo{
    public int id;
    public Subject subject;
    public Student student;
    public Course course;
    public int grade, finalGrade;

    public StudentSubjectInfo(int id, Course course, Subject subject, Student student){
        this.id = id;
        this.course = course;
        this.subject = subject;
        this.student = student;
    }

    public void setGrade(int initialGrade){
        grade = initialGrade;
        finalGrade = initialGrade * this.subject.credit;
        this.grade = grade;
        this.finalGrade = finalGrade;
    }
    public int getGrade(){
        return grade;
    }
    public int getFinalGrade(){
        return finalGrade;
    }

    public static float computeAverageGrade(List<StudentSubjectInfo> ssiList){
        int sum = 0;
        int totalCredit = 0;
        for(StudentSubjectInfo ssi : ssiList){
            sum += ssi.getFinalGrade();
            totalCredit += ssi.subject.credit;
        }
        return (float) sum / (float) totalCredit;
    }

    

}

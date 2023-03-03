package com.collegemanagement.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import java.util.Map;

import java.sql.SQLException;

import com.collegemanagement.model.*;
import com.collegemanagement.repository.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam; // For @RequestParam

@Controller
public class StudentController{
    
    @GetMapping("/student")
    public String index(Model studentModel) throws SQLException {
        StudentRepo sRepo = new StudentRepo();
        List<Student> students = sRepo.fetchAll();
        studentModel.addAttribute("studentList", students);
        return "student/student";
    }

    @GetMapping("/student-info")
    public String studentInfo(@RequestParam int id, Model model) throws SQLException {
        // Get StudentInfos | id is the student's id
        StudentInfoRepo studentInfoRepo = new StudentInfoRepo();
        StudentInfo studentInfo = studentInfoRepo.fetchStudentInfo(id);
        model.addAttribute("studentInfo", studentInfo);
        return "student/info";
    }

    // Page: "Add student"
    @GetMapping("/student-add")
    public String addStudent(Model model) throws SQLException {
        CourseRepo rep = new CourseRepo();
        List<Course> courseList = rep.fetchAll();
        model.addAttribute("courseList", courseList);
        return "student/add";
    }

    // Submit add student
    @PostMapping("/student-add")
    public String submit_addStudent(
    @RequestParam("firstName") String firstName,
    @RequestParam("lastName") String lastName,
    @RequestParam("course") int courseId
    ) throws SQLException {
        StudentRepo sRepo = new StudentRepo();
        sRepo.create(
            firstName, lastName, courseId
        );
        // sRepo.autoPopulateInfo();
        return "redirect:student";
    }

    // Edit Student Personnal
    @GetMapping("/student-edit-personnal")
    public String editStudentPersonnal(@RequestParam int id, Model model) throws SQLException {
        StudentRepo sRepo = new StudentRepo();
        Student student = sRepo.fetch(id);
        model.addAttribute("student", student);
        CourseRepo cRepo = new CourseRepo();
        List<Course> courseList = cRepo.fetchAll();
        model.addAttribute("courseList", courseList);
        return "student/edit-personnal";
    }

    // Submit Edit Student
    @PostMapping("/student-edit-personnal")
    public String submit_editStudentPersonnal(
    @RequestParam("studentId") int studentId,
    @RequestParam("firstName") String firstName,
    @RequestParam("lastName") String lastName,
    @RequestParam("course") int courseId,
    Model model
    ) throws SQLException {
        StudentRepo sRepo = new StudentRepo();
        sRepo.edit(studentId, firstName, lastName, courseId);
        return "redirect:/student-info?id=" + studentId;
    }

    // Edit Student Notes
    @GetMapping("/student-edit-notes")
    public String editStudentNotes(
    @RequestParam int id,
    Model model)
    throws SQLException {
        // Get StudentInfos | id is the student's id
        StudentInfoRepo studentInfoRepo = new StudentInfoRepo();
        StudentInfo studentInfo = studentInfoRepo.fetchStudentInfo(id);
        model.addAttribute("studentInfo", studentInfo);
        return "student/edit-notes";
    }

    // Submit Edit Student
    @PostMapping("/student-edit-notes")
    public String submit_editStudentNotes(
    Model model,
    @RequestParam Map<String, String> allParam
    ) throws SQLException {
        int studentId = Integer.parseInt(allParam.get("studentId"));
        allParam.remove("studentId");
        StudentInfoRepo studentInfoRepo = new StudentInfoRepo();
        studentInfoRepo.editStudentInfo(studentId, allParam);
        return "redirect:/student-info?id=" + studentId;
    }
}

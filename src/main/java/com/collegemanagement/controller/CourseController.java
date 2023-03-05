package com.collegemanagement.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import java.sql.SQLException;

import com.collegemanagement.model.Course;
import com.collegemanagement.model.Subject;
import com.collegemanagement.model.Student;
import com.collegemanagement.model.StudentSubjectInfo;
import com.collegemanagement.repository.CourseRepo;
import com.collegemanagement.repository.StudentRepo;
import com.collegemanagement.repository.SubjectRepo;
import com.collegemanagement.repository.StudentSubjectInfoRepo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourseController{
    
    @GetMapping("/course")
    public String index(Model model) throws SQLException {
        CourseRepo repo = new CourseRepo();
        List<Course> courses = repo.fetchAll();
        model.addAttribute("courseList", courses);
        return "course/course";
    }

    @GetMapping("/course-info")
    public String courseInfo(@RequestParam int id, Model model) throws SQLException {
        CourseRepo rep = new CourseRepo();
        Course course = rep.fetch(id);
        model.addAttribute("course", course);

        // Fetch student List
        StudentRepo studRep = new StudentRepo();
        List<Student> studentList = studRep.fetchByCourse(id);
        model.addAttribute("studentList", studentList);

        // Fetch Subject List
        SubjectRepo subjRep = new SubjectRepo();
        List<Subject> subjectList = subjRep.fetchByCourse(id);
        model.addAttribute("subjectList", subjectList);

        return "course/info";
    }

}
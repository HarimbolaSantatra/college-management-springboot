package com.collegemanagement.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import java.sql.SQLException;

import com.collegemanagement.model.Subject;
import com.collegemanagement.model.Course;
import com.collegemanagement.repository.CourseRepo;
import com.collegemanagement.repository.SubjectRepo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubjectController{
    
    @GetMapping("/subject")
    public String index(Model subjectModel) throws SQLException {
        SubjectRepo sRepo = new SubjectRepo();
        List<Subject> subjects = sRepo.fetchAll();
        subjectModel.addAttribute("subjectList", subjects);
        return "subject/subject";
    }

    @GetMapping("/subject-info")
    public String index(@RequestParam int id, Model model) throws SQLException {
        SubjectRepo sRepo = new SubjectRepo();
        Subject subject = sRepo.fetch(id);
        model.addAttribute("subject", subject);

        // Get list of course that contains this subject
        CourseRepo course_repo = new CourseRepo();
        List<Course> courseList = course_repo.fetchBySubject(id);
        model.addAttribute("courseList", courseList);
        return "subject/info";
    }

}
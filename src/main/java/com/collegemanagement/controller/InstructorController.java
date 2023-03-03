package com.collegemanagement.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import java.sql.SQLException;

import com.collegemanagement.model.*;
import com.collegemanagement.repository.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestParam; // Pour @RequestParam

@Controller
public class InstructorController{
    
    @GetMapping("/instructor")
    public String index(Model model) throws SQLException {
        InstructorRepo rep = new InstructorRepo();
        List<Instructor> instructors = rep.fetchAll();
        model.addAttribute("instructors", instructors);       
        return "instructor/instructor";
    }

    @GetMapping("/instructor-info")
    public String instructorInfo(@RequestParam int id, Model model) throws SQLException {

        // Instructor
        InstructorRepo rep = new InstructorRepo();
        Instructor instructor = rep.fetch(id);
        model.addAttribute("instructor", instructor);

        // fetch subject taught
        SubjectRepo subjectRep = new SubjectRepo();
        List<Subject> subjectTaught = subjectRep.fetchByInstructor(id);
        model.addAttribute("subjectTaught", subjectTaught);

        return "instructor/info";
    }
}

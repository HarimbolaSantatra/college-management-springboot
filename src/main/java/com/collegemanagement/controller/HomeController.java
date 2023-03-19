package com.collegemanagement.controller;

import com.collegemanagement.repository.*;
import com.collegemanagement.model.*;
import com.collegemanagement.utils.PdfGenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// PdfGenerator
import java.io.IOException;
import com.lowagie.text.DocumentException;

@Controller
public class HomeController{
    
    @GetMapping("/")
    public String index() throws SQLException {
        return "index";
    }

    @PostMapping("/pdf/course-info")
    public @ResponseBody String exportPdf(
        @RequestParam int courseId
    ) throws DocumentException, IOException, SQLException{

        CourseInfoRepo courseInfoRepo = new CourseInfoRepo();
        CourseInfo courseInfo = courseInfoRepo.fetch(courseId);
        // PdfGenerator pdfGenerator = new PdfGenerator();
        // pdfGenerator.generateCourseInfoTable(courseInfo);

        return "redirect:/course";
    }

}

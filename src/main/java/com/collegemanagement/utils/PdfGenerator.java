package com.collegemanagement.utils;

import com.collegemanagement.model.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import java.io.IOException;
import java.io.FileOutputStream; // To return a file

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfGenerator{

    public void generateCourseInfoTable(
        CourseInfo courseInfo) 
    throws DocumentException, IOException {

        // Create document
        Document document = new Document(PageSize.A4);

        // Create a FileOutputStream object with the path and name of the file
        FileOutputStream pdfOutputFile = new FileOutputStream(
            "./src/main/resources/file_output/course-info.pdf");

        // Create a file writer instance from the class
        final PdfWriter pdfWriter = PdfWriter.getInstance(document, pdfOutputFile);

        // Open document to modify it
        document.open();

        // Create font
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);
        Font textFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);

        // Create paragraph; align it; add it to document
        Paragraph paragraph = new Paragraph("Course Information", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(paragraph);

        // Create subject table and student table
        PdfPTable subjectTable = new PdfPTable(3); // colums nbr in parenthesis
        PdfPTable studentTable = new PdfPTable(2);
        subjectTable.setWidthPercentage(100f);
        subjectTable.setWidths(new int[]{3,3,3});
        subjectTable.setSpacingBefore(5);

        // Create cell for table header and set padding
        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);

        // Add cell to table
        cell.setPhrase(new Phrase("Name", textFont));
        subjectTable.addCell(cell);
        cell.setPhrase(new Phrase("Credit", textFont));
        subjectTable.addCell(cell);
        cell.setPhrase(new Phrase("Instructor", textFont));
        subjectTable.addCell(cell);

        // Iterating over the list of subject
        for(Subject subject : courseInfo.subjects){
            subjectTable.addCell(String.valueOf(subject.name));
            subjectTable.addCell(String.valueOf(subject.credit));
            subjectTable.addCell(String.valueOf(subject.instructor.name));
        }
        document.add(subjectTable);

        document.close();
        pdfWriter.close();
    }
}
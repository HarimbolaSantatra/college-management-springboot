package com.collegemanagement.repository;

import com.collegemanagement.model.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class CourseInfoRepo extends BaseRepository{
	private Connection _conn = null;

	public CourseInfo fetch(int id) throws SQLException {
        _conn = openConnection();

		// Fetch Course
		CourseRepo rep = new CourseRepo();
        Course course = rep.fetch(id);

		// Fetch Subject List
        SubjectRepo subjRep = new SubjectRepo();
        List<Subject> subjectList = subjRep.fetchByCourse(id);

		// Fetch student list
        StudentRepo studRep = new StudentRepo();
        List<Student> students = studRep.fetchByCourse(id);

		// Fetch StudentInfo list
		StudentInfoRepo studentInfoRepo = new StudentInfoRepo();
		List<StudentInfo> studentInfos = new ArrayList<>();
		for(Student student : students){
			studentInfos.add( studentInfoRepo.fetch(student.id) );
		}

		CourseInfo courseInfo = new CourseInfo(course.id, course);
		courseInfo.setStudentInfos(studentInfos);
		courseInfo.setSubjectList(subjectList);

		return courseInfo;
    }
}
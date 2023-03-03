package com.collegemanagement.model;

import java.util.List;
import java.util.ArrayList;

public class Course{
    public int id;
    public String name;
    public List<Subject> subjects;
    public List<Student> students;

    public Course(int _id, String _name){
        this.id = _id;
        this.name = _name;
    }
}
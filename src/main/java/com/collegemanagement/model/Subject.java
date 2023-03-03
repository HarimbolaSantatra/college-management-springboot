package com.collegemanagement.model;

import java.util.List;
import java.util.ArrayList;

public class Subject{
    public int id, credit;
    public String name;
    public Instructor instructor;

    public Subject(int _id, String _name, int _credit, Instructor _instructor){
        this.id = _id;
        this.name = _name;
        this.credit = _credit;
        this.instructor = _instructor;
    }

}
package com.example.gpacalculator.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String studentNumber;
    private List<Double> gpas; // Tüm dönem GPA'ları
    private Double cumulativeGpa; // Genel ortalama
    private List<CourseGradeInfo> courseGrades;

    public Student() {
        this.gpas = new ArrayList<>();
        this.courseGrades = new ArrayList<>();
    }

    public Student(Long id, String name, String surname, String email, String studentNumber, List<Double> gpas, Double cumulativeGpa) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.studentNumber = studentNumber;
        this.gpas = gpas;
        this.cumulativeGpa = cumulativeGpa;
        this.courseGrades = new ArrayList<>();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getStudentNumber() { return studentNumber; }
    public void setStudentNumber(String studentNumber) { this.studentNumber = studentNumber; }
    public List<Double> getGpas() { return gpas; }
    public void setGpas(List<Double> gpas) { this.gpas = gpas; }
    public Double getCumulativeGpa() { return cumulativeGpa; }
    public void setCumulativeGpa(Double cumulativeGpa) { this.cumulativeGpa = cumulativeGpa; }
    public List<CourseGradeInfo> getCourseGrades() { return courseGrades; }
    public void setCourseGrades(List<CourseGradeInfo> courseGrades) { this.courseGrades = courseGrades; }
} 
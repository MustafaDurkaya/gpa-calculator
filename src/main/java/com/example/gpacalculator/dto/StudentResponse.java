package com.example.gpacalculator.dto;

import java.util.List;
import com.example.gpacalculator.model.CourseGradeInfo;

public class StudentResponse {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String studentNumber;
    private Double cumulativeGpa;
    private Integer totalCourses;
    private List<CourseGradeInfo> courseGrades;

    public StudentResponse() {}

    public StudentResponse(Long id, String name, String surname, String email, String studentNumber, Double cumulativeGpa, Integer totalCourses) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.studentNumber = studentNumber;
        this.cumulativeGpa = cumulativeGpa;
        this.totalCourses = totalCourses;
        this.courseGrades = null;
    }

    public StudentResponse(Long id, String name, String surname, String email, String studentNumber, Double cumulativeGpa, Integer totalCourses, List<CourseGradeInfo> courseGrades) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.studentNumber = studentNumber;
        this.cumulativeGpa = cumulativeGpa;
        this.totalCourses = totalCourses;
        this.courseGrades = courseGrades;
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
    public Double getCumulativeGpa() { return cumulativeGpa; }
    public void setCumulativeGpa(Double cumulativeGpa) { this.cumulativeGpa = cumulativeGpa; }
    public Integer getTotalCourses() { return totalCourses; }
    public void setTotalCourses(Integer totalCourses) { this.totalCourses = totalCourses; }
    public List<CourseGradeInfo> getCourseGrades() { return courseGrades; }
    public void setCourseGrades(List<CourseGradeInfo> courseGrades) { this.courseGrades = courseGrades; }
} 
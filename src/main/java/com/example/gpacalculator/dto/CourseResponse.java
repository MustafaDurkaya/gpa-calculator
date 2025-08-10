package com.example.gpacalculator.dto;

public class CourseResponse {
    private String courseName;

    public CourseResponse() {}

    public CourseResponse(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
} 
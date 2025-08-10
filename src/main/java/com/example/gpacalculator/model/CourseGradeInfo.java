package com.example.gpacalculator.model;

public class CourseGradeInfo {
    private String courseName;
    private Double finalScore;
    private String grade;
    private Double gpa;

    public CourseGradeInfo() {}

    public CourseGradeInfo(String courseName, Double finalScore, String grade, Double gpa) {
        this.courseName = courseName;
        this.finalScore = finalScore;
        this.grade = grade;
        this.gpa = gpa;
    }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public Double getFinalScore() { return finalScore; }
    public void setFinalScore(Double finalScore) { this.finalScore = finalScore; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    public Double getGpa() { return gpa; }
    public void setGpa(Double gpa) { this.gpa = gpa; }
} 
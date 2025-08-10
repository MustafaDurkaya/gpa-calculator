package com.example.gpacalculator.dto;

public class CalculateGpaResponse {
    private String name;
    private String surname;
    private String course;
    private Double midterm1;
    private Double midterm2;
    private Double homework;
    private Double finalExam;
    private Double finalScore;  // Hesaplanan final puanı
    private String grade;       // Harf notu (AA, BA, BB, vb.)
    private Double gpa;         // GPA değeri (4.00, 3.50, vb.)

    public CalculateGpaResponse() {}

    public CalculateGpaResponse(String name, String surname, String course, Double midterm1, Double midterm2, Double homework, Double finalExam, Double finalScore, String grade, Double gpa) {
        this.name = name;
        this.surname = surname;
        this.course = course;
        this.midterm1 = midterm1;
        this.midterm2 = midterm2;
        this.homework = homework;
        this.finalExam = finalExam;
        this.finalScore = finalScore;
        this.grade = grade;
        this.gpa = gpa;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public Double getMidterm1() { return midterm1; }
    public void setMidterm1(Double midterm1) { this.midterm1 = midterm1; }
    public Double getMidterm2() { return midterm2; }
    public void setMidterm2(Double midterm2) { this.midterm2 = midterm2; }
    public Double getHomework() { return homework; }
    public void setHomework(Double homework) { this.homework = homework; }
    public Double getFinalExam() { return finalExam; }
    public void setFinalExam(Double finalExam) { this.finalExam = finalExam; }
    public Double getFinalScore() { return finalScore; }
    public void setFinalScore(Double finalScore) { this.finalScore = finalScore; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    public Double getGpa() { return gpa; }
    public void setGpa(Double gpa) { this.gpa = gpa; }
} 
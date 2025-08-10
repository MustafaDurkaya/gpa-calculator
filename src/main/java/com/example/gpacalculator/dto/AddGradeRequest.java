package com.example.gpacalculator.dto;

import jakarta.validation.constraints.*;

public class AddGradeRequest {
    @NotBlank
    private String course;
    @NotNull @Min(0) @Max(100)
    private Double midterm1;
    @NotNull @Min(0) @Max(100)
    private Double midterm2;
    @NotNull @Min(0) @Max(100)
    private Double homework;
    @NotNull @Min(0) @Max(100)
    private Double finalExam;

    public AddGradeRequest() {}

    public AddGradeRequest(String course, Double midterm1, Double midterm2, Double homework, Double finalExam) {
        this.course = course;
        this.midterm1 = midterm1;
        this.midterm2 = midterm2;
        this.homework = homework;
        this.finalExam = finalExam;
    }

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
} 
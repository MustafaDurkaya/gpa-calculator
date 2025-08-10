package com.example.gpacalculator.dto;

import jakarta.validation.constraints.*;

public class StudentRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String studentNumber;

    public StudentRequest() {}

    public StudentRequest(String name, String surname, String email, String studentNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.studentNumber = studentNumber;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getStudentNumber() { return studentNumber; }
    public void setStudentNumber(String studentNumber) { this.studentNumber = studentNumber; }
} 
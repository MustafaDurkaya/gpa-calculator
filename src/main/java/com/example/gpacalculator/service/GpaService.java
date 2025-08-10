package com.example.gpacalculator.service;

import org.springframework.stereotype.Service;

@Service
public class GpaService {
    public double calculateFinalScore(Double midterm1, Double midterm2, Double homework, Double finalExam) {
        return (midterm1 * 0.20) + (midterm2 * 0.20) + (homework * 0.20) + (finalExam * 0.40);
    }

    public String getLetterGrade(double score) {
        if (score >= 90) return "AA";
        if (score >= 85) return "BA";
        if (score >= 80) return "BB";
        if (score >= 75) return "CB";
        if (score >= 70) return "CC";
        if (score >= 65) return "DC";
        if (score >= 60) return "DD";
        if (score >= 50) return "FD";
        return "FF";
    }

    public double getGpaValue(String grade) {
        return switch (grade) {
            case "AA" -> 4.00;
            case "BA" -> 3.50;
            case "BB" -> 3.00;
            case "CB" -> 2.50;
            case "CC" -> 2.00;
            case "DC" -> 1.50;
            case "DD" -> 1.00;
            case "FD" -> 0.50;
            default -> 0.00;
        };
    }

    public double round(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
} 
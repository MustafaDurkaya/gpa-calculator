package com.example.gpacalculator.service;

import com.example.gpacalculator.model.CourseGradeInfo;
import com.example.gpacalculator.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private Long studentIdCounter = 1L;

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Optional<Student> getStudentById(Long id) {
        return students.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    public boolean existsByStudentNumber(String studentNumber) {
        return students.stream().anyMatch(s -> s.getStudentNumber().equals(studentNumber));
    }

    public boolean existsByStudentNumberExceptId(String studentNumber, Long id) {
        return students.stream().anyMatch(s -> !s.getId().equals(id) && s.getStudentNumber().equals(studentNumber));
    }

    public Student addStudent(String name, String surname, String email, String studentNumber) {
        Student student = new Student(studentIdCounter++, name, surname, email, studentNumber, new ArrayList<>(), 0.0);
        students.add(student);
        return student;
    }

    public Optional<Student> updateStudent(Long id, String name, String surname, String email, String studentNumber) {
        Optional<Student> opt = getStudentById(id);
        opt.ifPresent(s -> {
            s.setName(name);
            s.setSurname(surname);
            s.setEmail(email);
            s.setStudentNumber(studentNumber);
        });
        return opt;
    }

    public boolean deleteStudent(Long id) {
        return students.removeIf(s -> s.getId().equals(id));
    }

    public List<Student> getTopPerformers(int limit) {
        List<Student> sorted = new ArrayList<>(students);
        sorted.sort((a, b) -> Double.compare(b.getCumulativeGpa(), a.getCumulativeGpa()));
        return sorted.subList(0, Math.min(limit, sorted.size()));
    }

    public void addGpaToStudent(Long id, double gpa) {
        getStudentById(id).ifPresent(s -> {
            s.getGpas().add(gpa);
            // Cumulative GPA güncelle
            double sum = s.getGpas().stream().mapToDouble(Double::doubleValue).sum();
            s.setCumulativeGpa(s.getGpas().isEmpty() ? 0.0 : sum / s.getGpas().size());
        });
    }

    public void addCourseGrade(Long studentId, String course, double finalScore, String grade, double gpa) {
        getStudentById(studentId).ifPresent(s -> {
            s.getCourseGrades().add(new CourseGradeInfo(course, finalScore, grade, gpa));
            s.getGpas().add(gpa);
            double sum = s.getGpas().stream().mapToDouble(Double::doubleValue).sum();
            s.setCumulativeGpa(s.getGpas().isEmpty() ? 0.0 : sum / s.getGpas().size());
        });
    }
} 
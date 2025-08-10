package com.example.gpacalculator.controller;

import com.example.gpacalculator.dto.CalculateGpaRequest;
import com.example.gpacalculator.dto.CalculateGpaResponse;
import com.example.gpacalculator.dto.StudentRequest;
import com.example.gpacalculator.dto.StudentResponse;
import com.example.gpacalculator.dto.AddGradeRequest;
import com.example.gpacalculator.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.gpacalculator.service.GpaService;
import com.example.gpacalculator.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api")
public class GpaController {
    private final GpaService gpaService;
    private final StudentService studentService;

    @Autowired
    public GpaController(GpaService gpaService, StudentService studentService) {
        this.gpaService = gpaService;
        this.studentService = studentService;
    }

    // Statik veri için liste tanımlamaları
    private static List<String> courses = Arrays.asList(
            "Calculus I", "Calculus II", "Programming I", "Programming II",
            "Data Structures", "Database Systems", "Software Engineering",
            "Computer Networks", "Operating Systems", "Algorithms"
    );

    // Ders Listesi Endpoint'i
    @GetMapping("/courses")
    public ResponseEntity<List<String>> getCourses() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(courses);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList("Ders listesi alınırken hata oluştu."));
        }
    }

    // GPA Hesaplama Endpoint'i
    @PostMapping("/gpa/calculate")
    //@RequestMapping(value = "/gpa/calculate", method = RequestMethod.POST)
    public ResponseEntity<?> calculateGpa(@Valid @RequestBody CalculateGpaRequest request) {
        // Ders adı kontrolü
        try {
            if (!courses.contains(request.getCourse())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "Geçersiz ders adı."));
            }
            // Not validasyonu (0-100 arası ve null kontrolü zaten @Valid ile sağlanıyor)
            double finalScore = gpaService.calculateFinalScore(request.getMidterm1(), request.getMidterm2(), request.getHomework(), request.getFinalExam());
            String grade = gpaService.getLetterGrade(finalScore);
            double gpa = gpaService.getGpaValue(grade);
            CalculateGpaResponse response = new CalculateGpaResponse(
                    request.getName(),
                    request.getSurname(),
                    request.getCourse(),
                    request.getMidterm1(),
                    request.getMidterm2(),
                    request.getHomework(),
                    request.getFinalExam(),
                    gpaService.round(finalScore, 1),
                    grade,
                    gpa
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "GPA hesaplama sırasında hata oluştu."));
        }
    }

    // Öğrenci Kaydetme
    @PostMapping("/students")
    public ResponseEntity<?> addStudent(@Valid @RequestBody StudentRequest request) {
        try {
            if (studentService.existsByStudentNumber(request.getStudentNumber())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("error", "Bu öğrenci numarası zaten kayıtlı."));
            }
            Student student = studentService.addStudent(request.getName(), request.getSurname(), request.getEmail(), request.getStudentNumber());
            StudentResponse response = new StudentResponse(
                    student.getId(), student.getName(), student.getSurname(), student.getEmail(), student.getStudentNumber(), student.getCumulativeGpa(), 0
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Öğrenci kaydı sırasında hata oluştu."));
        }
    }

    // Tüm Öğrencileri Listeleme
    @GetMapping("/students")
    public List<StudentResponse> getAllStudents() {
        try {
            List<StudentResponse> list = new ArrayList<>();
            for (Student s : studentService.getAllStudents()) {
                list.add(new StudentResponse(
                        s.getId(), s.getName(), s.getSurname(), s.getEmail(), s.getStudentNumber(), s.getCumulativeGpa(), s.getCourseGrades() != null ? s.getCourseGrades().size() : 0, s.getCourseGrades()
                ));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            // Hata durumunda courseGrades null ver
            return Collections.singletonList(new StudentResponse(-1L, "Hata", "Hata", "Hata", "Hata", 0.0, 0, null));
        }
    }

    // Öğrenci Detayı
    @GetMapping("/students/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id) {
        try {
            Optional<Student> opt = studentService.getStudentById(id);
            if (opt.isPresent()) {
                Student s = opt.get();
                return ResponseEntity.ok().body(new StudentResponse(
                        s.getId(), s.getName(), s.getSurname(), s.getEmail(), s.getStudentNumber(), s.getCumulativeGpa(), s.getCourseGrades() != null ? s.getCourseGrades().size() : 0, s.getCourseGrades()
                ));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Öğrenci bulunamadı."));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Öğrenci bilgileri alınırken hata oluştu."));
        }
    }

    // Öğrenci Silme
    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        try {
            boolean deleted = studentService.deleteStudent(id);
            if (deleted) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Öğrenci bulunamadı."));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Öğrenci silinirken hata oluştu."));
        }
    }

    // Öğrenci Güncelleme
    @PutMapping("/students/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequest request) {
        try {
            if (studentService.existsByStudentNumberExceptId(request.getStudentNumber(), id)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("error", "Bu öğrenci numarası başka bir öğrenciye ait."));
            }
            Optional<Student> opt = studentService.updateStudent(id, request.getName(), request.getSurname(), request.getEmail(), request.getStudentNumber());
            if (opt.isPresent()) {
                Student s = opt.get();
                return ResponseEntity.ok().body(new StudentResponse(
                        s.getId(), s.getName(), s.getSurname(), s.getEmail(), s.getStudentNumber(), s.getCumulativeGpa(), s.getCourseGrades() != null ? s.getCourseGrades().size() : 0, s.getCourseGrades()
                ));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Öğrenci bulunamadı."));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Öğrenci güncellenirken hata oluştu."));
        }

    }

    // En Yüksek GPA'lı Öğrenciler
    @GetMapping("/students/top-performers")
    public List<StudentResponse> getTopPerformers(@RequestParam(defaultValue = "5") int limit) {
        try {
            List<StudentResponse> result = new ArrayList<>();
            for (Student s : studentService.getTopPerformers(limit)) {
                result.add(new StudentResponse(
                        s.getId(), s.getName(), s.getSurname(), s.getEmail(), s.getStudentNumber(), s.getCumulativeGpa(), s.getCourseGrades() != null ? s.getCourseGrades().size() : 0, s.getCourseGrades()
                ));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            // Hata durumunda courseGrades null ver
            return Collections.singletonList(new StudentResponse(-1L, "Hata", "Hata", "Hata", "Hata", 0.0, 0, null));
        }
    }

    // Öğrenciye ders notu ekleme
    @PostMapping("/students/{id}/grades")
    public ResponseEntity<?> addCourseGrade(@PathVariable Long id, @Valid @RequestBody AddGradeRequest request) {
        // Öğrenci var mı?
        try {
            Optional<Student> opt = studentService.getStudentById(id);
            if (!opt.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Öğrenci bulunamadı."));
            }
            // Ders adı kontrolü
            if (!courses.contains(request.getCourse())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "Geçersiz ders adı."));
            }
            // Not validasyonu zaten @Valid ile sağlanıyor
            double finalScore = gpaService.calculateFinalScore(request.getMidterm1(), request.getMidterm2(), request.getHomework(), request.getFinalExam());
            String grade = gpaService.getLetterGrade(finalScore);
            double gpa = gpaService.getGpaValue(grade);
            studentService.addCourseGrade(id, request.getCourse(), gpaService.round(finalScore, 1), grade, gpa);
            Student s = studentService.getStudentById(id).get();
            StudentResponse response = new StudentResponse(
                    s.getId(), s.getName(), s.getSurname(), s.getEmail(), s.getStudentNumber(), s.getCumulativeGpa(), s.getCourseGrades() != null ? s.getCourseGrades().size() : 0, s.getCourseGrades()
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Ders notu eklenirken hata oluştu."));
        }
    }
} 
# 🎓 GPA Calculator API

Bu proje, Spring Boot ile yazılmış bir REST API örneğidir. Öğrenci, ders ve not işlemlerini yönetir. Kodun içinde geçen önemli kavramlar ve açıklamaları aşağıda bulabilirsin.

---

## 📖 Kodda Sıkça Geçen Kavramlar ve Açıklamaları

### 1. `@Valid`
- **Açıklama:**
  - Bir request (istek) objesinin alanlarının doğrulanmasını (validasyonunu) sağlar.
  - Örneğin: Bir öğrencinin email adresi boş veya hatalıysa, otomatik olarak hata döner.
- **Kullanım:**
  ```java
  public ResponseEntity<?> addStudent(@Valid @RequestBody StudentRequest request) { ... }
  ```
- **Detay:**
  - `@NotBlank`, `@Email`, `@Min`, `@Max` gibi anotasyonlarla birlikte kullanılır.

### 2. DTO (Data Transfer Object)
- **Açıklama:**
  - API'ye gelen/giden veriyi taşımak için kullanılan sade veri sınıflarıdır.
  - Modelden farklı olarak, sadece API'ye özel alanları içerir.
- **Örnek:**
  - `StudentRequest`, `StudentResponse`, `CalculateGpaRequest`, `AddGradeRequest` gibi.

### 3. Model
- **Açıklama:**
  - Sistemin ana iş nesnelerini (ör: Student, CourseGradeInfo) temsil eder.
  - Genellikle veritabanı veya iş mantığı ile ilgilidir.

### 4. Service
- **Açıklama:**
  - İş mantığının (business logic) yazıldığı katmandır.
  - Controller, servisleri çağırır. Servisler model üzerinde işlem yapar.
- **Örnek:**
  - `StudentService`, `GpaService`

### 5. Controller
- **Açıklama:**
  - API endpoint'lerinin tanımlandığı yerdir.
  - HTTP isteklerini alır, validasyon yapar, servislere yönlendirir ve cevap döner.
- **Örnek:**
  - `GpaController`

### 6. `ResponseEntity`
- **Açıklama:**
  - API'den dönen HTTP cevabını ve durum kodunu (ör: 200, 400, 404) sarmalar.
  - Hatalı isteklerde uygun hata mesajı ve kodu döner.
- **Örnek:**
  ```java
  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Geçersiz veri"));
  ```

### 7. Swagger / OpenAPI
- **Açıklama:**
  - API'nin otomatik dokümantasyonunu ve test arayüzünü sağlar.
  - Projede springdoc-openapi ile entegre edilmiştir.
- **Kullanım:**
  - Uygulama çalışırken: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### 8. `@RestController`, `@Service`, `@Configuration`
- **Açıklama:**
  - Spring'e bu sınıfların özel bir rolü olduğunu söyler.
  - `@RestController`: HTTP endpoint'leri içerir.
  - `@Service`: İş mantığı içerir.
  - `@Configuration`: Konfigürasyon içerir (ör: Swagger ayarı).

### 9. `@RequestBody`, `@PathVariable`, `@RequestParam`
- **Açıklama:**
  - API'ye gelen verinin nasıl alınacağını belirtir.
  - `@RequestBody`: JSON body'den veri alır.
  - `@PathVariable`: URL'den veri alır (ör: /students/{id}).
  - `@RequestParam`: Sorgu parametresinden veri alır (ör: ?limit=5).

### 10. Error Handling (Hata Yönetimi)
- **Açıklama:**
  - Hatalı isteklerde uygun HTTP kodu ve mesajı döner.
  - GlobalExceptionHandler ile merkezi hata yönetimi yapılır.

---

## 🧑‍💻 Kodda Örnek Kullanımlar

- **Öğrenci eklerken validasyon:**
  ```java
  public ResponseEntity<?> addStudent(@Valid @RequestBody StudentRequest request) { ... }
  ```
- **Bir endpoint tanımı:**
  ```java
  @GetMapping("/students/{id}")
  public ResponseEntity<?> getStudent(@PathVariable Long id) { ... }
  ```
- **Bir servisi controller'da kullanmak:**
  ```java
  @Autowired
  private StudentService studentService;
  ```

---

package com.example.courseregistration.controller;

import com.example.courseregistration.dto.EnrollmentRequestDTO;
import com.example.courseregistration.dto.EnrollmentResponseDTO;
import com.example.courseregistration.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;


    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/register")
    public ResponseEntity<EnrollmentResponseDTO> registerStudent(@Valid @RequestBody EnrollmentRequestDTO enrollmentRequestDTO) {
        EnrollmentResponseDTO response = enrollmentService.registerStudent(enrollmentRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/drop/{enrollmentId}")
    public ResponseEntity<EnrollmentResponseDTO> dropCourse(@PathVariable Long enrollmentId) {
        EnrollmentResponseDTO response = enrollmentService.dropCourse(enrollmentId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDTO>> getAllEnrollments() {
        List<EnrollmentResponseDTO> enrollments = enrollmentService.getAllEnrollments();
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentResponseDTO>> getEnrollmentsByStudent(@PathVariable Long studentId) {
        List<EnrollmentResponseDTO> enrollments = enrollmentService.getEnrollmentsByStudent(studentId);
        return ResponseEntity.ok(enrollments);
    }
    

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentResponseDTO>> getEnrollmentsByCourse(@PathVariable Long courseId) {
        List<EnrollmentResponseDTO> enrollments = enrollmentService.getEnrollmentsByCourse(courseId);
        return ResponseEntity.ok(enrollments);
    }
}

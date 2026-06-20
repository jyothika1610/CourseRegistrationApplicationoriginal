package com.example.courseregistration.service;

import com.example.courseregistration.dto.EnrollmentRequestDTO;
import com.example.courseregistration.dto.EnrollmentResponseDTO;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponseDTO registerStudent(EnrollmentRequestDTO enrollmentRequestDTO);
    EnrollmentResponseDTO dropCourse(Long enrollmentId);
    List<EnrollmentResponseDTO> getAllEnrollments();
    List<EnrollmentResponseDTO> getEnrollmentsByStudent(Long studentId);
    List<EnrollmentResponseDTO> getEnrollmentsByCourse(Long courseId);
}

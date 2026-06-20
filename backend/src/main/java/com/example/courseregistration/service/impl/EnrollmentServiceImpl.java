package com.example.courseregistration.service.impl;

import com.example.courseregistration.dto.EnrollmentRequestDTO;
import com.example.courseregistration.dto.EnrollmentResponseDTO;
import com.example.courseregistration.entity.Course;
import com.example.courseregistration.entity.Enrollment;
import com.example.courseregistration.entity.EnrollmentStatus;
import com.example.courseregistration.entity.Student;
import com.example.courseregistration.exception.DuplicateEnrollmentException;
import com.example.courseregistration.exception.NoSeatsAvailableException;
import com.example.courseregistration.exception.ResourceNotFoundException;
import com.example.courseregistration.repository.CourseRepository;
import com.example.courseregistration.repository.EnrollmentRepository;
import com.example.courseregistration.repository.StudentRepository;
import com.example.courseregistration.service.EnrollmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("null")
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    // Constructor injection (no Lombok)
    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository,
                                 StudentRepository studentRepository,
                                 CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public EnrollmentResponseDTO registerStudent(EnrollmentRequestDTO enrollmentRequestDTO) {
        Student student = studentRepository.findById(enrollmentRequestDTO.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + enrollmentRequestDTO.getStudentId()));

        Course course = courseRepository.findById(enrollmentRequestDTO.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + enrollmentRequestDTO.getCourseId()));

        // Prevent duplicate registration for active status REGISTERED
        boolean alreadyEnrolled = enrollmentRepository.existsByStudentIdAndCourseIdAndStatus(
                student.getId(),
                course.getId(),
                EnrollmentStatus.REGISTERED
        );
        if (alreadyEnrolled) {
            throw new DuplicateEnrollmentException("Student is already registered in this course.");
        }

        // Check seat availability
        if (course.getAvailableSeats() <= 0) {
            throw new NoSeatsAvailableException("No seats available for course code: " + course.getCourseCode());
        }

        // Create enrollment
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDateTime.now());
        enrollment.setStatus(EnrollmentStatus.REGISTERED);

        // Decrease available seats
        course.setAvailableSeats(course.getAvailableSeats() - 1);
        courseRepository.save(course);

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return mapToResponseDTO(savedEnrollment);
    }

    @Override
    @Transactional
    public EnrollmentResponseDTO dropCourse(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment record not found with id: " + enrollmentId));

        if (enrollment.getStatus() == EnrollmentStatus.DROPPED) {
            throw new IllegalStateException("This course enrollment has already been dropped.");
        }

        // Update status to DROPPED
        enrollment.setStatus(EnrollmentStatus.DROPPED);

        // Increase available seats by 1
        Course course = enrollment.getCourse();
        course.setAvailableSeats(course.getAvailableSeats() + 1);
        courseRepository.save(course);

        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
        return mapToResponseDTO(updatedEnrollment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentResponseDTO> getAllEnrollments() {
        return enrollmentRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentResponseDTO> getEnrollmentsByStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student not found with id: " + studentId);
        }
        return enrollmentRepository.findByStudentId(studentId).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnrollmentResponseDTO> getEnrollmentsByCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course not found with id: " + courseId);
        }
        return enrollmentRepository.findByCourseId(courseId).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Manual mapping helper
    private EnrollmentResponseDTO mapToResponseDTO(Enrollment enrollment) {
        EnrollmentResponseDTO dto = new EnrollmentResponseDTO();
        dto.setId(enrollment.getId());
        dto.setStudentId(enrollment.getStudent().getId());
        dto.setStudentName(enrollment.getStudent().getName());
        dto.setCourseId(enrollment.getCourse().getId());
        dto.setCourseCode(enrollment.getCourse().getCourseCode());
        dto.setCourseName(enrollment.getCourse().getCourseName());
        dto.setEnrollmentDate(enrollment.getEnrollmentDate());
        dto.setStatus(enrollment.getStatus().name());
        return dto;
    }
}

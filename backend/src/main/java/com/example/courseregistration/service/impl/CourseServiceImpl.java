package com.example.courseregistration.service.impl;

import com.example.courseregistration.dto.CourseDTO;
import com.example.courseregistration.entity.Course;
import com.example.courseregistration.exception.ResourceNotFoundException;
import com.example.courseregistration.repository.CourseRepository;
import com.example.courseregistration.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("null")
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    // Constructor injection (no Lombok)
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public CourseDTO createCourse(CourseDTO courseDTO) {
        if (courseRepository.existsByCourseCode(courseDTO.getCourseCode())) {
            throw new IllegalArgumentException("Course with course code " + courseDTO.getCourseCode() + " already exists.");
        }
        Course course = mapToEntity(courseDTO);
        Course savedCourse = courseRepository.save(course);
        return mapToDTO(savedCourse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        return mapToDTO(course);
    }

    @Override
    @Transactional
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));

        // If code is being updated, verify it is not taken by another course
        if (!existingCourse.getCourseCode().equalsIgnoreCase(courseDTO.getCourseCode()) &&
                courseRepository.existsByCourseCode(courseDTO.getCourseCode())) {
            throw new IllegalArgumentException("Course with course code " + courseDTO.getCourseCode() + " already exists.");
        }

        existingCourse.setCourseCode(courseDTO.getCourseCode());
        existingCourse.setCourseName(courseDTO.getCourseName());
        existingCourse.setFaculty(courseDTO.getFaculty());
        existingCourse.setCredits(courseDTO.getCredits());
        existingCourse.setAvailableSeats(courseDTO.getAvailableSeats());
        existingCourse.setDepartment(courseDTO.getDepartment());
        existingCourse.setSemester(courseDTO.getSemester());

        Course updatedCourse = courseRepository.save(existingCourse);
        return mapToDTO(updatedCourse);
    }

    @Override
    @Transactional
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseDTO> searchCoursesByName(String name) {
        return courseRepository.findByCourseNameContainingIgnoreCase(name).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Manual mapping methods
    private CourseDTO mapToDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setCourseCode(course.getCourseCode());
        dto.setCourseName(course.getCourseName());
        dto.setFaculty(course.getFaculty());
        dto.setCredits(course.getCredits());
        dto.setAvailableSeats(course.getAvailableSeats());
        dto.setDepartment(course.getDepartment());
        dto.setSemester(course.getSemester());
        return dto;
    }

    private Course mapToEntity(CourseDTO dto) {
        Course course = new Course();
        course.setId(dto.getId());
        course.setCourseCode(dto.getCourseCode());
        course.setCourseName(dto.getCourseName());
        course.setFaculty(dto.getFaculty());
        course.setCredits(dto.getCredits());
        course.setAvailableSeats(dto.getAvailableSeats());
        course.setDepartment(dto.getDepartment());
        course.setSemester(dto.getSemester());
        return course;
    }
}

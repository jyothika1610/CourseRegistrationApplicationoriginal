package com.example.courseregistration.repository;

import com.example.courseregistration.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCourseNameContainingIgnoreCase(String courseName);
    boolean existsByCourseCode(String courseCode);
}

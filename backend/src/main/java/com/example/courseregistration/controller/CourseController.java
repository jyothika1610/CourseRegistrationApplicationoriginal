package com.example.courseregistration.controller;

import com.example.courseregistration.dto.CourseDTO;
import com.example.courseregistration.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/courses")
@CrossOrigin("*")
public class CourseController {

private final CourseService courseService;

public CourseController(CourseService courseService) {
    this.courseService = courseService;
}

@PostMapping
public ResponseEntity<CourseDTO> createCourse(
        @Valid @RequestBody CourseDTO courseDTO) {

    CourseDTO createdCourse =
            courseService.createCourse(courseDTO);

    return new ResponseEntity<>(
            createdCourse,
            HttpStatus.CREATED
    );
}

@GetMapping
public ResponseEntity<List<CourseDTO>> getAllCourses() {

    return ResponseEntity.ok(
            courseService.getAllCourses()
    );
}

@GetMapping("/{id}")
public ResponseEntity<CourseDTO> getCourseById(
        @PathVariable Long id) {

    return ResponseEntity.ok(
            courseService.getCourseById(id)
    );
}

@PutMapping("/{id}")
public ResponseEntity<CourseDTO> updateCourse(
        @PathVariable Long id,
        @Valid @RequestBody CourseDTO courseDTO) {

    return ResponseEntity.ok(
            courseService.updateCourse(id, courseDTO)
    );
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteCourse(
        @PathVariable Long id) {

    courseService.deleteCourse(id);

    return ResponseEntity.noContent().build();
}

@GetMapping("/search/{name}")
public ResponseEntity<List<CourseDTO>> searchCoursesByName(
        @PathVariable String name) {

    return ResponseEntity.ok(
            courseService.searchCoursesByName(name)
    );
}

}

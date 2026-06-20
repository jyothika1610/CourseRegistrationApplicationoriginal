package com.example.courseregistration.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_code", nullable = false, unique = true)
    private String courseCode;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(nullable = false)
    private String faculty;

    @Column(nullable = false)
    private Integer credits;

    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String semester;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments = new ArrayList<>();

    // Default constructor
    public Course() {
    }

    // Parameterized constructor without ID and Enrollments
    public Course(String courseCode, String courseName, String faculty, Integer credits, Integer availableSeats, String department, String semester) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.faculty = faculty;
        this.credits = credits;
        this.availableSeats = availableSeats;
        this.department = department;
        this.semester = semester;
    }

    // Full Parameterized constructor
    public Course(Long id, String courseCode, String courseName, String faculty, Integer credits, Integer availableSeats, String department, String semester, List<Enrollment> enrollments) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.faculty = faculty;
        this.credits = credits;
        this.availableSeats = availableSeats;
        this.department = department;
        this.semester = semester;
        this.enrollments = enrollments;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", faculty='" + faculty + '\'' +
                ", credits=" + credits +
                ", availableSeats=" + availableSeats +
                ", department='" + department + '\'' +
                ", semester='" + semester + '\'' +
                '}';
    }
}

import { useState } from "react";
import axios from "axios";
import "../styles/CourseForm.css";
import { useNavigate } from "react-router-dom";

function CourseForm() {

const navigate = useNavigate();

const [courseCode, setCourseCode] = useState("");
const [courseName, setCourseName] = useState("");
const [faculty, setFaculty] = useState("");
const [credits, setCredits] = useState("");
const [availableSeats, setAvailableSeats] = useState("");
const [department, setDepartment] = useState("");
const [semester, setSemester] = useState("");

async function SaveCourse() {

    const course = {
        courseCode,
        courseName,
        faculty,
        credits: Number(credits),
        availableSeats: Number(availableSeats),
        department,
        semester
    };

    try {

        const response = await axios.post(
            "https://courseregistrationapplicationoriginal.onrender.com/courses",
            course
        );

        if (response.status === 201) {

            alert("Course Added Successfully");

            setCourseCode("");
            setCourseName("");
            setFaculty("");
            setCredits("");
            setAvailableSeats("");
            setDepartment("");
            setSemester("");
        }

    } catch (error) {

        console.log(error);
        alert("Failed To Add Course");
    }
}

return (

    <div className="course-container">

        <div className="course-box">

            <h1>Add Course</h1>

            <input
                type="text"
                placeholder="Course Code"
                value={courseCode}
                onChange={(e) => setCourseCode(e.target.value)}
            />

            <input
                type="text"
                placeholder="Course Name"
                value={courseName}
                onChange={(e) => setCourseName(e.target.value)}
            />

            <input
                type="text"
                placeholder="Faculty Name"
                value={faculty}
                onChange={(e) => setFaculty(e.target.value)}
            />

            <input
                type="number"
                placeholder="Credits"
                value={credits}
                onChange={(e) => setCredits(e.target.value)}
            />

            <input
                type="number"
                placeholder="Available Seats"
                value={availableSeats}
                onChange={(e) => setAvailableSeats(e.target.value)}
            />

            <input
                type="text"
                placeholder="Department"
                value={department}
                onChange={(e) => setDepartment(e.target.value)}
            />

            <input
                type="text"
                placeholder="Semester"
                value={semester}
                onChange={(e) => setSemester(e.target.value)}
            />

            <button onClick={SaveCourse}>
                Save Course
            </button>

            <button
                className="view-btn"
                onClick={() => navigate("/courselist")}
            >
                View Courses
            </button>

        </div>

    </div>

);


}

export default CourseForm;

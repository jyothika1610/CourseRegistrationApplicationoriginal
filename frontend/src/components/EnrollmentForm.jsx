import { useState } from "react";
import axios from "axios";
import "../styles/EnrollementForm.css";

function EnrollmentForm() {

    const [studentId, setStudentId] = useState("");
    const [courseId, setCourseId] = useState("");

    async function registerCourse() {

        try {

            const response = await axios.post(
                "https://courseregistrationapplicationoriginal.onrender.com/enrollments/register",
                {
                    studentId: Number(studentId),
                    courseId: Number(courseId)
                }
            );

            alert("Enrollment Successful");

            setStudentId("");
            setCourseId("");

        } catch (error) {

            console.log(error);

            alert("Enrollment Failed");
        }
    }

 return (
    <div className="enrollment-container">

        <div className="enrollment-box">

            <h1>Course Enrollment</h1>

            <input
                type="number"
                placeholder="Student ID"
                value={studentId}
                onChange={(e) => setStudentId(e.target.value)}
            />

            <input
                type="number"
                placeholder="Course ID"
                value={courseId}
                onChange={(e) => setCourseId(e.target.value)}
            />

            <button onClick={registerCourse}>
                Enroll Student
            </button>

        </div>

    </div>
);
}

export default EnrollmentForm;
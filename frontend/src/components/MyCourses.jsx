import { useEffect, useState } from "react";
import axios from "axios";
import "../styles/MyCourses.css";

function MyCourses() {


const [courses, setCourses] = useState([]);

useEffect(() => {

    loadMyCourses();

}, []);

async function loadMyCourses() {

    try {

        const studentId =
            localStorage.getItem("studentId");

        const response = await axios.get(
            `http://localhost:8080/enrollments`
        );

        setCourses(response.data);

    } catch (error) {

        console.log(error);

        alert("Failed To Load Courses");
    }
}

return (

    <div className="mycourses-container">

        <div className="mycourses-box">

            <h1>My Courses</h1>

            <table>

                <thead>

                    <tr>

                        <th>Enrollment ID</th>
                        <th>Course</th>
                        <th>Status</th>

                    </tr>

                </thead>

                <tbody>

                    {
                        courses.map((course) => (

                            <tr key={course.id}>

                                <td>{course.id}</td>

                                <td>
                                    {course.courseName}
                                </td>

                                <td>
                                    {course.status}
                                </td>

                            </tr>

                        ))
                    }

                </tbody>

            </table>

        </div>

    </div>

);


}

export default MyCourses;

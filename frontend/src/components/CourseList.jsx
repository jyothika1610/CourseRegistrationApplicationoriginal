import { useEffect, useState } from "react";
import axios from "axios";
import "../styles/CourseList.css";

function CourseList() {


const [courses, setCourses] = useState([]);

useEffect(() => {

    getCourses();

}, []);

async function getCourses() {

    try {

        const response = await axios.get(
            "https://courseregistrationapplicationoriginal.onrender.com/courses"
        );

        setCourses(response.data);

    } catch (error) {

        console.log(error);

        alert("Failed To Load Courses");
    }
}

async function deleteCourse(id) {

    const confirmDelete = window.confirm(
        "Are you sure you want to delete this course?"
    );

    if (!confirmDelete) {
        return;
    }

    try {

        await axios.delete(
            `https://courseregistrationapplicationoriginal.onrender.com/courses/${id}`
        );

        alert("Course Deleted Successfully");

        getCourses();

    } catch (error) {

        console.log(error);

        alert("Delete Failed");
    }
}

return (

    <div className="courselist-container">

        <div className="courselist-box">

            <h1>Course List</h1>

            <table>

                <thead>

                    <tr>
                        <th>ID</th>
                        <th>Code</th>
                        <th>Name</th>
                        <th>Faculty</th>
                        <th>Credits</th>
                        <th>Seats</th>
                        <th>Department</th>
                        <th>Semester</th>
                        <th>Action</th>
                    </tr>

                </thead>

                <tbody>

                    {
                        courses.map((course) => (

                            <tr key={course.id}>

                                <td>{course.id}</td>
                                <td>{course.courseCode}</td>
                                <td>{course.courseName}</td>
                                <td>{course.faculty}</td>
                                <td>{course.credits}</td>
                                <td>{course.availableSeats}</td>
                                <td>{course.department}</td>
                                <td>{course.semester}</td>

                                <td>

                                    <button
                                        className="delete-btn"
                                        onClick={() => deleteCourse(course.id)}
                                    >
                                        Delete
                                    </button>

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

export default CourseList;

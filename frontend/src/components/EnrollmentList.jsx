import { useEffect, useState } from "react";
import axios from "axios";
import "../styles/EnrollmentList.css";

function EnrollmentList() {

const [enrollments, setEnrollments] = useState([]);

useEffect(() => {

    loadEnrollments();

}, []);

async function loadEnrollments() {

    try {

        const response = await axios.get(
            "https://courseregistrationapplicationoriginal.onrender.com/enrollments"
        );

        setEnrollments(response.data);

    } catch (error) {

        console.log(error);

        alert("Failed To Load Enrollments");
    }
}

return (

    <div className="enrollmentlist-container">

        <div className="enrollmentlist-box">

            <h1>Enrollment List</h1>

            <table>

                <thead>

                    <tr>

                        <th>ID</th>
                        <th>Student</th>
                        <th>Course</th>
                        <th>Status</th>

                    </tr>

                </thead>

                <tbody>

                    {
                        enrollments.map((enrollment) => (

                            <tr key={enrollment.id}>

                                <td>{enrollment.id}</td>

                                <td>{enrollment.studentName}</td>

                                <td>{enrollment.courseName}</td>

                                <td>{enrollment.status}</td>

                            </tr>

                        ))
                    }

                </tbody>

            </table>

        </div>

    </div>

);


}

export default EnrollmentList;

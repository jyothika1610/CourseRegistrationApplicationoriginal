import { useEffect, useState } from "react";
import axios from "axios";
import "../styles/StudentList.css";

function StudentList() {

const [students, setStudents] = useState([]);

useEffect(() => {

    loadStudents();

}, []);

async function loadStudents() {

    try {

        const response = await axios.get(
            "https://courseregistrationapplicationoriginal.onrender.com/students"
        );

        setStudents(response.data);

    } catch (error) {

        console.log(error);

        alert("Failed To Load Students");
    }
}

async function deleteStudent(id) {

    const confirmDelete = window.confirm(
        "Are you sure you want to delete this student?"
    );

    if (!confirmDelete) {
        return;
    }

    try {

        await axios.delete(
            `https://courseregistrationapplicationoriginal.onrender.com/students/${id}`
        );

        alert("Student Deleted Successfully");

        loadStudents();

    } catch (error) {

        console.log(error);

        alert("Delete Failed");
    }
}

return (

    <div className="student-container">

        <div className="student-box">

            <h1>Student List</h1>

            <table>

                <thead>

                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Department</th>
                        <th>Year</th>
                        <th>Action</th>
                    </tr>

                </thead>

                <tbody>

                    {
                        students.map((student) => (

                            <tr key={student.id}>

                                <td>{student.id}</td>
                                <td>{student.name}</td>
                                <td>{student.email}</td>
                                <td>{student.department}</td>
                                <td>{student.year}</td>

                                <td>

                                    <button
                                        className="delete-btn"
                                        onClick={() => deleteStudent(student.id)}
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

export default StudentList;

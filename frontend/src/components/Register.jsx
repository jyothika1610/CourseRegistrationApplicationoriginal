import { useState } from 'react';
import '../styles/Register.css';
import axios from "axios";
import { useNavigate } from 'react-router-dom';

function Register() {

const navigate = useNavigate();

const [name, setName] = useState("");
const [email, setEmail] = useState("");
const [password, setPassword] = useState("");
const [department, setDepartment] = useState("");
const [year, setYear] = useState("");

async function SubmitDetails() {

    const student = {
        name: name,
        email: email,
        password: password,
        department: department,
        year: Number(year)
    };

    try {

        const response = await axios.post(
            "https://courseregistrationapplicationoriginal.onrender.com/students",
            student
        );

        if (response.status === 200 || response.status === 201) {

            alert("Student Registered Successfully");

            navigate("/login");
        }

    } catch (error) {

    console.log("Error:", error);

    if(error.response){
        console.log("Status:", error.response.status);
        console.log("Data:", error.response.data);
    }

    alert("Registration Failed");
}
}

return (

    <div className="register-container">

        <div className="register-box">

            <h1>Course Registration System</h1>

            <h2>Student Registration</h2>

            <input
                type="text"
                placeholder="Enter Name"
                value={name}
                onChange={(e) => setName(e.target.value)}
            />

            <input
                type="email"
                placeholder="Enter Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
            />

            <input
                type="password"
                placeholder="Enter Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />

            <input
                type="text"
                placeholder="Enter Department"
                value={department}
                onChange={(e) => setDepartment(e.target.value)}
            />

            <input
                type="number"
                placeholder="Enter Year"
                value={year}
                onChange={(e) => setYear(e.target.value)}
            />

            <button onClick={SubmitDetails}>
                Register
            </button>

            <p>
                Already have an account?
                <span onClick={() => navigate("/login")}>
                    Login
                </span>
            </p>

        </div>

    </div>
);


}

export default Register;

import { useState } from 'react';
import '../styles/Login.css';
import { useNavigate } from 'react-router-dom';

function Login() {

const navigate = useNavigate();

const [email, setEmail] = useState("");
const [password, setPassword] = useState("");

function LoginUser() {

    if(email === "" || password === "") {

        alert("Please Enter Email And Password");
        return;
    }

    alert("Login Successful");

    navigate("/dashboard");
}

return (

    <div className="login-container">

        <div className="login-box">

            <h1>Course Registration System</h1>

            <h2>Student Login</h2>

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

            <button onClick={LoginUser}>
                Login
            </button>

            <p>
                New User?
                <span onClick={() => navigate("/")}>
                    Register
                </span>
            </p>

        </div>

    </div>

);


}

export default Login;

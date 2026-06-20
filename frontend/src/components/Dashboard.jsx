import "../styles/Dashboard.css";
import { useNavigate } from "react-router-dom";

function Dashboard() {

const navigate = useNavigate();

const role = localStorage.getItem("role");

function Logout() {

    localStorage.clear();

    navigate("/login");
}

return (

    <div className="dashboard-container">

        <div className="dashboard-header">

            <h1>
                {role === "teacher"
                    ? "Teacher Dashboard"
                    : "Student Dashboard"}
            </h1>

            <button onClick={Logout}>
                Logout
            </button>

        </div>

        <div className="dashboard-cards">

            {
                role === "teacher" ? (

                    <>

                        <div
                            className="card"
                            onClick={() => navigate("/courses")}
                        >
                            Add Course
                        </div>

                        <div
                            className="card"
                            onClick={() => navigate("/courselist")}
                        >
                            Course List
                        </div>

                        <div
                            className="card"
                            onClick={() => navigate("/students")}
                        >
                            Student List
                        </div>

                        <div
                            className="card"
                            onClick={() => navigate("/enrollmentlist")}
                        >
                            Enrollment List
                        </div>

                    </>

                ) : (

                    <>

                        <div
                            className="card"
                            onClick={() => navigate("/courselist")}
                        >
                            View Courses
                        </div>

                        <div
                            className="card"
                            onClick={() => navigate("/enrollments")}
                        >
                            Enroll Course
                        </div>

                        <div
                            className="card"
                            onClick={() => navigate("/mycourses")}
                        >
                            My Courses
                        </div>

                    </>

                )
            }

        </div>

    </div>

);


}

export default Dashboard;

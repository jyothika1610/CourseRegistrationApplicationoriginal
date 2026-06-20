import { BrowserRouter, Routes, Route } from "react-router-dom";

import Register from "./components/Register";
import Login from "./components/Login";
import Dashboard from "./components/Dashboard";
import CourseForm from "./components/CourseForm";
import CourseList from "./components/CourseList";
import StudentList from "./components/StudentList";
import EnrollmentForm from "./components/EnrollmentForm";
import MyCourses from "./components/MyCourses";

import "./App.css";

function App() {

return (

<BrowserRouter>

  <Routes>

    <Route path="/" element={<Register />} />

    <Route path="/login" element={<Login />} />

    <Route path="/dashboard" element={<Dashboard />} />

    <Route path="/courses" element={<CourseForm />} />

    <Route path="/courselist" element={<CourseList />} />

    <Route path="/students" element={<StudentList />} />

    <Route path="/enrollments" element={<EnrollmentForm />} />
   <Route
    path="/mycourses"
    element={<MyCourses />}
/>
  </Routes>

</BrowserRouter>


);
}

export default App;

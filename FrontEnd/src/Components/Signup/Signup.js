import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Signup.css";
import jobSeekerService from "../../services/jobSeeker.service";

function Signup() {
  const [userType, setUserType] = useState("");
  const navigate = useNavigate();

  const handleSignUp = (e) => {
    e.preventDefault();

    if (userType === "jobSeeker") {
      navigate("/dashboard"); // Replace with the actual path to your Job Seeker component
    } else if (userType === "jobProvider") {
      // Redirect to job provider component
    } else if (userType === "company") {
      // Redirect to company component
    }
  };


  return (
    <div className="cs">
      <div className="scontainer">
        <h1 className="text-center mb-4">Signup Here</h1>
        <form onSubmit={handleSignUp}>
          <div className="form-group">
            <label htmlFor="userType">Select User: </label>
            <select
              className="form-control"
              id="userType"
              value={userType}
              onChange={(e) => setUserType(e.target.value)}
            >
              <option value="jobSeeker">Job Seeker</option>
              <option value="jobProvider">Job Provider</option>
              <option value="company">Company</option>
            </select>
          </div>
          <div className="form-group">
            <label htmlFor="firstName">First Name:</label>
            <input
              type="text"
              className="form-control"
              id="firstName"
              placeholder="Enter your first name"
            />
          </div>
          <div className="form-group">
            <label htmlFor="lastName">Last Name:</label>
            <input
              type="text"
              className="form-control"
              id="lastName"
              placeholder="Enter your last name"
            />
          </div>
          <div className="form-group">
            <label htmlFor="email">Email:</label>
            <input
              type="email"
              className="form-control"
              id="email"
              placeholder="Enter your email address"
            />
          </div>
          <div className="form-group">
            <label htmlFor="password">Password:</label>
            <input
              type="password"
              className="form-control"
              id="password"
              placeholder="Enter your password"
            />
          </div>
          <div className="form-group">
            <label htmlFor="confirmPassword">Confirm Password:</label>
            <input
              type="password"
              className="form-control"
              id="confirmPassword"
              placeholder="Confirm your password"
            />
            <br></br>
          </div>

          <button type="submit" className="btn btn-primary btn-block">
            Sign Up
          </button>
        </form>
      </div>
    </div>
  );
}

export default Signup;
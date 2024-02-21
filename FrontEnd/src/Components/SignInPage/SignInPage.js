// SignInPage.js
import React, { useState } from "react";
import "./SignInPage.css";
import { Link,useNavigate,useParams } from "react-router-dom";
import jobSeekerService from "../../services/jobSeeker.service";

function SignInPage() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [userType, setUserType] = useState('jobSeeker'); // State for selected user type
    const navigate =useNavigate();

    const handleUserTypeChange = (event) => {
        setUserType(event.target.value); // Update selected user type when it changes
    };

    const handleSubmit = (event) => {
        event.preventDefault(); // Prevent default form submission behavior

        if(userType==='jobSeeker'){
            jobSeekerService.checkCredentials({email,password})
            .then((response)=>{
                console.log("Signed in successfully",response.data);
                if(response?.status===200){
                    navigate('/jobSeekerdashboard');
                }else{
                    console.error('unexpected response:',response);
                }
            })
            .catch((error)=>{
                alert(error.response.status);
                console.log('Error code ' + error);
                console.log('Something went wrong', error.response.data);
            });
        }else if(userType==='jobProvider'){
            console.log('jobProvider');
        }else if(userType==='company'){
            console.log('company');
        }

    };

    return (
        <div className="signin-container">
            <div className="signin-box">
                <img
                    src="/images/jobportal.png"
                    alt="logo"
                    style={{ width: "8cm", height: "8cm" }}
                />

                <h1>Sign In</h1>
                <form onSubmit={handleSubmit}>
                    <label htmlFor="userType">Select User:</label>
                    <select id="userType" name="userType" value={userType} onChange={handleUserTypeChange}>
                        <option value="jobSeeker">Job Seeker</option>
                        <option value="jobProvider">Job Provider</option>
                        <option value="company">Company</option>
                    </select>

                    <label htmlFor="email">Email:</label>
                    <input type="email" id="email" name="email" value={email} onChange={(e) => setEmail(e.target.value)} />

                    <label htmlFor="password">Password:</label>
                    <input type="password" id="password" name="password" value={password} onChange={(e) => setPassword(e.target.value)} />

                    <button type="submit">Sign In</button>
                </form>
            </div>
        </div>
    );
}

export default SignInPage;

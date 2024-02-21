import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Route, Routes, Navigate } from "react-router-dom";
import Navbar from "./Components/Navbar/Navbar";
import Signup from "./Components/Signup/Signup";
import LandingPage from "./Components/LandingPage/LandingPage";
import SignInPage from "./Components/SignInPage/SignInPage";
import JobSeekerDashboard from "./Components/JobSeekerDashboard/JobSeekerDashboard";
import StartupImage from "./Components/StartupImage/StartupImage";
import JobList from "./Components/JobList/JobList";


function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [showStartupImage, setShowStartupImage] = useState(true);

  useEffect(() => {
    const timeout = setTimeout(() => {
      setShowStartupImage(false);
    }, 3000);

    return () => clearTimeout(timeout);
  }, []);

  return (
    <Router>
      {showStartupImage ? (
        <StartupImage />
      ) : (
          <>
            <Navbar isLoggedIn={isLoggedIn} />
            <Routes>
              <Route path="/" element={<LandingPage />} />
              <Route path="/signup" element={<Signup setIsLoggedIn={setIsLoggedIn} />} />
              <Route path="/login" element={<SignInPage />} />
              <Route path="/jobSeekerdashboard" element={<JobSeekerDashboard />} />
              <Route path="/jobLists" element={<JobList/>} /> 
              {/* Add more routes as needed
              <Route path="/*" element={<Navigate to="/" />} /> */}
            </Routes>
          </>
        )}
    </Router>
  );
}

export default App;

// LandingPage.js
import React from "react";
import Footer from "../Footer/Footer";
import "./LandingPage.css";
import { Link } from "react-router-dom";
// import 'bootstrap/dist/css/bootstrap.css';


function LandingPage() {
  return (
    <div className="landing-container">
      <div className="landing-page">
        <div className="hero-section">
          <h1>Welcome to JobPortal!</h1>
          <img
            src="/images/jobportal.png"
            alt="Background"
            className="hero-image"
          />
        </div>

        <div className="features-section">
          <h1>Your work people are here!</h1>
          <p>
            Are you ready to embark on a journey towards your dream career? Look
            no further than JobSphere - your ultimate destination for the latest
            and most exciting job opportunities tailored just for you!
          </p>
          <div className="feature">
            <h2>Explore Countless Opportunities</h2>
            <ul>
              <li>Curated collection of the latest job openings</li>
              <li>Top companies across various industries</li>
              <li>Suitable for recent graduates and experienced professionals</li>
            </ul>
          </div>
          <div className="feature">
            <h2>Stay Ahead with Real-Time Updates</h2>
            <ul>
              <li>Real-time updates on job listings</li>
              <li>Eliminate missed opportunities</li>
              <li>Up-to-date information for informed decisions</li>
            </ul>
          </div>
          <div className="feature">
            <h2>User-Friendly Interface</h2>
            <ul>
              <li>Seamless navigation through job opportunities</li>
              <li>Effortless search and filtering based on preferences</li>
              <li>Easy application process with a few clicks</li>
            </ul>
          </div>
          <div className="feature">
            <h2>Connect with Employers</h2>
            <ul>
              <li>Building connections with employers</li>
              <li>Expanding your professional network</li>
              <li>Increasing chances of landing the perfect job</li>
            </ul>
          </div>
        </div>

        <div className="cta-section">
          <h2>Start Your Journey Today</h2>
          <p>
            Your dream job is just a click away. Join JobSphere now and kickstart
            your journey towards a fulfilling and successful career. The next
            chapter of your professional life begins here!
          </p>
          {/* <button className="cta-button">Join Now</button> */}
          <Link to="/signup" className="cta-button">Join Now</Link>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default LandingPage;

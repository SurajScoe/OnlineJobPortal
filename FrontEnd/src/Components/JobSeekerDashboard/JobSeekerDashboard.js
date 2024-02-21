// JobSeekerDashboard.js
import React, { useState, useEffect } from 'react';
import './JobSeekerDashboard.css';
import { Link } from 'react-router-dom';

const JobSeekerDashboard = () => {
  const [jobs, setJobs] = useState([]);

  return (
    <div className="dashboard-container">
      <div className="section my-profile" >
        <div className="card"  style={{width:'15rem', borderRadius: '5px'}}>
          <img src="\images\profileimage.jpg" className="card-img-top" alt="..." style={{width:'15rem', borderRadius: '5px'}}/>
          <div className="card-body">
            <h5 className="card-title">MyProfile</h5>
            <p className="card-text"> A streamlined view for viewing your profile details in a user-friendly format.</p>
            <a href="#" className="btn btn-primary">MyProfile</a>
          </div>
        </div>
      </div>

      <div className="section jobs">
        <div className="card" style={{width:'15rem', borderRadius: '5px'}}>
          <img src="\images\jobSearchicon.png" className="card-img-top" alt="..."  style={{width:'15rem', borderRadius: '5px'}}/>
          <div className="card-body">
            <h5 className="card-title">Search Jobs</h5>
            <p className="card-text">Embark on your job search journey and discover a world of possibilities. 
            Browse through a vast array of job listings tailored to your skills, interests, and career goals. 
            Find the perfect match for your expertise and aspirations as you navigate through diverse industries and roles.</p>
            <Link to={"/jobLists"} className="btn btn-primary">Jobs</Link>
          </div>
        </div>
      </div>

      <div className="section jobs">
        <div className="card"style={{width:'15rem', borderRadius: '5px'}}>
          <img src="\images\resumeicon.png" className="card-img-top" alt="..."  style={{width:'15rem', borderRadius: '5px'}}/>
          <div className="card-body">
            <h5 className="card-title">Build Resume</h5>
            <p className="card-text">Craft a standout resume that highlights your skills, experiences, and achievements, 
            tailored to your desired job roles. Showcase your unique strengths and qualifications to make a lasting impression on potential employers.</p>
            <a href="#" className="btn btn-primary">MyProfile</a>
          </div>
        </div>
      </div>
    </div>
  );
};

export default JobSeekerDashboard;
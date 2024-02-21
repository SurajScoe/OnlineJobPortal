import {React,useState,useEffect} from 'react';
import jobSeekerService from '../../services/jobSeeker.service';
import './JobList.css';

const JobList = () => {

  const [jobs,setJobs]=useState([]);

  const init = ()=>{
    jobSeekerService.getjobs()
    .then((response)=>{
      console.log('Printing jobs data',response.data);
      setJobs(response.data);
    })
    .catch((error)=>{
      console.log('something went wrong',error);
    });
  };

    useEffect(()=>{
      init();
    },[]);

    return (
      <div className='container'>
        <h3>Job List</h3>
        <hr />
        <div className="job-grid">
          {jobs.map((job, index) => (
            <div key={index} className="card" style={{ width: "20rem" }}>
              <div className="card-body">
                <h5 className="card-title">{job.companyName}</h5>
                <h6 className="card-subtitle mb-2 text-body-secondary">{job.position}</h6>
                <p className="card-text">{job.description}</p>
                <a href="#" className="btn btn-primary" >Another link</a>
              </div>
            </div>
          ))}
        </div>
      </div>
    );
}

export default JobList

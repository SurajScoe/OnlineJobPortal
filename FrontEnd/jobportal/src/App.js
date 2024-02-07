import logo from './logo.svg';
import './App.css';
import Navbar from './components/Navbar';
import SearchBar from './components/SearchBar';
import JobCard from './components/Jobboard';
import Header from './components/Header';
import jobData from './JobDummyData';

function App() {
  return (
    <div className="App">
      <Navbar/>
      <Header/>
      <SearchBar/>
      {jobData.map((job)=>(
        <JobCard key={job.id} {...job}/>
      ))}
      {/* <JobCard/> */}
    </div>
  );
}

export default App;

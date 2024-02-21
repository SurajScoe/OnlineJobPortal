import React from "react";
// import web from "../src/images/about.png";
import { NavLink } from "react-router-dom";
// import Common from "./Common";
const About = () => {
    return (
        <>

            <section id="header" className="d-flex align-items-center">
                <div className='container-fluid '>
                    <div className='row'>
                        <div className='col-10 mx-auto'>
                            <div className="row">
                                <div className="col-md-6 pt-5 pt-lg-0 order-2 order-lg-1 d-flex justify-content-center flex-column">
                                    <h1>About Us
                                        <strong className="brand-name"></strong>
                                    </h1>
                                    <p>Established in 2019

                                        Technocolabs was founded in 2019 by a Team of Non-Profit Orgnization in Indore, who brought years of experience in Machine learning, Data science and AI Product Development to a new venture to make it a success. In 2019 an experienced leader Yasin Shah joined the team as a CEO and brought the company to a whole new level.</p>
                                    {/* <p>Find Jobs, Employment & Career Opportunities</p>
                                    <select class="form-select" aria-label="Default select example">
                                        <option selected>Open this select menu</option>
                                        <option value="1">One</option>
                                        <option value="2">Two</option>
                                        <option value="3">Three</option>
                                    </select>
                                    <div className="mt-3">
                                        <NavLink to="/" className="btn-get-started ">
                                            Search
                                        </NavLink>


                                    </div> */}
                                </div>
                                <div className="col-lg-6 order-1 order-lg-2 header-img">
                                    {/* <img src={web} className="img-fluid animated" alt="home img" /> */}
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </section>


        </>
    );
};

export default About;
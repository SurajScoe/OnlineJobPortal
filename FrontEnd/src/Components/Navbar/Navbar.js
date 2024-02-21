import React from "react";
import { Link } from "react-router-dom";
import "./Navbar.css";

function Navbar() {
  return (
    <nav className="bordered">
      <div className="logo">
        <Link to="/">OnlineJobPortal</Link>
      </div>
      <ul>
        <li>
          <Link to="/">Home</Link>
        </li>
        <li>
          <Link to="/opportunities">opening</Link>
        </li>
        <li>
          <Link to="/aboutus">About Us</Link>
        </li>
        <li className="auth">
          <Link to="/login">Login</Link>
          <Link to="/signup">Signup</Link>
        </li>
      </ul>
    </nav>
  );
}

export default Navbar;
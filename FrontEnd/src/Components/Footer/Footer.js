import React from "react";

function Footer() {
    return (
        <div className="footer">
            <div className="copyright">
                <p>&copy; {new Date().getFullYear()} JobSphere. All Rights Reserved.</p>
            </div>
        </div>
    );
}

export default Footer;
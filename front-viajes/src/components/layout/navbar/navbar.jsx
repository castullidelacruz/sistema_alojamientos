import "./navbar.css";
import React from "react";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import "bootstrap/dist/css/bootstrap.min.css";


const Navbar = () => {

    return (
        <nav className="navbar bg-dark border-bottom border-body" data-bs-theme="dark">
            <div className="container-fluid">
                <a className="navbar-brand" href="#">Navbar</a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNav">
                <ul className="navbar-nav">
                    <li className="nav-item">
                    <a className="nav-link active" aria-current="page" href="#">Home</a>
                    </li>
                    <li className="nav-item">
                    <a className="nav-link" href="#">Hoteles</a>
                    </li>
                    <li className="nav-item">
                    <a className="nav-link" href="#">Perfil</a>
                    </li>
                    <li className="nav-item">
                    <a className="nav-link" href="#">Carrito</a>
                    </li>
                </ul>
                </div>
            </div>
        </nav>
    );
};

export default Navbar;
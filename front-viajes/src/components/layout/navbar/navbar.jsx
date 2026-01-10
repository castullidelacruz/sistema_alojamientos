import "./navbar.css";
import React from "react";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import "bootstrap/dist/css/bootstrap.min.css";


const Navbar = () => {

    return (
        <nav class="navbar bg-dark border-bottom border-body" data-bs-theme="dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Navbar</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                    <a class="nav-link" href="#">Hoteles</a>
                    </li>
                    <li class="nav-item">
                    <a class="nav-link" href="#">Perfil</a>
                    </li>
                    <li class="nav-item">
                    <a class="nav-link" href="#">Carrito</a>
                    </li>
                </ul>
                </div>
            </div>
        </nav>
    );
};

export default Navbar;
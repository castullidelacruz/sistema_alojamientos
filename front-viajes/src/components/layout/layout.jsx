import "./layout.css"
import React from "react";
import Navbar from "./navbar/navbar.jsx";
import Footer from "./footer/footer.jsx";
import { Outlet } from "react-router-dom";

const Layout = () => {
    return (
        <div className="layout">
      <Navbar></Navbar>
      <main className="layout__content">
        <Outlet></Outlet>
      </main>
      <Footer></Footer>
    </div>
    );
};

export default Layout;
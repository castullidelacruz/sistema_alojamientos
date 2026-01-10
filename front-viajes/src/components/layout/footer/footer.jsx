import React from "react";
import "./footer.css";

const Footer = () => {
  return (
    <footer class="footer bg-dark border-bottom border-body" data-bs-theme="dark">
      <div class="container-fluid text-center py-3 text-white">
        <p>Viajes</p>
        <p>
          © {new Date().getFullYear()} Viajes. Todos los derechos
          reservados.
        </p>
      </div>
    </footer>
  );
};

export default Footer;
import "./header.css";

const Header = () => {
  return (
    <section className="header">
      <div className="header__inner">
        <h1 className="header__title">Bienvenido a tu gestor de viajes</h1>
        <p className="header__subtitle">
          Tu destino online para viajes con la mejor energía
        </p>
      </div>
    </section>
  );
};

export default Header;
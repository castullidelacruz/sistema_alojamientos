import "./home.css";
import React from "react";
import Header from "./header/header.jsx";
import AccomodationSearchBar from "../../accomodationSearchBar/AccomodationSearchBar.jsx";

const Home = () => {
    return (
    <>
      <Header></Header>
      <AccomodationSearchBar></AccomodationSearchBar>
    </>
  );
}

export default Home;
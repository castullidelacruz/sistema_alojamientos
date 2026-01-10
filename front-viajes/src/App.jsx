import logo from './logo.svg';
import './App.css';
import React from 'react';
import Layout from './components/layout/layout.jsx';
import { Routes, Route, BrowserRouter } from "react-router-dom";
import Home from './components/pages/home/home.jsx';

function App() {

  

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;

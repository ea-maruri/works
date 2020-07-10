import React from "react";
import "./App.css";

// Components
import NavCard from './../nav-card/nav-card';

import diamond from "./diamond-17.svg";
import spades from "./symbols-spades-light.svg";


function App() {
  return (
    <nav>

      <div className="container">
        Hello
      </div>

      <div id="mprincipal-nav" className="nav-cards">

        <div className="card1">
          <NavCard imgUrl={spades} imgAlt="Spades" title="Portfolio"/>
        </div>

        <div className="card2">
          <NavCard imgUrl={diamond} imgAlt="Diamond" title="About"/>
        </div>

      </div>

    </nav>
  );
}

export default App;

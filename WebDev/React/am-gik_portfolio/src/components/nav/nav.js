import React from "react";

import "./nav.css";

// Components
import NavCard from "./../nav-card/nav-card";

// Images
import diamond from "./diamond-17.svg";
import spades from "./symbols-spades-light.svg";


class Nav extends React.Component {
  render() {
    return (
      <nav>
        <div className="container-fluid">
          <div className="row nav-cards">
            <div className="col-sm-12 d-flex justify-content-center">
              <div className="rotate-left compense-right">
                <NavCard imgUrl={spades} imgAlt="Spades" title="Portfolio" reference="./portfolio"/>
              </div>

              <div className="rotate-right compense-left">
                <NavCard imgUrl={diamond} imgAlt="Diamond" title="About" reference="./about"/>
              </div>
            </div>
          </div>
        </div>
      </nav>
    );
  }
}


export default Nav;
import React from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

import "./Navigation.css";

import NavCard from "./../navigation-card/NavigationCard";

// Pages
import Home from "./../../pages/Home";
import About from "./../../pages/About";
import Portfolio from "./../../pages/Portfolio";

// Images
import imgDiamond from "./diamond-17.svg";
import imgSpades from "./symbols-spades-light.svg";

class Navigation extends React.Component {
  render() {
    return (
      <Router>
        <div className="top-space">
          <nav className="d-flex justify-content-around mt-5">
            <NavCard
              title="Home"
              _to="/home"
              imgUrl={imgSpades}
              imgAlt="Spades"
            />
            <NavCard
              title="About"
              _to="/about"
              imgUrl={imgDiamond}
              imgAlt="Diamond"
            />
            <NavCard
              title="Portfolio"
              _to="/portfolio"
              imgUrl={imgSpades}
              imgAlt="Spades"
            />
          </nav>
        </div>

        {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL. */}

        <Switch>
          <Route path="/about">
            <About />
          </Route>
          <Route path="/portfolio">
            <Portfolio />
          </Route>
          <Route path="/home">
            <Home />
          </Route>
        </Switch>
      </Router>
    );
  }
}

export default Navigation;

import React from "react";
import "./top-bar.css";
import logo from "./am-gik-borders-25.svg";

// Components
import ChipButton from "./../chip-button/chip-button";

class TopBar extends React.Component {
  render() {
    return (
      <header>
        <div className="fixed-top">
          <div className="container">
            <div className="row">
              <div className="col-sm-12">
                <div className="d-flex justify-content-between align-itmes-center mt-4">
                  <div className="search-bar-logo">
                    <a href="./index.html">
                      <img
                        title="Home"
                        className="mlogo"
                        src={logo}
                        alt="LOGO"
                      ></img>
                    </a>
                  </div>

                  <ChipButton
                    id="search-chip"
                    value="Search"
                    class="button-chip-sm"
                    dataToggle="modal"
                    dataTarget="#searchModal"
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
      </header>
    );
  }
}

export default TopBar;

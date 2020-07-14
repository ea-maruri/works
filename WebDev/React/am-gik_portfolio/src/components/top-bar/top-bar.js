import React from "react";
import "./top-bar.css";


// Components
import ChipButton from "./../chip-button/chip-button";

// Image
import logo from "./am-gik-borders-25.svg";

class TopBar extends React.Component {
  render() {
    return (
      <header className="container fixed-top mt-5">
        <div className="row">
          <div className="col-sm-12 d-flex justify-content-between align-itmes-center">
            <div>
              <a href="./">
                <img title="Home" className="mlogo" src={logo} alt="LOGO"></img>
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
      </header>
    );
  }
}

export default TopBar;

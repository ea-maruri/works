import React from "react";
import "./footer.css";

// Components
import ChipButton from "./../chip-button/chip-button";

class Footer extends React.Component {
  render() {
    return (
      <footer className="bottom-space">
        <div className="container">
          <div className="d-flex justify-content-around">
            <ChipButton
              id="search-chip"
              value="Send email"
              class="button-chip-lg"
              dataToggle=""
              dataTarget=""
            />

            <div className="mt-5">
              <ChipButton
                id="search-chip"
                value="&#169; Copyright 2020 AM-GIK"
                class="button-chip-lg"
                dataToggle=""
                dataTarget=""
              />
            </div>

            <a target="_blanck" href="https://www.canva.com/design/DAD-z3YWowI/CTzEgEQNURNXEBhda53MEQ/view?utm_content=DAD-z3YWowI&utm_campaign=designshare&utm_medium=link&utm_source=publishsharelink">
              <ChipButton
                id="search-chip"
                value="My CV"
                class="button-chip-lg"
                dataToggle=""
                dataTarget=""
              />
            </a>
          </div>
        </div>
      </footer>
    );
  }
}

export default Footer;

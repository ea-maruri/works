import React from "react";
import "./footer.css";

// Components
import ChipButton from "./../chip-button/chip-button";

class Footer extends React.Component {
  render() {
    // Year for copyright
    let mtoday = new Date();
    let year = mtoday.getFullYear();

    return (
      <footer id="footer-bottom" className="container bottom-space">
        <div className="d-flex justify-content-around">
          <ChipButton
            id="search-chip"
            value="Send email"
            class="button-chip-lg"
            dataToggle=""
            dataTarget=""
          />

          <div className="mt-5">
            <a href="./home">
              <ChipButton
                id="search-chip"
                value={"c " + year + " AM-GIK"}
                class="button-chip-lg"
                dataToggle=""
                dataTarget=""
              />
            </a>
          </div>

          <a
            target="_blanck"
            href="https://www.canva.com/design/DAD-z3YWowI/CTzEgEQNURNXEBhda53MEQ/view?utm_content=DAD-z3YWowI&utm_campaign=designshare&utm_medium=link&utm_source=publishsharelink"
          >
            <ChipButton
              id="search-chip"
              value="My CV"
              class="button-chip-lg"
              dataToggle=""
              dataTarget=""
            />
          </a>
        </div>
      </footer>
    );
  }
}

export default Footer;

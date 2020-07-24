import React from "react";

import "./nav.css";

// Components
import NavCard from "./../nav-card/nav-card";

// Images
import diamond from "./diamond-17.svg";
import spades from "./symbols-spades-light.svg";

class Nav extends React.Component {
  constructor(props) {
    super(props);

    this.checkScrollDirectionForNav = this.checkScrollDirectionForNav.bind(
      this
    );
    this.checkScrollDirectionForNav();

    this.state = {
      lastScrollTop: 0
    };
  }

  checkScrollDirectionForNav() {
    let body = document.body;
    let html = document.documentElement;

    const width = Math.max(
      body.scrollWidth,
      body.offsetWidth,
      html.clientWidth,
      html.scrollWidth,
      html.offsetWidth
    );

    // Check Scroll Direction
    let lastScrollTop = 0;

    window.addEventListener(
      "scroll",
      function () {
        // Credits: "https://github.com/qeremy/so/blob/master/so.dom.js#L426"
        const navCards = document.getElementById("nav-cards");
        let st = window.pageYOffset || document.documentElement.scrollTop;

        if (navCards) {
          if (st > lastScrollTop) {
            console.log("Scroll to down");

            if (width < 770) {
              navCards.style.bottom = "-170px";
            } else {
              navCards.style.bottom = "-220px";
            }
          } else {
            console.log("Scroll to Up");
            if (width < 770) {
              navCards.style.bottom = "-120px";
            } else {
              navCards.style.bottom = "-170px";
            }
          }
        } else {
          console.log("Something Wrong");
        }

        lastScrollTop = st <= 0 ? 0 : st; // For Mobile or negative scrolling*/
      },
      false
    );
  }

  render() {
    return (
      <nav>
        <div className="container-fluid">
          <div className="row">
            <div
              id="nav-cards"
              className="col-sm-12 d-flex justify-content-center"
            >
              <div className="rotate-left compense-right">
                <NavCard
                  imgUrl={spades}
                  imgAlt="Spades"
                  title="Portfolio"
                  reference="./portfolio"
                />
              </div>

              <div className="rotate-right compense-left">
                <NavCard
                  imgUrl={diamond}
                  imgAlt="Diamond"
                  title="About"
                  reference="./about"
                />
              </div>
            </div>
          </div>
        </div>
      </nav>
    );
  }
}

export default Nav;

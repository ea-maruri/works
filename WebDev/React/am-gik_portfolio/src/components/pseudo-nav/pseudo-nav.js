import React from "react";
import "./pseudo-nav.css";

import CardShape from "./../card-shape/card-shape";

class PseudoNav extends React.Component {
  render() {
    return (
      <nav className="my-5">

        <div className="container">
          <div className="row">
            <div className="col-sm-12 d-flex">

              <CardShape 
                title="Web"
                href="./portfolio#web-section" 
                itemClass="fa fa-code" 
              />

              <CardShape
                title="Programming"
                href="./portfolio#prog-section"
                itemClass="fa fa-file-code-o"
              />

              <CardShape
                title="Artificial Intelligence"
                href="./portfolio#ia-section"
                itemClass="fa fa-address-card"
              />

              <CardShape
                title="Nerworking"
                href="./portfolio#net-section"
                itemClass="fa fa-code-fork"
              />

              <CardShape
                title="Design"
                href="./portfolio#des-section"
                itemClass="fa fa-desktop"
              />

            </div>
          </div>
        </div>

      </nav>
    );
  }
}

export default PseudoNav;

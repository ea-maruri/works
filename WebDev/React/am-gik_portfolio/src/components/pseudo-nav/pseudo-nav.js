import React from "react";
import "./pseudo-nav.css";

import CardShape from "./../card-shape/card-shape";

class PseudoNav extends React.Component {
  render() {
    return (
      <nav className="mb-5">

        <div className="container">
          <div className="row">
            <div className="col-sm-12 d-flex">

              <CardShape 
                title="Web"
                href="#web-card" 
                itemClass="fa fa-code" 
              />

              <CardShape
                title="Programming"
                href="#prog-card"
                itemClass="fa fa-file-code-o"
              />

              <CardShape
                title="Artificial Intelligence"
                href="#ia-card"
                itemClass="fa fa-address-card"
              />

              <CardShape
                title="Nerworking"
                href="#net-card"
                itemClass="fa fa-code-fork"
              />

              <CardShape
                title="Design"
                href="#des-card"
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

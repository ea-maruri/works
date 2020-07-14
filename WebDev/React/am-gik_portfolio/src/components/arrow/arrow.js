import React from "react";

import "./arrow.css";

class Arrow extends React.Component {
  render() {
    return (
      <div
        id="marrow-container"
        className="position-absolute position-fixed d-flex flex-column justify-content-center align-items-center arrow-container"
      >
        <div className="line"></div>

        <p>
          <i id="marrow" className="arrow down"></i>
        </p>
      </div>
    );
  }
}

export default Arrow;

import React from "react";
import "./about-card.css";

class AboutCard extends React.Component {
  render() {
    return (
      <div className="card mcard">
        <h3 className="mcard-header">{this.props.title}</h3>

        <img
          className="mcard-symbol"
          src={this.props.imgUrl}
          alt={this.props.alternative}
          height="100px"
          width="auto"
        ></img>

        <div className="mcard-content mx-auto">{this.props.value}</div>

        <div className="mcard-circle"></div>
      </div>
    );
  }
}

export default AboutCard;

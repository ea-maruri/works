import React from "react";
import "./about-card.css";

class AboutCard extends React.Component {
  render() {
    return (
      <div>
        <div class="card mcard">
          <h3 class="mcard-header">{this.props.title}</h3>

          <img
            class="mcard-symbol"
            src={this.props.imgUrl}
            alt={this.props.alternative}
            height="100px"
            width="auto"
          ></img>

          <div class="mcard-content mx-auto">{this.props.value}</div>

          <div class="mcard-circle"></div>
        </div>
      </div>
    );
  }
}

export default AboutCard;

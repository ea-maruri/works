import React from "react";

import "./nav-card.css";


class NavCard extends React.Component {
  render() {
    return (
      <div className="nav-card">
        <div className="nav-card-content">
          <h3 className="nav-card-title">
            <a title={this.props.title} href={this.props.reference}>
              {this.props.title}
            </a>
          </h3>

          <img
            src={this.props.imgUrl}
            alt={this.props.imgAlt}
            width="70px"
            height="auto"
          ></img>
        </div>
      </div>
    );
  }
}

export default NavCard;

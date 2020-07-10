import React from "react";
import "./card-shape.css";

class CardShape extends React.Component {
  render() {
    return (
      <div title={"Go to " + this.props.title} className="card-shape d-flex justify-content-center align-items-center">
        <a href={this.props.href}>
          <h6>{this.props.title}</h6>
          <div>
            <i className={this.props.itemClass} aria-hidden="true"></i>
          </div>
        </a>
      </div>
    );
  }
}


export default CardShape;
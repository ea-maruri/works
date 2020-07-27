import React from "react";
import "./card-shape.css";

class CardShape extends React.Component {
  render() {
    return (
      <div title={"Go to " + this.props.title} className="d-flex flex-colum justify-content-around align-items-center card-shape">
        <a href={this.props.href} className="d-flex flex-column justify-content-between">
          <h4>{this.props.title}</h4>
          <div>
            <i className={this.props.itemClass} aria-hidden="true"></i>
          </div>
        </a>
      </div>
    );
  }
}


export default CardShape;
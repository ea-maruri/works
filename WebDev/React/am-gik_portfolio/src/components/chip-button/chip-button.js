import React from "react";
import "./chip-button.css";

class ChipButton extends React.Component {
  
  render() {
    return (
      <button
        type="button"
        title="Click me!"
        id={this.props.id}
        className={this.props.class + " d-flex justify-content-center align-items-center"}
        data-toggle={this.props.dataToggle}
        data-target={this.props.dataTarget}
      >
        {this.props.value}
      </button>
    );
  }
}


export default ChipButton;
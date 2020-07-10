import React from "react";
import "./chip-button.css";

class ChipButton extends React.Component {
  
  render() {
    return (
      <button
        type="button"
        title="Click me!"
        id="search-chip"
        className="button-chip search-circle"
        data-toggle="modal"
        data-target="#searchModal"
      >
        {this.props.value}
      </button>
    );
  }
}


export default ChipButton;
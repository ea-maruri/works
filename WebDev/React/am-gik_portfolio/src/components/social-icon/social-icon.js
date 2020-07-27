import React from "react";

import "./social-icon.css";

class SocialIcon extends React.Component {
  render() {
    return (
      <div>
        <a
          id={this.props._id}
          title={this.props.title}
          href={this.props.link}
          target="_blank"
          rel="noopener noreferrer"
        >
          <i className={this.props.icon + " social-icon"} aria-hidden="true"></i>
        </a>
      </div>
    );
  }
}

export default SocialIcon;

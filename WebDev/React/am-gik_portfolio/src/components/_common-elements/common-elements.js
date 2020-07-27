import React from "react";
import "./common-elements.css";

// Components
import TopBar from "./../top-bar/top-bar";
import SocialMedia from "./../social-media/social-media";
import Nav from "./../nav/nav";


class CommonElement extends React.Component {
  render() {
    return (
      <div>
        <TopBar />

        <SocialMedia containerArrowPosition={this.props.containerArrowPosition} arrowDirection={this.props.arrowDirection}/>

        <Nav />
      </div>
    );
  }
}

export default CommonElement;

import React from "react";

import "./arrow.css";

class Arrow extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      title: "Go to bottom",
      reference: "#footer-bottom", // document-top
    };

    this.documentGoto = this.documentGoto.bind(this);
  }


  // Indicates where to go
  documentGoto(event) {
    let body = document.body;
    let html = document.documentElement;
    let ref_point = 2.5;

    const height = Math.max(
      body.scrollHeight,
      body.offsetHeight,
      html.clientHeight,
      html.scrollHeight,
      html.offsetHeight
    );

    let scrollTop =
      window.pageYOffset ||
      (document.documentElement || document.body.parentNode || document.body)
        .scrollTop;

    if (scrollTop >= height / ref_point) {
      this.setState({title: "Go to top" });
      this.setState({reference: "#document-top"})
    } else {
      this.setState({ title: "Go to bottom"});
      this.setState({reference: "#footer-bottom"})
    }
  }

  
  render() {
    return (
      <a href={this.state.reference}>
        <div
          title={this.state.title}
          id="marrow-container"
          onMouseOver={this.documentGoto}
          onClick={this.documentGoto}
          className={
            "position-absolute position-fixed d-flex " +
            this.props.containerArrowPosition +
            " justify-content-center align-items-center arrow-container"
          }
        >
          <div className="line"></div>

          <p>
            <i id="marrow" className={this.props.arrowDirection}></i>
          </p>
        </div>
      </a>
    );
  }
}

export default Arrow;

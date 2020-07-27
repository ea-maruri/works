import React from "react";
import "./social-media.css";

// Components
import ChipButton from "./../chip-button/chip-button";
import SocialIcon from "./../social-icon/social-icon";
import Arrow from "./../arrow/arrow";

// Images
import Heart from "./symbol-hearts.svg";

class SocialMedia extends React.Component {
  render() {
    return (
      <aside className="position-absolute">

        <div className="position-absolute position-fixed social-chip ">
          <ChipButton
            id="social-chip"
            value="Get in touch"
            class="button-chip-sm"
            dataToggle=""
            dataTarget=""
          />
        </div>

        <div className="position-relative position-fixed mt-5 social-container">
          <header className="container-fluid mt-2">
            <div className="row">
              <div className="col">
                <h3 className="text-center">Social</h3>
              </div>
            </div>
          </header>

          <div className="container-fluid">
            <div className="row mt-2 p-2">
              <div className="col d-flex justify-content-between">
                <SocialIcon
                  _id="facebook-link"
                  title="Facebook"
                  link="https://www.facebook.com/alejandro.maruri.790"
                  icon="fa fa-facebook"
                />

                <SocialIcon
                  _id="instagram-link"
                  title="Instagram"
                  link="https://www.instagram.com/ea_maruri/"
                  icon="fa fa-instagram"
                />
              </div>
            </div>

            <div className="row mt-2 p-2">
              <div className="col d-flex justify-content-between">
                <SocialIcon
                  _id="github-link"
                  title="Github"
                  link="https://github.com/ea-maruri/"
                  icon="fa fa-github"
                />

                <SocialIcon
                  _id="linkedin-link"
                  title="Linkedin"
                  link="https://linkedin.com/in/edwin-alejandro-maruri-tinajero-eamt"
                  icon="fa fa-linkedin"
                />
              </div>
            </div>
          </div>

          <footer className="container-fluid mt-1">
            <div className="row">
              <div className="col">
                <img
                  className="d-block mx-auto"
                  title="Get in touch"
                  src={Heart}
                  alt="Heart"
                  height="40px"
                ></img>
              </div>
            </div>
          </footer>
        </div>

        <Arrow containerArrowPosition={this.props.containerArrowPosition} arrowDirection={this.props.arrowDirection}/>
        
      </aside>
    );
  }
}

export default SocialMedia;

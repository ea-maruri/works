import React from "react";
import "./social-media.css";

// Components
import ChipButton from "./../chip-button/chip-button";

// Images
import Heart from "./symbol-hearts.svg";


class SocialMedia extends React.Component {
  render() {
    return (
      <aside className="position-absolute">
        <div className="social-chip position-absolute position-fixed">
          <ChipButton
            id="social-chip"
            value="Get in touch"
            dataToggle=""
            dataTarget=""
          />
        </div>

        <div className="social-container position-relative position-fixed mt-5">
          <header>
            <div className="container-fluid">
              <div className="row">
                <div className="col">
                  <h3 className="text-center">
                    Social
                  </h3>
                </div>
              </div>
            </div>
          </header>

          <div className="container-fluid">
            <div className="row mt-2">
              <div className="col">
                <div className="d-flex justify-content-between">
                  <a
                    id="facebook-link"
                    title="Facebook"
                    href="https://www.facebook.com/alejandro.maruri.790"
                    target="_blank"
                  >
                    <i class="fa fa-facebook" aria-hidden="true"></i>
                  </a>

                  <a
                    id="instagram-link"
                    title="Instgram"
                    href="https://www.instagram.com/ea_maruri/"
                    target="_blank"
                  >
                    <i class="fa fa-instagram" aria-hidden="true"></i>
                  </a>
                </div>
              </div>
            </div>

            <div className="row mt-3">
              <div className="col">
                <div className="d-flex justify-content-between">
                  <a
                    id="github-link"
                    title="Github"
                    href="https://github.com/ea-maruri/"
                    target="_blank"
                  >
                    <i class="fa fa-github" aria-hidden="true"></i>
                  </a>

                  <a
                    id="linkedin-link"
                    title="LinkedIn"
                    href="https://linkedin.com/in/edwin-alejandro-maruri-tinajero-eamt"
                    target="_blank"
                  >
                    <i class="fa fa-linkedin" aria-hidden="true"></i>
                  </a>
                </div>
              </div>
            </div>
          </div>

          <footer className="mt-2">
            <div className="container-fluid">
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
            </div>
          </footer>
        </div>

        <div id="marrow-container" class="position-absolute position-fixed arrow-container">
          <div class="line"></div>

          <p>
            <i id="marrow" class="arrow down"></i>
          </p>
        </div>
      </aside>
    );
  }
}

export default SocialMedia;

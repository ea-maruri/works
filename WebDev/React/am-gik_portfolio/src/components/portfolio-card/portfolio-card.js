import React from "react";
import "./portfolio-card.css";

class PortfolioCard extends React.Component {
  render() {
    return (
      <div>
        <div
          id="webAccordion"
          role="tablist"
          aria-multiselectable="true"
        >
          <div className="card">
            <div className="card-header" role="tab" id="webAccordionHeader">
              <h5 className="mb-0">
                <a
                  data-toggle="collapse"
                  data-parent="#webAccordion"
                  href="#webAccordionContent"
                  aria-expanded="true"
                  aria-controls="webAccordionContent"
                >
                  AM-GIK Portfolio
                </a>
              </h5>
            </div>

            <div
              id="webAccordionContent"
              className="collapse in"
              role="tabpanel"
              aria-labelledby="webAccordionHeader"
            >
              <div className="card-body">
                <div className="card mportfoliocard">
                  <img
                    src="./../../public/assets/images/works/web/home.png"
                    className="card-img-top"
                    alt="..."
                  ></img>
                  <div className="card-body">
                    <h5 className="card-title">Portfolio</h5>

                    <p className="card-text">
                      A brand portfolio for Alejandro Maruri.
                      <br></br>
                      <br></br>
                      It is deployed on <strong>github pages</strong>. You are
                      using it now.
                      <br></br>
                      <br></br>
                      Many knowledge in HTML, CSS, SASS, and JS is used.
                    </p>
                  </div>

                  <ul className="list-group list-group-flush">
                    <li className="list-group-item">
                      HTML, SASS, CSS, Bootstrap, JS
                    </li>
                  </ul>

                  <div className="card-body">
                    <button type="button" className="btn-border-primary">
                      <a href="./../../index.html" target="_blank">
                        <i className="fa fa-home" aria-hidden="true"></i> Go to
                        Home
                      </a>
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default PortfolioCard;

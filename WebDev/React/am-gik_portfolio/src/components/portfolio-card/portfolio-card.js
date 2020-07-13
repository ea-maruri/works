import React from "react";
import "./portfolio-card.css";

class PortfolioCard extends React.Component {
  render() {
    return (
      <div>
        <div id={this.props.cardId} role="tablist" aria-multiselectable="true">
          <div className="card w-75 mx-auto">
            <div className="card-header" role="tab" id={this.props.headerId}>
              <h5>
                <a
                  data-toggle="collapse"
                  data-parent={this.props.dataParent}
                  href={this.props.href}
                  aria-expanded="true"
                  aria-controls={this.props.webAccordionContent}
                >
                  {this.props.cardTitle}
                </a>
              </h5>
            </div>

            <div
              id={this.props.contentId}
              className="collapse in"
              role="tabpanel"
              aria-labelledby={this.props.webAccordionHeader}
            >
              <div className="card-body">
                <div className="card mportfoliocard">
                  <img
                    className="card-img-top"
                    src={this.props.imgSrc}
                    alt={this.props.imgAlt}
                  ></img>
                  <div className="card-body">
                    <h5 className="card-title">{this.props.innerCardTitle}</h5>

                    <div>
                      {this.props.content} 
                    </div>

                  </div>

                  <ul className="list-group list-group-flush">
                    <li className="list-group-item">{this.props.listTechs}</li>
                  </ul>

                  <div className="card-body">
                    <button type="button" className="btn-border-primary">
                      <a href={this.props.buttonRef} target={this.props.buttonTarget}>
                        <i
                          className={this.props.buttonImgClass}
                          aria-hidden="true"
                        ></i>{" "}
                        {this.props.buttonText}
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

import React from "react";
import "./portfolio-card.css";

class PortfolioCard extends React.Component {
  constructor(props) {
    super(props);

    this.showModal = this.showModal.bind(this);
    this.hideModal = this.hideModal.bind(this);
  }


  // Show modal for images
  showModal(imgSrc, imgTitle, imgContent) {
    let modal = document.getElementById("myModal");
    let imgModal = document.getElementById("imgModal");
    let caption = document.getElementById("caption");

    modal.style.display = "block";
    imgModal.setAttribute("src", imgSrc);
    caption.innerHTML = imgTitle + "<br><br>" + imgContent;
  }

  // Hide modal
  hideModal() {
    let modal = document.getElementById("myModal");
    modal.style.display = "none";
  }

  render() {
    return (
      <div
        id={this.props.cardId}
        role="tablist"
        aria-multiselectable="true"
        className="card w-75 mx-auto"
      >
        <h5 className="card-header" role="tab" id={this.props.headerId}>
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

        <div
          id={this.props.contentId}
          className="collapse in"
          role="tabpanel"
          aria-labelledby={this.props.webAccordionHeader}
        >
          <div className="card-body">
            <div className="card mportfoliocard">
              <img
                onClick={() => {
                  this.showModal(
                    this.props.imgSrc,
                    this.props.cardTitle,
                    this.props.imgAlt
                  );
                }}
                title="Click me!"
                className="card-img-top myModalImg"
                src={this.props.imgSrc}
                alt={this.props.imgAlt}
              ></img>
              <div className="card-body">
                <h5 className="card-title text-center">
                  {this.props.innerCardTitle}
                </h5>

                <div>{this.props.content}</div>
              </div>

              <ul className="list-group list-group-flush">
                <li className="list-group-item">{this.props.listTechs}</li>
              </ul>

              <div className="card-body">
                <button type="button" className="btn-border-primary">
                  <a
                    href={this.props.buttonRef}
                    target={this.props.buttonTarget}
                  >
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

        <div id="myModal" className="modal">
          <span className="close" onClick={this.hideModal}>
            &times;
          </span>
          <img className="modal-content" id="imgModal" alt=""></img>
          <div id="caption"></div>
        </div>
      </div>
    );
  }
}

export default PortfolioCard;

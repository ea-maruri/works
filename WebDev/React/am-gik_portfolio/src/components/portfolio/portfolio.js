import React from "react";
import "./portfolio.css";

// Components
import PortfolioCard from "./../portfolio-card/portfolio-card";

class Portfolio extends React.Component {
  render() {
    return (
      <div>
        <div className="container">
          <div className="row">
            <div className="col-sm-12 col-md-6">
              <PortfolioCard />
            </div>
            <div className="col-sm-12 col-md-6"></div>
          </div>
        </div>
      </div>
    );
  }
}

export default Portfolio;

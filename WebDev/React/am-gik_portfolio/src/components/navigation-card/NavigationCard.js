import React from 'react';
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

import './NavigationCard.css';

class NavigationCard extends React.Component {
    render(){
        return(
            <div className="nav-card">
              <div className="nav-card-content">
                <h3 className="nav-card-title" title={this.props.title}>
                    <Link to={this.props._to}>{this.props.title}</Link>
                </h3>
                <img
                  src={this.props.imgUrl}
                  alt={this.props.imgAlt}
                  width="70px"
                  height="auto"
                ></img>
              </div>
            </div>
        );
    }
    
}

export default NavigationCard;
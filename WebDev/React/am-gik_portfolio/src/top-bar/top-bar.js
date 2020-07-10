import React from 'react';
import './top-bar.css';
import logo from './am-gik-borders-25.svg';

// Components
import ChipButton from './../chip-button/chip-button';


class TopBar extends React.Component {
    render(){
        return (
            <header>
                <div className="flex-search-bar">
                    <div className="search-bar-logo">
                        <a href="./index.html">
                            <img title="Home" className="mlogo" src={logo} alt="LOGO"></img>
                        </a>
                    </div>

                   <ChipButton />
                </div>
            </header>
        );
    }
}

export default TopBar;

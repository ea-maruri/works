import React from "react";
import ReactDOM from "react-dom";
import "./index/index.css";

// Components
import Home from "./components/home/home";
import About from "./components/about/about";


import * as serviceWorker from "./serviceWorker";

// Put 
// <Home />
// <About />

ReactDOM.render(
  <React.StrictMode>
    <About />
  </React.StrictMode>,
  document.getElementById("root-index")
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();

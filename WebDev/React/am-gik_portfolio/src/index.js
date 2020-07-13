import React from "react";
import ReactDOM from "react-dom";
import "./index/index.css";

// Components
import App from "./app/App";
import Home from "./pages/Home";
import About from "./pages/About";
import Portfolio from "./pages/Portfolio";

import * as serviceWorker from "./serviceWorker";

// Put
// <Home />
// <About />
// <Portfolio />
// <CommonElement />
//history={hashHistory}

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById("root-index")
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();

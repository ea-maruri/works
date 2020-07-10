import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';


// Components
import App from './app/App'; // It is not used
import TopBar from './top-bar/top-bar';

import * as serviceWorker from './serviceWorker';

ReactDOM.render(
  <React.StrictMode>
    <App />
    <TopBar />
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();

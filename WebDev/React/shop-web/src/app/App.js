import React from 'react';
import logo from './logo.svg';
import './App.css';
import HttpService from '../services/htttp-service';


const http = new HttpService();

class App extends React.Component {
  constructor(props){
    super(props);

    console.log("Suppose I make an http request");

    // We need to bind functions
    this.loadData = this.loadData.bind(this);
    this.loadData();
    //http.getProducts();
  }


  loadData = () => {
    http.getProducts().then(products => {
      console.log(products);
    }, err => {
      console.log("Something went wrong!");
    });
  }


  render(){
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1>Welcome to EA-SHOP</h1>
          <p>
            Edit <code>src/App.js</code> and save to reload.
          </p>
          <a
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
          >
            Learn React
          </a>
        </header>
      </div>
    );
  }
}

export default App;

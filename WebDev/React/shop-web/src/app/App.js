import React from "react";
import logo from "./logo.svg";
import "./App.css";
import Product from "../product/product";

import HttpService from "../services/htttp-service";

const http = new HttpService();

class App extends React.Component {
  constructor(props) {
    super(props);

    this.state = { products: [] };

    console.log("Suppose I make an http request");

    // We need to bind functions
    this.productList = this.productList.bind(this);
    //this.loadData = this.loadData.bind(this);
    //this.loadData();
    //http.getProducts();
  }


  loadData = () => {
    http.getProducts().then(data => {
        this.setState({products: data});
      },
      (err) => {
        //console.log("Something went wrong!");
      }
    );
  };


  productList = () => {
    const list = this.state.products.map((product) => 
      <div className="col-sm-12 col-md-4" key={product._id}>
        <Product
          title={product.title}
          price={product.price}
          imgUrl={product.imgUrl}
        />
      </div>
    );

    return (list);
  }


  render() {
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

        <div className="container App-main">
          <div className="row">
            <div className="col flex-row">
              {this.productList()}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default App;

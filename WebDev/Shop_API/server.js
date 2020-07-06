var express = require("express");
var app = express(); // Constructs the app
var bodyParser = require("body-parser");
var mongoose = require("mongoose");
var db = mongoose.connect("mongodb://localhost/shop"); // Create database

var Product = require("./model/product");
var WishList = require("./model/wishlist");
const { request } = require("http");
const { response } = require("express");

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended:false}));


// Create post Request
app.post("/product", function(request, response){
    //var product = new Product(request.body);
    //var product = new Product({title: request.body.title, price: request.body.price})
    var product = new Product(); // Using model
    product.title = request.body.title;
    product.price = request.body.price;

    product.save(function(err, savedProduct){
        if(err){
            response.status(500).send({error: "Coul not save the product"})
        }
        else {
            response.status(200).send(savedProduct);
        }
    })
});


// Create get request. It is Asynchronous. Processes are separate from main process in threads
app.get("/product", function(request, response){
   
    Product.find({}, function(err, products){
        if(err){
            response.status(500).send({error: "Could not fecth the products"});
        }
        else {
            response.send(products);
        }
    })
})



// Run API
app.listen(3000, function(){
    console.log("Shop API running on port 3000..."); 
})

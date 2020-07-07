var express = require("express");
var app = express(); // Constructs the app
var bodyParser = require("body-parser");
var mongoose = require("mongoose");
var db = mongoose.connect("mongodb://localhost/shop"); // Create database

var Product = require("./model/product");
var WishList = require("./model/wishlist");
const { request } = require("http");
const { response } = require("express");
const { model } = require("./model/product");

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
            response.status(500).send({error: "Coul not save the product"});
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
            response.status(500).send({error: "Could not fetch the products"});
        }
        else {
            response.send(products);
        }
    })
})


// Create post request wishlist
app.post("/wishlist", function(request, response){
    var wishlist = new WishList();
    wishlist.title = request.body.title;

    wishlist.save(function(err, savedWishlist){
        if(err){
            response.status(500).send({error: "Could not create wishlist"});
        }
        else{
            response.send(wishlist);
        }
    })
})


// Create get request wishlist
app.get("/wishlist", function(request, response){
    WishList.find({}).populate({path:'products', model: "Product"}).exec(function(err, wishlists){
        if(err){
            response.status(500).send({error: "Could not fetch wishlists"});
        }
        else{
            response.status(200).send(wishlists);
        }
    })
})


// Update items on wishlist
app.put("/wishlist/product/add", function(request, response){
    Product.findOne({_id: request.body.productId}, function(err, product){
        if(err){
            response.status(500).send({error: "Could not put the new product to wishlist"});
        }
        else {
            WishList.update({_id: request.body.wishlistId}, 
                {$addToSet: {
                    products: product._id}},
                    function(err, wishlist){
                        if(err){
                            response.status(500).send({error: "Could not put the new product to wishlist"});
                        }
                        else {
                            //response.send("Suppose Successfully")
                            response.send(wishlist);
                        }
                })
        }
    })
})

// Run API
app.listen(3000, function(){
    console.log("Shop API running on port 3000..."); 
})

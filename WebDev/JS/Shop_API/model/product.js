var mongoose = require("mongoose");
const { type } = require("os");
var Schema = mongoose.Schema;  // Skin behind data

var product = new Schema({
    title: String,
    price: Number,
    likes: {type: Number, default: 0},
})


// Export it with "Product" as name and pass the schema.
module.exports = mongoose.model("Product", product);

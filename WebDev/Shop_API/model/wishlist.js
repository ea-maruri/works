var mongoose = require("mongoose");
var Schema = mongoose.Schema;  // Skin behind data
var ObjectId = mongoose.Schema.Types.ObjectId;

var wishlist = new Schema({
    title: {type: String, default: "My Cool WishList", required: true},
    // Referenc of Product in "product.js". Having a relation between Products and the list.
    // An array of Products.
    product: [{type: ObjectId, ref: "Product"}]
})


// Export it with "Product" as name and pass the schema.
module.exports = mongoose.model("Wishlist", wishlist);

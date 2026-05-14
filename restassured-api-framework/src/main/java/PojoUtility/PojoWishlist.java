package PojoUtility;

public class PojoWishlist {


    private int productId;
    private int quantity;

    // No-args constructor (needed for deserialization)
    public PojoWishlist() {}
	

    // All-args constructor
    public PojoWishlist(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;

}
}

package constantEndpoints;

public interface IEndPoints {

	public String ShopperCart ="/shoppers/{shopperId}/carts";
	public String ShopperCartPut="/shoppers/{shopperId}/carts/{itemId}";

	
	public String ShopperCartGet="/products";
	public String ShopperCartDelete="/shoppers/{shopperId}/carts/{productId}";

//>>>>>>> branch 'SecondBranch' of https://github.com/Divya-Vijay-Naik/ShopperStackProject.git

	public String GetAddress="/shoppers/{shopperId}/address";
	public String GetParticularAddress="/shoppers/{shopperId}/address/{addressId}";
	public String GetProductFromWishlist="/shoppers/{shopperId}/wishlist";
	public String DeleteProductFromWishlist="/shoppers/{shopperId}/wishlist/{productId}";
	public String GetProductReview="/reviews/{productId}";
	public String AddProductReview="/reviews";
	public String UpdateProductReview="/reviews/{reviewId}";
	public String GetProduct="/products";
	public String GetProductAlpha="/products/alpha";

//>>>>>>> branch 'SecondBranch' of https://github.com/Divya-Vijay-Naik/ShopperStackProject.git


	    String REGISTER_SHOPPER="/shoppers";

	    String LOGIN="/users/login";

	    String GET_SHOPPER="/shoppers/{shopperId}";

	    String UPDATE_SHOPPER="/shoppers/{shopperId}";
	    
	    String DELETE_SHOPPER = "/shoppers/{shopperId}";


}

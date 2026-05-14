package PojoUtility;

public class PojoProductReview {
	private String reviewDateTime;    
    private String reviewDescription; 
    private String reviewTitle;       
    private int rating;
    private int shopperId;
    private String shopperName;

    // No-args constructor
    public PojoProductReview() {}

    // Updated All-args constructor
    public PojoProductReview(String reviewDateTime, String reviewDescription, String reviewTitle,
                  int rating, int shopperId, String shopperName) {
        this.reviewDateTime = reviewDateTime;
        this.reviewDescription = reviewDescription;
        this.reviewTitle = reviewTitle;
        this.rating = rating;
        this.shopperId = shopperId;
        this.shopperName = shopperName;
    }

    // Updated Getters and Setters
    public String getReviewDateTime() {
        return reviewDateTime;
    }

    public void setReviewDateTime(String reviewDateTime) {
        this.reviewDateTime = reviewDateTime;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getShopperId() {
        return shopperId;
    }

    public void setShopperId(int shopperId) {
        this.shopperId = shopperId;
    }

    public String getShopperName() {
        return shopperName;
    }

    public void setShopperName(String shopperName) {
        this.shopperName = shopperName;
    }
	}



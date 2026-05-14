package com.shopperStack;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import GenericUtility.BaseAPIclass;
import PojoUtility.PojoWishlist;
import constantEndpoints.IEndPoints;
import io.restassured.response.Response;

public class ShopperStackWishlist extends BaseAPIclass {
	PojoWishlist pojo;
	String token="";
	String shopperId="";
	String ProductId="";
	
	
	@Test(priority = 2)
	public void GetProductFromWishlistTest() throws Throwable
	{
		shopperId = excelUtility.getDataFromExcel("Sheet1", 1, 0);
    	token=excelUtility.getDataFromExcel("Sheet1", 1, 1);

        Response resp = given()
                .spec(spcReqobj)
                .pathParam("shopperId", shopperId)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(IEndPoints.GetProductFromWishlist);

        resp.then()
                .assertThat().statusCode(200)
                .spec(spcRespobj)
                .log().all();
        
        
	}
	
	@Test(priority = 1)
	public void AddProductToWishlistTest() throws Throwable
	{
		shopperId = excelUtility.getDataFromExcel("Sheet1", 1, 0);
    	token=excelUtility.getDataFromExcel("Sheet1", 1, 1);
    	
		int productId = Integer.parseInt(excelUtility.getDataFromExcel("Sheet1", 7, 13));
        int quantity = Integer.parseInt(excelUtility.getDataFromExcel("Sheet1", 7, 14));
        
        pojo =new PojoWishlist(productId,quantity);
        
        
        Response resp = given()
            .spec(spcReqobj)
            .pathParam("shopperId", shopperId)
            .header("Authorization", "Bearer " + token)
            .body(pojo)
            .when()
            .post(IEndPoints.GetProductFromWishlist);

        resp.then()
            .assertThat().statusCode(201) // POST should return 201 Created
            .assertThat().time(Matchers.lessThan(3000L))
            .spec(spcRespobj)
            .log().all();
        
    System.out.println("Product Added to wishlist");
    String itemId = resp.jsonPath().getString("data.itemId");
    System.out.println("Created Item ID: " + itemId);
    excelUtility.writeDataIntoExcel("Sheet1",7 ,14, itemId);
}
	@Test(priority = 3)
	public void DeleteProductFromWishlist() throws EncryptedDocumentException, IOException
	    {
	    token=excelUtility.getDataFromExcel("Sheet1", 1, 1);
	    shopperId=excelUtility.getDataFromExcel("Sheet1", 1, 0);
	    ProductId=excelUtility.getDataFromExcel("Sheet1", 7, 13);
	    	Response resp = given()
	                .spec(spcReqobj)
	                .pathParam("shopperId", shopperId)
	                .pathParam("productId", ProductId)
	                .header("Authorization", "Bearer " + token)
	              //  .body(pojo)
	                .when()
	                .delete(IEndPoints.DeleteProductFromWishlist);

	        resp.then()
	              .assertThat().statusCode(204) // POST should return 201 Created
	                .assertThat().time(Matchers.lessThan(3000L))
	                .spec(spcRespobj)
	                .log().all();
	        
	        System.out.println("Successfully Deleted");


	    }
}

package com.shopperStack;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.EncryptedDocumentException;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import GenericUtility.BaseAPIclass;
import PojoUtility.PojoProductReview;

import constantEndpoints.IEndPoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;



public class ShopperStackProductReview extends BaseAPIclass {
	PojoProductReview pojo;
	String token="";
	String shopperId="";
	String ProductId="";
	String reviewId="";
	
	@Test
	public void AddProductReviewTest() throws Throwable
	{
		shopperId = excelUtility.getDataFromExcel("Sheet1", 1, 0);
    	token=excelUtility.getDataFromExcel("Sheet1", 1, 1);
    	ProductId=excelUtility.getDataFromExcel("Sheet1", 7, 13);
    	
    	String dateTime = excelUtility.getDataFromExcel("Sheet1", 7, 16);
    	String description = excelUtility.getDataFromExcel("Sheet1", 7, 17);
    	String heading = excelUtility.getDataFromExcel("Sheet1", 7, 18);
    	int rating = Integer.parseInt(excelUtility.getDataFromExcel("Sheet1", 7, 19));
    	String shopperName = excelUtility.getDataFromExcel("Sheet1", 7, 6);
//    	OffsetDateTime dt = OffsetDateTime.parse(dateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
//    	String formatted = dt.format(DateTimeFormatter.ISO_INSTANT); 
    	//System.out.println("DateTime from Excel: " + dateTime);

        pojo =new PojoProductReview(dateTime,description,heading,rating,Integer.parseInt(shopperId),shopperName);
        RestAssured.useRelaxedHTTPSValidation();

        Response resp = given()
            .spec(spcReqobj)
            .log().all()
            .queryParam("productId", ProductId)
            .header("Authorization", "Bearer " + token)
            .body(pojo)
            .when()
            .post(IEndPoints.AddProductReview);
        System.out.println("Error Message from Server: " + resp.asString());

        resp.then()
            .assertThat().statusCode(200) // POST should return 201 Created
            .assertThat().time(Matchers.lessThan(3000L))
            .spec(spcRespobj)
            .log().all();
        
//    System.out.println("Product Added to wishlist");
//    String itemId = resp.jsonPath().getString("data.itemId");
//    System.out.println("Created Item ID: " + itemId);
//    excelUtility.writeDataIntoExcel("Sheet1",7 ,14, itemId);
}

	@Test
	public void GetProductReviews() throws EncryptedDocumentException, IOException
	{
		token=excelUtility.getDataFromExcel("Sheet1", 1, 1);
		ProductId=excelUtility.getDataFromExcel("Sheet1", 7, 13);
        Response resp = given()
                .spec(spcReqobj)
                .pathParam("productId", ProductId)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(IEndPoints.GetProductReview);

        resp.then()
                .assertThat().statusCode(200)
                .spec(spcRespobj)
                .log().all();
	}
	@Test
	public void DeleteProductReviewTest() throws EncryptedDocumentException, IOException
	{
		token=excelUtility.getDataFromExcel("Sheet1", 1, 1);
		ProductId=excelUtility.getDataFromExcel("Sheet1", 7, 13);
		reviewId=excelUtility.getDataFromExcel("Sheet1", 7, 20);
        Response resp = given()
                .spec(spcReqobj)
                .pathParam("productId", ProductId)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(IEndPoints.GetProductReview);

        resp.then()
                .assertThat().statusCode(200)
                .spec(spcRespobj)
                .log().all();
	}
}

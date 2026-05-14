package com.shopperStack;

import GenericUtility.BaseAPIclass;
import constantEndpoints.IEndPoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CartModuleTest extends BaseAPIclass {
	

	@Test(priority = 1)
	public void getProductId() throws Throwable {
		String jwtToken = excelUtility.getDataFromExcel("Sheet1", 1, 1);
        

	    Response productResponse = given()
	            .spec(spcReqobj)
	            .header("Authorization", "Bearer " + jwtToken)
	            .queryParam("zoneId", "ALPHA")   
	            .when()
	            .get(IEndPoints.ShopperCartGet)     
	            .then()
	            .extract().response();

	    System.out.println("Products response: " + productResponse.asString());

	    int productId = productResponse.jsonPath().getInt("data[0].productId");
	    excelUtility.writeDataIntoExcel("Sheet1", 4, 0, String.valueOf(productId));
	}

	@Test(priority = 3)
	public void getCartItems() throws Throwable {
	    int shopperId = Integer.parseInt(excelUtility.getDataFromExcel("Sheet1", 1, 0));
	    String jwtToken = excelUtility.getDataFromExcel("Sheet1", 1, 1);

	    Response getResponse = given()
	            .spec(spcReqobj)
	            .header("Authorization", "Bearer " + jwtToken)
	            .pathParam("shopperId", shopperId)
	            .when()
	            .get(IEndPoints.ShopperCart)
	            .then()
	            .extract().response();

	    System.out.println("Status: " + getResponse.getStatusCode());
	    System.out.println("Headers: " + getResponse.getHeaders());
	    System.out.println("Cart contents: " + getResponse.asString());

	    // Only proceed if JSON is returned
	    if (getResponse.getContentType() != null && getResponse.getContentType().contains("application/json")) {
	        int productId = getResponse.jsonPath().getInt("data[0].productId");
	        excelUtility.writeDataIntoExcel("Sheet1", 4, 0, String.valueOf(productId));
	    } else {
	        throw new RuntimeException("Expected JSON but got: " + getResponse.getContentType());
	    }
	}
    @Test(priority = 2)
    public void addCartItem() throws Throwable {
        int shopperId = Integer.parseInt(excelUtility.getDataFromExcel("Sheet1", 1, 0));
        int productId = Integer.parseInt(excelUtility.getDataFromExcel("Sheet1", 4, 0));
        int quantity  = Integer.parseInt(excelUtility.getDataFromExcel("Sheet1", 4, 1));

        String requestBody = String.format("{ \"productId\": %d, \"quantity\": %d }", productId, quantity);
        String jwtToken = excelUtility.getDataFromExcel("Sheet1", 1, 1);
        Response postResponse = given()
                .spec(spcReqobj)
                .header("Authorization", "Bearer " + jwtToken)
                .pathParam("shopperId", shopperId)
                .body(requestBody)
                .when()
                .post(IEndPoints.ShopperCart)
                .then()
                .spec(spcRespobj)
                .extract().response();

        System.out.println("Add item response: " + postResponse.asString());

        // Extract itemId from POST response and write to row 5 col 2 (ItemId)
        int itemId = postResponse.jsonPath().getInt("data.itemId");
        excelUtility.writeDataIntoExcel("Sheet1", 4, 2, String.valueOf(itemId));
    }

    @Test(priority = 4, dependsOnMethods = "addCartItem")
    public void updateCartItem() throws Exception {
        int shopperId = Integer.parseInt(excelUtility.getDataFromExcel("Sheet1", 1, 0));
        int productId = Integer.parseInt(excelUtility.getDataFromExcel("Sheet1", 4, 0));
        int quantity  = Integer.parseInt(excelUtility.getDataFromExcel("Sheet1", 4, 1));
        int itemId    = Integer.parseInt(excelUtility.getDataFromExcel("Sheet1", 4, 2));

        String updateBody = String.format("{ \"productId\": %d, \"quantity\": %d }", productId, quantity);
        String jwtToken = excelUtility.getDataFromExcel("Sheet1", 1, 1);
        Response putResponse = given()
                .spec(spcReqobj)
                .header("Authorization", "Bearer " + jwtToken)
                .pathParam("shopperId", shopperId)
                .pathParam("itemId", itemId)
                .body(updateBody)
                .when()
                .put(IEndPoints.ShopperCartPut)
                .then()
                .spec(spcRespobj)
                .extract().response();

        System.out.println("Update response: " + putResponse.asString());
    }

    @Test(priority = 5)
    public void deleteCartItem() throws Exception {
        int shopperId = Integer.parseInt(excelUtility.getDataFromExcel("Sheet1", 1, 0));
        int productId = Integer.parseInt(excelUtility.getDataFromExcel("Sheet1", 4, 0));
        String jwtToken = excelUtility.getDataFromExcel("Sheet1", 1, 1);
        Response deleteResponse = given()
                .spec(spcReqobj)
                .header("Authorization", "Bearer " + jwtToken)
                .pathParam("shopperId", shopperId)
                .pathParam("productId", productId)
                .when()
                .delete(IEndPoints.ShopperCartDelete)
                .then()
                .spec(spcRespobj)
                .extract().response();

        System.out.println("Delete response: " + deleteResponse.asString());
    }
}

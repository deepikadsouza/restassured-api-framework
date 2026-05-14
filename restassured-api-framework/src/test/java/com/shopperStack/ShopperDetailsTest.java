package com.shopperStack;

import org.testng.annotations.Test;

import GenericUtility.BaseAPIclass;
import GenericUtility.ExcelUtility;
import constantEndpoints.IEndPoints;
import static io.restassured.RestAssured.*;

public class ShopperDetailsTest  extends BaseAPIclass {
	
	
	
	@Test
	public void getShopperDetailsTest() throws Throwable
	{
	    ExcelUtility eUtil = new ExcelUtility();

	    String shopperId = eUtil.getDataFromExcel("Sheet1",1,0);
	    String token = eUtil.getDataFromExcel("Sheet1",1,1);

	    given()
	        .relaxedHTTPSValidation()
	        .auth().oauth2(token)
	        .pathParam("shopperId", shopperId)

	    .when()
	        .get(IEndPoints.GET_SHOPPER)

	    .then()
	        .log().all();
	}
}
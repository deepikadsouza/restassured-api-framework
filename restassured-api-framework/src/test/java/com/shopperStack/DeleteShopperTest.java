package com.shopperStack;

import org.testng.annotations.Test;

import GenericUtility.BaseAPIclass;
import GenericUtility.ExcelUtility;
import constantEndpoints.IEndPoints;
import static io.restassured.RestAssured.*;

public class DeleteShopperTest  extends BaseAPIclass {
	

	
	@Test
	public void deleteShopperTest() throws Throwable
	{
	    ExcelUtility eUtil = new ExcelUtility();

	    String shopperId = eUtil.getDataFromExcel("Sheet1",1,0);
	    String token = eUtil.getDataFromExcel("Sheet1",1,1);

	    given()
	        .relaxedHTTPSValidation()
	        .auth().oauth2(token)
	        .pathParam("shopperId", shopperId)

	    .when()
	        .delete(IEndPoints.DELETE_SHOPPER)

	    .then()
	        .log().all();
	}

}

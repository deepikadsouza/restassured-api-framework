package com.shopperStack;

import org.testng.annotations.Test;

import GenericUtility.BaseAPIclass;
import GenericUtility.ExcelUtility;
import PojoUtility.UpdateShopperPojo;
import constantEndpoints.IEndPoints;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

public class UpdateShopperTest  extends BaseAPIclass {
	
	@Test
	public void updateShopperTest() throws Throwable
	{
	    ExcelUtility eUtil = new ExcelUtility();

	    String shopperId = eUtil.getDataFromExcel("Sheet1",1,0);
	    String token = eUtil.getDataFromExcel("Sheet1",1,1);

	    UpdateShopperPojo body = new UpdateShopperPojo(
	            "Mysore",
	            "India",
	            "John",
	            "Doe",
	            "9876543210",
	            "Karnataka"
	    );

	    given()
	        .relaxedHTTPSValidation()
	        .auth().oauth2(token)
	        .pathParam("shopperId", shopperId)
	        .contentType(ContentType.JSON)
	        .body(body)

	    .when()
	        .patch(IEndPoints.UPDATE_SHOPPER)

	    .then()
	        .log().all();
	}

}

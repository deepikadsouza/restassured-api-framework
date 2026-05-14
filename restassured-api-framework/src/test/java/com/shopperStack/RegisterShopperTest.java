package com.shopperStack;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import GenericUtility.BaseAPIclass;
import PojoUtility.RegisterShopperPojo;
import constantEndpoints.IEndPoints;
import io.restassured.http.ContentType;

public class RegisterShopperTest extends BaseAPIclass {
	
	   public static String email; 

    @Test
    public void registerShopperTest() {
    	
    	RegisterShopperPojo body = new RegisterShopperPojo(
    	        "Bangalore",
    	        "India",
    	        "test"+System.currentTimeMillis()+"@gmail.com",
    	        "John",
    	        "MALE",
    	        "Doe",
    	        "Password@123",
    	        "9876543210",
    	        "Karnataka"
    	);

    	given()
    	.relaxedHTTPSValidation()
    	.queryParam("zoneId", "ALPHA")
    	.contentType(ContentType.JSON)
    	.body(body)

    	.when()
    	.post(IEndPoints.REGISTER_SHOPPER)

    	.then()
    	.log().all();
    }
}

    
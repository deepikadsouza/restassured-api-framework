package com.shopperStack;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import GenericUtility.BaseAPIclass;
import constantEndpoints.IEndPoints;
import io.restassured.response.Response;

public class ShopperStackProduct extends BaseAPIclass {
	String token="";
//	String shopperId="";
//	String ProductId="";
	String zoneId="";
	
	
	@Test
	public void GetProductTest() throws EncryptedDocumentException, IOException
	{
		zoneId = excelUtility.getDataFromExcel("Sheet1", 7, 20);
    	token=excelUtility.getDataFromExcel("Sheet1", 1, 1);

        Response resp = given()
                .spec(spcReqobj)
                .queryParam("zoneId", zoneId)
                .header("Authorization", "Bearer " + token)
                .when()
                .get(IEndPoints.GetProduct);

        resp.then()
                .assertThat().statusCode(200)
                .spec(spcRespobj)
                .log().all();

	}

}

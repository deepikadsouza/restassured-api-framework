package com.shopperStack;

import org.testng.Assert;
import org.testng.annotations.Test;

import GenericUtility.BaseAPIclass;
import PojoUtility.PojoAddressClass;
import constantEndpoints.IEndPoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

//import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.hamcrest.Matchers;

public class ShopperStackAddress extends BaseAPIclass {
		PojoAddressClass pojo;
		String token="";
		String shopperId="";
		String addressId="";
		    @Test(priority = 1)
		    public void AddShopperAddresseTest() throws Throwable {
		        //RestAssured.baseURI = "http://your-api-base-url.com"; // replace with actual base URL
		    	token=excelUtility.getDataFromExcel("Sheet1", 1, 1);
		    	shopperId = excelUtility.getDataFromExcel("Sheet1", 1, 0); // replace with actual shopperId
		       
		    	int addressIds = Integer.parseInt(excelUtility.getDataFromExcel("Sheet1", 7, 1));
		        String buildingInfo = excelUtility.getDataFromExcel("Sheet1", 7, 2);
		        String city = excelUtility.getDataFromExcel("Sheet1", 7, 3);
		        String country = excelUtility.getDataFromExcel("Sheet1", 7, 4);
		        String landmark = excelUtility.getDataFromExcel("Sheet1", 7, 5);
		        String name = excelUtility.getDataFromExcel("Sheet1", 7, 6);
		        String phone = excelUtility.getDataFromExcel("Sheet1", 7, 7);
		        String pincode = excelUtility.getDataFromExcel("Sheet1", 7, 8);
		        String state = excelUtility.getDataFromExcel("Sheet1", 7, 9);
		        String streetInfo = excelUtility.getDataFromExcel("Sheet1", 7, 10);
		        String type = excelUtility.getDataFromExcel("Sheet1", 7, 11);

		        PojoAddressClass pojo = new PojoAddressClass(
		                addressIds, buildingInfo, city, country, landmark,
		                name, phone, pincode, state, streetInfo, type
		            );

		        
		        RestAssured.useRelaxedHTTPSValidation();

		        Response resp = given()
		                .spec(spcReqobj)
		                .pathParam("shopperId", shopperId)
		                .header("Authorization", "Bearer " + token)
		                .body(pojo)
		                .when()
		                .post(IEndPoints.GetAddress);

		        resp.then()
		                .assertThat().statusCode(201) // POST should return 201 Created
		                .assertThat().time(Matchers.lessThan(3000L))
		                .spec(spcRespobj)
		                .log().all();

		        String addressId = resp.jsonPath().getString("data.addressId");
		        System.out.println("Created Address ID: " + addressId);
		        excelUtility.writeDataIntoExcel("Sheet1",7 ,0, addressId);		
		    }

		    @Test(priority = 2)
		    public void GetShopperAddressesTest() throws EncryptedDocumentException, IOException {
		        //RestAssured.baseURI = "http://your-api-base-url.com"; // replace with actual base URL

		    	shopperId = excelUtility.getDataFromExcel("Sheet1", 1, 0);
		    	token=excelUtility.getDataFromExcel("Sheet1", 1, 1);

		        Response resp = given()
		                .spec(spcReqobj)
		                .pathParam("shopperId", shopperId)
		                .header("Authorization", "Bearer " + token)
		                //.body(pojo)
		                .when()
		                .get(IEndPoints.GetAddress);

		        resp.then()
		                .assertThat().statusCode(200)
		        	.spec(spcRespobj)
		        	.log().all();
		    }
		    
		   
		    
		    @Test(priority = 3)
		    public void UpdateAddress() throws Throwable
		    {
		    	token=excelUtility.getDataFromExcel("Sheet1", 1, 1);
		    	String randomno=jlib.getRandomString(10);
		    	addressId=excelUtility.getDataFromExcel("Sheet1", 7, 0);
		        shopperId = excelUtility.getDataFromExcel("Sheet1", 1, 0); // replace with actual shopperId
		        
		        int addressIds = Integer.parseInt(excelUtility.getDataFromExcel("Sheet1", 7, 1));
		        String buildingInfo = excelUtility.getDataFromExcel("Sheet1", 7, 2);
		        String city = excelUtility.getDataFromExcel("Sheet1", 7, 3);
		        String country = excelUtility.getDataFromExcel("Sheet1", 7, 4);
		        String landmark = excelUtility.getDataFromExcel("Sheet1", 7, 5);
		        String name = excelUtility.getDataFromExcel("Sheet1", 7, 6);
		        String phone = excelUtility.getDataFromExcel("Sheet1", 7, 7);
		        String pincode = excelUtility.getDataFromExcel("Sheet1", 7, 8);
		        String state = excelUtility.getDataFromExcel("Sheet1", 7, 9);
		        String streetInfo = excelUtility.getDataFromExcel("Sheet1", 7, 10);
		        String type = excelUtility.getDataFromExcel("Sheet1", 7, 11);

		        PojoAddressClass pojo = new PojoAddressClass(
		                addressIds, buildingInfo+randomno, city, country, landmark,
		                name, phone, pincode, state, streetInfo, type
		            );

		        
		        RestAssured.useRelaxedHTTPSValidation();

		        Response resp = given()
		                .spec(spcReqobj)
		                .pathParam("shopperId", shopperId)
		                .pathParam("addressId", addressId)
		                .header("Authorization", "Bearer " + token)
		                .body(pojo)
		                .when()
		                .put(IEndPoints.GetParticularAddress);

		        resp.then()
		                .assertThat().statusCode(200) // POST should return 201 Created
		                .assertThat().time(Matchers.lessThan(3000L))
		                .spec(spcRespobj)
		                .log().all();

		        String buildlinginfo = resp.jsonPath().getString("data.buildingInfo");
		        System.out.println("Updated: " + buildingInfo);
		        excelUtility.writeDataIntoExcel("Sheet1",7 ,12,buildingInfo);
		    }
		    
		    @Test(priority = 4)
		    public void GetParticularShopperAddressesTest() throws EncryptedDocumentException, IOException {
		        //RestAssured.baseURI = "http://your-api-base-url.com"; // replace with actual base URL

		    	shopperId = excelUtility.getDataFromExcel("Sheet1", 1, 0);
		    	token=excelUtility.getDataFromExcel("Sheet1", 1, 1);
		    	addressId=excelUtility.getDataFromExcel("Sheet1", 7, 0);

		        Response resp = given()
		                .spec(spcReqobj)
		                .pathParam("shopperId", shopperId)
		                .pathParam("addressId", addressId)
		                .header("Authorization", "Bearer " + token)
		                //.body(pojo)
		                .when()
		                .get(IEndPoints.GetParticularAddress);

		        resp.then()
		                .assertThat().statusCode(200)
		        	.spec(spcRespobj)
		        	.log().all();
		    }
		    
		    
		    @Test(priority = 5)
		    public void DeleteAddress() throws EncryptedDocumentException, IOException
		    {
		    token=excelUtility.getDataFromExcel("Sheet1", 1, 1);
		    shopperId=excelUtility.getDataFromExcel("Sheet1", 1, 0);
		    addressId=excelUtility.getDataFromExcel("Sheet1", 7, 0);
		    	Response resp = given()
		                .spec(spcReqobj)
		                .pathParam("shopperId", shopperId)
		                .pathParam("addressId", addressId)
		                .header("Authorization", "Bearer " + token)
		              //  .body(pojo)
		                .when()
		                .delete(IEndPoints.GetParticularAddress);

		        resp.then()
		              .assertThat().statusCode(204) // POST should return 201 Created
		                .assertThat().time(Matchers.lessThan(3000L))
		                .spec(spcRespobj)
		                .log().all();
		        
		        System.out.println("Successfully Deleted");


		    }
		     
		
	}




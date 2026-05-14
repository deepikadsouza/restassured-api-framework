package com.shopperStack;

import org.testng.annotations.Test;

import GenericUtility.BaseAPIclass;
import GenericUtility.ExcelUtility;
import PojoUtility.LoginPojo;
import constantEndpoints.IEndPoints;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;



public class ShopperLoginTest extends BaseAPIclass {

    @Test
    public void shopperLoginTest() throws Throwable {

        String email = excelUtility.getDataFromExcel("Sheet1",1,9);
        System.out.println(email);
        String password = excelUtility.getDataFromExcel("Sheet1",1,6);
        System.out.println(password);

        LoginPojo body = new LoginPojo(
                email,
                password,
                "SHOPPER"
        );
        Response response =
        given()
            .spec(spcReqobj)
            .relaxedHTTPSValidation()
            .contentType(ContentType.JSON)
            .body(body)

        .when()
            .post(IEndPoints.LOGIN)

        .then()
            .log().all()
            .statusCode(200)
            .extract().response();
        
        // Capture userId and JWT token
        int userId = response.jsonPath().getInt("data.userId");
        String token = response.jsonPath().getString("data.jwtToken");

        System.out.println("UserId: " + userId);
        System.out.println("JWT Token: " + token);

        // Write values into Excel
        excelUtility.writeDataIntoExcel("Sheet1",1,0,String.valueOf(userId));
        excelUtility.writeDataIntoExcel("Sheet1",1,1,token);
    }

    }

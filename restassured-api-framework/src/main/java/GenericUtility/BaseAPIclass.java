package GenericUtility;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import GenericUtility.FileUtility;
import GenericUtility.JavaUtility;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseAPIclass {
	public JavaUtility jlib=new JavaUtility();
	public FileUtility flib=new FileUtility();
	public ExcelUtility excelUtility=new ExcelUtility();
	
	public static RequestSpecification spcReqobj;
	public static ResponseSpecification spcRespobj;

	@BeforeSuite
	public void configDB() throws Exception
	{
		System.out.println("Before suite DB connection");
		RequestSpecBuilder req=new RequestSpecBuilder();
		req.setContentType(ContentType.JSON);
		req.setBaseUri(flib.getDataFromProperties("BASEUri"));
		 spcReqobj = req.build();
	}
		   @BeforeClass
		    public void config() {

		       // RestAssured.baseURI = "https://www.shoppersstack.com/shopping";
		        RestAssured.useRelaxedHTTPSValidation();

		 
		 ResponseSpecBuilder resp=new ResponseSpecBuilder();
		 //resp.expectContentType(ContentType.JSON);
		 spcRespobj=resp.build();
	}
	
	@AfterSuite
	public void aftersuite()
	{
		System.out.println("Aftersuite Close DB connection");
	}
}

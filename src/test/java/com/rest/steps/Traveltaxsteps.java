package com.rest.steps;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections4.functors.CatchAndRethrowClosure;
import org.json.simple.JSONObject;
import org.testng.Assert;

import com.google.gson.JsonArray;
import com.rest.utils.Baseclass;
import com.rest.utils.Excelread;
import com.rest.utils.ReportRuntimeListener;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;



public class Traveltaxsteps extends Baseclass {
	String baseURI="https://traveltaxdaytest.optisolbusiness.com";
	public static Random rand;
	RequestSpecification request;
	Response response;
	JSONObject payload;
	public static String userid;
	public static String email;
	String mobilenumber;
	int statuscode;
	String bodystatus;
	String bodymessage;
	public static String accesstoken;
	public static String travelid;
	Excelread excel=new Excelread();
	ReportRuntimeListener rrl=new ReportRuntimeListener();


	@Given("I have an traveltax register API")
	public void I_have_an_traveltax_register_API() throws Throwable {
		rrl.getReportInstance();
		rrl.reportCreateFeature("To validate CRUD operations");
		rrl.reportCreateScenario("User registers an account");
		rrl.createStep("Given", "I have an traveltax register API " + baseURI);
		RestAssured.baseURI=baseURI;
		request=RestAssured.given();
	}

	@When("I post the register payload request")
	public void i_post_the_register_payload_request() throws Throwable {

		rrl.createStep("When","I post the register payload request");
		rand = new Random();
		email="anatravel" + rand.nextInt(100) + "@gmail.com";
		System.out.println(email);
		mobilenumber ="989067" + rand.nextInt(10000) ;
		System.out.println(mobilenumber);
		payload=new JSONObject();
		payload.put("firstName",getProperty("firstName", crudloc));
		payload.put("lastName",getProperty("lastName", crudloc));
		payload.put("mobileNo",mobilenumber);
		payload.put("userType",getProperty("userType", crudloc));
		payload.put("password",getProperty("password", crudloc));
		payload.put("confirmPassword",getProperty("confirmPassword", crudloc));
		payload.put("countryCode",getProperty("countryCode", crudloc));
		payload.put("email",email);		
		payload.put("resident",getProperty("resident", crudloc));
		response=request.contentType("application/json").body(payload).when().post(excel.getCellData("Testdata","URL",1));

	}

	@Then("I have created the user")
	public void i_have_created_the_user() throws ClassNotFoundException {
		rrl.createStep("Then", "I have created the user");
		System.out.println("****Pretty print****");
		response.prettyPrint();
		rrl.reportStepPass(response.prettyPrint());

	}

	@And("I am validating the register response")
	public void i_am_validating_the_register_response() throws ClassNotFoundException {
		try{
			rrl.createStep("Then", "I am validating the register response");
			//status code check
			statuscode=response.getStatusCode();
			System.out.println("***Status Code***"+ statuscode);
			Assert.assertEquals(200, statuscode);
			rrl.reportStepPass("Status Code - "+ statuscode);
			//checking the body 
			bodystatus = response.jsonPath().getString("status.status");
			System.out.println( "***BODY Status*** " + bodystatus );
			Assert.assertEquals("200",bodystatus);
			rrl.reportStepPass("BODY Status - " + bodystatus);
			bodymessage = response.jsonPath().getString("status.message");
			System.out.println("***BODY Message*** "+bodymessage);
			Assert.assertEquals("SUCCESS", bodymessage);
			rrl.reportStepPass(" BODY Message - "+bodymessage);
			//get user id
			userid=response.jsonPath().getString("entity.userId");
			System.out.println("***BODY Entity*** + "+userid);
			Assert.assertNotNull(userid);
		}
		catch(Exception e)
		{
			rrl.reportStepFail("Failure");
		}
	}
	@Given("I have an traveltax createpassword API")
	public void I_have_an_traveltax_createpassword_API() throws Throwable {
		rrl.reportCreateScenario("User creates a new password");
		rrl.createStep("Given", "I have an traveltax createpassword API " + baseURI);
		RestAssured.baseURI=baseURI;
		request=RestAssured.given();
	}

	@When("I post the create password payload request")
	public void I_post_the_create_password_payload_request() throws Exception {
		rrl.createStep("When", "I post the create password payload request");
		payload=new JSONObject();
		payload.put("email",email);
		System.out.println(email);
		payload.put("password",getProperty("password", crudloc));
		payload.put("confirmPassword",getProperty("confirmPassword", crudloc));
		response=request.contentType("application/json").body(payload).when().post(excel.getCellData("Testdata","URL",2));
	}

	@Then("I have created new password")
	public void I_have_created_new_password() throws ClassNotFoundException {
		rrl.createStep("Then", "I have created new password");
		System.out.println("****Pretty print****");
		rrl.reportStepPass(response.prettyPrint());
		response.prettyPrint();
	}

	@And("I am validating the create password response")
	public void I_am_validating_the_create_password_response() throws ClassNotFoundException {
		try {
			rrl.createStep("Then", "I am validating the create password response");
			//status code check
			statuscode=response.getStatusCode();
			System.out.println("***Status Code***"+ statuscode);
			Assert.assertEquals(200, statuscode);
			rrl.reportStepPass("Status Code - "+ statuscode);
			//validate response body
			bodystatus = response.jsonPath().getString("status.status");
			System.out.println( "***BODY Status*** " + bodystatus);
			Assert.assertEquals("200",bodystatus);
			rrl.reportStepPass("BODY Status - " + bodystatus);
			//validate response message
			bodymessage=response.jsonPath().getString("status.message");
			System.out.println( "***BODY Message*** " + bodymessage );
			Assert.assertEquals("SUCCESS",bodymessage);
			rrl.reportStepPass(" BODY Message - " + bodymessage);
		}
		catch(Exception e) {
			rrl.reportStepFail("Failure");
		}

	}
	@Given("I have an traveltax login API")
	public void I_have_an_traveltax_login_API() throws Throwable {
		rrl.reportCreateScenario("User Login with valid credentials");
		rrl.createStep("Given", "I have an traveltax login API " + baseURI);
		RestAssured.baseURI=baseURI;
		request=RestAssured.given();
	}
	@When("I post the login payload request")
	public void i_post_the_login_payload_request() throws Exception {
		rrl.createStep("When", "I post the login payload request");
		response=request.contentType("application/x-www-form-urlencoded; charset=utf-8")
				.formParam("username", email)
				.formParam("password", getProperty("password", crudloc)).when().post(excel.getCellData("Testdata","URL",3));
	}

	@Then("I have logged in")
	public void i_have_logged_in() {
		try {
			rrl.createStep("Then", "I have logged in");
			System.out.println("*** Pretty Print ***");
			response.prettyPrint();
			rrl.reportStepPass(response.prettyPrint());
			accesstoken=response.jsonPath().getString("access_token");
			System.out.println("*** Access token ***" + accesstoken);
			rrl.reportStepPass("Access token - " + accesstoken);
			//validating user id
			String userid1=response.jsonPath().getString("user_id");
			System.out.println("*** USER ID *** "+ userid);
			Assert.assertEquals(userid, userid1);
			rrl.reportStepPass("USER ID - "+ userid);
		}
		catch(Exception e)
		{
			rrl.reportStepFail("Failure");
		}
	}

	@Then("I am validating the login response")
	public void i_am_validating_the_login_response() throws ClassNotFoundException {
		try {
			rrl.createStep("Then", "I am validating the login response");
			//status code check
			statuscode=response.getStatusCode();
			System.out.println("***Status Code***"+ statuscode);
			Assert.assertEquals(200, statuscode);
			rrl.reportStepPass("Status Code - "+ statuscode);
			//validate response body
			bodystatus = response.jsonPath().getString("status.status");
			System.out.println( "***BODY Status*** " + bodystatus );
			Assert.assertEquals("200",bodystatus);
			rrl.reportStepPass("BODY Status - " + bodystatus);
			//validate response message
			bodymessage=response.jsonPath().getString("status.message");
			System.out.println( "***BODY Message*** " + bodymessage );
			Assert.assertEquals("Login successfully",bodymessage);
			rrl.reportStepPass("BODY Message - " + bodymessage);
		}
		catch(Exception e)
		{
			rrl.reportStepFail("Failure");
		}
	}
	@Given("I have an traveltax createtravel API")
	public void I_have_an_traveltax_createtravel_API() throws Throwable {
		rrl.reportCreateScenario("User creates a travel record");
		rrl.createStep("Given", "I have an traveltax createtravel API" + baseURI);
		RestAssured.baseURI=baseURI;
		request=RestAssured.given();
	}
	@When("I request to create a travel info")
	public void i_request_to_create_a_travel_info() throws Exception {
		rrl.createStep("When", "I request to create a travel info");
		String payload1 = "{ " +
				"\"userId\":\"" + userid + "\"," +
				"\"origin\":62, " +
				"\"destination\":131, " +
				"\"startDate\":\"2023-12-11T08:30:00.0000\", " +
				"\"endDate\":\"2023-12-11T08:30:00.0000\", " +
				"\"taxableDays\":123, " +
				"\"definitionOfTaxDays\":13, " +
				"\"fiscalStartYear\":\"2023-01-01T00:00:00.0000\", " +
				"\"fiscalEndYear\":\"2023-12-31T00:00:00.0000\", " +
				"\"thresholdDays\":5, " +
				"\"travelType\":3, " +
				"\"otherTravelType\":\"Testing\", " +
				"\"checkList\":\"Test\", " +
				"\"travelHotel\":[\"abc.txt\", \"asd.png\"], " +
				"\"foodEntertainment\":[\"caa.txt\", \"assasd.png\"], " +
				"\"shoppingUtility\":[\"arbc.txt\", \"ete.png\"], " +
				"\"others\":[\"sfsf.txt\", \"hgjg.png\"], " +
				"\"nonWorkDays\":[\"2023-08-25T00:00:00.0000\"]}";
		response=request.header("Authorization", "Bearer " +accesstoken).contentType(ContentType.JSON) .body(payload1)
				.put(excel.getCellData("Testdata","URL",4));
	}

	@Then("I check the created travel info")
	public void i_check_the_created_travel_info() throws ClassNotFoundException {
		rrl.createStep("Then", "I check the created travel info");
		System.out.println("****Pretty print****");
		response.prettyPrint();

	}

	@Then("I am validating the created travel response")
	public void i_am_validating_the_created_travel_response() {
		try {
			rrl.createStep("Then", "I am validating the created travel response");
			//status code check
			statuscode=response.getStatusCode();
			System.out.println("***Status Code***"+ statuscode);
			Assert.assertEquals(200, statuscode);
			rrl.reportStepPass("Status Code - "+ statuscode);
			//validate response body
			bodystatus = response.jsonPath().getString("status.status");
			System.out.println( "***BODY Status*** " + bodystatus );
			Assert.assertEquals("200",bodystatus);
			rrl.reportStepPass("BODY Status - " + bodystatus);
			//validate response message
			bodymessage=response.jsonPath().getString("status.msg");
			System.out.println( "***BODY Message*** " + bodymessage );
			Assert.assertEquals("success",bodymessage);
			rrl.reportStepPass("BODY Message - " + bodymessage);
			travelid=response.jsonPath().getString("entity.travelId");
			Assert.assertNotNull(travelid);
			rrl.reportStepPass("Travel id - "+travelid);
		}
		catch(Exception e)
		{
			rrl.reportStepFail("Failure");
		}
	}
	@Given("I have an traveltax getrecord API")
	public void I_have_an_traveltax_getrecord_API() throws Throwable {
		rrl.reportCreateScenario("User gets the travel record");
		rrl.createStep("Given", "I have an traveltax getrecord API " + baseURI);
		RestAssured.baseURI=baseURI;
		request=RestAssured.given();
	}
	@When("I request to get all travel records")
	public void i_request_to_get_all_travel_records() throws Exception {
		rrl.createStep("When", "I request to get all travel records");
		response=request.queryParam("userId", userid).header("Authorization", "Bearer " +accesstoken).when()
				.get(excel.getCellData("Testdata","URL",5));
	}

	@Then("I check the records")
	public void i_check_the_records() throws ClassNotFoundException {
		rrl.createStep("Then", "I check the records");
		response.prettyPrint();
		rrl.reportStepPass(response.prettyPrint());
	}

	@Then("I am validating the travel records response")
	public void i_am_validating_the_travel_records_response() throws ClassNotFoundException {
		try {
			rrl.createStep("Then", "I am validating the travel records response");
			//status code check
			statuscode=response.getStatusCode();
			System.out.println("***Status Code***"+ statuscode);
			Assert.assertEquals(200, statuscode);
			rrl.reportStepPass("Status Code - "+ statuscode);
			//validate response body
			bodystatus = response.jsonPath().getString("status.status");
			System.out.println( "***BODY Status*** " + bodystatus );
			Assert.assertEquals("200",bodystatus);
			rrl.reportStepPass("BODY Status - " + bodystatus);
			//validate response message
			bodymessage=response.jsonPath().getString("status.msg");
			System.out.println( "***BODY Message*** " + bodymessage );
			Assert.assertEquals("success",bodymessage);
			rrl.reportStepPass("BODY Message - " + bodymessage);

			//verify headers
			String server = response.getHeader("Server");	
			Assert.assertEquals("nginx/1.18.0 (Ubuntu)", server);
			rrl.reportStepPass("Server header " + server);
			String contenttype = response.getHeader("Content-Type");
			Assert.assertEquals("application/json", contenttype);
			rrl.reportStepPass("Content-type header " + contenttype);

			//verify user id
			String verifyuserID = response.jsonPath().getString("entity.userId.userId");
			Assert.assertEquals("["+userid+"]", verifyuserID);
			rrl.reportStepPass("User id matched");

			String verifytravelid = response.jsonPath().getString("entity.travelId");
			Assert.assertEquals("["+travelid+"]", verifytravelid);
			rrl.reportStepPass("Travel id matched");
		}
		catch(Exception e)
		{
			rrl.reportStepFail("Failure");
		}
	}
	@Given("I have an traveltax delete API")
	public void I_have_an_traveltax_delete_API() throws Throwable {
		rrl.reportCreateScenario("User delete the travel record");
		rrl.createStep("Given", "I have an traveltax delete API " + baseURI);
		RestAssured.baseURI=baseURI;
		request=RestAssured.given();
	}
	@When("I request to delete a record")
	public void i_request_to_delete_a_record() throws Exception {
		rrl.createStep("When", "I request to delete a record");
		response=request.queryParam("travelId", travelid).queryParams("userId", userid).header("Authorization", "Bearer " +accesstoken).when()
				.delete(excel.getCellData("Testdata","URL",6));
	}

	@Then("I check the deleted record")
	public void i_check_the_deleted_record() throws ClassNotFoundException {
		rrl.createStep("Then", "I check the deleted record");
		response.prettyPrint();
		rrl.reportStepPass(response.prettyPrint());
	}

	@Then("I am validating the deleted record response")
	public void i_am_validating_the_deleted_record_response() throws ClassNotFoundException {
		try {
			rrl.createStep("Then", "I am validating the deleted record response");
			//status code check
			statuscode=response.getStatusCode();
			System.out.println("***Status Code***"+ statuscode);
			Assert.assertEquals(200, statuscode);
			rrl.reportStepPass("Status Code - "+ statuscode);
			//validate response body
			bodystatus = response.jsonPath().getString("status");
			System.out.println( "***BODY Status*** " + bodystatus );
			Assert.assertEquals("200",bodystatus);
			rrl.reportStepPass("BODY Status - " + bodystatus);
			//validate response message
			bodymessage=response.jsonPath().getString("msg");
			System.out.println( "***BODY Message*** " + bodymessage );
			Assert.assertEquals("success",bodymessage);
			rrl.reportStepPass("BODY Message - " + bodymessage);
		}
		catch(Exception e)
		{
			rrl.reportStepFail("Failure");
		}}

	@When("I post the register payload request with already existing account")
	public void I_post_the_register_payload_request_with_already_existing_account() throws Throwable {

		rrl.createStep("When","I post the register payload request with already existing account");
		payload=new JSONObject();
		payload.put("firstName",getProperty("firstName", crudloc));
		payload.put("lastName",getProperty("lastName", crudloc));
		payload.put("mobileNo",mobilenumber);
		payload.put("userType",getProperty("userType", crudloc));
		payload.put("password",getProperty("password", crudloc));
		payload.put("confirmPassword",getProperty("confirmPassword", crudloc));
		payload.put("countryCode",getProperty("countryCode", crudloc));
		payload.put("email",email);		
		payload.put("resident",getProperty("resident", crudloc));
		response=request.contentType("application/json").body(payload).when().post(excel.getCellData("Testdata","URL",1));

	}

	@Then("I log the response")
	public void I_log_the_response() throws ClassNotFoundException {
		rrl.createStep("Then", "I log the response");
		System.out.println("****Pretty print****");
		response.prettyPrint();
		rrl.reportStepPass(response.prettyPrint());

	}

	@And("I am validating the register response for failure")
	public void I_am_validating_the_register_response_for_failure() throws ClassNotFoundException {
		try{
			rrl.createStep("Then", "I am validating the register response for failure");
			//status code check
			statuscode=response.getStatusCode();
			System.out.println("***Status Code***"+ statuscode);
			Assert.assertEquals(200, statuscode);
			rrl.reportStepPass("Status Code - "+ statuscode);
			//checking the body 
			bodystatus = response.jsonPath().getString("status.status");
			System.out.println( "***BODY Status*** " + bodystatus );
			Assert.assertEquals("200",bodystatus);
			rrl.reportStepPass("BODY Status - " + bodystatus);
			bodymessage = response.jsonPath().getString("status.message");
			System.out.println("***BODY Message*** "+bodymessage);
			Assert.assertEquals("SUCCESS", bodymessage);
			rrl.reportStepPass(" BODY Message - "+bodymessage);
			//get user id
			userid=response.jsonPath().getString("entity.userId");
			System.out.println("***BODY Entity*** + "+userid);
			Assert.assertNotNull(userid);
		}
		catch(Exception e)
		{
			rrl.reportStepFail("Failure");
		}
	}
	@Given("Report flush")
	public void Report_flush() throws Throwable {
		rrl.reportCooldown();
	}
}

package com.rest.steps;
//package com.steps;
//
//import javax.print.DocFlavor.READER;
//
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import io.restassured.RestAssured;
//import io.restassured.http.Header;
//import io.restassured.http.Headers;
//import io.restassured.http.Method;
//import io.restassured.internal.proxy.RestAssuredProxySelector;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//import io.restassured.response.ResponseBody;
//import io.restassured.specification.RequestSpecification;
//
//public class Basiccommands {
//	//Validate response status code,status line
//	@Test (enabled=false)
//	public void getRequest() {
//		//to specify base URL 
//		RestAssured.baseURI="https://demoqa.com/BookStore/v1/Books";
//		RequestSpecification httprequest=RestAssured.given();
//		//Response response=httprequest.request(Method.GET,"");
//		Response response=httprequest.get("");
//		//System.out.println(response.getStatusLine());
//		//System.out.println(response.prettyPrint());
//		int statuscode = response.getStatusCode();
//	System.out.println("Status code ::" +statuscode);
//		Assert.assertEquals(response.getStatusCode(), 200,"Status code matched");
//		String statusLine = response.getStatusLine();
//		System.out.println("Status line ::" + statusLine);
//		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK","Status Line matched");
//	
//	}
////Validate response headers
//	@Test (enabled=false)
//	public void getResponse() {
//		RestAssured.baseURI="https://demoqa.com/BookStore/v1/Books";
//		RequestSpecification httprequest = RestAssured.given();
//		Response response=httprequest.get("");
//		//content-type
//		String contenttype=response.getHeader("Content-type");
//		System.out.println("Content type - "+ contenttype);
//		Assert.assertEquals(contenttype, "application/json; charset=utf-8","Content type matches");
//	String server =response.getHeader("Server");
//	System.out.println("Server - " + server);
//	Assert.assertEquals(server, "nginx/1.17.10 (Ubuntu)","Server matches");
//	String contentencoding = response.getHeader("Content-Encoding");
//	System.out.println("Content Encoding-" + contentencoding);
//	Assert.assertNull(contentencoding, "Content-Encoding matches");
//	//Assert.assertEquals(contentencoding,"null","Content-Encoding matches");
//	
//	}
//	//iterate headers
//		@Test (enabled=false)
//		public void getResponse1() {
//			RestAssured.baseURI="https://demoqa.com/BookStore/v1/Books";
//			RequestSpecification httprequest = RestAssured.given();
//			Response response=httprequest.get("");
//	      Headers headers = response.getHeaders();
//	      for (Header header : headers) {
//	    	  System.out.println("Key " + header.getName() + " Value "+ header.getValue());
//	      }
//		}
//	    //getbody
//			@Test 
//			public void getResponse2() {
//		RestAssured.baseURI="https://demoqa.com/BookStore/v1/Books";
//		RequestSpecification httprequest=RestAssured.given();
//		
//        //Response response=httprequest.get("");
//      //  ResponseBody responsebody=response.getBody();
//      //  System.out.println("Response Body : "+ responsebody.asString());
//		
//		Response response=httprequest.get("");
//	      ResponseBody responsebody=response.getBody();
//	      System.out.println("Response Body : "+ responsebody.asString());
//	      Assert.assertEquals(responsebody.asString().contains("title"), true,"matched");
//	      String s1 = response.getBody().asString();
//	      Assert.assertEquals(s1.toLowerCase().contains("title"), true,"matched");
//}
//			//checking the specific value
//			@Test 
//			public void getResponse3() {
//		RestAssured.baseURI="https://restapi.demoqa.com/utilities/weather/city";
//		RequestSpecification httprequest=RestAssured.given();
//		Response response=httprequest.get("/Hyderabad");
//		JsonPath jsonPathEvaluator=response.jsonPath();
//		String title=jsonPathEvaluator.get("City");
//		System.out.println("title + " + title);
//	//	System.out.println("Title of the book : " + title);
//		//Assert.assertEquals(title, "A Working Introduction","Title matches correctly");
//}	
//			
//}
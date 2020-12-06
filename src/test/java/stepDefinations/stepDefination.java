package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestData;
import resources.utils;

public class stepDefination extends utils {
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	static String place_id;
	TestData data = new TestData();

	
	@Given("Add place payload with {string}, {string}, {int}")
	public void add_place_payload_with(String name, String address, Integer accuracy) throws IOException {
		
		//Using Request Spec Builder in given
		res= given().spec(RequestSpecification())
		.body(data.AddPlacePayload(name, address, accuracy));
	  
	}

	@When("User calls {string} using {string} http request")
	public void user_calls_using_http_request(String resource, String method) {
		
		
		APIResources resourcesAPI=APIResources.valueOf(resource);
		System.out.println(resourcesAPI.getResource());
		
		//Response Spec Builder
		resspec = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(200).build();
		
		if(method.equalsIgnoreCase("Post"))
		response = res.when().post(resourcesAPI.getResource());
		else if(method.equalsIgnoreCase("Get"))
		response = res.when().get(resourcesAPI.getResource());
				
				
	    
	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
		
		assertEquals(response.getStatusCode(),200);
	    
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		
		assertEquals(getJsonPath(response,keyValue), expectedValue);
	    
	}
	
	@Then("verify place id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	   
		place_id = getJsonPath(response,"place_id");
		res= given().spec(RequestSpecification()).queryParam("place_id", place_id);
		user_calls_using_http_request(resource, "Get");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
	}
	
	@Given("Delete Place Payload")
	public void delete_Place_Payload() throws IOException {
	    
		
		res = given().spec(RequestSpecification()).body(data.DeletePlacePayload(place_id));
	}


}

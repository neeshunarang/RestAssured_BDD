package StepsDefinition;

import static io.restassured.RestAssured.given;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.testng.Assert;

import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import Utilities.XLUtils;

public class Users_Get extends BaseClass{

	@Given("User is on GET Method")
	public void user_is_on_get_method() {
		request = given();
	}

	@When("User enters Username:{string} and Password:{string} and sends GET request")
	public void user_enters_username_and_password_and_sends_get_request(String userName, String passWord) {
		response = request.auth().basic(userName, passWord)
				   .get(baseURL + endPointU);
	}

	@Then("Status code {int} ok will be displayed")
	public void status_code_ok_will_be_displayed(int statusCode) throws IOException {
		Assert.assertNotNull(response);
		response.then().assertThat().body(matchesJsonSchemaInClasspath(usersJSchema));
		System.out.println("Status code is: " + response.statusCode());
		Assert.assertEquals(response.statusCode(),statusCode);
		//writeToExcel();
	}

	@Then("Status Code {int} unauthorized will be displayed.")
	public void status_code_unauthorized_will_be_displayed(int statusCode) {
		System.out.println("Status code is: " + response.statusCode());
		Assert.assertEquals(response.statusCode(),statusCode);
	}

	@Given("User is on Get method for single user request")
	public void user_is_on_get_method_for_single_user_request() {
		request = given();
	}

	@When("User sends GET request with valid user_id")
	public void user_sends_get_request_with_valid_user_id() throws IOException {
		response = ValidAuthorization()
				   .get(baseURL + endPointU + "/" + XLUtils.getCellData(usersExcelPath, "GETByID", 1,1));
	}

	@Then("The Json response user id,first_name,lastname,phone number,location,time_zone,linkedin url will be displayed with status code {int} Ok.")
	public void the_json_response_user_id_first_name_lastname_phone_number_location_time_zone_linkedin_url_will_be_displayed_with_status_code_ok(int statusCode) throws IOException {
		Assert.assertNotNull(response);
		System.out.println("Response Body: " + response.asPrettyString());
		response.then().assertThat().body(matchesJsonSchemaInClasspath(user_JSchema));
		
		Assert.assertEquals(response.statusCode(),statusCode);
		XLUtils.setCellData(usersExcelPath, "GETByID",1,2,response.statusCode() ,CellType.NUMERIC);
		XLUtils.setCellData(usersExcelPath, "GETByID",1,3,response.asPrettyString() ,CellType.STRING);
	}

	@When("User sends GET request with invalid user_id")
	public void user_sends_get_request_with_invalid_user_id() throws IOException {
		response = ValidAuthorization()
				   .get(baseURL + endPointU + "/" + XLUtils.getCellData(usersExcelPath, "GETByID", 2,1));
	}

	@Then("Status code {int} not found will be displayed")
	public void status_code_not_found_will_be_displayed(int statusCode) throws IOException {
		Assert.assertEquals(response.statusCode(),statusCode);
		XLUtils.setCellData(usersExcelPath, "GETByID",2,2,response.statusCode() ,CellType.NUMERIC);
		XLUtils.setCellData(usersExcelPath, "GETByID",2,3,response.asPrettyString() ,CellType.STRING);
	}
	void writeToExcel() throws IOException 
	{
		String sheet = "GET";
		
		JsonPath jpath=JsonPath.from(response.asPrettyString());
		
		int maxUsers=jpath.get("size()");
		for(int i=0;i<maxUsers;i++) 
		{
			XLUtils.setCellData(usersExcelPath, sheet,i+1,0, jpath.get("["+i+"].user_id"),CellType.STRING);
			XLUtils.setCellData(usersExcelPath, sheet,i+1,1, jpath.get("["+i+"].name"),CellType.STRING);
			XLUtils.setCellData(usersExcelPath, sheet,i+1,2, jpath.get("["+i+"].phone_number"),CellType.NUMERIC);
			XLUtils.setCellData(usersExcelPath, sheet,i+1,3, jpath.get("["+i+"].location"),CellType.STRING);
			XLUtils.setCellData(usersExcelPath, sheet,i+1,4, jpath.get("["+i+"].time_zone"),CellType.STRING);
			XLUtils.setCellData(usersExcelPath, sheet,i+1,5, jpath.get("["+i+"].linkedin_url"),CellType.STRING);
		}
		
	}	
}

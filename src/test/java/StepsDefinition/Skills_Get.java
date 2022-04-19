package StepsDefinition;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.testng.Assert;

import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import Utilities.XLUtils;

public class Skills_Get extends BaseClass{
	
	@Given("User is on GET Request for all skills with valid authorization")
	public void user_is_on_get_request_for_all_skills_with_valid_authorization() {
		request = given();
	}

	@When("User enters Username:{string} and Password {string} and sends GET request")
	public void user_enters_username_and_password_and_sends_get_request(String userName, String passWord) {
		response = request.auth().basic(userName, passWord)
				   .get(baseURL + endPointS);
	}

	@Then("Status code {int} will be displayed")
	public void status_code_will_be_displayed(int statusCode) throws IOException {
		Assert.assertNotNull(response);
		//JSON Schema Validation
		response.then().assertThat().body(matchesJsonSchemaInClasspath(skills_JSchema));
		System.out.println("Status code is: " + response.statusCode());
		Assert.assertEquals(response.statusCode(),statusCode);
		//writeToExcel();
	}

	@Given("UUser is on GET Request for all skills with invalid authorization")
	public void u_user_is_on_get_request_for_all_skills_with_invalid_authorization() {
		request = given();
	}

	@Then("Status Code {int} Unauthorized will be displayed")
	public void status_code_unauthorized_will_be_displayed(int statusCode) {
		System.out.println("Status code is: " + response.statusCode());
		Assert.assertEquals(response.statusCode(),statusCode);
	}

	@Given("User is on Get method for single skill request")
	public void user_is_on_get_method_for_single_skill_request() {
		request = given();
	}

	@When("User sends GET request with valid skill_id")
	public void user_sends_get_request_with_valid_skill_id() throws IOException {
		response = ValidAuthorization()
				   .get(baseURL + endPointS + "/" + XLUtils.getCellData(skillsExcelPath, "GETByID", 1,1));
	}

	@Then("The Json response skill_id, and skill_name will be displayed with status code {int} Ok.")
	public void the_json_response_skill_id_and_skill_name_will_be_displayed_with_status_code_ok(int statusCode) throws IOException {
		response.then().assertThat().body(matchesJsonSchemaInClasspath(skill_JSchema));
		System.out.println("Response Body: " + response.asPrettyString());
		Assert.assertEquals(response.statusCode(),statusCode);
		XLUtils.setCellData(skillsExcelPath, "GETByID",1,2,"Passed" ,CellType.STRING);
		XLUtils.setCellData(skillsExcelPath, "GETByID",1,3,response.statusCode() ,CellType.NUMERIC);
		XLUtils.setCellData(skillsExcelPath, "GETByID",1,4,response.asPrettyString() ,CellType.STRING);
	}

	@When("User sends GET request with invalid skill_id")
	public void user_sends_get_request_with_invalid_skill_id() throws IOException {
		response = ValidAuthorization()
				  .get(baseURL + endPointS + "/" + XLUtils.getCellData(skillsExcelPath, "GETByID", 2,1));
	}

	@Then("The error message will be displayed as \"No records found for this id\"with the status code {int} not found")
	public void the_error_message_will_be_displayed_as_no_records_found_for_this_id_with_the_status_code_not_found(int statusCode) throws IOException {
		Assert.assertEquals(response.statusCode(),statusCode);
		XLUtils.setCellData(skillsExcelPath, "GETByID",2,2,"Failed" ,CellType.STRING);
		XLUtils.setCellData(skillsExcelPath, "GETByID",2,3,response.statusCode() ,CellType.NUMERIC);
		XLUtils.setCellData(skillsExcelPath, "GETByID",2,4,response.asPrettyString() ,CellType.STRING);
	}
	void writeToExcel() throws IOException 
	{
		String sheet = "GET";
		
		JsonPath jpath=JsonPath.from(response.asPrettyString());
		
		int maxS=jpath.get("size()");
		for(int i=0;i<maxS;i++) 
		{
			
			XLUtils.setCellData(skillsExcelPath, sheet,i+1,0, jpath.get("["+i+"].skill_id"),CellType.NUMERIC);
			XLUtils.setCellData(skillsExcelPath, sheet,i+1,1, jpath.get("["+i+"].skill_name"),CellType.STRING);
			
		}
		
	}
}

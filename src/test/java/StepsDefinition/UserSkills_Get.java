package StepsDefinition;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.testng.Assert;

import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import Utilities.XLUtils;
public class UserSkills_Get extends BaseClass{
	
	@Given("User is on GET Method with valid authorization")
	public void user_is_on_get_method_with_valid_authorization() {
		request = given();
	}

	@When("User enters Username: {string} and Password: {string} and sends GET request")
	public void user_enters_username_and_password_and_sends_get_request(String userName, String passWord) {
		response = request.auth().basic(userName, passWord)
				   .get(baseURL + endPointUS);
	}

	@Then("Status code {int} Ok will be displayed")
	public void status_code_ok_will_be_displayed(int statusCode) throws IOException { 
		Assert.assertNotNull(response);
		//JSON Schema Validation
		response.then().assertThat().body(matchesJsonSchemaInClasspath(USjSchema));
		System.out.println("Status code is: " + response.statusCode());
		Assert.assertEquals(response.statusCode(),statusCode);
		//writeToExcel();
	}

	@Given("User is on GET Method with invalid authorization")
	public void user_is_on_get_method_with_invalid_authorization() {
		request = given();
	}

	@Then("Status Code {int} unauthorized will be displayed")
	public void status_code_unauthorized_will_be_displayed(int statusCode) {
		System.out.println("Status code is: " + response.statusCode());
		Assert.assertEquals(response.statusCode(),statusCode);
	}

	@Given("User is on Get method for single user skill request")
	public void user_is_on_get_method_for_single_user_skill_request() {
		request = given();
	}
	
	@When("User sends GET request with valid user_skill_id")
	public void user_sends_get_request_with_valid_user_skill_id() throws IOException {
		response = ValidAuthorization()
				   .get(baseURL + endPointUS + "/" + XLUtils.getCellData(USExcelPath, "GETByID", 1,1));
	}
	
	@Then("The Json response user_skill_id, user_id, Skill_Id and months_of_exp will be displayed with status code {int} Ok.")
	public void the_json_response_user_skill_id_user_id_skill_id_and_months_of_exp_will_be_displayed_with_status_code_ok(int statusCode) throws IOException {
		response.then().assertThat().body(matchesJsonSchemaInClasspath(USSinglejSchema));
		System.out.println("Response Body: " + response.asPrettyString());
		Assert.assertEquals(response.statusCode(),statusCode);
		XLUtils.setCellData(USExcelPath, "GETByID",1,2,response.statusCode() ,CellType.NUMERIC);
		XLUtils.setCellData(USExcelPath, "GETByID",1,3,response.asPrettyString() ,CellType.STRING);
	}
	
	@When("User sends GET request with invalid user_skill_id")
	public void user_sends_get_request_with_invalid_user_skill_id() throws IOException {
		response = ValidAuthorization()
				  .get(baseURL + endPointUS + "/" + XLUtils.getCellData(USExcelPath, "GETByID", 2,1));
		
	}
	
	@Then("The error message will be displayed as {string} with the status code {int} not found")
	public void the_error_message_will_be_displayed_as_with_the_status_code_not_found(String errMsg, int statusCode) throws IOException {
		
		System.out.println(errMsg);
		Assert.assertEquals(response.statusCode(),statusCode);
		XLUtils.setCellData(USExcelPath, "GETByID",2,2,response.statusCode() ,CellType.NUMERIC);
		XLUtils.setCellData(USExcelPath, "GETByID",2,3,response.asPrettyString() ,CellType.STRING);
	}
	
	void writeToExcel() throws IOException 
	{
		String sheet = "GETAll";
		
		JsonPath jpath=JsonPath.from(response.asPrettyString());
		
		int maxUS=jpath.get("size()");
		for(int i=0;i<maxUS;i++) 
		{
			XLUtils.setCellData(USExcelPath, sheet,i+1,0, jpath.get("["+i+"].user_skill_id"),CellType.STRING);
			XLUtils.setCellData(USExcelPath, sheet,i+1,1, jpath.get("["+i+"].user_id"),CellType.STRING);
			XLUtils.setCellData(USExcelPath, sheet,i+1,2, jpath.get("["+i+"].skill_id"),CellType.NUMERIC);
			XLUtils.setCellData(USExcelPath, sheet,i+1,3, jpath.get("["+i+"].months_of_exp"),CellType.NUMERIC);
		}
		
	}	

}

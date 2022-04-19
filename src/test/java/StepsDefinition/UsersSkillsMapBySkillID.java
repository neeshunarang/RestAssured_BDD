package StepsDefinition;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.testng.Assert;

import io.cucumber.java.en.*;

import Utilities.XLUtils;
public class UsersSkillsMapBySkillID extends BaseClass{
	@Given("User is on Get method for single UsersSkillsMap  with valid skill_id")
	public void user_is_on_get_method_for_single_users_skills_map_with_valid_skill_id() {
		request = given();
	}

	@When("User sends GET request for single UsersSkillsMap with valid skill_id")
	public void user_sends_get_request_for_single_users_skills_map_with_valid_skill_id() throws IOException {
		response = ValidAuthorization()
				   .get(baseURL + endPointUSMByS + "/" + XLUtils.getCellData(USMExcelPath, "GetBySkillID", 1,1));
	}
	
	@Then("Status code {int} Ok will be displayed with Json body")
	public void status_code_ok_will_be_displayed_with_json_body(int statusCode) throws IOException {
		response.then().assertThat().body(matchesJsonSchemaInClasspath(UsersSkillMapbySkillID_JSchema));
		//System.out.println("Response Body: " + response.asPrettyString());
		Assert.assertEquals(response.statusCode(),statusCode);
		XLUtils.setCellData(USMExcelPath, "GetBySkillID",1,2,response.statusCode() ,CellType.NUMERIC);
		XLUtils.setCellData(USMExcelPath, "GetBySkillID",1,3,response.asPrettyString() ,CellType.STRING);
	}
	@Given("User is on Get method for single UsersSkillsMap with invalid skill_id")
	public void user_is_on_get_method_for_single_users_skills_map_with_invalid_skill_id() {
		request = given();
	}

	@When("User sends GET request for single UsersSkillsMap with invalid skill_id")
	public void user_sends_get_request_for_single_users_skills_map_with_invalid_skill_id() throws IOException {
		response = ValidAuthorization()
				   .get(baseURL + endPointUSMByS + "/" + XLUtils.getCellData(USMExcelPath, "GetBySkillID", 2,1));
	}

	@Then("Response message will be displayed as {string} with the status code {int} not found")
	public void response_message_will_be_displayed_as_with_the_status_code_not_found(String string, int statusCode) throws IOException {
		Assert.assertEquals(response.statusCode(),statusCode);
		XLUtils.setCellData(USMExcelPath, "GetBySkillID",2,2,response.statusCode() ,CellType.NUMERIC);
		XLUtils.setCellData(USMExcelPath, "GetBySkillID",2,3,response.asPrettyString() ,CellType.STRING);
	}
}

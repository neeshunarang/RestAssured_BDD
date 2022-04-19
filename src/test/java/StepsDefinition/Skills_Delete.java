package StepsDefinition;


import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellType;
import org.testng.Assert;
import io.cucumber.java.en.*;
import Utilities.XLUtils;


public class Skills_Delete extends BaseClass{
	String skill_id;
	String sheet = "DELETE";
	@Given("User is on DELETE method with valid skill_id")
	public void user_is_on_delete_method_with_valid_skill_id() {
		request=given();
	}

	@When("User sends DELETE request for valid skill_id")
	public void user_sends_delete_request_for_valid_skill_id() throws IOException {
		String sheet = "DELETE";
		skill_id = XLUtils.getCellData(skillsExcelPath, sheet, 1, 1).toString();
			response=ValidAuthorization()
				.when()
				.delete(baseURL + endPointS + "/" + skill_id);
	}

	@Then("The Response message will be displayed as {string} with the status code {int}")
	public void the_response_message_will_be_displayed_as_with_the_status_code(String string, int statusCode) throws IOException {
		response.then().assertThat().body(matchesJsonSchemaInClasspath(skillDelete_JSchema));
		Assert.assertEquals(response.statusCode(), statusCode);
		response.then().assertThat().body("Skill_Id",equalTo(skill_id));
		response.then().assertThat().body("message_response",equalTo("The record has been deleted !!"));
		
		XLUtils.setCellData(skillsExcelPath, sheet,1,2,"Passed" ,CellType.STRING);
		XLUtils.setCellData(skillsExcelPath, sheet,1,3,response.statusCode() ,CellType.NUMERIC);
		XLUtils.setCellData(skillsExcelPath, sheet,1,4,response.asPrettyString() ,CellType.STRING);
	}
	@Given("User is on DELETE method with invalid skill_id")
	public void user_is_on_delete_method_with_invalid_skill_id() {
		request=given();
	}

	@When("User sends DELETE request for invalid skill_id")
	public void user_sends_delete_request_for_invalid_skill_id() throws IOException {
		String sheet = "DELETE";
		skill_id = XLUtils.getCellData(skillsExcelPath, sheet, 2, 1).toString();
		
		response=ValidAuthorization()
				.when()
				.delete(baseURL + endPointS + "/" + skill_id);
	}

	@Then("Status code {int} Not Found will be displayed")
	public void status_code_not_found_will_be_displayed(int statusCode) throws IOException {
		Assert.assertEquals(response.statusCode(), statusCode);
		XLUtils.setCellData(skillsExcelPath, sheet,2,2,"Failed" ,CellType.STRING);
		XLUtils.setCellData(skillsExcelPath, sheet,2,3,response.statusCode() ,CellType.NUMERIC);
		XLUtils.setCellData(skillsExcelPath, sheet,2,4,response.asPrettyString() ,CellType.STRING);
	}

	
}

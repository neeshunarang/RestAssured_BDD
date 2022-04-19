package StepsDefinition;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellType;
import org.testng.Assert;
import io.cucumber.java.en.*;
import Utilities.XLUtils;

public class UserSkills_Delete extends BaseClass{
	String user_skill_id;
	String sheet = "DELETE";
	@Given("User is on DELETE method with invalid user_skill_id")
	public void user_is_on_delete_method_with_invalid_user_skill_id() {
		request=given();
	}

	@When("User sends DELETE request for invalid user_skill_id")
	public void user_sends_delete_request_for_invalid_user_skill_id() throws IOException {
		
		user_skill_id = XLUtils.getCellData(USExcelPath, sheet, 2, 0).toString();
		
		
		response=ValidAuthorization()
				.when()
					.delete(baseURL + endPointUS + "/" + user_skill_id);
	
		
	
	}

	@Then("The Response message will be displayed as {string} with the status code {int} Not Found")
	public void the_response_message_will_be_displayed_as_with_the_status_code_not_found(String string, int statusCode) throws IOException {
		
		Assert.assertEquals(response.statusCode(), statusCode);
		XLUtils.setCellData(USExcelPath, sheet,2,1,"Failed" ,CellType.STRING);
		XLUtils.setCellData(USExcelPath, sheet,2,2,response.statusCode() ,CellType.NUMERIC);
		XLUtils.setCellData(USExcelPath, sheet,2,3,response.asPrettyString() ,CellType.STRING);
	}

	@Given("User is on DELETE method with valid user_skill_id")
	public void user_is_on_delete_method_with_valid_user_skill_id() {
		request=given();
	}

	@When("User sends DELETE request for valid user_skill_id")
	public void user_sends_delete_request_for_valid_user_skill_id() throws IOException {
		String sheet = "DELETE";
		user_skill_id = XLUtils.getCellData(USExcelPath, sheet, 1, 0).toString();
		
		
		response=ValidAuthorization()
				.when()
				.delete(baseURL + endPointUS + "/" + user_skill_id);
	
	}

	@Then("The Response message will be displayed as {string} with the status code {int} Ok")
	public void the_response_message_will_be_displayed_as_with_the_status_code_ok(String responseMsg, int statusCode) throws IOException {
		response.then().assertThat().body(matchesJsonSchemaInClasspath(USJSchema_Delete));
		Assert.assertEquals(response.statusCode(), statusCode);
		response.then().assertThat().body("user_skill_id",equalTo(user_skill_id));
		response.then().assertThat().body("message_response",equalTo("The record has been deleted !!"));
		
		XLUtils.setCellData(USExcelPath, sheet,1,1,"Passed" ,CellType.STRING);
		XLUtils.setCellData(USExcelPath, sheet,1,2,response.statusCode() ,CellType.NUMERIC);
		XLUtils.setCellData(USExcelPath, sheet,1,3,response.asPrettyString() ,CellType.STRING);
	}
}

package StepsDefinition;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.json.simple.JSONObject;
import org.testng.Assert;

import Utilities.XLUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;

public class Skills_Post extends BaseClass{
	String skill_name;
	String sheet = "POST";
	//Positive Scenario
	@Given("User is on POST method for creating new skill with valid JSON Request")
	public void user_is_on_post_method_for_creating_new_skill_with_valid_json_request() {
		request=given();
	}

	@When("User sends POST request with JSON request body by inputing valid skill_name")
	public void user_sends_post_request_with_json_request_body_by_inputing_valid_skill_name() throws IOException {
		skill_name = XLUtils.getCellData(skillsExcelPath, sheet, 1, 1).toString();
		JSONObject reqparam = new JSONObject();
		reqparam.put("skill_name", skill_name);
		
		response=ValidAuthorization().header("Content-Type", "application/json")
						.contentType(ContentType.JSON).accept(ContentType.JSON)
					    .body(reqparam.toJSONString())
					.when()
						.post(baseURL + endPointS);
	}

	@Then("Status message will be displayed {string} with the status code {int} Created")
	public void status_message_will_be_displayed_with_the_status_code_created(String string, int statusCode) throws IOException {
		response.then().assertThat().body(matchesJsonSchemaInClasspath(skillPostPut_JSchema));
		Assert.assertEquals(response.statusCode(), statusCode);
		response.then().assertThat().body("skill_name",equalTo(skill_name));
		response.then().assertThat().body("message_response",equalTo("Successfully Created !!"));
		
		XLUtils.setCellData(skillsExcelPath, sheet,1,2,"Passed" ,CellType.STRING);
		XLUtils.setCellData(skillsExcelPath, sheet,1,3,response.statusCode() ,CellType.NUMERIC);
		XLUtils.setCellData(skillsExcelPath, sheet,1,4,response.asPrettyString() ,CellType.STRING);
	}
	//Negative Scenario
	@Given("User is on POST method for creating new skill with invalid JSON Request")
	public void user_is_on_post_method_for_creating_new_skill_with_invalid_json_request() {
		request=given();
	}

	@When("User sends POST request for creating new skill with invalid JSON Request")
	public void user_sends_post_request_for_creating_new_skill_with_invalid_json_request() throws IOException {
		//Reading from Excel Sheet
		skill_name = XLUtils.getCellData(skillsExcelPath, sheet, 2, 1).toString();
				
				
				JSONObject reqparam = new JSONObject();
				reqparam.put("skill_name", skill_name);
				
				response=ValidAuthorization().header("Content-Type", "application/json")
								.contentType(ContentType.JSON).accept(ContentType.JSON)
							    .body(reqparam.toJSONString())
							.when()
								.post(baseURL + endPointS);
	}

	@Then("Status code {int} Bad Request will be displayed")
	public void status_code_bad_request_will_be_displayed(int statusCode) throws IOException {
		XLUtils.setCellData(skillsExcelPath, sheet,2,2,"Failed" ,CellType.STRING);
		XLUtils.setCellData(skillsExcelPath, sheet,2,3,response.statusCode() ,CellType.NUMERIC);
		XLUtils.setCellData(skillsExcelPath, sheet,2,4,response.asPrettyString() ,CellType.STRING);
		Assert.assertEquals(response.statusCode(), statusCode);
	}

	

}

package StepsDefinition;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.json.simple.JSONObject;
import org.testng.Assert;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import Utilities.XLUtils;

public class UserSkills_Put extends BaseClass{
	String user_skill_id;
	String sheet = "PUT";
	String userid;
	int skill_id ;
	int months_of_exp;
	@Given("User is on PUT method with invalid user_skill_id")
	public void user_is_on_put_method_with_invalid_user_skill_id() {
		request=given();
	}

	@When("User sends PUT request with invalid user_skill_id for JSON request body")
	public void user_sends_put_request_with_invalid_user_skill_id_for_json_request_body() throws IOException {
		user_skill_id = XLUtils.getCellData(USExcelPath, sheet, 2, 0).toString();
		userid = XLUtils.getCellData(USExcelPath, sheet, 2, 1).toString();
		skill_id = Integer.parseInt(XLUtils.getCellData(USExcelPath, sheet,2, 2));
		months_of_exp = Integer.parseInt(XLUtils.getCellData(USExcelPath, sheet, 2,3));
		
		JSONObject reqparam = new JSONObject();
		reqparam.put("user_id", userid);
		reqparam.put("skill_id", skill_id);
		reqparam.put("months_of_exp",months_of_exp);
		
		response=ValidAuthorization().header("Content-Type", "application/json")
						.contentType(ContentType.JSON).accept(ContentType.JSON)
					    .body(reqparam.toJSONString())
					.when()
						.put(baseURL + endPointUS +"/" + user_skill_id);
	
	}

	@Then("The error message will be displayed as {string} with status code {int}")
	public void the_error_message_will_be_displayed_as_with_status_code(String string, int statusCode) throws IOException {
		
		Assert.assertEquals(response.statusCode(), statusCode);
		
		XLUtils.setCellData(USExcelPath, sheet,2,4,"Failed" ,CellType.STRING);
		XLUtils.setCellData(USExcelPath, sheet,2,5,response.statusCode() ,CellType.NUMERIC);
		XLUtils.setCellData(USExcelPath, sheet,2,6,response.asPrettyString() ,CellType.STRING);
	}

	@Given("User is on PUT method with valid user_skill_id")
	public void user_is_on_put_method_with_valid_user_skill_id() {
		request=given();
	}

	@When("User sends PUT request with valid user_skill_id for JSON request body")
	public void user_sends_put_request_with_valid_user_skill_id_for_json_request_body() throws IOException {
		
		
		user_skill_id = XLUtils.getCellData(USExcelPath, sheet, 1, 0).toString();
		userid = XLUtils.getCellData(USExcelPath, sheet, 1, 1).toString();
		skill_id = Integer.parseInt(XLUtils.getCellData(USExcelPath, sheet, 1, 2));
		months_of_exp = Integer.parseInt(XLUtils.getCellData(USExcelPath, sheet, 1,3));
		
		JSONObject reqparam = new JSONObject();
		reqparam.put("user_id", userid);
		reqparam.put("skill_id", skill_id);
		reqparam.put("months_of_exp",months_of_exp);
		
		response=ValidAuthorization().header("Content-Type", "application/json")
						.contentType(ContentType.JSON).accept(ContentType.JSON)
					    .body(reqparam.toJSONString())
					.when()
						.put(baseURL + endPointUS +"/" + user_skill_id);
	
		
	
	}

	@Then("Response message will be displayed with status code {int} Created")
	public void response_message_will_be_displayed_with_status_code_created(int statusCode) throws IOException {
		response.then().assertThat().body(matchesJsonSchemaInClasspath(USPOSTJSchema));
		Assert.assertEquals(response.statusCode(), statusCode);
		response.then().assertThat().body("user_id",equalTo(userid));
		response.then().assertThat().body("skill_id",equalTo(skill_id));
		response.then().assertThat().body("months_of_exp",equalTo(months_of_exp));
		response.then().assertThat().body("message_response",equalTo("Successfully Updated !!"));
		
		XLUtils.setCellData(USExcelPath, sheet,1,4,"Passed" ,CellType.STRING);
		XLUtils.setCellData(USExcelPath, sheet,1,5,response.statusCode() ,CellType.NUMERIC);
		XLUtils.setCellData(USExcelPath, sheet,1,6,response.asPrettyString() ,CellType.STRING);
	}
}

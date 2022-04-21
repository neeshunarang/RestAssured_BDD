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

public class Skills_Put extends BaseClass{
	String sheet = "PUT";
	int skill_id ;
	String skill_name;
	
	@Given("User is on PUT method with valid skill_id")
	public void user_is_on_put_method_with_valid_skill_id() {
		request=given();
	}

	@When("User sends PUT request with valid skill_id for JSON request body")
	public void user_sends_put_request_with_valid_skill_id_for_json_request_body() throws NumberFormatException, IOException {
		skill_id = Integer.parseInt(XLUtils.getCellData(skillsExcelPath, sheet, 1, 1));
		skill_name = XLUtils.getCellData(skillsExcelPath, sheet, 1, 2).toString();
		
		JSONObject reqparam = new JSONObject();
		reqparam.put("skill_name",skill_name);
		
		response=ValidAuthorization().header("Content-Type", "application/json")
						.contentType(ContentType.JSON).accept(ContentType.JSON)
					    .body(reqparam.toJSONString())
					.when()
						.put(baseURL + endPointS +"/" + skill_id);
	
	}

	@Then("Status code {int} Created will be displayed")
	public void status_code_created_will_be_displayed(int statusCode) throws IOException {
		response.then().assertThat().body(matchesJsonSchemaInClasspath(skillPostPut_JSchema));
		Assert.assertEquals(response.statusCode(), statusCode);
		//Response Body Validation
		response.then().assertThat().body("skill_id",equalTo(skill_id));
		response.then().assertThat().body("skill_name",equalTo(skill_name));
		response.then().assertThat().body("message_response",equalTo("Successfully Updated !!"));
		
		//DB Validation- Checking DB by calling GET Method 
		resDBCheck = ValidAuthorization()
				   .get(baseURL + endPointS + "/" + skill_id);
		
		Assert.assertEquals(resDBCheck.path("skill_id"),skill_id);//Checking Autogenerating ID
		Assert.assertEquals(resDBCheck.path("skill_name"),skill_name);
				
		XLUtils.setCellData(skillsExcelPath, sheet,1,3,"Passed" ,CellType.STRING);
		XLUtils.setCellData(skillsExcelPath, sheet,1,4,response.statusCode() ,CellType.NUMERIC);
		XLUtils.setCellData(skillsExcelPath, sheet,1,5,response.asPrettyString() ,CellType.STRING);
	}
	
	@Given("User is on PUT method with invalid skill_id")
	public void user_is_on_put_method_with_invalid_skill_id() {
		request=given();
	}

	@When("User sends PUT request with invalid skill_id for JSON request body")
	public void user_sends_put_request_with_invalid_skill_id_for_json_request_body() throws IOException {
		skill_id = Integer.parseInt(XLUtils.getCellData(skillsExcelPath, sheet, 2, 1));
		skill_name = XLUtils.getCellData(skillsExcelPath, sheet, 2, 2).toString();
		
		JSONObject reqparam = new JSONObject();
		reqparam.put("skill_id", skill_id);
		reqparam.put("skill_name",skill_name);
		
		response=ValidAuthorization().header("Content-Type", "application/json")
						.contentType(ContentType.JSON).accept(ContentType.JSON)
					    .body(reqparam.toJSONString())
					.when()
						.put(baseURL + endPointS +"/" + skill_id);
	}

	@Then("Status code {int} Not found will be displayed")
	public void status_code_not_found_will_be_displayed(int statusCode) throws IOException {
		Assert.assertEquals(response.statusCode(), statusCode);
		
		XLUtils.setCellData(skillsExcelPath, sheet,2,3,"Failed" ,CellType.STRING);
		XLUtils.setCellData(skillsExcelPath, sheet,2,4,response.statusCode() ,CellType.NUMERIC);
		XLUtils.setCellData(skillsExcelPath, sheet,2,5,response.asPrettyString() ,CellType.STRING);
	}

	

}

package StepsDefinition;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.testng.Assert;

import Utilities.XLUtils;
import io.cucumber.java.en.*;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;

public class Users_Delete extends BaseClass{
	String sheet ="DELETE";
	String user_id;
	@Given("User is on DELETE method with invalid user_id")
	public void user_is_on_delete_method_with_invalid_user_id() {
		request=given();
	}

	@When("User sends DELETE request for invalid user_id")
	public void user_sends_delete_request_for_invalid_user_id() throws IOException {
		user_id = XLUtils.getCellData(usersExcelPath, sheet, 2, 1).toString();
		
		response=ValidAuthorization()
				.when()
					.delete(baseURL + endPointU + "/" + user_id);
	}

	@Then("The Response message will be displayed  as {string} with the status code {int} Not Found")
	public void the_response_message_will_be_displayed_as_with_the_status_code_not_found(String string, int statusCode) throws IOException {
		Assert.assertEquals(response.statusCode(), statusCode);
		XLUtils.setCellData(usersExcelPath, sheet,2,2,"Failed" ,CellType.STRING);
		XLUtils.setCellData(usersExcelPath, sheet,2,3,response.statusCode() ,CellType.NUMERIC);
		XLUtils.setCellData(usersExcelPath, sheet,2,4,response.asPrettyString() ,CellType.STRING);
	}

	@Given("User is on DELETE method with valid user_id")
	public void user_is_on_delete_method_with_valid_user_id() {
		request=given();
	}

	@When("User sends DELETE request for valid user_id")
	public void user_sends_delete_request_for_valid_user_id() throws IOException {
		user_id = XLUtils.getCellData(usersExcelPath, sheet, 1, 1).toString();
		
		response=ValidAuthorization()
				.when()
					.delete(baseURL + endPointU + "/" + user_id);
	}

	@Then("The Response message will be displayed  as {string} with the status code {int} Ok")
	public void the_response_message_will_be_displayed_as_with_the_status_code_ok(String string, int statusCode) throws IOException {
		response.then().assertThat().body(matchesJsonSchemaInClasspath(userDelete_JSchema));
		Assert.assertEquals(response.statusCode(), statusCode);
		Assert.assertEquals(response.asString().contains(user_id),true);
		Assert.assertEquals(response.asString().contains("The record has been deleted !!"),true);
		
		//response.then().assertThat().body("user_ id",equalTo(user_id));
		//response.then().assertThat().body("message_response",equalTo("The record has been deleted !!"));
		
		XLUtils.setCellData(usersExcelPath, sheet,1,2,"Passed" ,CellType.STRING);
		XLUtils.setCellData(usersExcelPath, sheet,1,3,response.statusCode() ,CellType.NUMERIC);
		XLUtils.setCellData(usersExcelPath, sheet,1,4,response.asPrettyString() ,CellType.STRING);
	}
}
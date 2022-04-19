package StepsDefinition;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.json.simple.JSONObject;
import org.testng.Assert;

import Utilities.XLUtils;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;

public class Users_Post extends BaseClass{
	String sheet = "POST";
	String name;
	int phone_number;
	String location;
	String time_zone;
	String linkedin_url;
	String visa_status;
	@Given("User is on POST method for JSON Request with invalid details of user")
	public void user_is_on_post_method_for_json_request_with_invalid_details_of_user() {
		request=given();
	}

	@When("Sends POST request for invalid JSON Request of user")
	public void sends_post_request_for_invalid_json_request_of_user() throws IOException {
		//Reading from Excel Sheet
				name = XLUtils.getCellData(usersExcelPath, sheet, 2, 1).toString();
				phone_number = Integer.parseInt(XLUtils.getCellData(usersExcelPath, sheet,2,2));
				location = XLUtils.getCellData(usersExcelPath, sheet,2,3);
				time_zone = XLUtils.getCellData(usersExcelPath, sheet,2,4);
				linkedin_url = XLUtils.getCellData(usersExcelPath, sheet,2,5);
				visa_status= XLUtils.getCellData(usersExcelPath, sheet,2,6);
				JSONObject reqparam = new JSONObject();
				reqparam.put("name", name);
				reqparam.put("phone_number",phone_number);
				reqparam.put("location",location);
				reqparam.put("time_zone",time_zone);
				reqparam.put("linkedin_url",linkedin_url);
				reqparam.put("visa_status",visa_status);
				
				response=ValidAuthorization().header("Content-Type", "application/json")
								.contentType(ContentType.JSON).accept(ContentType.JSON)
							    .body(reqparam.toJSONString())
							.when()
								.post(baseURL + endPointU);
	}

	@Then("Status message will be displayed as {string} with the status code {int}")
	public void status_message_will_be_displayed_as_with_the_status_code(String string, int statusCode) throws IOException {
		XLUtils.setCellData(usersExcelPath, sheet,2,7,"Failed" ,CellType.STRING);
		XLUtils.setCellData(usersExcelPath, sheet,2,8,response.statusCode() ,CellType.NUMERIC);
		XLUtils.setCellData(usersExcelPath, sheet,2,9,response.asPrettyString() ,CellType.STRING);
		Assert.assertEquals(response.statusCode(), statusCode);
	}

	@Given("User is on POST method for JSON Request with valid details of user")
	public void user_is_on_post_method_for_json_request_with_valid_details_of_user() {
		request=given();
	}

	@When("User sends POST request by inputing user_id,name,ph no,location,time_zone,linkedin_url in JSON body")
	public void user_sends_post_request_by_inputing_user_id_name_ph_no_location_time_zone_linkedin_url_in_json_body() throws IOException {
		//Reading from Excel Sheet
		name = XLUtils.getCellData(usersExcelPath, sheet, 1, 1).toString();
		phone_number = Integer.parseInt(XLUtils.getCellData(usersExcelPath, sheet,1,2));
		location = XLUtils.getCellData(usersExcelPath, sheet,1,3);
		time_zone = XLUtils.getCellData(usersExcelPath, sheet,1,4);
		linkedin_url = XLUtils.getCellData(usersExcelPath, sheet,1,5);
		visa_status= XLUtils.getCellData(usersExcelPath, sheet,1,6);
		
		JSONObject reqparam = new JSONObject();
		reqparam.put("name", name);
		reqparam.put("phone_number",phone_number);
		reqparam.put("location",location);
		reqparam.put("time_zone",time_zone);
		reqparam.put("linkedin_url",linkedin_url);
		reqparam.put("visa_status",visa_status);
		
		response=ValidAuthorization().header("Content-Type", "application/json")
						.contentType(ContentType.JSON).accept(ContentType.JSON)
					    .body(reqparam.toJSONString())
					.when()
						.post(baseURL + endPointU);
	}

	@Then("Status  message will be displayed {string} with the status code {int} created")
	public void status_message_will_be_displayed_with_the_status_code_created(String string, int statusCode) throws IOException {
		response.then().assertThat().body(matchesJsonSchemaInClasspath(userPostPut_JSchema));
		Assert.assertEquals(response.statusCode(), statusCode);
		response.then().assertThat().body("name",equalTo(name));
		response.then().assertThat().body("phone_number",equalTo(phone_number));
		response.then().assertThat().body("location",equalTo(location));
		response.then().assertThat().body("time_zone",equalTo(time_zone));
		response.then().assertThat().body("linkedin_url",equalTo(linkedin_url));
		response.then().assertThat().body("visa_status",equalTo(visa_status));
		response.then().assertThat().body("visa_status",equalTo(visa_status));
		
		XLUtils.setCellData(usersExcelPath, sheet,1,7,"Passed" ,CellType.STRING);
		XLUtils.setCellData(usersExcelPath, sheet,1,8,response.statusCode() ,CellType.NUMERIC);
		XLUtils.setCellData(usersExcelPath, sheet,1,9,response.asPrettyString() ,CellType.STRING);
		
	}
}

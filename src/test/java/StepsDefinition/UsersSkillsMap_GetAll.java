package StepsDefinition;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellType;
import org.testng.Assert;
import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import Utilities.XLUtils;

public class UsersSkillsMap_GetAll extends BaseClass{
	@Given("User is on GET Method for UsersSkillsMap with valid authorization")
	public void user_is_on_get_method_for_users_skills_map_with_valid_authorization() {
		request = given();
	}

	@When("User enters Username: {string} and Password: {string} and sends request")
	public void user_enters_username_and_password_and_sends_request(String userName, String passWord) {
		response = request.auth().basic(userName, passWord)
				   .get(baseURL + endPointUSM);
	}

	@Then("Status code {int} Ok will be displayed.")
	public void status_code_ok_will_be_displayed(int statusCode) throws IOException {
		Assert.assertNotNull(response);
		//JSON Schema Validation
		response.then().assertThat().body(matchesJsonSchemaInClasspath(USMjSchema));
		System.out.println("Status code is: " + response.statusCode());
		Assert.assertEquals(response.statusCode(),statusCode);
		writeToExcel();
	}
	
	@Given("User is on GET Method for UsersSkillsMap with invalid authorization")
	public void user_is_on_get_method_for_users_skills_map_with_invalid_authorization() {
		request = given();
	}

	
	@Then("The Status Code {int} unauthorized will be displayed.")
	public void the_status_code_unauthorized_will_be_displayed(int statusCode) {
		System.out.println("Status code is: " + response.statusCode());
		Assert.assertEquals(response.statusCode(),statusCode);
	}

	void writeToExcel() throws IOException 
	{
		String sheet = "GET";
		
		JsonPath jpath=JsonPath.from(response.asPrettyString());
		
		int maxUSM=jpath.get("users.size()");
		int rowNo =1;
		for(int i=0;i<maxUSM;i++) 
		{
			XLUtils.setCellData(USMExcelPath, sheet,rowNo,0, jpath.get("users["+i+"].id"),CellType.STRING);
			XLUtils.setCellData(USMExcelPath, sheet,rowNo,1, jpath.get("users["+i+"].firstName"),CellType.STRING);
			XLUtils.setCellData(USMExcelPath, sheet,rowNo,2, jpath.get("users["+i+"].lastName"),CellType.STRING);
			int maxSkills=jpath.get("users["+i+"].skillmap.size()");
			for (int j = 0; j<maxSkills;j++) 
			{
				XLUtils.setCellData(USMExcelPath, sheet,rowNo,3,  jpath.get("users["+i+"].skillmap["+j+"].id"),CellType.NUMERIC);
				XLUtils.setCellData(USMExcelPath, sheet,rowNo,4, jpath.get("users["+i+"].skillmap["+j+"].skill"),CellType.STRING);
				rowNo=rowNo+1;
			}
		}
		
	}	
}

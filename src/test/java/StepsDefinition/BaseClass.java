package StepsDefinition;
import Utilities.ReadConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class BaseClass {
	public static RequestSpecification request;
	public static Response response;
	public static Response resDBCheck;
	//public static RestAssured baseURI;
	
	ReadConfig readconfig=new ReadConfig();
	
	public String baseURL=readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	
	//===============Users=========================
	public String endPointU=readconfig.getEndPointU();
	public String usersJSchema=readconfig.getUjSchema();
	public String user_JSchema=readconfig.getUser_JSchema();
	public String userPostPut_JSchema=readconfig.getuserPostPut_JSchema();
	public String userDelete_JSchema=readconfig.getuserDelete_JSchema();
	public String usersExcelPath=readconfig.getUsersExcelPath();
	
	//===============Skills=========================
	public String endPointS=readconfig.getEndPointS();
	public String skillsExcelPath=readconfig.getSkillsExcelPath();
	public String skills_JSchema=readconfig.getskills_JSchema();
	public String skill_JSchema=readconfig.getskill_JSchema();
	public String skillPostPut_JSchema=readconfig.getSkillPostPut_JSchema();
	public String skillDelete_JSchema=readconfig.getSkillDelete_JSchema();
	
	//===============User Skills=========================
	public String endPointUS=readconfig.getEndPointUS();
	public String USjSchema=readconfig.getUSjSchema();
	public String USSinglejSchema=readconfig.getUSSinglejSchema();
	public String USExcelPath=readconfig.getUSExcelPath();
	public String USPOSTJSchema=readconfig.getUSPOSTJSchema();
	public String USJSchema_Delete=readconfig.getUSJSchemaDelete();
	
	//===============Users Skills Map=========================
	public String endPointUSM=readconfig.getendPointUSM();
	public String USMjSchema=readconfig.getUSMjSchema();
	public String UsersSkillMapbyuserID_JSchema=readconfig.getUsersSkillMapbyuserID_JSchema();
	public String UsersSkillMapbySkillID_JSchema=readconfig.getUsersSkillMapbySkillID_JSchema();
	public String USMExcelPath=readconfig.getUSMExcelPath();
	public String endPointUSMByS=readconfig.getendPointUSMByS();
	
	public RequestSpecification ValidAuthorization() 
	{
		request.auth().basic(username, password);
		return request;
	}
}


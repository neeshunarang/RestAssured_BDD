package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties pro;
	
	public ReadConfig()
	{
		File src = new File("src\\test\\resources\\Configuration\\config.properties");

		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
	}
	
	public String getApplicationURL()
	{
		String url=pro.getProperty("baseURL");
		return url;
	}
	
	public String getUsername()
	{
	String username=pro.getProperty("username");
	return username;
	}
	
	public String getPassword()
	{
	String password=pro.getProperty("password");
	return password;
	}
	
	public String getEndPointUS()
	{
	String endPointUS=pro.getProperty("endPointUS");
	return endPointUS;
	}
	public String getUSjSchema()
	{
		String path=pro.getProperty("USjSchema");
		return path;
	}
	public String getUSSinglejSchema()
	{
		String path=pro.getProperty("USSinglejSchema");
		return path;
	}
	public String getUSExcelPath()
	{
		String xlspath=pro.getProperty("USExcelPath");
		return xlspath;
	}
	public String getUSPOSTJSchema()
	{
		String uspostjschema=pro.getProperty("USPOSTJSchema");
		return uspostjschema;
	}
	public String getUSJSchemaDelete()
	{
		String usjschemadelete=pro.getProperty("USJSchema_Delete");
		return usjschemadelete;
	}
	
	public String getEndPointU()
	{
	String endPointU=pro.getProperty("endPointU");
	return endPointU;
	}
	
	public String getUjSchema()
	{
		String ujSchema=pro.getProperty("usersJSchema");
		return ujSchema;
	}
	public String getUsersExcelPath()
	{
		String usersExcelPath=pro.getProperty("usersExcelPath");
		return usersExcelPath;
	}
	public String getUser_JSchema()
	{
		String ujSchema=pro.getProperty("user_JSchema");
		return ujSchema;
	}
	public String getuserPostPut_JSchema()
	{
		String userPost_JSchema=pro.getProperty("userPostPut_JSchema");
		return userPost_JSchema;
	}
	public String getuserDelete_JSchema()
	{
		String userDelete_JSchema=pro.getProperty("userDelete_JSchema");
		return userDelete_JSchema;
	}
	public String getEndPointS()
	{
	String endPointS=pro.getProperty("endPointS");
	return endPointS;
	}
	public String getSkillsExcelPath()
	{
		String skillsExcelPath=pro.getProperty("skillsExcelPath");
		return skillsExcelPath;
	}
	public String getskills_JSchema()
	{
		String skills_JSchema=pro.getProperty("skills_JSchema");
		return skills_JSchema;
	}
	public String getskill_JSchema()
	{
		String skill_JSchema=pro.getProperty("skill_JSchema");
		return skill_JSchema;
	}
	public String getSkillPostPut_JSchema()
	{
		String SkillPostPut_JSchema=pro.getProperty("SkillPostPut_JSchema");
		return SkillPostPut_JSchema;
	}
	public String getSkillDelete_JSchema()
	{
		String skillDelete_JSchema=pro.getProperty("skillDelete_JSchema");
		return skillDelete_JSchema;
	}
	public String getendPointUSM()
	{
	String endPointUSM=pro.getProperty("endPointUSM");
	return endPointUSM;
	}
	public String getUSMjSchema()
	{
		String USMjSchema=pro.getProperty("USMjSchema");
		return USMjSchema;
	}
	public String getUSMExcelPath()
	{
		String USMExcelPath=pro.getProperty("USMExcelPath");
		return USMExcelPath;
	}
	public String getUsersSkillMapbyuserID_JSchema()
	{
		String UsersSkillMapbyuserID_JSchema=pro.getProperty("UsersSkillMapbyuserID_JSchema");
		return UsersSkillMapbyuserID_JSchema;
	}
	public String getUsersSkillMapbySkillID_JSchema()
	{
		String UsersSkillMapbySkillID_JSchema=pro.getProperty("UsersSkillMapbySkillID_JSchema");
		return UsersSkillMapbySkillID_JSchema;
	}
	public String getendPointUSMByS()
	{
		String endPointUSMByS=pro.getProperty("endPointUSMByS");
		return endPointUSMByS;
	}
	
}

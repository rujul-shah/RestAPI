package utilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.gson.JsonObject;
import java.util.List;

import base.baseTest;
import io.restassured.RestAssured;


public class commonUtilities{

	public void login(JsonObject login) throws IOException
	{
//		System.out.println("Login");
		dataUtility objData = new dataUtility();
		baseTest.sURL=appConstants.sHostname+objData.readMethods("sURI_Login");
		RestAssured.baseURI=baseTest.sURL;
		//System.out.println("Username:"+login.get("username").toString());
		//System.out.println("Password:"+login.get("password").toString());
		String uname=login.get("username").toString().substring(2);
		String pwd=login.get("password").toString().substring(2);
		//login.getAsString();
		if(uname.equalsIgnoreCase(""))
		{
			baseTest.test.info("Blank Username");
			System.out.println("Blank Username");
		}
		
		else if(pwd.equalsIgnoreCase(""))
		{
			baseTest.test.info("Blank Password");
			System.out.println("Blank Password");
		}
		else 
		{
				Response response = RestAssured.given().contentType(baseTest.contentType).body(login).post();
				response.prettyPrint();
				System.out.println("Status code"+response.statusCode());
				if(response.statusCode()<300)
				{
				baseTest.sUserId=response.jsonPath().getString("userid[0]");
				baseTest.sToken=response.jsonPath().getString("token[0]");
				baseTest.mMap = new HashMap();
				baseTest.mMap.put("token", baseTest.sToken);
				baseTest.test.info("Login Success");
				}
				else
				{
					baseTest.test.info("Invalid username/Password");
					System.out.println(response.statusLine());
				}
		}
			
	}
	public void addUser(JsonObject user) throws IOException
	{
		dataUtility objData = new dataUtility();
		baseTest.sURL=appConstants.sHostname+objData.readMethods("sURI_AddData");
		RestAssured.baseURI=baseTest.sURL;
		//System.out.println(user);
		//System.out.println(baseTest.sToken);
		Response response = RestAssured.given().contentType(baseTest.contentType).headers(baseTest.mMap).body(user).post();
		System.out.println(response.statusCode());
		if(response.statusCode()<300)
		{
			baseTest.test.pass("User added successfully.");
			System.out.println("User added successfully.");
		}
		else 
		{
			baseTest.test.pass("User added successfully.");
			System.out.println(response.statusLine());
		}
			
		
	}
	public void getUserData() throws IOException
	{
		dataUtility objData=new dataUtility();
		baseTest.sURL=appConstants.sHostname+objData.readMethods("sURI_GetData");
		RestAssured.baseURI=baseTest.sURL;
		Map userMap = new HashMap();
		userMap.put("userid", baseTest.sUserId);
		Response response = RestAssured.given().contentType(baseTest.contentType).headers(baseTest.mMap).get();
		List<String> lId= response.jsonPath().getList("id");
		List<String> lUid = response.jsonPath().getList("userid");
		int i=0;
		for(String strUid : lUid)
		{
			if(strUid.equals(baseTest.sUserId))
			{
				baseTest.sId = lId.get(i);
				break;
			}
				else
					i++;
			
		}
		//System.out.println("SID=============="+baseTest.sId);
	//	baseTest.sId=response.jsonPath().getString("id[0]");
		System.out.println("USERDATA AFTER PERFORMING PREVIOUS OPERATION");
		System.out.println(response.jsonPath().getString("[0]"));
	}
	public void updateUserData(JsonObject userData) throws IOException
	{
		System.out.println("IN updateUserData");
		dataUtility objData = new dataUtility();
		baseTest.sURL=appConstants.sHostname+objData.readMethods("sURI_Update");
		RestAssured.baseURI=baseTest.sURL;
		//System.out.println("=================USERDATA=============");
		//System.out.println(userData);
		Response response = RestAssured.given().contentType(baseTest.contentType).headers(baseTest.mMap).body(userData).put();
		System.out.println(response.statusCode());
	}
	public void deleteUserData(JsonObject userData) throws IOException
	{
		//System.out.println("IN updateUserData");
		dataUtility objData = new dataUtility();
		baseTest.sURL=appConstants.sHostname+objData.readMethods("sURI_Delete");
		RestAssured.baseURI=baseTest.sURL;
		Response response = RestAssured.given().contentType(baseTest.contentType).headers(baseTest.mMap).body(userData).delete();
		System.out.println(response.statusCode());
	}
	
}

package testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import base.baseTest;
import io.restassured.response.Response;
import utilities.commonUtilities;

public class userAddition extends baseTest{
	/*Account# less than 7 digits*/
	@Test(priority = 4)
	public void testCase6() throws IOException
	{
		System.out.println("TestCase6");
		commonUtilities objectCommon = new commonUtilities();
		JsonObject jUser = new JsonObject();
		jUser.addProperty("accountno", "TA-261");
		jUser.addProperty("departmentno", "1");
		jUser.addProperty("salary", "1000000");
		jUser.addProperty("pincode", "2555555");
		objectCommon.addUser(jUser);
		//objectCommon.getUserData();
	}
	/*Add user with multiple digit department #*/
	@Test(priority = 5)
	public void testCase7() throws IOException
	{
		System.out.println("TestCase7");
		commonUtilities objectCommon = new commonUtilities();
		JsonObject jUser = new JsonObject();
		jUser.addProperty("accountno", "TA-0206193");
		jUser.addProperty("departmentno", "123");
		jUser.addProperty("salary", "1000000");
		jUser.addProperty("pincode", "2555555");
		objectCommon.addUser(jUser);
		//objectCommon.getUserData();
	}
	/*User Addition with proper data*/
	@Test(priority = 8)
	public void testCase8() throws IOException
	{
		System.out.println("TestCase8");
		commonUtilities objectCommon = new commonUtilities();
		JsonObject jUser = new JsonObject();
		jUser.addProperty("accountno", "TA-1818188");
		jUser.addProperty("departmentno", "1");
		jUser.addProperty("salary", "");
		jUser.addProperty("pincode", "");
		objectCommon.addUser(jUser);
		objectCommon.getUserData();
		
	}
	
	
	/*User Addition with proper data*/
	@Test(priority = 10)
	public void testCase10() throws IOException
	{
		System.out.println("TestCase10");
		commonUtilities objectCommon = new commonUtilities();
		JsonObject jUser = new JsonObject();
		jUser.addProperty("accountno", "TA-2610151");
		jUser.addProperty("departmentno", "1");
		jUser.addProperty("salary", "1000000");
		jUser.addProperty("pincode", "2555555");
		objectCommon.addUser(jUser);
		objectCommon.getUserData();
		
	}

}

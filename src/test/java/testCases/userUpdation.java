package testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import base.baseTest;
import io.restassured.response.Response;
import utilities.commonUtilities;

public class userUpdation extends baseTest {
	
	/*Update user with account no with 2 digits, salary blank and pincode # with 10 digits*/
	@Test(priority = 11)
	public void testCase11() throws IOException
	{
		System.out.println("IN TestCase11");
		commonUtilities objectCommon = new commonUtilities();
		JsonObject jUser = new JsonObject();
		jUser.addProperty("accountno", "TA-12");
		jUser.addProperty("departmentno", "7");
		jUser.addProperty("salary", "");
		jUser.addProperty("pincode", "380007123");
		jUser.addProperty("userid",baseTest.sUserId);
		jUser.addProperty("id", baseTest.sId);
		objectCommon.updateUserData(jUser);
		baseTest.test.pass("User updated successfully.");
		objectCommon.getUserData();
		
	}
	/*Proper Userdata*/
	@Test(priority = 12)
	public void testCase12() throws IOException
	{
		System.out.println("IN TestCase12");
		commonUtilities objectCommon = new commonUtilities();
		JsonObject jUser = new JsonObject();
		jUser.addProperty("accountno", "TA-2610151");
		jUser.addProperty("departmentno", "7");
		jUser.addProperty("salary", "1000000");
		jUser.addProperty("pincode", "380007");
		jUser.addProperty("userid",baseTest.sUserId);
		jUser.addProperty("id", baseTest.sId);
		objectCommon.updateUserData(jUser);
		baseTest.test.pass("User updated successfully.");
		objectCommon.getUserData();
		
	}
	
	
	
	/*Delete Data*/
	@Test(priority = 14)
	public void testCase14() throws IOException
	{
		//System.out.println("IN TestCase12");
		commonUtilities objectCommon = new commonUtilities();
		JsonObject jUser = new JsonObject();
		jUser.addProperty("userid",baseTest.sUserId);
		jUser.addProperty("id", baseTest.sId);
		//System.out.println("userid="+baseTest.sUserId);
		//System.out.println("id="+baseTest.sId);
		objectCommon.deleteUserData(jUser);
		baseTest.test.pass("User deleted successfully.");
		objectCommon.getUserData();
		
	}
}

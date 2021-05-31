package testCases;

import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.gson.JsonObject;

import base.baseTest;
import utilities.commonUtilities;
import utilities.dataUtility;

import org.testng.Assert;
import org.testng.annotations.Parameters;

public class loginCases extends baseTest {
	@Test(priority = 1)
	public void TestCase01() throws IOException
	{
		commonUtilities objectCommon = new commonUtilities();
		dataUtility objData = new dataUtility();
		JsonObject jLogin1=objData.readCredentialData();
		try {
		JsonObject jLogin = new JsonObject();
		jLogin.addProperty("username", jLogin1.get("username").toString());
		jLogin.addProperty("password", "");
		objectCommon.login(jLogin);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Test(priority=2)
	public void TestCase02() throws IOException
	{
		commonUtilities objectCommon = new commonUtilities();
		dataUtility objData = new dataUtility();
		JsonObject jLogin1=objData.readCredentialData();
		try {
		JsonObject jLogin = new JsonObject();
		jLogin.addProperty("username", jLogin1.get("username").toString());
		jLogin.addProperty("password", "ABC");
		objectCommon.login(jLogin);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

	@Test(priority = 3)
	public void TestCase03() throws IOException
	{
		commonUtilities objectCommon = new commonUtilities();
		dataUtility objData = new dataUtility();
		JsonObject jLogin=objData.readCredentialData();
		test.info("Login successfull");
		objectCommon.login(jLogin);
		objectCommon.getUserData();
		
	}

}

package RestAssured;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DynamicVariable {
	
	public class login {
		
		String sHostname = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net/";
		String sURI_Login = "login";
		String sURI_GetData= "getdata";
		String sURI_AddData = "addData";
		String sURI_Update = "updateData";
		String sURI_Delete = "deleteData";
		String sURI_LogOut = "logout";
		String sURL=null;
		public String sToken,sUserId,sId;
		Map map=new HashMap();
		
		
		@BeforeMethod
		public void URL_Init()
		{
			sURL=null;
		}
		
		@Test(priority = 0)
		public void login_API()
		{
			sURL = sHostname+sURI_Login;
			RestAssured.baseURI=sURL;
		
			String sUname = "rujuls@hotmail.com";
			String sPwd = "rujul123";
			String sBody="{\"username\": \""+sUname+"\", \"password\": \""+sPwd+"\"}";
		//	System.out.println(sBody);
			Response response = RestAssured.given().contentType("application/json").body(sBody).post();
			response.prettyPrint();
			sToken = response.jsonPath().getString("token[0]");
			sUserId=response.jsonPath().getString("userid[0]");
			map.put("token", sToken);
			
		}
		@Test(priority=1)
		public void addData()
		{
			sURL=sHostname+sURI_AddData;
			RestAssured.baseURI=sURL;
			JsonObject addData = new JsonObject();
			addData.addProperty("accountno", "TA-2005208");
			addData.addProperty("departmentno", "9");
			addData.addProperty("salary", "200005");
			addData.addProperty("pincode", "555555");
			System.out.println("=======================");
			System.out.println(addData);
			//Map<String,String> map= new HashMap<String, String>();
			
			Response response = RestAssured.given().contentType("application/json").headers(map).body(addData).post();
		//	response.prettyPrint();
			
		}
		@Test(priority=2)
		public void getData()
		{
			sURL=sHostname+sURI_GetData;
			RestAssured.baseURI=sURL;
			//Map map = new HashMap();
			//map.put("token", sToken);
			Response response = RestAssured.given().contentType("application/json").headers(map).get();
			sId=response.jsonPath().getString("id[0]");
			//System.out.println(response.jsonPath().getString("[0]"));
		}
		@Test(priority=3)
		public void updateData()
		{
			System.out.println("UID"+sUserId);
			System.out.println("ID"+sId);
			sURL=sHostname+sURI_Update;
			RestAssured.baseURI=sURL;
			JsonObject updateData=new JsonObject();
			updateData.addProperty("accountno", "TA-12345");
			updateData.addProperty("departmentno", "7");
			updateData.addProperty("salary", "100000");
			updateData.addProperty("pincode", "555555");
			updateData.addProperty("userid",sUserId);
			updateData.addProperty("id", sId);
			System.out.println("=======================");
			System.out.println(updateData);
			Response response=RestAssured.given().contentType("application/json").headers(map).body(updateData).put();
			
			//System.out.println(response.statusCode());
			//getData();
		}
		

}
}

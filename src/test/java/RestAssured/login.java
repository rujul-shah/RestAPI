package RestAssured;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class login {
	
	String sHostname = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net/";
	String sURI_Login = "login";
	String sURI_GetData= "getdata";
	String sURI_AddData = "addData";
	String sURI_Update = "updateData";
	String sURI_Delete = "deleteData";
	String sURI_LogOut = "logout";
	String sURL=null;
	public String sToken;
	
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
		System.out.println(RestAssured.baseURI);
		
		Response response = RestAssured.given().contentType("application/json").body("{\"username\": \"rujuls@hotmail.com\", \"password\": \"rujul123\"}").post();
	//	System.out.println(ras.asString());
		response.prettyPrint();
		//System.out.println(ras.jsonPath().getString("token[0]"));
		sToken = response.jsonPath().getString("token[0]");
		//System.out.println("Token="+sToken);
		
	}
	@Test(priority=2)
	public void getData_API()
	{
		sURL=sHostname+sURI_GetData;
		RestAssured.baseURI=sURL;
		System.out.println(sToken);
		Map<String,String> map = new HashMap();
		map.put("token", sToken);
		Response response = RestAssured.given().contentType("application/json").headers(map).get();
		//Response ras = RestAssured.given().contentType("application/json").headers("Token", sToken).get();
		//ras.prettyPrint();
		System.out.println("AccountID="+response.jsonPath().getString("[0]"));
		List<Object> liUserId = response.jsonPath().getList("accountno");
		for(Object obj:liUserId)
		{
			System.out.println(obj.toString());
		}
		
	}
	/*@Test(priority=1)
	public void addDataAPI()
	{
		sURL=sHostname+sURI_AddData;
		RestAssured.baseURI=sURL;
		Map<String, String> map = new HashMap();
		map.put("token", sToken);
		//map.put(sToken, sHostname)
		Response ras = RestAssured.given().contentType("application/json").headers(map).body("{\"accountno\": \"TA-1212182\", \"departmentno\": \"2\", \"salary\": \"12222222\", \"pincode\": \"123456\"}").post();
		System.out.println("Token"+sToken);
		//String str="{\"accountno\": \"TA-1212182\", \"departmentno\": \"2\", \"salary\": \"12222222\", \"pincode\": \"123456\"}";
	//	Response ras = RestAssured.given().contentType("application/json").headers("token", sToken).body(str).post();
		System.out.println("RRRRRRRRRRRR="+ras);
		System.out.println(ras.statusCode());
	}*/
	@Test(priority=1)
	public void updateData()
	{
		sURL=sHostname+sURI_Update;
		RestAssured.baseURI=sURL;
		Response response = RestAssured.given().contentType("application/json").headers("token", sToken).body("{\"accountno\":\"TA-1212182\",\"departmentno\":6,\"salary\":999999,\"pincode\":950356,\"userid\":\"9ATDdiU55R3Axp8t2Wdw\",\"id\":\"OFRwGYg6MTGcYnaDIv1z\"}").put();
		System.out.println(response.statusCode());
	}
	@Test(priority=9)
	public void deleteAPI()
	{
		sURL=sHostname+sURI_Delete;
		RestAssured.baseURI=sURL;
		Response response=RestAssured.given().contentType("application/json").headers("token",sToken).body("{\"userid\":\"9ATDdiU55R3Axp8t2Wdw\",\"id\":\"OFRwGYg6MTGcYnaDIv1z\"}").delete();
		System.out.println(response.statusCode());
	}
	

}

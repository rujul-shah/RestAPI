package RestAssured;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

public class login1 {
	
	 String sHost = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
	 String sURI_Login = "/login";
	 String sURI_getdata = "/getdata";
	 String sURI_adddata = "/addData";
	 String  sURI_deleteData = "/deleteData";
	 
	 String sURL = null;
	 String sToken = null;
	 String  sUID = null;
	 int count = 0;

	@BeforeSuite
	 public void loginAPI() {
	  
	  sURL = sHost + sURI_Login ;
	  RestAssured.baseURI = sURL;
	  String userData = "{username\": \"userid\", \"password\": \"pW\"}";
	  Response res = RestAssured.given().contentType("application/json").body(userData).post();
	  //System.out.println(res.asString());
	  int sStatusCode = res.statusCode();
	  
	//  System.out.println("statuc code is "+ sStatusCode);
	//  System.out.println();
	//  res.prettyPrint();
	  //sToken = res.jsonPath().getString("token").toString();
	  sToken = res.jsonPath().getString("token[0]");
	  sUID = res.jsonPath().getString("userid[0]");
	  
	  System.out.println("token   is csx  "+sToken);
	  
	  System.out.println("  ");
	  
	  System.out.println("sUID   is csx  "+sUID);
	     
	 }
	 
	 @BeforeMethod
	 public void bMethod() {
	  sURL = null;}


public void getDataAPI() {
  
  sURL = sHost + sURI_getdata ;
  RestAssured.baseURI = sURL;
  HashMap<String , String > map = new HashMap();
  map.put("token", sToken);
  
  Response res = RestAssured.given().contentType("application/json").headers(map).get();
  //System.out.println(res.asString());
  
  int statusCode = res.getStatusCode();
  System.out.println("statusCode   "+statusCode);
  
  String json = res.toString();
  //String s = JsonPath.a(json, "$.data[0]");
  //String s = JsonPath.read(json, "$[0]");
  //System.out.println(s);
  String data  =  res.asString();
  List<String > listUserid =  res.jsonPath().getList("userid");
  
        JsonPath  jsonPath = res.jsonPath();
  
  List<HashMap > listMap = jsonPath.getList("$");
  
  for(HashMap accMap : listMap) {
            
   String str = (String)accMap.get("userid");
   
   if(str.equals(sUID)) {
    String id = (String)accMap.get("id");
    System.out.println(str +"  - "+id);
   }
  
  }
  

  System.out.println(" the no of time user addes the data is  fffffffffffffff  "+count);
 }


public void addDataAPI() {
//  sURL = sHost +  sURI_adddata ;
//  RestAssured.baseURI = sURL;
//  
//  HashMap<String , String > map = new HashMap();
//  map.put("token", sToken);
//  map.put("contentType", "application/json");
//  
//  String dataToAdd = "{\"accountno\": \"TA-9898989\", \"departmentno\": \"2\", \"salary\": \"2\", pincode\": \"444444}";
//  //String dataToAdd = "{accountno\": \"TA-3333333, \"departmentno: \"3, \"salary\": \"3\", \"pincode\": \"333333\"}";
//  
// Response res = RestAssured.given().contentType("application/json").headers(map).body(dataToAdd).post();
//  
//  //System.out.println("Response ->  " + res.prettyPrint());
// // Response res = RestAssured.given().headers(map).body(dataToAdd).post();
//  int statusCode = res.getStatusCode();
//  System.out.println("addDataAPI  statusCode " + statusCode);
//  
//  
// }
 
// @Test
// public void deleteData() {
//  
//  sURL = sHost +  sURI_deleteData ;
//  RestAssured.baseURI = sURL;
//  //String deleteData = "{\"accountno\": \"TA-3333333\", \"departmentno\": \"3\", \"salary\": \"3\", \"pincode\": \"333333\"}";
//  
//  String deleteData = "{\"userid\": \"5peqXdShya0PTZrXGvCk\",\"id\": \"Adq1OylqHe5tZbFRx0lz\"}";
//  
//  HashMap<String , String > map =  new HashMap<String , String >();
//  map.put("token", sToken);
//  Response res = RestAssured.given().contentType("application/json").headers(map).body(deleteData).delete();
//  int statusCode = res.getStatusCode();
//  System.out.println("deleteData  statusCode " + statusCode);
//  
// }

}

package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.JsonObject;

import base.baseTest;

public class dataUtility {

	public JsonObject readCredentialData() throws IOException
	{
		
		FileInputStream fis = new FileInputStream(appConstants.CREDENTIALS_FILE_PATH);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Credentials");
		String username=sheet.getRow(3).getCell(1).getStringCellValue();
		String password=sheet.getRow(3).getCell(2).getStringCellValue();
		JsonObject json=new JsonObject();
		//Map map = new HashedMap();
		json.addProperty("username", username);
		json.addProperty("password", password);
		
		return json;
		
	}
	public String readMethods(String key) throws IOException
	{
		baseTest.properties=new Properties();
	//	baseTest.properties=new Properties();
		FileInputStream fis = new FileInputStream(appConstants.METHOD_FILE_PATH);
		baseTest.properties.load(fis);
		return baseTest.properties.getProperty(key);
		
		
	}
}

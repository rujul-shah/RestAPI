package base;


import java.text.SimpleDateFormat;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import utilities.appConstants;

public class baseTest {

		public static Properties properties;
		public static ExtentReports reports=null;
		public static ExtentHtmlReporter htmlreporter = null;
		public static ExtentTest test =null;
		public static SoftAssert sa;
		public static String sURL,sToken,sUserId,sId;
		public static Map mMap;
		public static String contentType = "application/json";
		
		@BeforeTest
		public void initReport()
		{
			//System.out.println("In initReport");
			String sReportTime = new SimpleDateFormat("yyyymmddhhmm").format(new Date());
			reports = new ExtentReports();
			htmlreporter = new ExtentHtmlReporter(appConstants.REPORTS_PATH+sReportTime+".html");
			reports.attachReporter(htmlreporter);
			//System.out.println("Reports Path:"+appConstants.REPORTS_PATH+sReportTime+".html");
		}
		@AfterTest
		public void flushReport()
		{
			//System.out.println("In Flush Report");
			reports.flush();
		}
		@BeforeMethod
		public void createReport(Method sMethodname)
		{
			//System.out.println(sMethodname.getName());
			test = reports.createTest(sMethodname.getName());
			sURL=null;
		//	System.out.println("In CreateReport="+test);
			//baseTest.sa=new SoftAssert();
		}
		
}

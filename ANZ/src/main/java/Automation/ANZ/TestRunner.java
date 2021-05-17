package Automation.ANZ;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class) 

@CucumberOptions(
		
		 plugin =  {"com.cucumber.listener.ExtentCucumberFormatter:report/test-reports/report.html"},
				 monochrome = true,
		features = "src/test/java/Automation/ANZ/Features"
) 

public class TestRunner { 
	String fileName = new SimpleDateFormat("yyyyMMddHHmm'.txt'").format(new Date());

	@AfterClass
	public static void writeExtentReport() {
		//Reporter.loadXMLConfig(CommonUtilities.getReportConfigPath());
		 Reporter.loadXMLConfig(new File(CommonUtilities.getReportConfigPath()));
		 Reporter.setSystemInfo("user", System.getProperty("user.name"));
         Reporter.setSystemInfo("os", "Mac OSX");
         Reporter.setTestRunnerOutput("Sample test runner output message");
//        ExtentProperties.INSTANCE.setReportPath( "output" + File.separator + "Run_" + System.currentTimeMillis() + File.separator
  //               + "report.html");
	}

	
 }



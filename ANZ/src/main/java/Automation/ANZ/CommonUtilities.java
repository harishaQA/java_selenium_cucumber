package Automation.ANZ;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtilities {

	public static Properties readPropertyFile(String fileName) {
		FileInputStream fileInputStream = null;
		Properties properties = null;
		try {
			fileInputStream = new FileInputStream(fileName);
			properties = new Properties();
			properties.load(fileInputStream);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return properties;
	}

	public static void waitTillPageLoads(WebDriver driver) {
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
	}
	
	public static void waitTillElementIsDisplayed(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public static void waitTillElementIsClickable(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
	}
	
	public static void waitImplicit(WebDriver driver, long timeInSeconds) {
		 driver.manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
		
	}
	
	public static String getReportConfigPath(){
		Properties properties = readPropertyFile("./LocatorsAndConstants.properties");
		 String reportConfigPath = properties.getProperty("REPORT_CONFIG_FILE_PATH");
		 if(reportConfigPath!= null) return reportConfigPath;
		 else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:REPORT_CONFIG_FILE_PATH"); 
		}
	
	public String generateRandom100To999() {
        long  value = Math.round(Math.random()*10000);		
		return String.valueOf(value);
	}
}

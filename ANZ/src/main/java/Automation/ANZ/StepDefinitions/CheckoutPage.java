package Automation.ANZ.StepDefinitions;

import java.util.Properties;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import Automation.ANZ.CommonUtilities;
import Automation.ANZ.ProductDetails;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CheckoutPage {
	WebDriver driver = ProductListPage.driver;
	Properties objectRepository = CommonUtilities.readPropertyFile("./LocatorsAndConstants.properties");
	
	
	
	
	 @Then("^I click on \"([^\"]*)\" button on \"([^\"]*)\" section of checkout page$")
	   public void clickOnProceedToCheckoutOnCheckoutPage(String buttonName, String sectionName){
		 WebElement buttonElement;
		 if(sectionName.equalsIgnoreCase("Summary"))
			 buttonElement= driver.findElement(By.cssSelector(objectRepository.getProperty("ProceedToCheckoutSummary")));
		 else if(sectionName.equalsIgnoreCase("Shipping")) {
			 CommonUtilities.waitImplicit(driver, 10);
			 driver.findElement(By.cssSelector(objectRepository.getProperty("IAgreeCheckboxShipping"))).click();
			 buttonElement= driver.findElement(By.xpath(objectRepository.getProperty("ProceedToCheckoutPage")));
		 }
		 else {
			CommonUtilities.waitImplicit(driver, 10);
			 buttonElement=driver.findElement(By.xpath(objectRepository.getProperty("ProceedToCheckoutPage")));
		 }
		 
		 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buttonElement);
		 CommonUtilities.waitTillElementIsClickable(driver, buttonElement);
		 buttonElement.click();
	 } 
		 
	 
	 @Then("^I verify product added$")
	   public void verifyProductDetailsInPaymentSection() {
		String productNameInPayment =  driver.findElement(By.cssSelector(objectRepository.getProperty("CheckoutProductName"))).getText();
		String productPriceInPayment =driver.findElement(By.cssSelector(objectRepository.getProperty("CheckoutProductPrice"))).getText();
		
		Assert.assertEquals(productNameInPayment, ProductDetails.productName, "Error:Product Name in PorductPage is"+ ProductDetails.productName+" and on Checkout Page is"+productNameInPayment);;
		Assert.assertEquals(productPriceInPayment, ProductDetails.productPrice, "Error:Product Name in PorductPage is"+ProductDetails.productPrice+" and on Checkout Page is"+productPriceInPayment);
		
	  
	   }
	 
	 
	 
	
}
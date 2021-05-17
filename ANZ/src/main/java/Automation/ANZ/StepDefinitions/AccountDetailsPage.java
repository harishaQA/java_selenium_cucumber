package Automation.ANZ.StepDefinitions;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Automation.ANZ.CommonUtilities;
import cucumber.api.java.en.*;

public class AccountDetailsPage{
	
	Properties objectRepository = CommonUtilities.readPropertyFile("./LocatorsAndConstants.properties");
	WebDriver driver= ProductListPage.driver;
	 CommonUtilities commonUtilities = new CommonUtilities();
	
	   @When("^I login as a user with \"([^\"]*)\" and \"([^\"]*)\"$")
	   public void signInAsUser(String username, String password) {
		   driver.findElement(By.cssSelector(objectRepository.getProperty("SignInEmailTextBox"))).sendKeys(username);
		   driver.findElement(By.cssSelector(objectRepository.getProperty("SignInPasswordTextBox"))).sendKeys(password);
		   driver.findElement(By.cssSelector(objectRepository.getProperty("SignInSubmitButton"))).click();
	   }


	   @When("^I enter the emailId to create account$")
	   public void enterNewEmailAddressToSignUP()  {
		  WebElement emailTextField=driver.findElement(By.cssSelector(objectRepository.getProperty("CreateAccountEmailTextBox")));
		   String newUserName =objectRepository.getProperty("USERNAME_NEW") +commonUtilities.generateRandom100To999();
		   String newEmailId= newUserName+objectRepository.getProperty("DOMAIN_NAME");	
		   emailTextField.click();
		   emailTextField.sendKeys(newEmailId);
	   }

	   @When("^I fill details on personal Information Section$")
	   public void createAccountPersonalInformationFormFilling() {
		   WebElement textBoxElement;
		   textBoxElement = driver.findElement(By.cssSelector(objectRepository.getProperty("FirstNameofCustomer")));
		   textBoxElement.sendKeys(objectRepository.getProperty("SIGN_UP_FIRST_NAME"));
		   
		   textBoxElement = driver.findElement(By.cssSelector(objectRepository.getProperty("LastNameofCustomer")));
		   textBoxElement.sendKeys(objectRepository.getProperty("SIGN_UP_LAST_NAME"));
		   
		   textBoxElement = driver.findElement(By.cssSelector(objectRepository.getProperty("PasswordTextbox")));
		   textBoxElement.sendKeys(objectRepository.getProperty("SIGN_UP_PASSWORD"));
		   
		   textBoxElement = driver.findElement(By.cssSelector(objectRepository.getProperty("AddressLineTextbox")));
		   textBoxElement.sendKeys(objectRepository.getProperty("SIGN_UP_ADDRESS1"));
		   
		   textBoxElement = driver.findElement(By.cssSelector(objectRepository.getProperty("CityNameTextbox")));
		   textBoxElement.sendKeys(objectRepository.getProperty("SIGN_UP_CITYNAME"));
		   
		   //select state from drop down
		   CommonUtilities.waitImplicit(driver, 5);
		   Select state = new Select(driver.findElement(By.cssSelector(objectRepository.getProperty("StateSelector"))));
		   state.selectByVisibleText(objectRepository.getProperty("SIGN_UP_STATE"));
		   
		   textBoxElement = driver.findElement(By.cssSelector(objectRepository.getProperty("PostcodeTextbox")));
		   textBoxElement.sendKeys(objectRepository.getProperty("SIGN_UP_PINCODE"));
		   
		   textBoxElement = driver.findElement(By.cssSelector(objectRepository.getProperty("MobileTextBox")));
		   textBoxElement.sendKeys(objectRepository.getProperty("SIGN_UP_MOBILE"));
	
	   }
	
	   @Then("^I verify first name and last name is displayed on header$")
		public void verifyIfUserisSignedIn( )   {
		   String userNameDisplayed = driver.findElement(By.cssSelector(objectRepository.getProperty("SignedInUserNameOnHeader"))).getText();
		String expectedUserName=objectRepository.getProperty("SIGN_UP_FIRST_NAME")+" "+objectRepository.getProperty("SIGN_UP_LAST_NAME");
		Assert.assertEquals(userNameDisplayed,expectedUserName, "user is not signed up with "+objectRepository.getProperty("SIGN_UP_FIRST_NAME"+"this user" ));
	   }
}
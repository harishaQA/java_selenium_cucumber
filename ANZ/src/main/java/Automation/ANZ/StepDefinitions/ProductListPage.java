package Automation.ANZ.StepDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;

import Automation.ANZ.CommonUtilities;
import Automation.ANZ.ProductDetails;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;

public class ProductListPage {

	Properties objectRepository = CommonUtilities.readPropertyFile("./LocatorsAndConstants.properties");
	public static WebDriver driver = null;

	
	@Before
	 public void setUpDriver() {
		 System.setProperty("webdriver.chrome.driver", objectRepository.getProperty("CHROME_DRIVER_PATH"));
		 driver = new ChromeDriver();
	 }
	

	@After
	public void afterScenario(Scenario scenario) {
		if (scenario.isFailed()) {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			try {
				File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				File destinationPath = new File(
						System.getProperty("user.dir") + "/report/screenshots/" + screenshotName + ".png");
				System.out.println("Path where screenhot is stored: "+destinationPath);
				Files.copy(sourcePath, destinationPath);
				Reporter.addScreenCaptureFromPath(destinationPath.toString());
			} catch (IOException e) {
			}
		}
		driver.quit();
	}
	
	 
	@Given("^I am on the landing page$")
	public void launchingTheHomePage() {
		driver.get(objectRepository.getProperty("LANDING_PAGE_URL"));
		 driver.manage().window().maximize();
		CommonUtilities.waitTillPageLoads(driver);

	}
	

	@Then("^I am redirected to \"([^\"]*)\" page$")
	public void validateThePage(String PageName) {
		WebElement webelement = null;
		 CommonUtilities.waitTillPageLoads(driver);
		switch (PageName) {
		case "SignIn":
			webelement = driver.findElement(By.xpath(objectRepository.getProperty("AuthenticationLabel")));
			break;
		case "Checkout":
			CommonUtilities.waitImplicit(driver, 10);
			webelement = driver.findElement(By.xpath(objectRepository.getProperty("ShoppingCartHeader")));
			break;
		case "Payment Section of Checkout":
			CommonUtilities.waitImplicit(driver, 5);
			webelement = driver.findElement(By.xpath(objectRepository.getProperty("PaymentHeader")));
			break;
		case "Create an account":
			CommonUtilities.waitImplicit(driver, 10);
			webelement = driver.findElement(By.cssSelector(objectRepository.getProperty("FirstNameofCustomer")));
			break;
		case "Home":
			webelement = driver.findElement(By.cssSelector(objectRepository.getProperty("HomePageTabs")));
			break;
		}
       
		Assert.assertTrue(webelement.isDisplayed(), "The " + PageName + " Page is not loaded");

	}

	@When("^I go to \"([^\"]*)\" page$")
	public void navigateToDesiredPage(String arg1) throws Throwable {
		driver.findElement(By.cssSelector(objectRepository.getProperty("HeaderBrandLogo"))).click();
		CommonUtilities.waitTillElementIsDisplayed(driver, driver.findElement(By.cssSelector(objectRepository.getProperty("HomePageTabs"))));
	}

	@When("^I add \"([^\"]*)\" product to cart$")
	public void addProductToCart(String arg1) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElements(By.cssSelector(objectRepository.getProperty("ProductCardList"))).get(1)).perform();
		
		driver.findElement(By.cssSelector(objectRepository.getProperty("AddtoCartButton"))).click();
		
		CommonUtilities.waitTillElementIsDisplayed(driver, driver.findElement(By.cssSelector(objectRepository.getProperty("ProceedToCheckoutButton"))));
		ProductDetails.productName=driver.findElement(By.cssSelector(objectRepository.getProperty("ProductItemName"))).getText();
		ProductDetails.productPrice=driver.findElement(By.cssSelector(objectRepository.getProperty("ProductPrice"))).getText();
		
	}

	@When("^I click on \"([^\"]*)\" button$")
	public void clickOnButton(String ButtonName) {
		WebElement element ;
		switch (ButtonName) {

		case "SignIn":
			driver.findElement(By.cssSelector(objectRepository.getProperty("SignInButton"))).click();
			break;
		case "Proceed to checkout":
			element =driver.findElement(By.cssSelector(objectRepository.getProperty("ProceedToCheckoutButton")));
			CommonUtilities.waitTillElementIsClickable(driver, element);
			element.click();
			break;
		case"Create an account"	:
			driver.findElement(By.cssSelector(objectRepository.getProperty("CreatAnAccountButton"))).click();
			break;
		case"Register":
			driver.findElement(By.cssSelector(objectRepository.getProperty("RegisterButton"))).click();
			break;
    
		}

	}

	
	
	

}
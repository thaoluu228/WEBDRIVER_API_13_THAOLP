package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_WebElement_PartII {
	//khai bao 1 bien driver dai dien cho selenium webdriver
	WebDriver driver;
	
	By emailTextboxBy = By.id("mail");
	By under18RadioBy = By.id("under_18");
	By educationtextAreaBy = By.id("edu");
	By passwordBy = By.id("password");
	By radioButtonBy = By.id("radio-disabled");
	By developmentCheckbox = By.id("development");
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}

	@Test
	public void TC_01_Displayed() {
		if (isElementDisplayed(emailTextboxBy)) {
			senkeyToElement(emailTextboxBy, "Automation Testing");
		}
	
		if (isElementDisplayed(under18RadioBy)) {
			clickToElement(under18RadioBy);
		}

		if (isElementDisplayed(educationtextAreaBy)) {
			senkeyToElement(educationtextAreaBy, "Automation Test");
		}

	}

	@Test
	public void TC_02_Enabled() {
	
		Assert.assertTrue(isElementEnabled(emailTextboxBy));
		
		Assert.assertTrue(isElementEnabled(under18RadioBy));
		Assert.assertTrue(isElementEnabled(educationtextAreaBy));
	
		Assert.assertFalse(isElementEnabled(passwordBy));
		Assert.assertFalse(isElementEnabled(radioButtonBy));
	}

	@Test
	public void TC_03_Selected() {
		driver.navigate().refresh();
		Assert.assertFalse(isElementSelected(developmentCheckbox));
		Assert.assertFalse(isElementSelected(under18RadioBy));
		
		clickToElement(under18RadioBy);
		clickToElement(developmentCheckbox);
		
		Assert.assertTrue(isElementSelected(developmentCheckbox));
		Assert.assertTrue(isElementSelected(under18RadioBy));
		
		clickToElement(developmentCheckbox);
		
		Assert.assertFalse(isElementSelected(developmentCheckbox));
		Assert.assertTrue(isElementSelected(under18RadioBy));
	}

	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element "+ by +" is enable");
			return true;
		} else {
			System.out.println("Element "+ by +" is disable");
			return false;
		}
	}
	
	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			return true;
		} else {
			return false;

		}
	}
	
	public void senkeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.sendKeys(value);
		
	}
	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}


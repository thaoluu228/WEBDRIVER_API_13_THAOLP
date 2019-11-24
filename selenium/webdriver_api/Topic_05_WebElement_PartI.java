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

public class Topic_05_WebElement_PartI {
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
		WebElement emailTextbox = driver.findElement(emailTextboxBy);
		if (emailTextbox.isDisplayed()) {
			emailTextbox.sendKeys("Automation Testing");
		}
		WebElement under18Radio = driver.findElement(under18RadioBy);
		if (under18Radio.isDisplayed()) {
			under18Radio.click();
		}
		WebElement educationTextArea = driver.findElement(educationtextAreaBy);
		if (educationTextArea.isDisplayed()) {
			educationTextArea.sendKeys("Automation Testing");
		}

	}

	@Test
	public void TC_02_Enabled() {
		WebElement emailTextbox = driver.findElement(emailTextboxBy);
		if (emailTextbox.isEnabled()){
			System.out.println("Element "+ emailTextboxBy +" is enable");
		} else {
			System.out.println("Element "+ emailTextboxBy +" is disable");

		}
		WebElement under18Radio = driver.findElement(under18RadioBy);
		if (under18Radio.isEnabled()) {
			System.out.println("Element "+ under18RadioBy +" is enable");
		} else {
			System.out.println("Element "+ under18RadioBy +" is disable");

		}
		WebElement educationTextArea = driver.findElement(educationtextAreaBy);
		if (educationTextArea.isEnabled()) {
			System.out.println("Element "+ educationtextAreaBy +" is enable");
		} else {
			System.out.println("Element "+ educationtextAreaBy +" is disable");
			
		}
		WebElement password = driver.findElement(passwordBy);
		if (password.isEnabled()) {
			System.out.println("Element "+ passwordBy +" is enable");
		} else {
			System.out.println("Element "+ passwordBy +" is disable");

		}
		WebElement radioButton = driver.findElement(radioButtonBy);
		if (radioButton.isEnabled()) {
			System.out.println("Element "+ radioButtonBy +" is enable");
		} else {
			System.out.println("Element "+ radioButtonBy +" is disable");

		}
	}

	@Test
	public void TC_03_Selected() {
		WebElement under18Radio = driver.findElement(under18RadioBy);
		under18Radio.click();
		WebElement development = driver.findElement(developmentCheckbox);
		development.click();
		
		Assert.assertTrue(development.isSelected());
		Assert.assertTrue(under18Radio.isSelected());
		
		development.click();

		Assert.assertFalse(development.isSelected());
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}


package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Part1 {
	//khai bao 1 bien driver dai dien cho selenium webdriver
	WebDriver driver;
	String firstname = "Thao";
	String lastname = "Luu";
	String email = "thaotest@yopmail.com";
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeMethod 
	public void beforeMethod() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	}

	@Test
	public void TC_01_LoginWithEmailandPasswordEmpty() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(emailErrorMsg, "This is a required field.");
		
		String passErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(passErrorMsg, "This is a required field.");

	}

	@Test
	public void TC_02_LoginwithEmailInvalid() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("1234@4356.678");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(emailErrorMsg, "Please enter a valid email address. For example johndoe@domain.com.");
		
		String passErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(passErrorMsg, "This is a required field.");
		
	}

	@Test
	public void TC_03_LoginwithPasswordLessthan6Char() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("thaotest@yopmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("12345");
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		
		String passErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(passErrorMsg, "Please enter 6 or more characters without leading or trailing spaces.");
		
	}
	
	
	@Test
	public void TC_04_LoginwithPasswordIncorrect() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("thaotest@yopmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123457");
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		
		String errorMsg = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
		Assert.assertEquals(errorMsg, "Invalid login or password.");
		
	}
	
	@Test
	public void TC_05_LoginwithEmailandPasswordCorrect() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("thaotest@yopmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		
		String successMsg = driver.findElement(By.xpath("//div[@class='dashboard']//h1")).getText();
		Assert.assertEquals(successMsg, "MY DASHBOARD");
		
		String welcomeMsg = driver.findElement(By.xpath("//div[@class='welcome-msg']//strong[starts-with(text(),'Hello')]")).getText();
		System.out.println("Text = " + welcomeMsg);
		Assert.assertTrue(welcomeMsg.contains(firstname));
		Assert.assertTrue(welcomeMsg.contains(lastname));
		
		String boxContent = driver.findElement(By.xpath("//p[contains(.,'thaotest@yopmail.com')]")).getText();
		Assert.assertTrue(boxContent.contains(email));
	}
		

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
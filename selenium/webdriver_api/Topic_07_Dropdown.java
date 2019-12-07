package webdriver_api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Dropdown {
	//khai bao 1 bien driver dai dien cho selenium webdriver
	WebDriver driver;
	Select select;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Html_DropdownList() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		select = new Select(driver.findElement(By.id("job1")));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText("Mobile Testing");
		Assert.assertEquals("Mobile Testing", select.getFirstSelectedOption().getText());
		select.selectByValue("manual");
		Assert.assertEquals("Manual Testing", select.getFirstSelectedOption().getText());
		select.selectByIndex(9);
		Assert.assertEquals("Functional UI Testing", select.getFirstSelectedOption().getText());
		//get all items trong list
		int listJob1Number = select.getOptions().size();
		Assert.assertEquals(10,listJob1Number);
		
		select = new Select(driver.findElement(By.id("job2")));
		Assert.assertTrue(select.isMultiple());
		select.selectByVisibleText("Automation");
		select.selectByVisibleText("Mobile");
		select.selectByVisibleText("Desktop");
		Thread.sleep(3000);
		List <WebElement> optionSelected = select.getAllSelectedOptions();
		Assert.assertEquals(optionSelected.size(), 3);
		//lay text cua nhung items da chon
		List <String> arraySelected = new ArrayList<String>();
		for(WebElement list: optionSelected) {
			arraySelected.add(list.getText());
		}
		Assert.assertTrue(arraySelected.contains("Automation"));
		Assert.assertTrue(arraySelected.contains("Mobile"));
		Assert.assertTrue(arraySelected.contains("Desktop"));
		select.deselectAll();
		Thread.sleep(3000);
		List <WebElement> deselected = select.getAllSelectedOptions();
		Assert.assertEquals(deselected.size(), 0);

	}

	@Test
	public void TC_02_() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		driver.findElement(By.id("gender-female")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Thao");
		driver.findElement(By.id("LastName")).sendKeys("Luu");
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		Assert.assertEquals(select.getOptions().size(), 32);
		select.selectByValue("3");
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getOptions().size(), 13);
		select.selectByValue("10");
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		Assert.assertEquals(select.getOptions().size(), 112);
		select.selectByValue("1996");
		driver.findElement(By.id("Email")).sendKeys("thaotest123@yopmail.com");
		driver.findElement(By.id("Password")).sendKeys("12345678");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("12345678");
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-account'][text()='My account']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//a[@class='ico-logout']")).getText(), "Log out");
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Your registration completed']")).isDisplayed());
		
	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
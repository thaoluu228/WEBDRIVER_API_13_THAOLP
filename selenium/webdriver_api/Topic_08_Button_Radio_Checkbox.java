package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Button_Radio_Checkbox {
	//khai bao 1 bien driver dai dien cho selenium webdriver
	WebDriver driver;
	JavascriptExecutor javascript;
	Alert alert;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		javascript = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Javascript() {
		driver.get("http://live.demoguru99.com/");
		clickElementbyJavasript(By.xpath("//div[@class='footer']//a[@title='My Account']"));
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		clickElementbyJavasript(By.xpath("//a[@class='button']"));
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/styling/checkboxes");
		String checkboxInput = "//label[text()='Dual-zone air conditioning']/preceding-sibling::input";
		clickElementbyJavasript(By.xpath(checkboxInput));
		Assert.assertTrue(isElementSelected(By.xpath(checkboxInput)));
		clickElementbyJavasript(By.xpath(checkboxInput));
		Assert.assertFalse(isElementSelected(By.xpath(checkboxInput)));
	}
	@Test
	public void TC_03_Radiobutton() {
		driver.get("https://demos.telerik.com/kendo-ui/styling/radios");
		String checkboxCarInput = "//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input";
		Assert.assertTrue(isElementEnabled(By.xpath(checkboxCarInput)));
		clickElementbyJavasript(By.xpath(checkboxCarInput));
		checktoCheckbox(By.xpath(checkboxCarInput));
		Assert.assertTrue(isElementSelected(By.xpath(checkboxCarInput)));
		
	}
	@Test
	public void TC_04_AcceptAlert() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Thread.sleep(2000);
		Assert.assertTrue(isElementDisplayed(By.xpath("//p[@id='result' and text()='You clicked an alert successfully ']")));
	}
	@Test
	public void TC_05_CancelAlert() throws InterruptedException {
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Thread.sleep(2000);
		Assert.assertTrue(isElementDisplayed(By.xpath("//p[@id='result' and text()='You clicked: Cancel']")));
	}
	
	@Test
	public void TC_06_Prompt() {
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		String text = "Thao Luu";
		alert.sendKeys(text);
		alert.accept();
		//Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and text()='You entered: "+text+"']")).isDisplayed());
		Assert.assertTrue(isElementDisplayed(By.xpath("//p[@id='result' and text()='You entered: "+text+"']")));
	}
	public void clickElementbyJavasript (By by) {
		WebElement element = driver.findElement(by);
		javascript.executeScript("arguments[0].click();", element);
		
		
	}
	
	public boolean isElementSelected (By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			return true;
		} else {
			return false;

		}
	}

	public boolean isElementEnabled (By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			return true;
		} else {
			return false;

		}
	}
	public boolean isElementDisplayed (By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			return true;
		} else {
			return false;

		}
	}
	
	public void checktoCheckbox (By by) {
		WebElement element = driver.findElement(by);
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void checktoUnCheckbox (By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			element.click();
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
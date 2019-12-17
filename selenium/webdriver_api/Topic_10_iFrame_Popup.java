package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_10_iFrame_Popup {
	//khai bao 1 bien driver dai dien cho selenium webdriver
	WebDriver driver;
	WebDriverWait WaitExplicit;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		WaitExplicit = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Popup_Iframe() throws Exception {
		driver.get("https://kyna.vn/");
		
		List <WebElement> FancyPopup = driver.findElements(By.cssSelector(".fancybox-inner"));
		//Neu hien popup thi click vao
		if (FancyPopup.size() > 0) {
			Assert.assertTrue(FancyPopup.get(0).isDisplayed());
			driver.findElement(By.cssSelector(".fancybox-close")).click();
		}
		
		//Assert.assertTrue(driver.findElement(By.cssSelector(".fancybox-inner")).isDisplayed());
		//driver.findElement(By.cssSelector(".fancybox-close")).click();
		
		//switch sang iframe de tuong tac, chuyen tu domain chinh sang
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage']//iframe")));
		
		Assert.assertTrue(driver.findElement(By.cssSelector("#facebook")).isDisplayed());
		String FacebookLikes = driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText();
		System.out.println("So like = "+ FacebookLikes);
		Assert.assertEquals(FacebookLikes, "170K likes");
		
		//switch ve mainpage de tuong tac voi mainpage
		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		
		WaitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".fancybox-overlay-fixed")));
		
		driver.findElement(By.xpath("//li[@class='login-signup']/a[@class='button-login']")).click();
		driver.findElement(By.id("user-login")).sendKeys("automationfc.vn@gmail.com");
		driver.findElement(By.id("user-password")).sendKeys("automationfc.vn@gmail.com");
		driver.findElement(By.id("btn-submit-login")).click();
		
		WebElement UserLogin = driver.findElement(By.xpath("//li[@class='account dropdown wrap']//span[@class='user']"));
		
		Assert.assertTrue(UserLogin.isDisplayed());
		Assert.assertEquals(UserLogin.getText(), "Automation FC");
		

	}

	@Test
	public void TC_02_() {
		
	}

	@Test
	public void TC_03_() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
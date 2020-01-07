package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Wait {
	//khai bao 1 bien driver dai dien cho selenium webdriver
	WebDriver driver;
	WebDriverWait waitExplicit;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/libraries/geckodriver");
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_ElementDisplayorVisible() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		//Element co hien thi tren UI va trong DOM
		boolean status = driver.findElement(By.xpath("//button[@id='SubmitLogin']")).isDisplayed();
		//wait cho Element displayed visible
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));
		System.out.println("Tra ve status = " + status);
		
		//Element khong hien thi tren UI nhung co trong DOM
		boolean status2 = driver.findElement(By.xpath("//div[@id='create_account_error']")).isDisplayed();
		//wait cho Element khong hien thi
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='create_account_error']")));
		System.out.println("Tra ve status = " + status2);
		
		//Element khong co trong UI va DOM
		boolean status3 = driver.findElement(By.xpath("//input[]@id='id_order']")).isDisplayed();
		System.out.println("Tra ve status = " + status3);
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
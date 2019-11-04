package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Locator_In_Selenium {
	//khai bao 1 bien driver dai dien cho selenium webdriver
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();	
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();		
	}

	@Test
	public void TC_01_Locator() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		//ID
		driver.findElement(By.id("email")).sendKeys("testing@gmail.com");
		
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("email")).clear();
		//NAME
		driver.findElement(By.name("send")).click();
		
		//TAGNAME tim xem co bao nhieu duong link o page nay va in gia tri ra
		//Cont bao nhieu element o tren page
		
		List <WebElement> links = driver.findElements(By.tagName("a"));
		
		int linkNumber = links.size();
		System.out.println("Tong so link = " + linkNumber);
		
		//LINKTEXT
		driver.findElement(By.linkText("ORDERS AND RETURNS")).click();
			
	}
	
	@Test
	public void TC_02_CSS() {
		
		driver.findElement(By.cssSelector("input[name='oar_order_id']")).sendKeys("thaotest@gmail.com");
		driver.findElement(By.cssSelector("#oar_billing_lastname")).sendKeys("THAO");
		driver.findElement(By.cssSelector("#oar_email")).sendKeys("testing@gmail.com");
		System.out.println("The a dung css = " + driver.findElements(By.cssSelector("a")).size());
		driver.findElement(By.cssSelector("a[href='http://live.demoguru99.com/index.php/catalogsearch/advanced/']")).click();
	}

	@Test
	public void TC_03_XPATH() {
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("THAO");
		driver.findElement(By.xpath("//input[@id='description']")).sendKeys("testingxpath");
		driver.findElement(By.xpath("//input[@name='sku']")).sendKeys("ANK-1234");
		System.out.println("The a dung xpath = " + driver.findElements(By.xpath("//a")).size());
		driver.findElement(By.xpath("//a[text()='About Us']")).click();	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
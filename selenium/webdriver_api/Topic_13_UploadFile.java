package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class Topic_13_UploadFile {
	//khai bao 1 bien driver dai dien cho selenium webdriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String upload1 = projectPath + "/uploadFile/upload1.png";
	String upload2 = projectPath + "/uploadFile/upload2.png";
	String upload3 = projectPath + "/uploadFile/upload3.png";
	
	@BeforeClass
	public void beforeClass() {
		//Firefox
		//System.setProperty("webdriver.gecko.driver", projectPath + "/libraries/geckodriver");
		//driver = new FirefoxDriver();
		//Chrome
        System.setProperty("webdriver.chrome.driver", projectPath + "/libraries/chromedriver");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	public void TC_01_() throws InterruptedException {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(upload1 + "\n" + upload2 + "\n" + upload3);
		
		Thread.sleep(2000);
		
		List <WebElement> startButton = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		for (WebElement button: startButton)
			button.click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='upload1.png']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='upload2.png']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='upload3.png']")).isDisplayed());

	}

	public void TC_02_() {
		
	}

	public void TC_03_() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
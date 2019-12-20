package webdriver_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_11_Window_Tab {
	//khai bao 1 bien driver dai dien cho selenium webdriver
	WebDriver driver;
	Alert alert;
	
	@BeforeClass
	public void beforeClass() {
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "/libraries/chromedriver");
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public void TC_01_() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//truyen ID cua trang chu ban dau
		String parentID = driver.getWindowHandle();
		System.out.println("ParentID = " + parentID);
		//click vao link
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		Thread.sleep(2000);
		//chuyen qua tab moi (chi co 2 tab duoc mo)
		switch2WindowbyID(parentID);
		Assert.assertEquals(driver.getTitle(), "Google");
		
		driver.switchTo().window(parentID);
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		
		switchWindowbyTitle("Facebook - Đăng nhập hoặc đăng ký");
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		Thread.sleep(2000);
		driver.switchTo().window(parentID);
		
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		switchWindowbyTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		Thread.sleep(2000);
		driver.switchTo().window(parentID);
		
		closeAllTabWithoutParent(parentID);
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");

	}

	
	public void TC_02_Tab() {
		driver.get("https://kyna.vn/");
		String parentID = driver.getWindowHandle();
		List<WebElement> Fancypopup = driver.findElements(By.cssSelector(".fancybox-inner"));
		if (Fancypopup.size() > 0) {
			Assert.assertTrue(Fancypopup.get(0).isDisplayed());
			driver.findElement(By.cssSelector(".fancybox-close")).click();
		}
		driver.findElement(By.xpath("//a[@href='https://www.facebook.com/kyna.vn']")).click();
		switch2WindowbyID(parentID);
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
		driver.switchTo().window(parentID);
		
		driver.findElement(By.xpath("//a[@href='https://www.youtube.com/user/kynavn']")).click();
		switchWindowbyTitle("Kyna.vn - YouTube");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");
		driver.switchTo().window(parentID);
		
		driver.findElement(By.xpath("//a[@href='https://zalo.me/1985686830006307471']")).click();
		switchWindowbyTitle("Kyna.vn");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn");
		driver.switchTo().window(parentID);
		
		driver.findElement(By.xpath("//a[@href='https://kynaforkids.vn/']")).click();
		switchWindowbyTitle("Kynaforkids.vn trường học trực tuyến cho trẻ");
		Assert.assertEquals(driver.getTitle(), "Kynaforkids.vn trường học trực tuyến cho trẻ");
		driver.switchTo().window(parentID);
		
		closeAllTabWithoutParent(parentID);
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia");
	}

	@Test
	public void TC_03_TabandWindow() throws InterruptedException {
		driver.get("http://live.demoguru99.com/index.php/");
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();

		driver.findElement(By.xpath("//a[text()='Sony Xperia']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Sony Xperia has been added to comparison list.");
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		
		switchWindowbyTitle("Products Comparison List - Magento Commerce");
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Sony Xperia']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Samsung Galaxy']")).isDisplayed());
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		driver.findElement(By.xpath("//button[@title='Close Window']")).click();
		
		switchWindowbyTitle("Mobile");
		
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		Thread.sleep(2000);
		alert = driver.switchTo().alert();
		alert.accept();

		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void switch2WindowbyID (String parentID) {
		//lay tat ca ID dang co
		Set <String> allID = driver.getWindowHandles();
		//dung vong lap duyet qua tung ID
		for (String runID: allID) {
			//neu id hien tai khac id chu thi switch qua
			if (!runID.equals(parentID)) {
				driver.switchTo().window(runID);
				break;
			}
		}
	}
	
	public void switchWindowbyTitle  (String title) {
		//lay ra all ID
		Set <String> allID = driver.getWindowHandles();
		//duyet qua tung ID
		for (String runID: allID) {
			//chuyen qua cac tab dang chay
			driver.switchTo().window(runID);
			//lay title cua cac tab
			String currentWin = driver.getTitle();
			//neu title cua tab dang dung bang voi title mong muon thi dung lai
			if (currentWin.equals(title)) {
				break;
			}
		}
	}
	
	public boolean closeAllTabWithoutParent (String parentID) {
		Set <String> allID = driver.getWindowHandles();
		for (String runID: allID) {
			if (!runID.equals(parentID)) {
				driver.switchTo().window(runID);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
		if (driver.getWindowHandles().size()==1)
			return true;
		else
			return false;
	}
}	
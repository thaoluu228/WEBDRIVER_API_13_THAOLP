package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_UserInteraction {
	//khai bao 1 bien driver dai dien cho selenium webdriver
	WebDriver driver;
	Actions action;
	Alert alert;
	
	@BeforeClass
	public void beforeClass() {
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "/libraries/chromedriver");
		
		driver = new ChromeDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01_MoveMouse() {
		driver.get("https://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Discover')]"))).perform();
		driver.findElement(By.xpath("//a[contains(text(),'Forever21')]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Forever 21']")).isDisplayed());
		
	}
		
	
	public void TC_02_ClickandHold() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		//khai bao list cac items
		List <WebElement> listNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		int numberSize = listNumber.size();
		System.out.println("TotalNumber = " + numberSize);
		//click and hold chuot chon item
		action.clickAndHold(listNumber.get(0)).moveToElement(listNumber.get(3)).release().perform();
		//khai bao cac items da chon
		List <WebElement> selectedNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		System.out.println("TotalSelected = "+ selectedNumber.size());
		//in ra cac number da chon
		for (WebElement number: selectedNumber) {
			System.out.println(number.getText());
		}
		Assert.assertEquals(selectedNumber.size(), 4);
	}

	
	public void TC_03_ClickandSelect() throws InterruptedException {
		driver.navigate().refresh();
		List <WebElement> listNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println("TotalNumber = " + listNumber.size());
		//an va giu phim control
		action.keyDown(Keys.CONTROL).perform();
		//action.click(listNumber.get(0)).click(listNumber.get(2)).click(listNumber.get(5)).click(listNumber.get(10)).perform();
		listNumber.get(0).click();
		listNumber.get(3).click();
		listNumber.get(6).click();
		listNumber.get(11).click();
		Thread.sleep(2000);
		action.keyUp(Keys.CONTROL).perform();
		
		List <WebElement> selectedItems = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		System.out.println("ToTalSelected = " + selectedItems.size());
		
		for (WebElement number: selectedItems) {
			System.out.println(number.getText());
		}
		Assert.assertEquals(selectedItems.size(), 4);
	}
	
	
	public void TC_04_DoubleClick () throws Exception {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[contains(text(),'Double click me')]"))).perform();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
		Thread.sleep(2000);
	}
	
	public void TC_05_RightClick () {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"))).perform();
		List <WebElement> listMenu = driver.findElements(By.xpath("//ul[@class='context-menu-list context-menu-root']/li"));
		
		action.moveToElement(listMenu.get(6)).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']")).isDisplayed());
		
		action.click(driver.findElement(By.xpath("//li[contains(@class,'quit')]"))).perform();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "clicked: quit");
		alert.accept();
	}
	@Test
	public void TC_06_DragandDrop () throws InterruptedException {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
		WebElement sourceCircle = driver.findElement(By.id("draggable"));
		WebElement targetCircle = driver.findElement(By.id("droptarget"));
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		Thread.sleep(2000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
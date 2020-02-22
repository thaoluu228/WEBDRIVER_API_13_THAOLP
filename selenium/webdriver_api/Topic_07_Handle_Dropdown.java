package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Handle_Dropdown {
	// khai bao 1 bien driver dai dien cho selenium webdriver
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor javascript;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "/libraries/chromedriver");

		driver = new ChromeDriver();
		// System.setProperty("webdriver.gecko.driver", projectPath +
		// "/libraries/geckodriver");
		// driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 10);
		javascript = (JavascriptExecutor) driver;
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Jquery() throws InterruptedException {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectIteminCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li", "6");

		Assert.assertTrue(isDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='6']"));
		Thread.sleep(2000);

		selectIteminCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li", "19");

		Assert.assertTrue(isDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='19']"));
		Thread.sleep(2000);

	}

	@Test
	public void TC_02_Angular() {
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		selectIteminCustomDropdown("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']/li", "Cricket");

		Assert.assertEquals(getTextbyJS("#games_hidden > option"), "Cricket");
	}

	@Test
	public void TC_03_React() throws InterruptedException {
		driver.get("https://react.semantic-ui.com/modules/dropdown/");

		selectIteminCustomDropdown("//div[@class='ui fluid selection dropdown']",
				"//div[@class='visible menu transition']/div[@role='option']/span", "Christian");

		Assert.assertTrue(isDisplayed("//div[@role='listbox']/div[@class='text' and text()='Christian']"));
		Thread.sleep(2000);
	}

	@Test
	public void TC_04_Editable() throws InterruptedException {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		Thread.sleep(2000);
		inputIteminCustomDropdown("//div[@class='ui fluid search selection dropdown']", "//input[@class='search']",
				"Albania");
		Assert.assertTrue(isDisplayed("//div[@class='text' and text()='Albania']"));
	}

	@Test
	public void TC_05_VueDropdown() throws InterruptedException {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectIteminCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li/a",
				"Third Option");

		Assert.assertTrue(isDisplayed("//li[@class='dropdown-toggle' and contains(text(),'Third Option')]"));
		Thread.sleep(2000);
	}

	public void selectIteminCustomDropdown(String parentXpath, String allItemsXpath, String expectedText) {
		// click vao the de no hien thi het cac items
		driver.findElement(By.xpath(parentXpath)).click();

		// khai bao 1 list webElement chua all items ben trong
		List<WebElement> allItems = driver.findElements(By.xpath(allItemsXpath));

		// wait cho all items xuat hien trong dom
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));

		// get ra tung item roi so sanh voi item minh chon
		for (WebElement item : allItems) {
			System.out.println("Text =" + item.getText());
			// click vao item minh muon chon
			if (item.getText().equals(expectedText)) {
				item.click();
				break;
			}
		}
	}

	public void inputIteminCustomDropdown(String parentXpath, String inputXpath, String expectedText) {
		// click de hien thi het cac items
		driver.findElement(By.xpath(parentXpath)).click();

		// input text vao textbox
		driver.findElement(By.xpath(inputXpath)).sendKeys(expectedText);

		// truyen phim enter
		action.sendKeys(driver.findElement(By.xpath(inputXpath)), Keys.ENTER).perform();
	}

	public boolean isDisplayed(String xpathLocator) {
		WebElement element = driver.findElement(By.xpath(xpathLocator));
		if (element.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public String getText(String xpathLocator) {
		WebElement element = driver.findElement(By.xpath(xpathLocator));
		return element.getText();
	}

	public String getTextbyJS(String locator) {
		return (String) javascript.executeScript("return document.querySelector('" + locator + "').text");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
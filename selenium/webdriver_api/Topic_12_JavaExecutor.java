package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_JavaExecutor {
	//khai bao 1 bien driver dai dien cho selenium webdriver
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebElement element;

	@BeforeClass
	public void beforeClass() {
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.gecko.driver", projectPath + "/libraries/geckodriver");
		driver = new FirefoxDriver();
		//String projectPath = System.getProperty("user.dir");
		//System.setProperty("webdriver.chrome.driver", projectPath + "/libraries/chromedriver");
		
		//driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	public void TC_01_JS() {
		
		//Mo web bang JS
		navigateToUrlByJS("http://live.demoguru99.com/");
		
		//get domain bang JS
		String liveDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(liveDomain, "live.demoguru99.com");
		
		//get URL bang JS
		String liveURL = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(liveURL, "http://live.demoguru99.com/");
		
		//highlight 
		highlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button");
		
		//verify text bang JS
		verifyTextInInnerText("Samsung Galaxy was added to your shopping cart.");
		
		clickToElementByJS("//a[text()='Customer Service']");
		
		//verify title
		Object titlePage = jsExecutor.executeScript("return document.title");
		Assert.assertEquals(titlePage, "Customer Service");
		
		//scroll den newsletter
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		
		//verify text 
		String pageInnerText = (String) executeForBrowser("return document.documentElement.innerText;");
		Assert.assertTrue(pageInnerText.contains("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		String demoDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(demoDomain, "demo.guru99.com");
	}
	
	public void TC_02_Remove_Attribute() {
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		String username = "mngr238966";
		String password = "emYmEqe";
		
		//Input in new Customer/Output data
		String customerName = "Thao Luu";
		String gender = "female";
		String DOB = "1996-03-10";
		String address = "Cau Giay";
		String city = "Hanoi";
		String state = "Dich Vong";
		String pin = "333555";
		String mobile = "1256478";
		String email = "thaoluu" + randomNumber() + "@yopmail.com";
		
		//Locator for New/Edit Customer
		By nameTextbox = By.name("name");
		By genderRadio = By.xpath("//input[@value='f']");
		By DobTextbox = By.name("dob");
		By addressTextarea = By.name("addr");
		By cityTextbox = By.name("city");
		By stateTextbox = By.name("state");
		By pinTextbox = By.name("pinno");
		By phoneTextbox = By.name("telephoneno");
		By emailTextbox = By.name("emailid");
		By passTextbox = By.name("password");
		
		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		String homepageWelcomeMsg = driver.findElement(By.tagName("marquee")).getText();
		Assert.assertEquals(homepageWelcomeMsg, "Welcome To Manager's Page of Guru99 Bank");
		String welcomeMsgId = driver.findElement(By.xpath("//td[starts-with(text(), 'Manger Id')]")).getText();
		Assert.assertTrue(welcomeMsgId.contains(username));
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		//Input data to New Customer
		driver.findElement(nameTextbox).sendKeys(customerName);
		driver.findElement(genderRadio).click();
		
		//remove attribute type='date'
		removeAttributeInDOM("//input[@id='dob']", "type");
		driver.findElement(DobTextbox).sendKeys(DOB);
		driver.findElement(addressTextarea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(mobile);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passTextbox).sendKeys(password);
		driver.findElement(By.name("sub")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Customer Registered Successfully!!!");
		
		//Verify Output data = Input data
		Assert.assertEquals(customerName, driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText());
		Assert.assertEquals(gender, driver.findElement(By.xpath("//td[text()='Gender']//following-sibling::td")).getText());
		Assert.assertEquals(DOB, driver.findElement(By.xpath("//td[text()='Birthdate']//following-sibling::td")).getText());
		Assert.assertEquals(address, driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText());
		Assert.assertEquals(city, driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText());
		Assert.assertEquals(state, driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")).getText());
		Assert.assertEquals(pin, driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")).getText());
		Assert.assertEquals(mobile, driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText());
		Assert.assertEquals(email, driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")).getText());
		
	}

	@Test
	public void TC_03_Create_Account() throws InterruptedException {
		String email = "thaoluu" + randomNumber() + "@yopmail.com";
		navigateToUrlByJS("http://live.demoguru99.com/");
		//click bang JS
		clickToElementByJS("//div[@id='header-account']//a[text()='My Account']");
		clickToElementByJS("//a[@class='button']");
		
		//nhap thong tin dang ky
		sendkeyToElementByJS("//input[@id='firstname']", "Thao");
		sendkeyToElementByJS("//input[@id='middlename']", "Phuong");
		sendkeyToElementByJS("//input[@id='lastname']", "Phuong");
		sendkeyToElementByJS("//input[@id='email_address']", email);
		sendkeyToElementByJS("//input[@id='password']", "12345678");
		sendkeyToElementByJS("//input[@id='confirmation']", "12345678");
		
		clickToElementByJS("//span[text()='Register']");
		Thread.sleep(2000);
		//verifly text
		String successText = (String) executeForBrowser("return document.documentElement.innerText;");
		Assert.assertTrue(successText.contains("Thank you for registering with Main Website Store."));
		
		clickToElementByJS("//a[text()='Log Out']");
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	// Browser
		public Object executeForBrowser(String javaSript) {
			return jsExecutor.executeScript(javaSript);
		}

		public boolean verifyTextInInnerText(String textExpected) {
			String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
			System.out.println("Text actual = " + textActual);
			return textActual.equals(textExpected);
		}
		
        //scroll den cuoi trang 
		public void scrollToBottomPage() {
			jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}

		//navigate
		public void navigateToUrlByJS(String url) {
			jsExecutor.executeScript("window.location = '" + url + "'");
		}

		// Element
		public void highlightElement(String locator) {
			element = driver.findElement(By.xpath(locator));
			String originalStyle = element.getAttribute("style");
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

		}

		public void clickToElementByJS(String locator) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].click();", element);
		}

		public void scrollToElement(String locator) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		}

		public void sendkeyToElementByJS(String locator, String value) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
		}

		public void removeAttributeInDOM(String locator, String attributeRemove) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
		}
		
		public int randomNumber() {
			Random rand = new Random();
			int value = rand.nextInt(1000);
			return value;
			}


}
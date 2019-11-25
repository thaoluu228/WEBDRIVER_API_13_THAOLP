package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_TextArea {
	//khai bao 1 bien driver dai dien cho selenium webdriver
	WebDriver driver;
	String customerID;
	String username = "mngr234905";
	String password = "agUruqe";
	
	//Input in new Customer/Output data
	String customerName = "Thao Luu";
	String gender = "female";
	String DOB = "1996-10-03";
	String address = "123Cau Giay";
	String city = "Hanoi";
	String state = "Dich Vong";
	String pin = "123456";
	String mobile = "1256478";
	String email = "thaoluu" + randomNumber() + "@yopmail.com";
	
	//Input in EditData
	String addressEdit = "12345Cau Giay";
	String cityEdit = "Hatay";
	String stateEdit = "Chuong My";
	String pinEdit = "444777";
	String mobileEdit = "14788493";
	String emailEdit = "thaoluu" + randomNumber() + "@yopmail.com";
	
	//Locator for New/Edit Customer
	By nameTextbox = By.name("name");
	By genderRadio = By.xpath("//input[@value='f']");
	By genderTexbox = By.name("gender");
	By DobTextbox = By.name("dob");
	By addressTextarea = By.name("addr");
	By cityTextbox = By.name("city");
	By stateTextbox = By.name("state");
	By pinTextbox = By.name("pinno");
	By phoneTextbox = By.name("telephoneno");
	By emailTextbox = By.name("emailid");
	By passTextbox = By.name("password");
			
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		String homepageWelcomeMsg = driver.findElement(By.tagName("marquee")).getText();
		Assert.assertEquals(homepageWelcomeMsg, "Welcome To Manager's Page of Guru99 Bank");
		String welcomeMsgId = driver.findElement(By.xpath("//td[starts-with(text(), 'Manger Id')]")).getText();
		Assert.assertTrue(welcomeMsgId.contains(username));
		
	}

	@Test
	public void TC_01_NewCustomer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		//Input data to New Customer
		driver.findElement(nameTextbox).sendKeys(customerName);
		driver.findElement(genderRadio).click();
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
		
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText();
		System.out.println("New CustomerID for New user=" + customerID);
	}

	@Test
	public void TC_02_EditCustomer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		//Verify field disable
		
		Assert.assertFalse(driver.findElement(nameTextbox).isEnabled());
		Assert.assertFalse(driver.findElement(genderTexbox).isEnabled());
		Assert.assertFalse(driver.findElement(DobTextbox).isEnabled());
		
		//Verify Output Edit Cutomer = Input New Customer
		
		Assert.assertEquals(customerName, driver.findElement(nameTextbox).getAttribute("value"));
		Assert.assertEquals(gender, driver.findElement(genderTexbox).getAttribute("value"));
		Assert.assertEquals(DOB, driver.findElement(DobTextbox).getAttribute("value"));
		Assert.assertEquals(address, driver.findElement(addressTextarea).getAttribute("value"));
		Assert.assertEquals(city, driver.findElement(cityTextbox).getAttribute("value"));
		Assert.assertEquals(state, driver.findElement(stateTextbox).getAttribute("value"));
		Assert.assertEquals(pin, driver.findElement(pinTextbox).getAttribute("value"));
		Assert.assertEquals(mobile, driver.findElement(phoneTextbox).getAttribute("value"));
		Assert.assertEquals(email, driver.findElement(emailTextbox).getAttribute("value"));
		
		//Edit data at Edit Customer Form
		driver.findElement(addressTextarea).clear();
		driver.findElement(addressTextarea).sendKeys(addressEdit);
		driver.findElement(cityTextbox).clear();
		driver.findElement(cityTextbox).sendKeys(cityEdit);
		driver.findElement(stateTextbox).clear();
		driver.findElement(stateTextbox).sendKeys(stateEdit);
		driver.findElement(pinTextbox).clear();
		driver.findElement(pinTextbox).sendKeys(pinEdit);
		driver.findElement(phoneTextbox).clear();
		driver.findElement(phoneTextbox).sendKeys(mobileEdit);
		driver.findElement(emailTextbox).clear();
		driver.findElement(emailTextbox).sendKeys(emailEdit);
		driver.findElement(By.name("sub")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer details updated Successfully!!!']")).isDisplayed());
		
		//Verify Input Edit Data = Output Edit Data
		Assert.assertEquals(customerID, driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText());
		Assert.assertEquals(customerName, driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText());
		Assert.assertEquals(gender, driver.findElement(By.xpath("//td[text()='Gender']//following-sibling::td")).getText());
		Assert.assertEquals(DOB, driver.findElement(By.xpath("//td[text()='Birthdate']//following-sibling::td")).getText());
		Assert.assertEquals(addressEdit, driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText());
		Assert.assertEquals(cityEdit, driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText());
		Assert.assertEquals(stateEdit, driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")).getText());
		Assert.assertEquals(pinEdit, driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")).getText());
		Assert.assertEquals(mobileEdit, driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText());
		Assert.assertEquals(emailEdit, driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")).getText());
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int randomNumber() {
		Random rand = new Random();
		int value = rand.nextInt(1000);
		return value;
		}

}
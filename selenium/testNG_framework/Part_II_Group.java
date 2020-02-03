package testNG_framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Part_II_Group {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/libraries/geckodriver");
		driver = new FirefoxDriver();
	
	Assert.assertTrue(false);
	
	}
	 
  @Test(groups = "user")
  public void TC_01() {
	  System.out.println("Run test 01");
  }
  
  @Test(groups = "test")
  public void TC_02() {
	  System.out.println("Run test 02");
  }
  
  @Test(groups = "user")
  public void TC_03() {
	  System.out.println("Run test 03");
  }
  
  
  @Test(groups = "1")
  public void TC_04() {
	  System.out.println("Run test 04");
  }
  
  @Test(groups = "test")
  public void TC_05() {
	  System.out.println("Run test 05");
  }
  

}

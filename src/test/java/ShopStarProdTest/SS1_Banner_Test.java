package ShopStarProdTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.mail.EmailException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestData.TestData;
import junit.framework.Assert;

public class SS1_Banner_Test {
	public WebDriver driver;
	

		
	@DataProvider(name="TestData")
	public Object[][] TestData() throws Exception
	{
		return TestData.TestData();
	}
	
	
	@BeforeMethod
	public void BrowserOpen() throws InterruptedException {
		//System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//driver.get("http://www.google.com"); 
		Thread.sleep(5000);
		Reporter.log("Driver used is:" + driver);

		try {
			 // Some code
			} catch (Exception e) {
			 // Code for Handling the exception
			}
	}
	
	
	@AfterMethod
	public void AfterSuites(ITestResult result) throws EmailException, InterruptedException 
	{	
		driver.quit(); 	
	}

	@Test (priority=1, enabled=true,dataProvider="TestData") 
	public void SS1_BannerTest(String TestCase,String storeid, String url) throws Exception {
		
		// Create object of SimpleDateFormat class and decide the format
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		String date1= dateFormat.format(date);

		driver.get(url); 

		String getTitile= driver.getTitle();
		
		if (driver.findElement(By.xpath("//span[text()='This site uses cookies to provide you with a great user experience. You can learn more by reviewing our Privacy Policy ']")).isDisplayed()) {
			Reporter.log("Privacy cookie is Present");
  		}else {
  			Reporter.log("Privacy cookie is Absent");
  		}
		
		/*Click Contact Us*/
		WebElement Contact = driver.findElement(By.xpath("//p[@class='ft-copyright']//a[text()='Contact Us']|//*[@id='ft']//a[text()='Contact Us']|//span[@class='top-menu']//*[text()='Contact Us']")); 
		JavascriptExecutor contact = (JavascriptExecutor)driver;
		contact.executeScript("arguments[0].click();", Contact);
		
		if (driver.findElement(By.xpath("//span[text()='This site uses cookies to provide you with a great user experience. You can learn more by reviewing our Privacy Policy ']")).isDisplayed()) {
			Reporter.log("Privacy cookie is Present");
  		}else {
  			Reporter.log("Privacy cookie is Absent");
  		}
		
		/*Click Login*/
		WebElement Login = driver.findElement(By.xpath("//*[text()='Login']|//*[text()='LOGIN  |']")); 
		JavascriptExecutor login = (JavascriptExecutor)driver;
		login.executeScript("arguments[0].click();", Login);
		
		if (driver.findElement(By.xpath("//span[text()='This site uses cookies to provide you with a great user experience. You can learn more by reviewing our Privacy Policy ']")).isDisplayed()) {
			Reporter.log("Privacy cookie is Present");
  		}else {
  			Reporter.log("Privacy cookie is Absent");
  		}
		
		/*Click Here*/
		WebElement Here = driver.findElement(By.xpath("//a[@href='https://www.halo.com/privacy-policy.aspx']")); 
			JavascriptExecutor here = (JavascriptExecutor)driver;
		here.executeScript("arguments[0].click();", Here);
		
		//Switch to new tab
  		//driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
  		for(String winHandle : driver.getWindowHandles()){
  		    driver.switchTo().window(winHandle);
  		    //Reporter.log(driver.getTitle());
  			}
  		
  		String URL = driver.getCurrentUrl();
  		Assert.assertEquals(URL, "https://www.halo.com/privacy-policy.aspx" );

		Thread.sleep(5000);
		
		Reporter.log("Current date and time is " +date1);
		Reporter.log("Get Title is:  "+ getTitile);
		Reporter.log("Current URL is: " + driver.getCurrentUrl());
	}
		
	
	}

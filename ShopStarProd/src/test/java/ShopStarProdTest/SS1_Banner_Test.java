package ShopStarProdTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.mail.EmailException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ProdData.ProdData;
import TestData.TestData;
import junit.framework.Assert;

public class SS1_Banner_Test {
	public WebDriver driver;
	

		
	@DataProvider(name="TestData")
	public Object[][] TestData() throws Exception
	{
		return TestData.TestData();
	}
	
	@DataProvider(name="ProdData")
	public Object[][] ProdData() throws Exception
	{
		return ProdData.ProdData();
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

		/*if(result.getStatus()==ITestResult.FAILURE)*/
			
		{
			//ProdEmail.sendemail();
			//ProdEmailNew.newemail();
		}	
	}

	@Test (priority=1, enabled=true,dataProvider="TestData") 
	public void SS1_BannerTest(String TestCase, String url) throws Exception {
		
		// Create object of SimpleDateFormat class and decide the format
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		String date1= dateFormat.format(date);

		driver.get(url); 

		Long loadtime = (Long)((JavascriptExecutor)driver).executeScript(
				"return performance.timing.loadEventEnd - performance.timing.navigationStart;");
		
		String getTitile= driver.getTitle();
		
		Reporter.log("Current date and time is " +date1);
		Reporter.log("Get Title is:  "+ getTitile);
		Reporter.log("Load Time for is:  "  + loadtime);
	}
		
	
	}

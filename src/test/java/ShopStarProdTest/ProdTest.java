package ShopStarProdTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

import ProdData.ProdData;
import TestData.TestData;
import junit.framework.Assert;

public class ProdTest {
	public WebDriver driver ;
	

		
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

	/*Simple Load testing and browser testing*/
	@Test (priority=1, enabled=false,dataProvider="TestData") 
	public void Application_Loadtest(String TestCase, String url, String company, String actualtitle) throws Exception {
		
		// Create object of SimpleDateFormat class and decide the format
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		String date1= dateFormat.format(date);

		driver.get(url); 

		Long loadtime = (Long)((JavascriptExecutor)driver).executeScript(
				"return performance.timing.loadEventEnd - performance.timing.navigationStart;");
		
		String getTitile= driver.getTitle();
		String actualTitle= actualtitle;
		
		Reporter.log("Current date and time is " +date1);
		Reporter.log("Get Title is:  "+ getTitile);
		Reporter.log("Actual Title is:  " + actualTitle);
		Reporter.log("Load Time for  " + company + " is:  "  + loadtime);
		
		/*Pass fail condition for data validation */
		if(actualTitle.equals(getTitile)){
	        Reporter.log("Test output: Pass");
	    }
	        else {
	        	Reporter.log("Test output: Fail");
	        }
		/*Pass/Fail Condition for a Test Case */
		Assert.assertEquals(getTitile,actualTitle);
	}
		
	@Test(priority=1,enabled=false,dataProvider="ProdData")
	public void Test001(String TestCase, String url,String username, String password,String itemid, String adminurl, String ssusername, String sspassword, String storeid) throws InterruptedException{
		
	    driver.get(url); 		
		Long loadtime = (Long)((JavascriptExecutor)driver).executeScript(
			"return performance.timing.loadEventEnd - performance.timing.navigationStart;");
		Reporter.log("Load Time is: "+ loadtime);
		
		String title = driver.getTitle();				  
		Reporter.log("Title of the webpage is :" +title);
		
		/*Click All Item*/
		WebElement AllItem = driver.findElement(By.xpath("//a[@href='dbquerydnu/shop/all-items']")); 
			JavascriptExecutor allitem = (JavascriptExecutor)driver;
		allitem.executeScript("arguments[0].click();", AllItem);
			
			if (driver.findElement(By.xpath("//a[@href='dbquerydnu/shop/all-items']")).isDisplayed()) {
				Reporter.log("All Item page is Present");
	  		}else {
	  			Reporter.log("All Item page is Absent");
	  		}

		//Thread.sleep(1000);

		/*Click a product*/
		WebElement ItemID = driver.findElement(By.xpath(itemid)); ////a[@id='parent-title-25053']
			JavascriptExecutor itemId = (JavascriptExecutor)driver;
		itemId.executeScript("arguments[0].click();", ItemID);  /*EXT00014*/
			
			if (driver.findElement(By.xpath("//*[@class='preview-detail-panel']")).isDisplayed()) {
				Reporter.log("Product is Present");
	  		}else {
	  			Reporter.log("Product is Absent");
	  		}
		/*Verify the item in stock*/	
			String stock= driver.findElement(By.xpath("//*[@id='liveInv']")).getText();
			Reporter.log(stock);	
			
			
		/*Click Add to Cart*/
		WebElement AddtoCart = driver.findElement(By.xpath("//a[@id='js-item-addtocartbtn-25053']"));
			JavascriptExecutor addtocart = (JavascriptExecutor)driver;
		addtocart.executeScript("arguments[0].click();", AddtoCart);
			
		Thread.sleep(1000);
			
		/*Click Add to Cart*/
		WebElement Checkoutbtn = driver.findElement(By.xpath("//*[@id='checkoutbtn']"));
			JavascriptExecutor checkoutbtn = (JavascriptExecutor)driver;
			checkoutbtn.executeScript("arguments[0].click();", Checkoutbtn);
			
		/*Enter User name and Password */
		driver.findElement(By.xpath("//input[@id='login']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
			
		WebElement SignIn = driver.findElement(By.xpath("//input[@type='submit']"));
			JavascriptExecutor signin = (JavascriptExecutor)driver;
		signin.executeScript("arguments[0].click();", SignIn);
		
			if (driver.findElement(By.xpath("//a[text()='Nitesh!']")).isDisplayed()) {
				Reporter.log("Login Successful: Username is Present");
	  		}else {
	  			Reporter.log("Login Unsuccessful: Username is Absent");
	  		}
			
		//Click on Continue Button
		WebElement Continue = driver.findElement(By.xpath("//*[@value='Continue']"));
			JavascriptExecutor sigcontinuebuttonnin = (JavascriptExecutor)driver;
		sigcontinuebuttonnin.executeScript("arguments[0].click();", Continue);
			
		//Click a payment method
		WebElement PBCC = driver.findElement(By.xpath("//*[@id='radio_1']")); /*//*[@id='radio_1']*/
			JavascriptExecutor pbcc = (JavascriptExecutor)driver;
		pbcc.executeScript("arguments[0].click();", PBCC);
		
		/*Enter PO number 
  		driver.findElement(By.xpath("//*[@id='input_2']")).sendKeys("1234"); */
			
		//Click on Continue Button
		WebElement Continue1 = driver.findElement(By.xpath("//*[@value='Continue']"));
			JavascriptExecutor sigcontinuebuttonnin1 = (JavascriptExecutor)driver;
		sigcontinuebuttonnin1.executeScript("arguments[0].click();", Continue1);
			
		Thread.sleep(10000);
			
		/*Credit Card Information*/
		//Card Number
		driver.findElement(By.xpath("//*[@id='card']")).sendKeys("T123456789LF1234");
		
		//Expire date
		driver.findElement(By.xpath("//*[@id='month']")).sendKeys("JAN");
		driver.findElement(By.xpath("//*[@id='year']")).sendKeys("2025");
		
		//CVV
		driver.findElement(By.xpath("//*[@id='cvv']")).sendKeys("111");
		
		//Name on Card
		driver.findElement(By.xpath("//*[@id='name']")).sendKeys("Test Test");
		
		//Click on Continue Button
		WebElement CCContinue = driver.findElement(By.xpath("//*[@value='Continue']"));
		JavascriptExecutor cccontinue = (JavascriptExecutor)driver;
		cccontinue.executeScript("arguments[0].click();", CCContinue);

		Thread.sleep(10000);
		
		//Click on Place Order Button
		WebElement Placeorder = driver.findElement(By.xpath("//*[@id='placeord']"));
			JavascriptExecutor placeorder = (JavascriptExecutor)driver;
		placeorder.executeScript("arguments[0].click();", Placeorder);
		
		Thread.sleep(5000);
		
		String order1= driver.findElement(By.xpath("//*[@id='checkout']/div[2]/div/div[3]/h3/strong")).getText();
		Reporter.log("Order Confirmation Number is: " +order1);		
			
		/* Sign Out*/
		WebElement SignOut = driver.findElement(By.xpath("//*[@class='fa fa-sign-out ada_screen_icon']"));
			JavascriptExecutor signout = (JavascriptExecutor)driver;
		signout.executeScript("arguments[0].click();", SignOut);
		
		
		Thread.sleep(5000);
		
		//Verifying the order is created in the ShopStar Admin side too 
		driver.get(adminurl); 
		Thread.sleep(5000);
		
		//Enter User name and Password 
		driver.findElement(By.xpath("//input[@name='j_username']")).sendKeys(ssusername);
		driver.findElement(By.xpath("//input[@name='j_password']")).sendKeys(sspassword);
		
		// Sign Out
		WebElement LogIn = driver.findElement(By.xpath("//input[@class='btn btn-info btn-block']"));
			JavascriptExecutor login = (JavascriptExecutor)driver;
		login.executeScript("arguments[0].click();", LogIn);
		
		//Search Store 
	  	driver.findElement(By.xpath("//*[@id='quick_search_box']")).sendKeys(storeid);
	  
	  	//Click Search button
	  	WebElement Search = driver.findElement(By.xpath("//*[@name='shopstar_search_form']//*[@type='submit']"));
  			JavascriptExecutor search = (JavascriptExecutor)driver;
  		search.executeScript("arguments[0].click();", Search);
	  	
  		//Click Store ID
	  	WebElement FirstRow = driver.findElement(By.xpath("(//*[@id='rows']//a[@target='_blank'])[1]"));
  			JavascriptExecutor firstrow = (JavascriptExecutor)driver;
  		firstrow.executeScript("arguments[0].click();", FirstRow);
  		
  		//Switch to new tab
  		//driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
  		for(String winHandle : driver.getWindowHandles()){
  		    driver.switchTo().window(winHandle);
  		    Reporter.log(driver.getTitle());
  			}
  	
  		Thread.sleep(2000);
  		Set<String> allWindows = driver.getWindowHandles();
  			int windowSize = allWindows.size();
  		Reporter.log("Window Number is:"+windowSize);
  		
  		//Click Store ID
	  	WebElement ManageOrders = driver.findElement(By.xpath("//*[@class='vendor-data']//a[text()='Manage Orders']"));
  			JavascriptExecutor manageorder = (JavascriptExecutor)driver;
  		manageorder.executeScript("arguments[0].click();", ManageOrders);
  		
  		String order2= driver.findElement(By.xpath("(//a[@name='orderid'])[1]")).getText();
  		Reporter.log("Order Confirmation Number is: " +order2);
		
	        String expected = order1;
	        String actual = order2;
	        Reporter.log("Expected order number is:  " +expected);
	        Reporter.log("Actual order number is:  " +actual);

	    if(expected.equals(actual)){
	    	Reporter.log("Pass: Order is Matched");
	    }
	        else {
	        	Reporter.log("Fail: Order is not Matched");
	        }
	    
		
	}
		
	
	}

package Action_Container;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import Operations.CommonLibrary;

public class WebStore_Common 
{
	SoftAssert softAssert=new SoftAssert();
	 CommonLibrary commonLib=new CommonLibrary();
	 WebDriver driver;
	 
	 public WebStore_Common(WebDriver driver)
		{
			this.driver = driver;
			 System.setProperty("org.uncommons.reportng.escape-output", "false");
		}

	 public void Webstore_Verifysignout()
	 {		
		 driver.findElement(By.linkText("Hello, Test_Admin")).click();
		 if(!driver.findElements(By.linkText("Hello, Test_Admin")).isEmpty())
		 	{
		      driver.findElement(By.linkText("Sign Out")).click();
		      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    }
		 else
		 	{
		        driver.findElement(By.id("ctl00_cpContent_txtUserName")).sendKeys(Keys.F5);
		        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    }
	 }
	 
	 public void WebStore_SignIn(String Username,String Pwd)
	 {
		 driver.findElement(By.id("ctl00_cpContent_txtUserName")).sendKeys(Username);
		 driver.findElement(By.id("ctl00_cpContent_txtPassword")).sendKeys(Pwd);
		 driver.findElement(By.id("ctl00_cpContent_btnSignIn")).click();
		 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		 
		 
	 }
	 public void Webstore_CLearCart()
	 {
		 driver.navigate().refresh();
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 driver.findElement(By.xpath("/html/body/form/div[4]/div[1]/div[1]/div/div[2]/div[1]/div/div[2]/ul/li[2]/a/span[1]")).click();
		 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 java.util.List<WebElement> items = driver.findElements(By.xpath("//*[contains(@class,'fa fa-times-circle-o fa-lg text-danger')]"));
		 int isize=items.size();
		 if (isize>0)
		 {
			 for(int i=0; i < isize ; i++ )
			 {
				 items.get(i).click();
				 driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				 if (i==isize)
				 {
					 Reporter.log("<br><B>Passed, List of products in the shopping cart are removed and the count is"+isize, true); 
					 break;
				 }
					 
			 }
		 }
		else
		{
		 Reporter.log("<br><B>Passed,  there is no list of products in the shopping cart", true);
		 }
	 }
		 
	 public String Get_OrderNum()
	 {
		 driver.navigate().refresh();
		 driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		 CommonLibrary CL= new CommonLibrary();
		 CL.takeScreenshot(driver);		 
		 driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		 String ordernum=driver.findElement(By.xpath("//*[contains(@id,'OrderSummary_efvBillingInformation_lblOrderNumber')]")).getText();
		 return ordernum;
	 }
}




package Action_Container;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import Operations.CommonLibrary;
import Operations.CommonWeb;
import Operations.Operations;

public class Admin_Orders
{
	SoftAssert softAssert=new SoftAssert();
	 CommonLibrary commonLib=new CommonLibrary();
	 WebDriver driver;
	 
	 public Admin_Orders(WebDriver driver)
		{
			this.driver = driver;
			 System.setProperty("org.uncommons.reportng.escape-output", "false");
		}
	 public String Order_Search(String OrderNum)
		{
			driver.findElement(By.linkText("All Orders")).click();
			driver.findElement(By.id("ctl00_ctl00_cpContent_cpCriteria_ucOrderListCriteria_efvCriteria_txtOrderNumber")).sendKeys(OrderNum);
			driver.findElement(By.id("ctl00_ctl00_cpContent_cpButtons_btnSearch")).click();
			driver.findElement(By.linkText(OrderNum)).click();
			return OrderNum;
		}
		public  void Return_Order() 
		{
			CommonWeb CW=new CommonWeb();
			Operations common=new Operations(driver);
			driver.findElement(By.linkText("Items")).click();
			driver.findElement(By.id("SelectControl")).click();
//			common.SetCheckBox("orderdetails", "order", "id","on");
			common.Click("Admin_Orders", "return", "id");
			Set<String> allWindowHandles = driver.getWindowHandles();
			for(String handle : allWindowHandles)
			{
				driver.switchTo().window(handle); //Switch to the desired window first and then execute commands using driver
			}
			driver.findElement(By.id("ctl00_ctl00_cpContent_cpDetails_efvDetails_txtReturnReason")).sendKeys("Test");
			driver.findElement(By.xpath("/html/body/form/div[8]/span/input[1]")).click();
			Set<String> allWindowHandlesafter = driver.getWindowHandles();
			for(String handle : allWindowHandlesafter)
			{
				
				driver.switchTo().window(handle); //Switch to the desired window first and then execute commands using driver
			}
			
			CW.webPage_VerifyText(driver, "The selected order items have been successfully returned.", true);		
		}
}

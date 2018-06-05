package Action_Container;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import Operations.CommonLibrary;
import Operations.Operations;



public class Admin_Common 
{
	 SoftAssert softAssert=new SoftAssert();
	 CommonLibrary commonLib=new CommonLibrary();
	 WebDriver driver;
	 
	 public Admin_Common(WebDriver driver)
		{
			this.driver = driver;
			 System.setProperty("org.uncommons.reportng.escape-output", "false");
		}
	 
	public void Admin_Login(String accountnumber,String username, String password)
	{
		Operations AL=new Operations(driver);
		AL.SetText("Admin_Common", "AccountNumber", "ID",accountnumber);
		AL.SetText("Admin_Common", "UserName", "ID",username);
		AL.SetText("Admin_Common", "Password", "ID",password);
		AL.Click("Admin_Common", "SignIn", "ID");		
	}
	public String AllOrg_SelectOrg(String Organization) throws Exception
	{
		driver.findElement(By.linkText("All Organizations")).click();
		driver.findElement(By.id("ctl00_ctl00_cpContent_cpCriteria_ucOrganizationListCriteria_efvOrganizationListCriteriaDetails_txtOrganizationName")).sendKeys(Organization);
		driver.findElement(By.id("ctl00_ctl00_cpContent_cpButtons_btnSearch")).click();
		driver.findElement(By.linkText(Organization)).click();
		Thread.sleep(10000);
		return Organization;
	}
	public String Order_Search(String OrderNum)
	{
		driver.findElement(By.linkText("All Orders")).click();
		driver.findElement(By.id("ctl00_ctl00_cpContent_cpCriteria_ucOrderListCriteria_efvCriteria_txtOrderNumber")).sendKeys(OrderNum);
		driver.findElement(By.id("ctl00_ctl00_cpContent_cpButtons_btnSearch")).click();
		driver.findElement(By.linkText(OrderNum)).click();
		return OrderNum;
		
	}

}


package Action_Container;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import Operations.CommonLibrary;
public class Admin_Webstore 
{
	
	SoftAssert softAssert=new SoftAssert();
	 CommonLibrary commonLib=new CommonLibrary();
	 WebDriver driver;
	 
	 public Admin_Webstore(WebDriver driver)
		{
			this.driver = driver;
			 System.setProperty("org.uncommons.reportng.escape-output", "false");
		}

	 public  String Get_PhyUrl() 
	 {
		 driver.findElement(By.linkText("WebStore")).click();
		 String PHYURL=driver.findElement(By.id("ctl00_ctl00_cpContent_cpDetails_efvWebStoreDetails_txtPhysicalUrl")).getAttribute("value");
		 return PHYURL;
	 }
	
}

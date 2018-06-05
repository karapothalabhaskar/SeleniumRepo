package testcases;


import java.util.concurrent.TimeUnit;

import Action_Container.Admin_Common;
import Action_Container.Admin_Webstore;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Operations.CommonWeb;
import Operations.CommonLibrary;
import Operations.Operations;
import Action_Container.WebStore_Common;
import Action_Container.Admin_Orders;


public class placing_Order_for_one_free_Product 
{
	@Test
	public  void DemoTest() throws Exception 
	{			
		
		Package packageName=this.getClass().getPackage();
		String Testsetname = packageName.getName();
		String testcasename=this.getClass().getSimpleName();
		String extentReportFile = System.getProperty("user.dir")+ "\\"+testcasename+".html";
		String extentReportImage = System.getProperty("user.dir")+ "\\extentReportImage.png";
		// Create object of extent report and specify the report file path.
		ExtentReports extent = new ExtentReports(extentReportFile);		 
		// Start the test using the ExtentTest class object.
		ExtentTest extentTest = extent.startTest(Testsetname,testcasename);
 
		CommonLibrary CL= new CommonLibrary(); //createing object for common library
		CommonWeb CW=new CommonWeb();// creating object for common web
		System.out.println("**********************STARTING of the case***********************");
		WebDriver driver=CL.createBrowser("chrome"); // opening browser 
		extentTest.log(LogStatus.PASS, "Browser Launched");
		String url=CL.getExcel_CellValue("D:/selenium Projects/java projects/Current_Release/Resources/Environments.xls", "Environments", 1, 1);
		CL.Navigate(driver,url); //navigating to particular url
		extentTest.log(LogStatus.PASS, "Navigated to Admin URL which we got from Excel sheet");
		Admin_Common AC=new Admin_Common(driver); // creating object for admin common
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// wait time 
		AC.Admin_Login("100001000", "Test_Admin", "Te$t1ng"); // admin login
		extentTest.log(LogStatus.PASS, "Admin Login is Done");
		CW.webPage_VerifyText(driver, "Kivuto",true);//
		AC.AllOrg_SelectOrg("DevQC Orders - Demo");
		extentTest.log(LogStatus.PASS, "DevQC Orders - Demo is selected");
		Admin_Webstore AW=new Admin_Webstore(driver);
		String phyurl=AW.Get_PhyUrl();
		extentTest.log(LogStatus.PASS, "Got phyurl from Admin webstore page.");
		driver.get(phyurl);
		extentTest.log(LogStatus.PASS, "Navigated to phyurl.");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebStore_Common WC=new WebStore_Common(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WC.Webstore_Verifysignout();
		extentTest.log(LogStatus.PASS, "if user logged in the user is logged out successfully");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WC.WebStore_SignIn("Shopper", "asdfasdf");
		extentTest.log(LogStatus.PASS, "Signed in wiht shopper");
		Thread.sleep(30000);
		CW.webPage_VerifyText(driver, "SHOPPER", true);
		WC.Webstore_CLearCart();
		extentTest.log(LogStatus.PASS, "clearing cart if any products are found");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Operations common=new Operations(driver);
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		common.Click("Webstore_Common", "Home", "ID");
		extentTest.log(LogStatus.PASS, "clicked on Home link");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		common.Click("Webstore_Common", "All", "ID");
		extentTest.log(LogStatus.PASS, "clicked on ALL link");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		common.Click("Webstore_Common", "product", "ID");
		extentTest.log(LogStatus.PASS, "clicked on the free product link");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		common.Click("Webstore_Common", "addtocart", "ID");
		extentTest.log(LogStatus.PASS, "clicked on Add to cart link");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		common.Click("Webstore_Common", "viewfullcart", "xpath");
		extentTest.log(LogStatus.PASS, "clicked on view full cart link");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		common.Click("Webstore_Common", "checkout", "ID");
		extentTest.log(LogStatus.PASS, "clicked on checkout link");
		Thread.sleep(30000);
		extentTest.log(LogStatus.PASS,"placing order page : "+ extentTest.addScreenCapture(extentReportImage));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String on=WC.Get_OrderNum();
		extentTest.log(LogStatus.PASS, "Got Order number from order details page in webstore");
		CL.SetExcel_CellValue("D:/selenium Projects/java projects/Current_Release/Resources/Orders.xls", on);
		extentTest.log(LogStatus.PASS, "stored that order number in excel file");
		//System.out.println(on);
		driver.get(url);
		extentTest.log(LogStatus.PASS, "Navigated to Admin url to return order");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		AC.Admin_Login("100001000", "Test_Admin", "Te$t1ng");
		extentTest.log(LogStatus.PASS, "logged in admin");
		Admin_Orders AO=new Admin_Orders(driver);
		AO.Order_Search(on);
		extentTest.log(LogStatus.PASS, "seraching and clicking on the order number");
		AO.Return_Order();
		extentTest.log(LogStatus.PASS, "return is done for the order placed.");
		driver.quit();
		extentTest.log(LogStatus.PASS, "Browser closed");
		// 	close report.
		extent.endTest(extentTest);
		// writing everything to document.
		extent.flush();
		System.out.println("**********************END of the case***********************");
		System.exit(0);
		
	}

}
 
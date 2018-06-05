
/////sync test repository 
package Operations;

import java.io.File;
import java.util.Iterator;
import java.util.List;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;



public class CommonWeb 
{
	 SoftAssert softassert=new SoftAssert();
	 CommonLibrary commonLibrary=new CommonLibrary();
	public CommonWeb() {
		// TODO Auto-generated constructor stub
		 System.setProperty("org.uncommons.reportng.escape-output", "false");			 
	}
	
	public void reportErrors()
	{
		softassert.assertAll();
	}
	
	
	public void getRow_With_CellText(WebDriver browser,WebElement table, String expectedCellText)
	{
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		Iterator<WebElement> rowCounter=rows.iterator();
		while (rowCounter.hasNext()) {
			WebElement row =  rowCounter.next();
			List<WebElement> columns = row.findElements(By.tagName("td"));	
			  Iterator<WebElement> columnsCounter= columns.iterator();
			  while (columnsCounter.hasNext()) {
				WebElement column =  columnsCounter.next();
				String cellText=column.getText();
				
				if (cellText.toLowerCase()==expectedCellText.toLowerCase()) {
					
				}
			}
		}
	}
	
	
	
	
	
public void webPage_VerifyText(WebDriver driver, String expectedText , boolean isExpected)
{
	  String allText=driver.findElement(By.tagName("body")).getText().toLowerCase();
	 boolean stringPresent= allText.contains(expectedText.toLowerCase());
	if (isExpected==true) 
	{
		 if (stringPresent==true ) 
		 {
			 Reporter.log("<br><B>Passed, as  expected text -<font color=green>"+expectedText+"</font> found in the webpage.</B></br>", true);		 				 
		 }	
		 else 
		 {	 
			 softassert.assertTrue(false, "text not found");
			 commonLibrary.reportLogScreenshot(new File(commonLibrary.takeScreenshot(driver)),"<br>failed, unexpectedly text -<font color=red>"+expectedText+"</font> not found in the webpage </B></br>");	
		}
	} 
	else 
	{
		if (stringPresent==false ) 
		 {
			 Reporter.log("<br><B>Passed, as  expected text -<font color=green>"+expectedText+"</font> not found in the browser.</B></br>", true);
		 }	
		 else 
		 {	 
			 softassert.assertTrue(false, "text found");
			 commonLibrary.reportLogScreenshot(new File(commonLibrary.takeScreenshot(driver)),"<br>failed, unexpectedly text -<font color=red>"+expectedText+"</font> found in the webpage</B></br>");	
		}
	}				
}

}

package Operations;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.support.ui.Select;

import com.google.common.base.Throwables;


public class Operations 

{
	 SoftAssert softAssert=new SoftAssert();
	 CommonLibrary commonLib=new CommonLibrary();
	 WebDriver driver;
	 
public Operations(WebDriver driver)
	{
		this.driver = driver;
		 System.setProperty("org.uncommons.reportng.escape-output", "false");
	}
	
	
	ReadObject object = new ReadObject();
	//*********************************--------------------------------------------------******************************* Click
	//click the object if found else report it as Fail
	public void Click(String repositoryFileName,String objectName,String objectType)  
	{	
	try{
	
		driver.findElement(this.getObject(repositoryFileName,objectName,objectType)).click();
		Reporter.log("<br><B>Passed,  click done, having objectName-"+objectName+",with Type-"+objectType+"</B></br>", true);
	} 
	catch (Exception e)
	{
		String stackTrace = Throwables.getStackTraceAsString(e);
		 softAssert.assertTrue(false);
		 commonLib.reportLogScreenshot(new File(commonLib.takeScreenshot(driver)),"<p>click operation Failed, object Details. RepositoryName-"+repositoryFileName+", objectName-"+objectName+",with Type-"+objectType+"</p>");	
	///report the Exception details	
		Reporter.log("Error Details:- "+stackTrace.split("at sun.reflect")[0]);//trim the unwanted error details and report it
	}
		
	}

	
	//*********************************--------------------------------------------------******************************* SetText	
	//performs the SetTex operation for web edit or input fields
	public void SetText(String repositoryFileName,String objectName,String objectType,String value) 
	{
		try
		{
			driver.findElement(this.getObject(repositoryFileName,objectName,objectType)).sendKeys(value);
			Reporter.log("<br><B>Passed,  Set Text done, having objectName-"+objectName+",with Type-"+objectType+" , value-"+value+"</B></br>", true);
		}
		catch (Exception e)
		{		 
			String stackTrace = Throwables.getStackTraceAsString(e);
			 softAssert.assertTrue(false);
			// e.printStackTrace();
			commonLib.reportLogScreenshot(new File(commonLib.takeScreenshot(driver)),"<br>Failed, Set operation failed,object not found having repository-"+repositoryFileName+", objectName-"+objectName+",with Type-"+objectType+"</br>");	
			Reporter.log("Error Details:- "+stackTrace.split("at sun.reflect")[0]);
		}		
	}
	
	//*********************************--------------------------------------------------******************************* GetText
	public String GetText(String repositoryFileName,String objectName,String objectType)
	{
		try
		{
		//Get text of an element
			return	driver.findElement(this.getObject(repositoryFileName,objectName,objectType)).getText();
		}
			catch (Exception e)
			{		 
				String stackTrace = Throwables.getStackTraceAsString(e);
				 softAssert.assertTrue(false);
				 commonLib.reportLogScreenshot(new File(commonLib.takeScreenshot(driver)),"<p>GetText operation Failed, object Details. RepositoryName-"+repositoryFileName+", objectName-"+objectName+",with Type-"+objectType+"</p>");	
				 Reporter.log("Error Details:- "+stackTrace.split("at sun.reflect")[0]);
				 return	"";							 
			}	
	}
	
	
	//*********************************--------------------------------------------------******************************* GetURL
	public void GoToUrl(String repositoryFileName,String urlKeyName) throws IOException
	{
		Properties allObjects =  object.getObjectRepository(repositoryFileName);
		driver.get(allObjects.getProperty(urlKeyName));
	}
	
	public void Select(String repositoryFileName,String objectName,String objectType,String value)
	{
		try
		{
			Select dropDown=new Select(driver.findElement(By.id("designation")));
			dropDown.selectByVisibleText(value);	
		}
		catch (Exception e)
		{		 
			String stackTrace = Throwables.getStackTraceAsString(e);
			 softAssert.assertTrue(false);
			 commonLib.reportLogScreenshot(new File(commonLib.takeScreenshot(driver)),"<p>Select operation Failed, object Details. RepositoryName-"+repositoryFileName+", objectName-"+objectName+",with Type-"+objectType+"</p>");	
			 Reporter.log("Error Details:- "+stackTrace.split("at sun.reflect")[0]);	 						 
		}
	}
	
	
//*********************************--------------------------------------------------******************************* SetCheck box	
	//performs the setcheckbox operation for checkboxes
		public void SetCheckBox(String repositoryFileName,String objectName,String objectType, String Value) throws Exception 
		{
			 java.util.List<WebElement> oCheckBox = driver.findElements(this.getObject(repositoryFileName,objectName,objectType)); 
			 int iSize = oCheckBox.size(); 
			// Start the loop from first checkbox to last checkboxe 
			 for(int i=0; i < iSize ; i++ )
			 {
				// Store the checkbox name to the string variable, using 'Value' attribute 
				 String sValue = oCheckBox.get(i).getAttribute("value");
				 boolean sselection= oCheckBox.get(i).isSelected();
				 if (!sselection)
				 	{
					 	try
					 		{
					 			if (sValue.equalsIgnoreCase(Value))
					 				{
					 				 	oCheckBox.get(i).click();
					 				 	Reporter.log("<br><B>Passed,  Set CheckBox done, having objectName-"+objectName+",with Type-"+objectType+" , value-"+Value+"</B></br>", true);
					 				 	break;// This will take the execution out of for loop 
					 				} 			
					 			
					 		}
					 	catch (Exception e)
					 		{		 
					 			String stackTrace = Throwables.getStackTraceAsString(e);
					 			softAssert.assertTrue(false);
					 			// 	e.printStackTrace();
					 			commonLib.reportLogScreenshot(new File(commonLib.takeScreenshot(driver)),"<br>Failed, Set operation failed,object not found having repository-"+repositoryFileName+", objectName-"+objectName+",with Type-"+objectType+"</br>");	
					 			Reporter.log("Error Details:- "+stackTrace.split("at sun.reflect")[0]);
					 		}
				 	}
				 else
				 	{
					 	Reporter.log("<br><B>Passed,  setting CheckBox is already done, having objectName-"+objectName+",with Type-"+objectType+ " , value-"+Value+"</B></br>", true);
				 	}
			 }
		}
		
		//*********************************--------------------------------------------------******************************* SetRadioButton
				public void SetRadioButton(String repositoryFileName,String objectName,String objectType,String Value) throws Exception 
				{
					java.util.List<WebElement> oRadioButton = driver.findElements(this.getObject(repositoryFileName,objectName,objectType)); 
					 int iSize = oRadioButton.size(); 
					// Start the loop from first RadioButton to last RadioButton 
					 for(int i=0; i < iSize ; i++ )
					 {
						// Store the RadioButton name to the string variable, using 'Value' attribute 
						 String sValue = oRadioButton.get(i).getAttribute("value");
						 boolean sselection= oRadioButton.get(i).isSelected();
						 if (!sselection)
						 	{
							 	try
							 		{
							 			if (sValue.equalsIgnoreCase(Value))
							 				{
							 					oRadioButton.get(i).click();
							 				 	Reporter.log("<br><B>Passed,  Set RadioButton done, having objectName-"+objectName+",with Type-"+objectType+" , value-"+Value+"</B></br>", true);
							 				 	break;// This will take the execution out of for loop 
							 				}						 			
							 			
							 		}
							 	catch (Exception e)
							 		{		 
							 			String stackTrace = Throwables.getStackTraceAsString(e);
							 			softAssert.assertTrue(false);
							 			// 	e.printStackTrace();
							 			commonLib.reportLogScreenshot(new File(commonLib.takeScreenshot(driver)),"<br>Failed, Set operation failed,object not found having repository-"+repositoryFileName+", objectName-"+objectName+",with Type-"+objectType+"</br>");	
							 			Reporter.log("Error Details:- "+stackTrace.split("at sun.reflect")[0]);
							 		}
						 	}
						 else
						 	{
							 	Reporter.log("<br><B>Passed,  setting RadioButton is already done, having objectName-"+objectName+",with Type-"+objectType+ " , value-"+Value+"</B></br>", true);
						 	}
					 }
				}
				
				//*********************************--------------------------------------------------******************************* Select Dropdown list
public void SelectDropDown(String repositoryFileName,String objectName,String objectType,String Value) throws Exception
	{
			WebElement oDropDown = driver.findElement(this.getObject(repositoryFileName,objectName,objectType));
			List<WebElement> lists = oDropDown.findElements(By.tagName("option"));
				  for(WebElement element: lists)  
					  {
					        String Dropdownvalue = element.getText();
					        try
					 		{
					 			if (Dropdownvalue.equalsIgnoreCase(Value))
					 				{
					 					System.out.println("Value are eqaul");
					 					WebElement DropdownEle = driver.findElement(By.xpath(objectName));
					 					DropdownEle.click();
					 					Select dropdown= new Select(DropdownEle);
					 					dropdown.selectByVisibleText(Value);
					 				 	Reporter.log("<br><B>Passed,  Select DropDown done, having objectName-"+objectName+",with Type-"+objectType+" , value-"+Value+"</B></br>", true);
					 				 	break;// This will take the execution out of for loop 
					 				}	
					 			
					 		}
					 	catch (Exception e)
					 		{		 
					 			String stackTrace = Throwables.getStackTraceAsString(e);
					 			softAssert.assertTrue(false);
					 			commonLib.reportLogScreenshot(new File(commonLib.takeScreenshot(driver)),"<br>Failed, Select operation failed,object not found having repository-"+repositoryFileName+", objectName-"+objectName+",with Type-"+objectType+"</br>");	
					 			Reporter.log("Error Details:- "+stackTrace.split("at sun.reflect")[0]);
					 		}
					        
					        
					    }
				}
				
				
	public void reportErrors()
	{
		softAssert.assertAll();	
	}
	
	/**
	 * Find element BY using object type and value
	 * @param p
	 * @param objectName
	 * @param objectType
	 * @return
	 * @throws Exception
	 */
	private By getObject(String repositoryFileName,String objectName,String objectType) throws Exception
	{
		//Find by xpath
		Properties allObjects =  object.getObjectRepository(repositoryFileName);
		if(objectType.equalsIgnoreCase("XPATH"))
		{
			
			return By.xpath(allObjects.getProperty(objectName));
		}
		//find by class
		else if(objectType.equalsIgnoreCase("CLASSNAME")){
			
			return By.className(allObjects.getProperty(objectName));
			
		}
		//find by name
		else if(objectType.equalsIgnoreCase("NAME")){
			
			return By.name(allObjects.getProperty(objectName));
			
		}
		else if(objectType.equalsIgnoreCase("ID")){
			
			return By.id(allObjects.getProperty(objectName));
			
		}
		//Find by css
		else if(objectType.equalsIgnoreCase("CSS")){
			
			return By.cssSelector(allObjects.getProperty(objectName));
			
		}
		//find by link
		else if(objectType.equalsIgnoreCase("LINK")){
			
			return By.linkText(allObjects.getProperty(objectName));
			
		}
		//find by partial link
		else if(objectType.equalsIgnoreCase("PARTIALLINK")){
			
			return By.partialLinkText(allObjects.getProperty(objectName));
			
		}else
		{
			throw new Exception("Wrong object type");
		}
	}
	
}

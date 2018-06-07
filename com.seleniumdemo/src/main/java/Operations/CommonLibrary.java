package Operations;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;

import com.opera.core.systems.OperaDriver;

public class CommonLibrary {
	


	public  WebDriver createBrowser(String Browser_Type) 
	{
		WebDriver driver = null;
		switch (Browser_Type.toLowerCase()) 
		{
        case "explorer":
        		System.setProperty("webdriver.ie.driver","C:\\Program Files\\Internet Explorer\\iexplore.exe");
        		 driver=new InternetExplorerDriver();
            	break;
        case "chrome":
        		System.setProperty("webdriver.chrome.driver","C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"); 
        		driver=new ChromeDriver();
            	break;
        case "firefox":
        	 	driver=new FirefoxDriver();
        		break;
        case "opera":
        		driver=new OperaDriver();
        		break;
        case "safari":
        		driver=new SafariDriver();
        		break;
   
		}
		
		
		driver.manage().window().maximize();		
		return driver;
	}
	public void Navigate(WebDriver driver,String Url)
	{
		driver.get(Url);
		
	}
	
	@SuppressWarnings("resource")
	public String getExcel_CellValue(String filePathWithName,String sheetName, int rowNumber,int columnNumber) throws IOException{
		//Create a object of File class to open xlsx file
		File file =	new File(filePathWithName);
		//Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);
		Workbook readWorkbook = null;	
		int length=filePathWithName.length();
		String extension=filePathWithName.substring(length-4, length);//getting only extension name
		//String fileExtensionName = fileName.substring(fileName.indexOf("."));
		//Check condition if the file is xlsx file
		if(extension.endsWith("xlsx")){
		//If it is xlsx file then create object of XSSFWorkbook class
		readWorkbook = new XSSFWorkbook(inputStream);	 
		}
		//Check condition if the file is xls file
		else if(extension.endsWith(".xls")){
			//If it is xls file then create object of HSSFWorkbook class
			readWorkbook = new HSSFWorkbook(inputStream);	
		}
		//Read sheet inside the workbook by its name
		Sheet excelSheet =  readWorkbook.getSheet(sheetName);
		Row row = excelSheet.getRow(rowNumber);
		Cell cell=row.getCell(columnNumber);
		 return cell.getStringCellValue();
		}
	
	
	public String SetExcel_CellValue(String filePathWithName,String data) throws IOException, Exception  
	{
				File file= new File(filePathWithName);
				if (file.exists())
				{
					file.delete();
					Thread.sleep(10000);
					try {
				        FileOutputStream fos = new FileOutputStream(filePathWithName);
				        @SuppressWarnings("resource")
						XSSFWorkbook  workbook = new XSSFWorkbook(); 
				        XSSFSheet sheet = workbook.createSheet("Orders"); 
				        Row row = sheet.createRow(0);   
				        Cell cell0 = row.createCell(0);
				        cell0.setCellValue("Order Number");
				        Cell cell1 = row.createCell(1);
				        cell1.setCellValue(data);  
				        workbook.write(fos);
				        fos.flush();
				        fos.close();
				    } catch (FileNotFoundException e) {
				        // TODO Auto-generated catch block
				        e.printStackTrace();
				    }
					
				}
				else 
				{

					try {
				        FileOutputStream fos = new FileOutputStream(filePathWithName);
				        @SuppressWarnings("resource")
						XSSFWorkbook  workbook = new XSSFWorkbook(); 
				        XSSFSheet sheet = workbook.createSheet("Orders"); 
				        Row row = sheet.createRow(0);   
				        Cell cell0 = row.createCell(0);
				        cell0.setCellValue("Order Number");
				        Cell cell1 = row.createCell(1);
				        cell1.setCellValue(data);  
				        workbook.write(fos);
				        fos.flush();
				        fos.close();
				    } catch (FileNotFoundException e) {
				        // TODO Auto-generated catch block
				        e.printStackTrace();
				    }
				}				
				return data;			
	}

	

	//creates unique number
	public  String uniqueNumber()
	{
		  String tempUniqueNumber,currentDate;
		  Date TodayDate=new Date();
		  DateFormat getDate=new SimpleDateFormat("ddmmyyyy");
		  currentDate=getDate.format(new Date());
		  tempUniqueNumber=currentDate+TodayDate.getTime();
		  return tempUniqueNumber;
  }
	
/////
	 public String takeScreenshot(WebDriver driver)
	 {
		  String workingDirectory = System.getProperty("user.dir");
		  String fileName = workingDirectory + File.separator + "screenshots" + File.separator  + "screenshot"+uniqueNumber()+".png";//filename
		  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		  try {
			  FileUtils.copyFile(scrFile, new File(fileName ));
			  } catch (IOException e) {
			/////  // TODO Auto-generated catch block
			  e.printStackTrace();
			  }
		  return fileName;
	 }
	 
	 
	 public File takeObjectScreenShot(WebDriver driver,WebElement element)
	 	{
		 String workingDirectory = System.getProperty("user.dir");
		 System.setProperty("org.uncommons.reportng.escape-output", "false");
		  String fileName = workingDirectory + File.separator + "screenshots" + File.separator  + "screenshot"+uniqueNumber()+".png";//filename
		  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		  try {
			  BufferedImage fullImg=ImageIO.read(scrFile);
			  org.openqa.selenium.Point point=element.getLocation();
			//Get width and height of the element
			  int eleWidth = element.getSize().getWidth();
			  int eleHeight = element.getSize().getHeight();
			//Crop the entire page screenshot to get only element screenshot
			  BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(), eleWidth,
			      eleHeight);
			  ImageIO.write(eleScreenshot, "png", scrFile);
			  FileUtils.copyFile(scrFile, new File(fileName ));
			  } catch (IOException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
			  }
		  return scrFile;
	 	}
	 
	public void  genarateRandomNumber()
	{
		Random tempRan=new Random();
		
		System.out.println(tempRan.nextInt());
	}
	
	protected void reportLogScreenshot(File file, String reportMessage) 
	{
	      System.setProperty("org.uncommons.reportng.escape-output", "false");  
//	      String absolute = file.getAbsolutePath();
//	      int beginIndex = absolute.indexOf(".");
//	      String relative = absolute.substring(beginIndex).replace(".\\","");
//	      String screenShot = relative.replace('\\','/');
	      Reporter.log("<a href=\"" + file  + "\">  <u>"+reportMessage+"</u> "+ "--- "+ new Date()+ "</a>" );  
	     // Reporter.log("<a href=\"" + file + "\">"+reportMessage+"<p align=\"left\"> " + "---- "+ new Date()+ "</p>");
//	      Reporter.log("<p><img width=\"512\" src=\"" + file.getAbsoluteFile()  + "\" alt=\"screenshot at " + new Date()+ "\"/></p></a><br />"); 
	}
	
	
}

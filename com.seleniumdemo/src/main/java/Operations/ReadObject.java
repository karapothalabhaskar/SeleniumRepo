//package Operations;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//
//public class ReadObject 
//{
//	Properties allProperties = new Properties();
//	public Properties getObjectRepository(String fileName) throws IOException
//	{		 
//			String path="D:\\selenium Projects\\java projects\\Current_Release\\Object Properties";
//			File folder = new File(path);
//			  File[] files=folder.listFiles();
//			        if (folder.exists()) 
//			        {
//			            for(File eachFile: files)
//			            {
//			            	String subpath=path+"\\"+eachFile.getName();
//			            	File subfolder = new File(subpath);
//			      		  	File[] subfiles=subfolder.listFiles();
//		      		  for(File eachsubFile: subfiles)
//			      		  {
//			      			String[] Efilename = eachsubFile.getName().split(".");
//			      			  if (fileName==Efilename[0])
//			      			  {			      				  
////			      				//Read object repository file
////			      				InputStream stream = new FileInputStream(subpath+fileName+".properties");
////			      				//load all objects		
////			      				allProperties.load(stream);	
////			      				break;
////			      			  }
////			      			  
////			      		  }
////			            	
////			            }
////			        } 
////			        else 
////			        {
////			            System.out.println("Directory is not exits");
////			        }
////					return allProperties;
//		
//		//Read object repository file
//				InputStream stream = new FileInputStream("D:\\selenium Projects\\java projects\\Demo_Project\\Object Properties\\Admin_Login\\"+fileName+".properties");
//				//load all objects		
//				allProperties.load(stream);
//				 return allProperties;
//	}
//	
//}
package Operations;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadObject 
{
	Properties allProperties = new Properties();
	public Properties getObjectRepository(String fileName) throws IOException
	{		 
		//Set the path of folder for which you wish to list name of all the files stored
			File file = new File("D:\\Git\\SeleniumRepo\\com.seleniumdemo\\Object Properties");// Object files contains all the files under the selected folder
			
			File[] files = file.listFiles();//for each file in the folder
			 outerLoop://Label
			for(File f: files)
			{			
			File[] subfiles = f.listFiles();
			for (File sf: subfiles)
				{					
					if (sf.getName().contains(fileName))
					{
						InputStream stream = new FileInputStream("D:\\Git\\SeleniumRepo\\com.seleniumdemo\\Object Properties\\"+f.getName()+"\\"+sf.getName());
						allProperties.load(stream);
						break  outerLoop;					
					}
										
				}
			if(f.equals(f.length()))
			{
				System.out.println("Properties file with the name "+fileName+" is not found in the object properties folder");
				break  outerLoop;
			}
					
		}
 		return allProperties;
	}
	
}

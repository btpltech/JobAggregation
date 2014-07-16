package shine.com;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import org.openqa.selenium.WebElement;

import com.thoughtworks.selenium.SeleniumException;


public class GetJobs {
	
	// static variables
//	static ArrayList<String> listOfJobs;
//	static int prevLength = 0;
//	static String temp1   = null;
//	static int firstTime  = 0;
	
		public static void main(String[] args) {
		
			String urlForJob = "http://www.shine.com/job-search/simple/tv-film-radio-entertainment/";
			Logger logger = JobAggregation.createLogger();
			
			GetJobs.FetchjobsFromJobPortal(urlForJob, logger);	
		}
	
	public static  ArrayList<String> FetchjobsFromJobPortal(String urlForJob, Logger logger)
	{
	    WebDriver driver1 =  new FirefoxDriver();
	    // Code for pagination
	    driver1.get(urlForJob);
	    ArrayList<String> listOfJobs      =  new ArrayList<String>();
		WebElement             divForJobs =  driver1.findElement(By.xpath("//*[@id=\"search_content\"]/div[4]"));
		WebElement         ulOfPagination =  divForJobs.findElement(By.xpath("//ul[contains(@class,'jPag-pages')]"));
		List<WebElement>       listOfHref =  ulOfPagination.findElements(By.tagName("li"));
		
		for(int i = 0 ; i < listOfHref.size() ; i++)
		{
			    
				try
				{
				 // Code for getting jobs
				 //prevLength = driver1.getPageSource().length();
				 logger.info(listOfHref.get(i).getText()+" is called.");
				 
				 listOfHref.get(i).click();
				
				 //System.out.println("Getting Data For Div Container...");
				 driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			     try 
				    {
							Thread.sleep(5000);
				    }// try close 
			     catch (InterruptedException e) 
					{
							e.printStackTrace();
					}// catch close
			         
			     WebElement    divForJobs1           =  driver1.findElement(By.xpath("//*[@id=\"search_content\"]/div[4]"));
				 List<WebElement>        listOfDiv1  =  divForJobs1.findElements(By.xpath("//div[contains(@class,'search_listing search_listing_blue')]"));
				 int j = 0;
				 for( ; j < listOfDiv1.size() ; j++)
					{ 
						  //System.out.println("---------------------------------------------------------------");
					      //System.out.println(listOfDiv1.get(j).getText());
						  listOfJobs.add(listOfDiv1.get(j).getText());
						  //Write In a file
//						  FileWriteUsingAppendMode("/home/amit/Desktop/shinejobs.txt",listOfDiv1.get(j).getText()+"\n");
//						  FileWriteUsingAppendMode("/home/amit/Desktop/shinejobs.txt","-----------------------------------------------\n");
//						  
		   		    }
				 
				 // mechanism for loading the elements
			     // Again assign the new values to the 
			         
			         divForJobs =  driver1.findElement(By.xpath("//*[@id=\"search_content\"]/div[4]"));
			     ulOfPagination =  divForJobs.findElement(By.xpath("//ul[contains(@class,'jPag-pages')]"));
					 listOfHref =  ulOfPagination.findElements(By.tagName("li"));
					
//						 if(prevLength == driver1.getPageSource().length() && i%4 == 0)
//						 {
//							 break;
//						 }
						 
				} // try close
				
				catch(NoSuchElementException e)
				{
					 logger.info("No Such Element Error");
				}// catch close
				catch(NotFoundException e)
				{
					logger.info("Not Found Exception");
				}// catch close
				catch(StaleElementReferenceException e)
				{
					logger.info("Not Found in the cache");
				}// catch close
		}// for close
	driver1.close();
	// return list of all jobs
 return listOfJobs;		
}
	
	// Function for writing in a file
private static void FileWriteUsingAppendMode(String path, String content) {
		try{
 
    		File file =new File(path);
 
    		//if file doesnt exists, then create it
    		if(!file.exists()){
    			file.createNewFile();
    		}
 
    		//true = append file
    		FileWriter fileWritter = new FileWriter(file,true);
    	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	        bufferWritter.write(content);
    	        bufferWritter.close();
 

    	}catch(IOException e){
    		e.printStackTrace();
    	}
	}
}

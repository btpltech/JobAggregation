package simplyhired.com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import org.openqa.selenium.WebElement;


public class temp {
public static void main(String[] args) {
	//String url    = "http://shine.com";
	String url = "http://www.simplyhired.co.in/";
	Logger logger = createLogger(); 
	// an arraylist to store joblinks of the pages from where we will get the jobs
	ArrayList<String>   listOfJobLinks = new ArrayList<String>();
	
	// call the function to get all the category links from jobportal
	//============================================================
	WebDriver driver =  new FirefoxDriver();
	try
	{
		logger.info("Links of job category are being fetched...");
		listOfJobLinks = GetJobLinksFrom(url,driver); // currently this funtion is just supporting shine.com
	    logger.info("Links of job category  = "+listOfJobLinks.size()+" are fetched.");
	    System.out.println(listOfJobLinks);
	    driver.close(); // driver is closed
	}
	catch(NoSuchElementException e)
	{
		driver.close();
		logger.info("Error occured in getting category links.");	
	}
	
	
}// main close

public static ArrayList<String> GetJobLinksFrom(String url,WebDriver driver)
{
	driver.get(url); // get url
	
	// get main container 
	WebElement	nextPageDiv  = driver.findElement(By.id("c_sh_find_jobs"));
	// get list of links of different categories for jobs
	List<WebElement>    a    = nextPageDiv.findElements(By.tagName("a"));
	// an arraylist to store joblinks
	ArrayList<String>   listOfJobLinks = new ArrayList<String>(); 
	// loop for the links we got
	for(int i = 0; i< a.size() ; i++)
		{
		//System.out.println(a.get(i).getAttribute("href"));
		if((a.get(i).getAttribute("href").contains("http://") || a.get(i).getAttribute("href").contains("www")) && !a.get(i).getAttribute("href").contains("job-search") )
	      {
				listOfJobLinks.add(a.get(i).getAttribute("href"));
	
	      }// if close
		}
	// loop for getting job links 
	driver.close();
	return listOfJobLinks;
}// GetJobLinksFrom close

// function for logging
public static Logger createLogger(){
	    Logger logger = Logger.getLogger("JobAggregation");  
	    FileHandler fh;  
	    try {  
            fh = new FileHandler("JobAggregation.log");  
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  
           } catch (SecurityException e) {  
		        e.printStackTrace();  
		   } catch (IOException e) {  	
		        e.printStackTrace();  
		   }  
	    return logger; 
}// logger close
}

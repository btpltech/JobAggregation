package shine.com;

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

import FilterJobs.*;
import StoreJobs.*;

public class JobAggregation {
public static void main(String[] args) {
	String url    = "http://shine.com";
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
	    driver.close(); // driver is closed
	}
	catch(NoSuchElementException e)
	{
		driver.close();
		logger.info("Error occured in getting category links.");	
	}
	//=============================================================
	// loop through arraylist to fetch all the jobs per page
	ArrayList<String> jobs = new ArrayList<String>();
	logger.info("Loop for category links started...");
	//=============================================================
	for(int i = 0 ; i < listOfJobLinks.size() ; i++)
	{
		// get jobs from link
		//=========================================================
		try
		{
			logger.info("Links for link "+listOfJobLinks.get(i)+" are being fetched...");
			jobs = GetJobs.FetchjobsFromJobPortal(listOfJobLinks.get(i),logger);
		    logger.info("Links for link "+listOfJobLinks.get(i)+" are fetched.");
		}
		catch(NoSuchElementException e)
		{
			logger.info("Error occured in fetching jobs from category links");
		}
		
		Map<String,Map<String,String>> jobFilter = new HashMap<String,Map<String,String>>();
		/*
		 * Filtering And Storing of data is done below here
		 * */
		//=========================================================
		try
		{
	 		logger.info("Filtering of jobs from "+listOfJobLinks.get(i)+" started...");
	 		jobFilter = FilterJobs.FilterRelevantInfoFromJob(jobs);
			logger.info("Filtering of jobs from "+listOfJobLinks.get(i)+" finished.");
		}
		catch(Exception e)
		{
			logger.info("Error in Filtering in jobs.");
		}
		//=========================================================

		try
		{
		logger.info("Storing of jobs from "+listOfJobLinks.get(i)+" started...");
		StoreJobs.StoreData(jobFilter); // store this job data somewhere
		logger.info("Storing of jobs from "+listOfJobLinks.get(i)+" finished.");
		}
		catch(Exception e)
		{
			logger.info("Error occured in storing jobs data.");
		}//catch close
		//=========================================================
	}// for close
	//=============================================================	
	// condition if no links are fetched from url
	if(listOfJobLinks.size() == 0)
		logger.info("Threre are no links fetched from "+url);
	
	logger.info("Loop for category links finished");
	//=============================================================
}// main close

public static ArrayList<String> GetJobLinksFrom(String url,WebDriver driver)
{
	driver.get(url); // get url
	
	// get main container 
	WebElement	nextPageDiv  = driver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div[7]/div"));
	// get list of links of different categories for jobs
	List<WebElement>    a    = nextPageDiv.findElements(By.tagName("a"));
	// an arraylist to store joblinks
	ArrayList<String>   listOfJobLinks = new ArrayList<String>(); 
	// loop for the links we got
	for(int i = 0; i< a.size() ; i++)
		{
		//System.out.println(a.get(i).getAttribute("href"));
		if(a.get(i).getAttribute("href").contains("http://") || a.get(i).getAttribute("href").contains("www"))
	      {
				listOfJobLinks.add(a.get(i).getAttribute("href"));
	      }// if close
		}
	// loop for getting job links 
	//driver.close();
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

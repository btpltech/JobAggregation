package monster.com;

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
	//String url    = "http://shine.com";
	String url = "http://jobsearch.monster.com/browse/?re=nv_gh_gnl1147_%2Fbrowse%2F";
	Logger logger = createLogger(); 
	// an arraylist to store joblinks of the pages from where we will get the jobs
	//ArrayList<String>   listOfJobs = new ArrayList<String>();
	ArrayList<String> jobs = new ArrayList<String>();
	// call the function to get all the category links from jobportal
	//============================================================
	WebDriver driver =  new FirefoxDriver();
	try
	{
		logger.info("jobs are being fetched...");
		jobs = GetJobs.FetchjobsFromJobPortal(url);
	    logger.info("jobs fetching is done.");
	    driver.close(); // driver is closed
	}
	catch(NoSuchElementException e)
	{
		driver.close();
		logger.info("Error occured in getting jobs.");	
	}
	
	 
	
	Map<String,Map<String,String>> jobFilter = new HashMap<String,Map<String,String>>();
	/*
	 * Filtering And Storing of data is done below here
	 * */
	//=========================================================
	try
	{
 		logger.info("Filtering of jobs started...");
 		jobFilter = FilterJobs.FilterRelevantInfoFromJob(jobs);
		logger.info("Filtering of jobs finished.");
	}
	catch(Exception e)
	{
		logger.info("Error in Filtering in jobs.");
	}
	//=========================================================

	try
	{
	logger.info("Storing of jobs started...");
	StoreJobs.StoreData(jobFilter); // store this job data somewhere
	logger.info("Storing of jobs finished.");
	}
	catch(Exception e)
	{
		logger.info("Error occured in storing jobs data.");
	}//catch close
	//=========================================================
		
	// condition if no links are fetched from url
	if(jobs.size() == 0)
		logger.info("Threre are no links fetched from "+url);
	
	logger.info("Loop for category links finished");
	//=============================================================
}// main close

public static ArrayList<String> GetJobLinksFrom(String url,WebDriver driver)
{
driver.get(url); // get url
	
	// get main container 
	WebElement	nextPageDiv  = driver.findElement(By.id("populartable"));
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

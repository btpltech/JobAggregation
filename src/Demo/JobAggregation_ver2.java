package Demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import org.openqa.selenium.WebElement;


public class JobAggregation_ver2 {
public static void main(String[] args) {
	String url = "http://shine.com";
	
	// an arraylist to store joblinks of the pages from where we will get the jobs
	ArrayList<String>   listOfJobLinks = new ArrayList<String>();
	// call the function to get all the category links from jobportal
	GetJobLinksFrom(url); // currently this funtion is just supporting shine.com
	
	// loop through arraylist to fetch all the jobs per page
	ArrayList<String> jobs = new ArrayList<String>();
	for(int i = 0 ; i < listOfJobLinks.size() ; i++)
	{
		// get jobs from link
		jobs = GetJobs.FetchjobsFromJobPortal(listOfJobLinks.get(i));
		
		Map<String,String> jobFilter = new HashMap<String,String>();
		/*
		 * Filtering And Storing of data is done below here
		 * */
		jobFilter = FilterData.FilterRelevantInfoFromJob(jobs); // this will filter out the data
		StoreData.StoreData(jobFilter); // store this job data somewhere
	
	}// for close
}// main close

public static ArrayList<String> GetJobLinksFrom(String url)
{
	WebDriver driver =  new FirefoxDriver();
	driver.get(url);
	
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
	driver.close();
	return listOfJobLinks;
}// GetJobLinksFrom close
}

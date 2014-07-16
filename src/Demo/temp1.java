package Demo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import org.openqa.selenium.WebElement;


public class temp1 {
public static void main(String[] args) {
//	WebDriver driver =  new FirefoxDriver();
//	driver.get("http://www.shine.com");
//	
//	WebElement	nextPageDiv  = driver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div[7]/div"));
//	List<WebElement>    a    = nextPageDiv.findElements(By.tagName("a"));
//	ArrayList<String>   listOfJobLinks = new ArrayList<String>(); 
//	for(int i = 0; i< a.size() ; i++)
//		{
//		//System.out.println(a.get(i).getAttribute("href"));
//		if(a.get(i).getAttribute("href").contains("http://") || a.get(i).getAttribute("href").contains("www"))
//	      {
//				listOfJobLinks.add(a.get(i).getAttribute("href"));
//	      }
//		}
//	// loop for getting job links 
//	driver.close();
	
	WebDriver driver1 =  new FirefoxDriver();
	
	//for(int i = 0 ; i < listOfJobLinks.size() ; i++)
	{
		// get the url for getting the jobs
		driver1.get("http://www.shine.com/job-search/simple/real-estate-industry/");
		System.out.println("Getting Data For Div Container...");
		WebElement             divForJobs = driver1.findElement(By.xpath("//*[@id=\"search_content\"]/div[4]"));
		//List<WebElement>       listOfDiv  = divForJobs.findElements(By.xpath("//*[@id=\"search_content\"]/div[4]/div[2]/div[2]"));
		
		//List<WebElement>        listOfDiv  =  divForJobs.findElements(By.xpath("//div[@class=\"search_listing search_listing_blue\"]"));
		System.out.println("Getting Data of Individual Container");
		List<WebElement>        listOfDiv  =  divForJobs.findElements(By.xpath("//div[contains(@class,'search_listing search_listing_blue')]"));
		for(int i = 0 ; i < listOfDiv.size() ; i++)
		{ 
			System.out.println("---------------------------------------------------------------");
			System.out.println(listOfDiv.get(i).getText());
			System.out.println("---------------------------------------------------------------");
		}
		
		
	}// for close
	driver1.close();
	
}
}

package Demo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import org.openqa.selenium.WebElement;


public class JobAggregation_ver1 {
public static void main(String[] args) {
	WebDriver driver =  new FirefoxDriver();
	driver.get("http://www.shine.com");
	
	WebElement	nextPageDiv  = driver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div[7]/div"));
	List<WebElement>    a    = nextPageDiv.findElements(By.tagName("a"));
	ArrayList<String>   listOfJobLinks = new ArrayList<String>(); 
	for(int i = 0; i< a.size() ; i++)
		{
		//System.out.println(a.get(i).getAttribute("href"));
		if(a.get(i).getAttribute("href").contains("http://") || a.get(i).getAttribute("href").contains("www"))
	      {
				listOfJobLinks.add(a.get(i).getAttribute("href"));
	      }
		}
	// loop for getting job links 
	driver.close();
	
	WebDriver driver1 =  new FirefoxDriver();
	
	for(int i = 0 ; i < listOfJobLinks.size() ; i++)
	{
		// get the url for getting the jobs
		driver1.get(listOfJobLinks.get(i));
		
		
	}// for close
	driver1.close();
	
}
}

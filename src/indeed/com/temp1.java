package indeed.com;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import org.openqa.selenium.WebElement;

import com.thoughtworks.selenium.SeleniumException;


public class temp1 {
	
	// static variables
//	static ArrayList<String> listOfJobs;
//	static int prevLength = 0;
//	static String temp1   = null;
//	static int firstTime  = 0;
	
		public static void main(String[] args) {
		
			String urlForJob = "http://www.indeed.co.in/Merchandiser-jobs";
			temp1.FetchjobsFromJobPortal(urlForJob);	
		}

public static  ArrayList<String> FetchjobsFromJobPortal(String urlForJob)
	{
	    WebDriver driver1 =  new FirefoxDriver();
	    // Code for pagination
	    driver1.get(urlForJob);
	    ArrayList<String> listOfJobs      =  new ArrayList<String>();
		
		
		//WebElement         ulOfPagination =  divForJobs.findElement(By.xpath("//ul[contains(@class,'jPag-pages')]"));
		//List<WebElement>       listOfHref =  ulOfPagination.findElements(By.tagName("li"));
	    WebElement             divForJobs =  driver1.findElement(By.xpath("//div[contains(@class,'pagination')]"));
		List<WebElement>       listOfHref =  divForJobs.findElements(By.tagName("a"));
		
		for(int i = 0 ; i < listOfHref.size() ; i++)
		{
			
			    
			    if(listOfHref.get(i).getText().contains("Next"))
			    {
				  
			    try
				{
				     //listOfHref.get(i).click();
			    	 //WebElement    divForJobs1           =  driver1.findElement(By.xpath("//*[@id=\"search_content\"]/div[4]"));
					 //List<WebElement>        listOfDiv1  =  divForJobs1.findElements(By.xpath("//div[contains(@class,'search_listing search_listing_blue')]"));
					 List<WebElement>        listOfDiv1  =  driver1.findElements(By.xpath("//div[contains(@class,'row ')]"));
					 
					 int j = 0 ;
					 for(; j < listOfDiv1.size() ; j++)
						{ 
							  System.out.println("---------------------------------------------------------------");
						      System.out.println(listOfDiv1.get(j).getText());
							  listOfJobs.add(listOfDiv1.get(j).getText());
							  //Write In a file
							//FileWriteUsingAppendMode("/home/amit/Desktop/jobs.txt",listOfDiv1.get(j).getText());
			   		    }
					 System.out.println(j+"jobs are got from this link :"+listOfHref.get(i).getAttribute("href"));
				     driver1.get(listOfHref.get(i).getAttribute("href"));
					 
					 driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				     try 
					    {
								Thread.sleep(5000);
					    }// try close 
				     catch (InterruptedException e) 
						{
								e.printStackTrace();
						}// catch close
				     
				     // reload the list of pagination
		              divForJobs =  driver1.findElement(By.xpath("//div[contains(@class,'pagination')]"));
				      listOfHref =  divForJobs.findElements(By.tagName("a"));
				 		 
				} // try close
				
				catch(NoSuchElementException e)
				{
					 System.out.println("No Such Element Error");
				}// catch close
				catch(NotFoundException e)
				{
					 System.out.println("Not Found Exception");
				}// catch close
				catch(StaleElementReferenceException e)
				{
					System.out.println("Not Found in the cache");
				}// catch close
			    i = 0;
			    } // if close
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
			}
		catch(IOException e){
    		e.printStackTrace();
    	}
	}
}

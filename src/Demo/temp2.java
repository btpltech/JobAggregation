package Demo;

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


public class temp2 {
	
	// static variables
	static ArrayList<String> listOfJobs;
	static int prevLength = 0;
	static String temp1   = null;
	static int firstTime  = 0;
	
		public static void main(String[] args) {
		
			FetchjobsFromJobPortal(0);	
		}
		public static int FetchjobsFromJobPortal(int temp)
		{
		    WebDriver driver1 =  new FirefoxDriver();
		    // Code for pagination
		    driver1.get("http://www.shine.com/job-search/simple/real-estate-industry/");
			WebElement             divForJobs =  driver1.findElement(By.xpath("//*[@id=\"search_content\"]/div[4]"));
			WebElement         ulOfPagination =  divForJobs.findElement(By.xpath("//ul[contains(@class,'jPag-pages')]"));
			List<WebElement>       listOfHref =  ulOfPagination.findElements(By.tagName("li"));
			
			for(int i = 0 ; i < listOfHref.size() ; i++)
			{
				    
					try
					{
					 // Code for getting jobs
					 prevLength = driver1.getPageSource().length();
					 listOfHref.get(i).click();
					
					 System.out.println("Getting Data For Div Container...");
					 //WebElement    divForJobs1 = driver1.findElement(By.xpath("//*[@id=\"search_content\"]/div[4]"));
					 //List<WebElement>        listOfDiv1  =  divForJobs1.findElements(By.xpath("//div[contains(@class,'search_listing search_listing_blue')]"));
					 //for(int j = 0 ; j < listOfDiv1.size() ; j++)
						{ 
							System.out.println("---------------------------------------------------------------");
						//	System.out.println(listOfDiv1.get(j).getText());
							
							// Write In a file
							//FileWriteUsingAppendMode("/home/amit/Desktop/jobs.txt",listOfDiv1.get(j).getText());
			   		    }
					 
					
					  // mechanism for loading the elements
				     driver1.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				     try 
					    {
								Thread.sleep(2000);
					    }// try close 
				     catch (InterruptedException e) 
							{
								e.printStackTrace();
							}// catch close
				         
				     // Again assign the new values to the 
				     ulOfPagination =  divForJobs.findElement(By.xpath("//ul[contains(@class,'jPag-pages')]"));
						 listOfHref =  ulOfPagination.findElements(By.tagName("li"));
						
					} // try close
					
					catch(NoSuchElementException e)
					{
						driver1.close();
						System.out.println("Recursion Is Called...");
						if(!temp1.equals(null))
						{
							temp1 = Integer.toString((Integer.parseInt(temp1)+1));
						    FetchjobsFromJobPortal(Integer.parseInt(temp1));
						}
						// close driver
						if(prevLength == driver1.getPageSource().length())
							break;
					}// catch close
					catch(NotFoundException e)
					{
						driver1.close();
						System.out.println("Recursion Is Called...");
						if(!temp1.equals(null))
						{
							temp1 = Integer.toString((Integer.parseInt(temp1)+1));
							FetchjobsFromJobPortal(Integer.parseInt(temp1));
						}
						 // close driver
						if(prevLength == driver1.getPageSource().length())
							break;
					
					}// catch close
					catch(StaleElementReferenceException e)
					{
						driver1.close();
						System.out.println("Recursion Is Called...");
						if(!temp1.equals(null))
						{
						  temp1 = Integer.toString((Integer.parseInt(temp1)+1));	
						  FetchjobsFromJobPortal((Integer.parseInt(temp1)));
						}
						 // close driver
						if(prevLength == driver1.getPageSource().length())
							break;
					}// catch close
			}// for close
		driver1.close();
		return 0;
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

package StoreJobs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class StoreJobs {
	public static String dbUrl = "jdbc:mysql://localhost:3306/";
	public static String dbName = "job";
	public static String driver = "com.mysql.jdbc.Driver";
	public static String userName = "root";
	public static String password = "10987";
	public static void main(String[] args) {
		Map<String,Map<String,String>> mapOFFilerData = new HashMap<String,Map<String,String>>();
		StoreData(mapOFFilerData);
	}
public static int StoreData(Map<String,Map<String,String>> mapOFFilerData) 
{
	//flag variable
	int flag      = 1;
	int count     = 1;
	int batchSize = 50;
	// calling function for database connection
	Connection con = dbConnection();
	// variable for database interaction
	PreparedStatement ps = null;
	try
	{
		// variables for schema
		String JobId       = "n/a";
		String Title       = "n/a";
		String Company     = "n/a";
		String Location    = "n/a";
		String Description = "n/a";
		String Designation = "n/a";
		String PostedDate  = "n/a";

		ps = con.prepareStatement("insert into job(JobId,Title,Company,Location,Description) values(?,?,?,?,?)");
		long startTime     = System.currentTimeMillis();
		// loop for preparing data to insert into database table
		for(Map.Entry<String, Map<String,String>> item : mapOFFilerData.entrySet())
		{
			for(Map.Entry<String, String> item1 : item.getValue().entrySet())
			{
			        JobId      = Long.toString(System.currentTimeMillis());
			    if(item1.getKey().equalsIgnoreCase("Title"))
			    {
			    	Title      = item1.getValue();
			    }
//			    else
//			    	Title      = "n/a";
			    if(item1.getKey().equalsIgnoreCase("Company"))
			    {
			    	Company    = item1.getValue();
			    }
//			    else
//			    	Company     = "n/a";
			    if(item1.getKey().equalsIgnoreCase("Location"))
			    {
			    	Location    = item1.getValue();
			    }
//			    else
//			    	Location    = "n/a";
			    if(item1.getKey().equalsIgnoreCase("Description"))
			    {
			    	Description = item1.getValue();
			    }
//			    else
//			    	Description = "n/a";
//			    if(item1.getKey().equalsIgnoreCase("Desgnation"))
//			    {
//			    	Designation = item1.getValue();
//			    }
//			    else
//			    	Designation = "n/a";
//			    if(item1.getKey().equalsIgnoreCase("Posted Date"))
//			    {
//			    	//PostedDate  = item1.getValue();
//			    }
//			    else 
//			    	//PostedDate  = "n/a";
			} // inner for close
			
			
			ps.setString(1, JobId);
			ps.setString(2, Title);
			ps.setString(3, Company);
			ps.setString(4, Location);
			ps.setString(5, Description);
			//ps.setString(6, Designation);
			//ps.setString(7, PostedDate);
			ps.addBatch();
			
			
			try
			{
				 if (count % batchSize == 0) 
				 {
					 //System.out.println("Now query is being executed...");
					 ps.executeBatch();
					 //System.out.println("Now query is executed");
				 }
			}
			catch(Exception e){
				System.out.println(e);
				flag  = 0;
			}
			count++;
			//System.out.println("Record is added");
		}// for close
		
		//System.out.println("For remaining...");
		try
		{
				 //System.out.println("Now query is being executed...");
				 ps.executeBatch();
				 //System.out.println("Now query is executed");
		
		}
		catch(Exception e){
			System.out.println(e);
			flag  = 0;
		}
		long endTime = System.currentTimeMillis();
		long elapsedTime = (endTime - startTime)/1000; //in seconds
        System.out.println("Total time required to execute"+ count +" queries using PreparedStatement with JDBC batch insert is :" + elapsedTime+" sec");
	}
	catch(Exception e){}
	
	return flag;
}// function close

// function for database connection
public static Connection dbConnection()
{
	Connection con = null;
	
	try {
		Class.forName(driver).newInstance();
		con = DriverManager.getConnection(dbUrl + dbName, userName, password);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return con;
}
}// class close

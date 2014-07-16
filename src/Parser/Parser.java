package Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Parser {
public static Map<String,Map<String,String>> parserForShine(String  path)
{
	Map<String,Map<String,String>> hashMap = new HashMap<String,Map<String,String>>();
	int count = 0;
	try
	{
		BufferedReader br = new BufferedReader(new FileReader(path));
		String Line;
		ArrayList<String> arrayList = new ArrayList<String>();
		Map<String,String> temp     = new HashMap<String,String>();
		int i = 0;
		while ((Line = br.readLine()) != null) {
			
			if(Line.equals("-----------------------------------------------"))
			{
			  //filterData(hashMap);
			  i++;
			  
			  int k = 0;
			  String skills = null;
			  for(int j = 0 ; j < arrayList.size() ; j = j+1)
			  {
				  if(arrayList.get(j).contains("Skills"))
				  {
					  j = j+1;
					  
					  while(!arrayList.get(j).contains("Posted Date"))
					  {
						if(k == 0)
						{
							skills = arrayList.get(j);
						    k++;
						}
						else
							skills = skills+","+arrayList.get(j);;
						  
						  j = j+1;
					  }
				  }
			  }
			  k = 0;
			  String Description = null;
			  for(int j = 0 ; j < arrayList.size() ; j = j+1)
			  {
				  if(arrayList.get(j).contains("Location"))
				  {
					  j = j+1;
					  while(!arrayList.get(j).contains("Skills"))
					  {
						if(k == 0)
						{
							Description = arrayList.get(j);
						    k++;
						}
						  j = j+1;
						  if(j >= arrayList.size())
							  break;
							  
					  }
				  }
			  }
			  if(skills != null)
				  temp.put("Skill", skills);
			  else
				  temp.put("Skill", "n/a");
			  if(Description != null)
				  temp.put("Description", Description);
			  else
				  temp.put("Description", "n/a");
				  
			  hashMap.put("job"+i, temp);
			  
			  // reset variables
			  temp  = new HashMap<String,String>();
			  arrayList = new ArrayList<String>();
			  count = 0; // reset counter;
			}
			else if(!Line.contains("|") && !Line.contains("All Jobs") && !Line.contains("Applied") && !Line.contains("Similar Jobs") && !Line.contains("Apply") && !Line.contains("Refer"))
			{
				arrayList.add(Line);
				
				if(count == 0)
				{
					String[] data = Line.split(",");
					try
					{
					data[0] = data[0].replaceAll("\"", "");
					temp.put("Title", data[0]);
					temp.put("Experience", data[1]);
					count++;
					}
					catch(Exception e)
					{
						temp.put("Title", "n/a");
						temp.put("Experience", "n/a");
					}
				}
				if(Line.contains("Location"))
			    {
					String[] data = Line.split("Location:");
					try
					{
						try
						{
							if(data[1].contains("/"))
							{
								
								//System.out.println(data[1]);
								String a = data[1];
								a = a.replaceAll("/", ",");
								//System.out.println(a);
								data[1] = a;
							}
						}
						catch(Exception e){}
					temp.put("Location", data[1]);
					}
					catch(Exception e)
					{
						temp.put("Location", "n/a");
					}
			    }
				if(Line.contains("Company"))
				{
					//System.out.println(Line);
					try
					{
					String[] data = Line.split("Company:");
					temp.put("Company", data[1]);
					}
					catch(Exception e)
					{
						temp.put("Company", "n/a");
					}
				}
				if(Line.contains("Posted Date"))
				{
					//System.out.println(Line);
					try
					{
					String[] data = Line.split("Posted Date ");
					temp.put("Posted Date", data[1]);
					}
					catch(Exception e)
					{
						temp.put("Posted Date", "n/a");
					}
				}
				if(Line.contains("Designation: "))
				{
					//System.out.println(Line);
					try
					{
					String[] data = Line.split("Designation: ");
					temp.put("Designation", data[1]);
					}
					catch(Exception e)
					{
						temp.put("Designation", "n/a");
					}
				}
			}
		}
		br.close();
}
catch (IOException e) {
	e.printStackTrace();
} // catch close
	return hashMap;
	}// parser close
public static Map<String,Map<String,String>> parserForIndeed(String path)
{

	Map<String,Map<String,String>> hashMap = new HashMap<String,Map<String,String>>();
	int count = 0;
	try
	{
		BufferedReader br = new BufferedReader(new FileReader(path));
		String Line;
		ArrayList<String> arrayList = new ArrayList<String>();
		Map<String,String> temp     = new HashMap<String,String>();
		int i = 0;
		while ((Line = br.readLine()) != null) {
			
			if(Line.equals("-----------------------------------------------"))
			{
			  //filterData(hashMap);
			  i++;
			  hashMap.put("job"+i, temp);
			  // reset variables
			  temp  = new HashMap<String,String>();
			  arrayList = new ArrayList<String>();
			  count = 0;
			}
			else if(!Line.contains("|") && !Line.contains("All Jobs") && !Line.contains("Applied") && !Line.contains("Similar Jobs") && !Line.contains("Apply") && !Line.contains("Refer") && !Line.equals(""))
			{
				arrayList.add(Line);
				
				if(count == 0)
				{
					temp.put("Title", Line);
					//temp.put("Experience", data[1]);
					count = count+1;	
					
				}
				else if(count == 1)
			    {
					String[] data = Line.split("-");
					try
					{
					  //System.out.println("Company: "+data[0]);
					  //System.out.println("Location: "+data[1]);
					  temp.put("Company", data[0]);
					  temp.put("Location", data[1]);
					  count = count+1;
					}
					catch(Exception e)
					{
						count = count+1;
					}
			    }
				else if(count == 2)
				{
					System.out.println("Descrption:"+Line);
					temp.put("Description", Line);
					//System.out.println("Description: "+Line);
					count = count+1;
				}
			}
		}
		br.close();
}
catch (IOException e) {
	e.printStackTrace();
} // catch close
	return hashMap;
	
}// function close

// funciton for monster
public static Map<String,Map<String,String>> parserForMonster(String path)
{

	Map<String,Map<String,String>> hashMap = new HashMap<String,Map<String,String>>();
	int count = 0;
	try
	{
		BufferedReader br = new BufferedReader(new FileReader(path));
		String Line;
		ArrayList<String> arrayList = new ArrayList<String>();
		Map<String,String> temp     = new HashMap<String,String>();
		int i = 0;
		while ((Line = br.readLine()) != null) {
			
			if(Line.equals("-----------------------------------------------"))
			{
			  //filterData(hashMap);
			  i++;
			  hashMap.put("job"+i, temp);
			  // reset variables
			  temp  = new HashMap<String,String>();
			  arrayList = new ArrayList<String>();
			  count = 0;
			}
			else if(!Line.contains("|") && !Line.contains("All Jobs") && !Line.contains("Applied") && !Line.contains("Similar Jobs") && !Line.contains("Apply") && !Line.contains("Refer") && !Line.equals("") && !Line.contains("Company:") && !Line.equals("Today"))
			{
				arrayList.add(Line);
				
				if(count == 0)
				{
					temp.put("Title", Line);
					//temp.put("Experience", data[1]);
					count = count+1;	
					
				}
				else if(count == 1)
			    {
					temp.put("Company", Line);
					count = count+1;
				}
				else if(count == 2)
				{
					//System.out.println("Location:"+Line);
					temp.put("Location", Line);
					//System.out.println("Description: "+Line);
					count = count+1;
				}
			}
		}
		br.close();
}
catch (IOException e) {
	e.printStackTrace();
} // catch close
	return hashMap;
	
}// function close

// function for Simplyhired
public static Map<String,Map<String,String>> parserForSimplyHired(String path)
{

	Map<String,Map<String,String>> hashMap = new HashMap<String,Map<String,String>>();
	int count = 0;
	try
	{
		BufferedReader br = new BufferedReader(new FileReader(path));
		String Line;
		ArrayList<String> arrayList = new ArrayList<String>();
		Map<String,String> temp     = new HashMap<String,String>();
		int i = 0;
		while ((Line = br.readLine()) != null) {
			
			if(Line.equals("-----------------------------------------------"))
			{
			  //filterData(hashMap);
			  i++;
			  hashMap.put("job"+i, temp);
			  // reset variables
			  temp  = new HashMap<String,String>();
			  arrayList = new ArrayList<String>();
			  count = 0;
			}
			else if(!Line.contains("|") && !Line.contains("All Jobs") && !Line.contains("Applied") && !Line.contains("Similar Jobs") && !Line.contains("Apply") && !Line.contains("Refer") && !Line.equals(""))
			{
				arrayList.add(Line);
				
				if(count == 0)
				{
					
					Line = Line.replaceAll("NEW$", "");
					Line = Line.replaceAll("-", "");
					temp.put("Title", Line);
					//temp.put("Experience", data[1]);
					count = count+1;	
					
				}
				else if(count == 1)
			    {
					String[] data = Line.split("-");
					try
					{
					  //System.out.println("Company: "+data[0]);
					  //System.out.println("Location: "+data[1]);
					  temp.put("Company", data[0]);
					  temp.put("Location", data[1]);
					  count = count+1;
					}
					catch(Exception e)
					{
						count = count+1;
					}
			    }
				else if(count == 2)
				{
					//System.out.println("Descrption:"+Line);
					temp.put("Description", Line);
					//System.out.println("Description: "+Line);
					count = count+1;
				}
			}
		}
		br.close();
}
catch (IOException e) {
	e.printStackTrace();
} // catch close
	return hashMap;
	
}// function close
}

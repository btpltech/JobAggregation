package FilterJobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import IO.IO;
import Parser.Parser;
import StoreJobs.StoreJobs;

public class FilterJobs {
	public static void main(String[] args) {
		FileReadAndFilter("/home/amit/Desktop/shinejobs.txt");
	}
	
//========================   Filter  ============================================
public static Map<String,Map<String,String>> FilterRelevantInfoFromJob(ArrayList<String> listOfjobs)
{
	Map<String,Map<String,String>> hashMap = new HashMap<String,Map<String,String>>();
    String mkDirectory = "./data"; // this is the directory where temp files will be created...
	IO.mkDirectory(mkDirectory);
	String tempFileLocation = mkDirectory+"/"+System.currentTimeMillis()+".txt"; // make temp file
	
	for(int i = 0 ; i< listOfjobs.size();i++){
		IO.FileWriteUsingAppendMode(tempFileLocation,listOfjobs.get(i)+"\n"); // write in file
		String separator = "-----------------------------------------------";
		IO.FileWriteUsingAppendMode(tempFileLocation,separator+"\n"); // write in file
	} // for close
	
	hashMap = Parser.parserForShine(tempFileLocation); // parser is called on temp file and it will return hashMap
	IO.deleteFile(tempFileLocation); // DELETE FILE AFTER FILTERING
	return hashMap; // return hashMap
}//function close
//==============================================================================
// this function is for testing only
public static Map<String,Map<String,String>> FileReadAndFilter(String path)
{
	Map<String,Map<String,String>> hashMap = new HashMap<String,Map<String,String>>();
	//Map<String,ArrayList<String>> hashMap1 = new HashMap<String,ArrayList<String>>();
	//hashMap = Parser.parserForIndeed(path); // here parser is called to extract job information (currently for shine.com)
    //hashMap   = Parser.parserForMonster(path);
	//hashMap   = Parser.parserForSimplyHired(path);
	hashMap   = Parser.parserForShine(path);
	int flag  = StoreJobs.StoreData(hashMap);
	
//	for(Map.Entry<String, Map<String,String>> item: hashMap.entrySet() )
//	{
//		for(Map.Entry<String, String> item2 : item.getValue().entrySet())
//		{
//			System.out.print(item2.getKey()+": ");
//			System.out.println(item2.getValue());
//		}
//	        System.out.println("----------------------------------------------");	
//	}
	return hashMap;
}//  function is closed
}

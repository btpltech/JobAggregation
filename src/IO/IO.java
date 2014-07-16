package IO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class IO {
	public static void FileWriteUsingAppendMode(String path, String content) {
		try{
 
    		File file =new File(path);
            
    		//if file doesnt exists, then create it
    		if(!file.exists()){
    			file.createNewFile();
    			file.setExecutable(true);
    			file.setReadable(true);
    			file.setWritable(true);
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
	
	public static void deleteFile(String path) {
		try
		{
		File file = new File(path);
			if(file.delete()){
				System.out.println(file.getName() + " is deleted!");
			}else{
				System.out.println("Delete operation is failed.");
			}
		}catch(Exception e){

			e.printStackTrace();
		}
		}
	private static void deleteDir(String path) {
		try {
			FileUtils.deleteDirectory(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // function close
	
	public static void mkDirectory(String mkDirectory)
	{
		// create directory
	   new File(mkDirectory).mkdir();
	   // change permissions
	   File file = new File(mkDirectory);
	   file.setExecutable(true);
	   file.setReadable(true);
	   file.setWritable(true);
	} // function close
}

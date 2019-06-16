package com.pkg.utility;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


public class FileWritter {
	//static String inputpath="C:\\Users\\Sidheshwar.Tondare\\workspace\\Merit_Search\\resources\\TestData\\";
	static String inputpath=FileWritter.getDesktopPath();
	 static String filename="AllResult.csv";
	static String filename1="AllResult.txt";
	
	public static void filewritter(ArrayList<ArrayList<String>> data) throws IOException, InvalidFormatException{
		FileWriter csvWriter = new FileWriter(inputpath+filename);
		csvWriter.append("Provisional Merit No");  
		csvWriter.append(",");  
		csvWriter.append("GujCET Roll No");  
		csvWriter.append(",");  
		csvWriter.append("Name");  
		csvWriter.append(",");  
		csvWriter.append("Board Name"); 
		csvWriter.append(",");  
		csvWriter.append("Board PCM Total"); 
		csvWriter.append(",");  
		csvWriter.append("GujCET PCM Total"); 
		csvWriter.append(",");  
		csvWriter.append("PCM Board Percentile (A)"); 
		csvWriter.append(",");  
		csvWriter.append("PCM GujCET Percentile (B)"); 
		csvWriter.append(","); 		
		csvWriter.append("Merit Mark ( A*0.6 + B*0.4 )"); 
		csvWriter.append(",");  
		csvWriter.append("Remarks"); 		
		csvWriter.append("\n");
		
		for (ArrayList<String> rowData : data) {  
		    csvWriter.append(String.join(",", rowData));
		    csvWriter.append("\n");
		}
		
		csvWriter.flush();  
		csvWriter.close();		
	
	}

	public static FileWriter writeTextfile() throws IOException{
		FileWriter write = new FileWriter(inputpath+filename1);
		write.append("Provisional Merit No");  
		write.append(",");  
		write.append("GujCET Roll No");  
		write.append(",");  
		write.append("Name");  
		write.append(",");  
		write.append("Board Name"); 
		write.append(",");  
		write.append("Board PCM Total"); 
		write.append(",");  
		write.append("GujCET PCM Total"); 
		write.append(",");  
		write.append("PCM Board Percentile (A)"); 
		write.append(",");  
		write.append("PCM GujCET Percentile (B)"); 
		write.append(","); 		
		write.append("Merit Mark ( A*0.6 + B*0.4 )"); 
		write.append(",");  
		write.append("Remarks"); 		
		write.append("\r\n");
		return write;
		
	}
	
	public static String getDesktopPath(){
		 String desktopPath=null;
		 String path=null;
		try{
			desktopPath=(System.getProperty("user.home") + "/Desktop/").replace("\\", "/");
			path=desktopPath+"MeritSearch/";
			
			  System.out.print(path);
			  }catch (Exception e){
			  System.out.println("Exception caught ="+e.getMessage());
			  }
		return path;
	}


	}



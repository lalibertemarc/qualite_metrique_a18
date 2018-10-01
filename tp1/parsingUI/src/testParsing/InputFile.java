package testParsing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class InputFile {
	public InputFile(){}

	public String scanFile() throws IOException
	{
		File file = new File("inputFile.txt"); 
		  
		  BufferedReader br = new BufferedReader(new FileReader(file)); 
		  String inputFile = "";
		  String st; 
		  while ((st = br.readLine()) != null) 
		  {
			  inputFile += st+"\n"; 
		  } 
		  return inputFile;
	}
	
}

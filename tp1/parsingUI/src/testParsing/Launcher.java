package testParsing;


import java.io.IOException;

import packageModels.Model;

public class Launcher {

	public static void main(String[] args) {
		
		InputFile test = new InputFile();
		String file = "";
		try {
			file = test.scanFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(file);
		Model model  = Parser.getModel(file);
		
		PrinterHelper.printModel(model);
	}

}
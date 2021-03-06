package testParsing;

import java.io.IOException;

import packageModels.Model;
import packageModels.Modelable;

public class PrettyPrinter {

	public static void main(String[] args) {
		
		InputFile test = new InputFile();
		String file = "";
		try {
			file = test.scanFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Modelable model  = Parser.getModel(file);
		
		PrinterHelper.printModel(model);
	}

}

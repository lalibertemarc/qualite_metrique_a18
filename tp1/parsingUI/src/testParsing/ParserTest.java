package testParsing;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import packageModels.*;
import parsingUI.ParseInterface;

public class ParserTest {
private String path = "testFiles/";
	
	@Test
	public void emptyFile() throws IOException
	{
		//Arrange
		File file = new File(path+"emptyFile.txt");
		String input = ParseInterface.scanFile(file);
		String message = "Empty File.";
		
		//Act
		Modelable model = Parser.getModel(input);
		
		//Assert
		assertTrue(model instanceof ParsingError);
		assertEquals(((ParsingError)model).getMessage(),message);
	}
	
	@Test
	public void emptyModelName() throws IOException
	{
		//Arrange
		File file = new File(path+"missingModelName.ucd");
		String input = ParseInterface.scanFile(file);
		String message="Empty model name";
		
		//Act
		Modelable model = Parser.getModel(input);
		
		//Assert
		assertTrue(model instanceof ParsingError);
		assertEquals(((ParsingError)model).getMessage(),message);
	}
	
	@Test
	public void emptyClassName() throws IOException
	{
		//Arrange
		File file = new File(path+"missingClassName.ucd");
		String input = ParseInterface.scanFile(file);
		String message="Empty class name";
		
		//Act
		Modelable model = Parser.getModel(input);
		
		//Assert
		assertTrue(model instanceof ParsingError);
		assertEquals(((ParsingError)model).getMessage(),message);
	}
	
	@Test
	public void dupeClassDec() throws IOException
	{
		//Arrange
		File file = new File(path+"dualEquipeClass.ucd");
		String input = ParseInterface.scanFile(file);
		String message="Class Equipe already declared";
		
		//Act
		Modelable model = Parser.getModel(input);
		
		//Assert
		assertTrue(model instanceof ParsingError);
		assertEquals(((ParsingError)model).getMessage(),message);
	}
	
	@Test
	public void noClassDec() throws IOException
	{
		//Arrange
		File file = new File(path+"noClassdec.ucd");
		String input = ParseInterface.scanFile(file);
		String message="No classes declared";
		
		//Act
		Modelable model = Parser.getModel(input);
		
		//Assert
		assertTrue(model instanceof ParsingError);
		assertEquals(((ParsingError)model).getMessage(),message);
	}
	

	@Test
	public void noSubClass() throws IOException
	{
		//Arrange
		File file = new File(path+"noSubClass.ucd");
		String input = ParseInterface.scanFile(file);
		String message="Malformed subclasses name, subclasses name cannot be empty.";
		
		//Act
		Modelable model = Parser.getModel(input);
		
		//Assert
		assertTrue(model instanceof ParsingError);
		assertEquals(((ParsingError)model).getMessage(),message);
	}
	
	@Test
	public void malformedOpsMissingType() throws IOException
	{
		//Arrange
		File file = new File(path+"malformedOps.ucd");
		String input = ParseInterface.scanFile(file);
		String message="Malformed operation declaration, name or type cannot be empty";
		
		//Act
		Modelable model = Parser.getModel(input);
		
		//Assert
		assertTrue(model instanceof ParsingError);
		assertEquals(((ParsingError)model).getMessage(),message);
	}
	
	@Test
	public void malformedOpsMissingName() throws IOException
	{
		//Arrange
		File file = new File(path+"malformedOps2.ucd");
		String input = ParseInterface.scanFile(file);
		String message="Malformed operation declaration, name or type cannot be empty";
		
		//Act
		Modelable model = Parser.getModel(input);
		
		//Assert
		assertTrue(model instanceof ParsingError);
		assertEquals(((ParsingError)model).getMessage(),message);
	}
	
	@Test
	public void malformedOpsMissingSemiColon() throws IOException
	{
		//Arrange
		File file = new File(path+"malformedOps3.ucd");
		String input = ParseInterface.scanFile(file);
		String message="Malformed operation declaration, name or type cannot be empty";
		
		//Act
		Modelable model = Parser.getModel(input);
		
		//Assert
		assertTrue(model instanceof ParsingError);
		assertEquals(((ParsingError)model).getMessage(),message);
	}
	
	@Test
	public void malformedDataMissingType() throws IOException
	{
		//Arrange
		File file = new File(path+"malformedData1.ucd");
		String input = ParseInterface.scanFile(file);
		String message="Malformed data item";
		
		//Act
		Modelable model = Parser.getModel(input);
		
		//Assert
		assertTrue(model instanceof ParsingError);
		assertEquals(((ParsingError)model).getMessage(),message);
	}
	
	@Test
	public void malformedDataMissingName() throws IOException
	{
		//Arrange
		File file = new File(path+"malformedData2.ucd");
		String input = ParseInterface.scanFile(file);
		String message="Malformed data item";
		
		//Act
		Modelable model = Parser.getModel(input);
		
		//Assert
		assertTrue(model instanceof ParsingError);
		assertEquals(((ParsingError)model).getMessage(),message);
	}
	
	@Test
	public void notExistingMultiplicity() throws IOException
	{
		//Arrange
		File file = new File(path+"notExistingMult.ucd");
		String input = ParseInterface.scanFile(file);
		String message="Mutliplicity does not exists";
		
		//Act
		Modelable model = Parser.getModel(input);
		
		//Assert
		assertTrue(model instanceof ParsingError);
		assertEquals(((ParsingError)model).getMessage(),message);
	}
	
	@Test
	public void malformedRole() throws IOException
	{
		//Arrange
		File file = new File(path+"malformedRole.ucd");
		String input = ParseInterface.scanFile(file);
		String message="Malformed role declaration";
		
		//Act
		Modelable model = Parser.getModel(input);
		
		//Assert
		assertTrue(model instanceof ParsingError);
		assertEquals(((ParsingError)model).getMessage(),message);
	}
	
	@Test
	public void goodModelParsing() throws IOException
	{
		//Arrange
		File file = new File(path+"Ligue.ucd");
		String input = ParseInterface.scanFile(file);
		
		
		//Act
		Modelable model = Parser.getModel(input);
		
		//Assert
		assertTrue(model instanceof Model);
	}
	
	
	
	
}

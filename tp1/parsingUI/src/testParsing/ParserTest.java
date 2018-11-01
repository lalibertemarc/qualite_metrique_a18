package testParsing;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import packageModels.*;
import parsingUI.ParseInterface;

/**
 * The Class ParserTest.
 * Unit tests for the project
 */
public class ParserTest {

/** The path. */
private String path = "testFiles/";
	
	/**
	 * Empty file.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	
	/**
	 * Empty model name.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	
	/**
	 * Empty class name.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	
	/**
	 * Dupe class dec.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	
	/**
	 * No class dec.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	

	/**
	 * No sub class.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	
	/**
	 * Malformed ops missing type.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	
	/**
	 * Malformed ops missing name.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	
	/**
	 * Malformed ops missing semi colon.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	
	/**
	 * Malformed data missing type.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	
	/**
	 * Malformed data missing name.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	
	/**
	 * Not existing multiplicity.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	
	/**
	 * Malformed role.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	
	/**
	 * Good model parsing.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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

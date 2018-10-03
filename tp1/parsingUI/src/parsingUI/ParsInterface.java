package parsingUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import testParsing.Parser;
import testParsing.PrinterHelper;
import packageModels.Aggregation;
import packageModels.Association;
import packageModels.Class_dec;
import packageModels.Data_Item;
import packageModels.Model;
import packageModels.Operation;




public class ParsInterface extends JFrame implements ActionListener{
	
	private JPanel container = new JPanel();
	private JPanel containerNorth = new JPanel();
	private JTextField file = new JTextField();
	private JButton selectFile = new JButton("Charger fichier");
	
	private PanelContainer classes = new PanelContainer("classes");
	private PanelContainer attributes = new PanelContainer("attributs");
	private PanelContainer methods = new PanelContainer("méthodes");
	private PanelContainer sousClasses = new PanelContainer("sous_Classes");
	private PanelContainer associations = new PanelContainer("Associations/Agrégation");
	private PanelContainer details = new PanelContainer("details");

	
	private DefaultListModel modelClass = new DefaultListModel();
	private DefaultListModel<String> modelAttr = new DefaultListModel();
	private DefaultListModel<String> modelMeth = new DefaultListModel();
	private DefaultListModel modelSub = new DefaultListModel();
	private DefaultListModel modelAgr = new DefaultListModel();
	private DefaultListModel modelDet = new DefaultListModel();

	private ArrayList<Class_dec> myClasses;
	private ArrayList<Data_Item> myAttributes;
	private ArrayList<Operation> myMethods;
	private ArrayList<String> mySubClass;
	private ArrayList<Aggregation> myAggregations;
	private ArrayList<Association> myAssociations;
	
	
	
	JList<String> jClass = new JList<>(modelClass);
	JList<String> jAttributes= new JList<>(modelAttr);
	JList<String> jMethods= new JList<>(modelMeth);
	JList<String> jSubClass= new JList<>(modelSub);
	JList<String> jAggregations= new JList<>(modelAgr);
	JList<String> jDetails= new JList<>(modelDet);
	//private Object ListselectionModel;


	public ParsInterface(){	
		//modelClass.addElement(null);
		//basic appearance 
		this.setTitle("Parseur");
		this.setPreferredSize(new Dimension(1000, 1000));
		this.setSize(1000, 1000);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null); 
		this.getContentPane().setBackground(new Color(240,248,255));
		
		 
		//select zone
		selectFile.setPreferredSize(new Dimension(200, 50));
		selectFile.addActionListener(this);
		
		file.setBorder(BorderFactory.createLineBorder(Color.black));
		containerNorth.setLayout(new BorderLayout(10,10));
		containerNorth.setPreferredSize(new Dimension(900, 100));
		containerNorth.add(selectFile, BorderLayout.WEST);
		containerNorth.add(file, BorderLayout.CENTER);
		containerNorth.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		containerNorth.setBounds(0, 0, 1000, 100);
		this.getContentPane().add(containerNorth);
		
		
		//event listener for the list of Classes
		jClass.addListSelectionListener(new ListSelectionListener(){
		    public void valueChanged(ListSelectionEvent e) {
		    	if(e.getValueIsAdjusting()) {
		    		return;
		    	} 
		    	System.out.println(jClass.getSelectedIndex());
		    	int selectedIndex =jClass.getSelectedIndex();
		    	//System.out.println(jClass.getSelectedValues());
		    	//Model model = 
		    	if(modelAttr.size()>0) {
		    		modelAttr.removeAllElements();
		    	}
		    	Class_dec selectedClass =  myClasses.get(selectedIndex);
		    	myAttributes =(ArrayList<Data_Item>) selectedClass.getAttributes();
		    	
		    	for(int i=0; i< myAttributes.size(); i++) {
		    		modelAttr.addElement(myAttributes.get(i).getIdentifier());
		    		//modelAttr.notify();
		    	}
		    	myMethods =(ArrayList<Operation>) selectedClass.getOperations();
		    	for(int i=0; i< myMethods.size(); i++) {
		    		modelMeth.addElement(myMethods.get(i).getIdentifier());
		    	}
		    	
		    	mySubClass =(ArrayList<String>) selectedClass.getSubclasses();
		    	if(mySubClass!=null) {
		    		//mySubClass.clear();
		    		for(int i=0; i< mySubClass.size(); i++) {
			    		modelSub.addElement(mySubClass.get(i));
			    	}
		    	}
//		    	my =(ArrayList<String>) selectedClass.getSubclasses();
//		    	for(int i=0; i< myAggregations.size(); i++) {
//		    		modelAgr.addElement(myAggregations.get(i));
//		    	}
//		    	for(int i=0; i< myAssociations.size(); i++) {
//		    		modelAgr.addElement(myAssociations.get(i));
//		    	}
		    }
		});
		
		jClass.setBounds(10, 100, 300, 850);

		classes.add(jClass);
		attributes.add(jAttributes);
		methods.add(jMethods);
		sousClasses.add(jSubClass);
		associations.add(jAggregations);
		details.add(jDetails);
		
		classes.setBounds(10, 100, 300, 850);
		attributes.setBounds(320, 100, 300, 260);
		methods.setBounds(630, 100, 360, 260);
		sousClasses.setBounds(320, 370, 300, 240);
		associations.setBounds(630, 370, 360, 240);
		details.setBounds(320, 630, 660, 300);
		
		this.getContentPane().add(classes);
		this.getContentPane().add(attributes);
		this.getContentPane().add(methods);
		this.getContentPane().add(sousClasses);
		this.getContentPane().add(associations);
		this.getContentPane().add(details);
		
		
		//set a background color
		containerNorth.setBackground(new Color(240,248,255));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
		this.setVisible(true);		
	}

	
	public String scanFile(File f) throws IOException
	{
		  BufferedReader br = new BufferedReader(new FileReader(f)); 
		  String inputFile = "";
		  String st; 
		  while ((st = br.readLine()) != null) 
		  {
			  inputFile += st+"\n"; 
		  } 
		  return inputFile;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == this.selectFile) 
		{
			File myFile = chooseFile();
			String fullPath = myFile.getAbsolutePath();
			file.setText(fullPath);	
			String toPars = "";
			try {
				toPars = scanFile(myFile);
				//System.out.println(toPars);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Model model = Parser.getModel(toPars);
			
			if(model==null) {
				
				JOptionPane panelErreur = new JOptionPane();
				panelErreur.showMessageDialog(null, "le fichier est corrompu", 
						"Erreur", JOptionPane.ERROR_MESSAGE);
			}
			else {
				myClasses = (ArrayList<Class_dec>) model.getList_dec();
				for(Class_dec c : myClasses) {
					modelClass.addElement(c.getIdentifier());
				}
				System.out.println(modelClass);
				//this.jClass = new JList(modelClass);
				classes.add(jClass);	
			}	
		}
		//if(arg0.getSource()==this.)
	}
	
	//open a window dialog
	public File chooseFile() 
	{
		FileSystemView openSystem = FileSystemView.getFileSystemView(); 
		File defaut = openSystem.getDefaultDirectory();  
		JFileChooser defautChooser = new JFileChooser(defaut); 
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			    "allowedFiles", "txt", "ucd");
		defautChooser.addChoosableFileFilter(filter);
		defautChooser.showOpenDialog(getParent());
		File fc = defautChooser.getSelectedFile();
		
		return fc;
	}

}


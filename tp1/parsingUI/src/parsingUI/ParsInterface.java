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
import packageModels.Modelable;
import packageModels.Operation;
import packageModels.ParsingError;




public class ParsInterface extends JFrame implements ActionListener{
	
	private JPanel containerNorth = new JPanel();
	private JTextField file = new JTextField();
	private JButton selectFile = new JButton("Load File");
	
	private PanelContainer classes = new PanelContainer("Classes");
	private PanelContainer attributes = new PanelContainer("Attributs");
	private PanelContainer methods = new PanelContainer("Methodes");
	private PanelContainer sousClasses = new PanelContainer("Sous Classes");
	private PanelContainer associations = new PanelContainer("Associations/Agregation");
	private PanelContainer details = new PanelContainer("Details");

	
	private DefaultListModel<String> modelClass = new DefaultListModel<String>();
	private DefaultListModel<String> modelAttr = new DefaultListModel<String>();
	private DefaultListModel<String> modelMeth = new DefaultListModel<String>();
	private DefaultListModel<String> modelSub = new DefaultListModel<String>();
	private DefaultListModel<String> modelAgr = new DefaultListModel<String>();
	private DefaultListModel<String> modelDet = new DefaultListModel<String>();

	private ArrayList<Class_dec> myClasses;
	private ArrayList<Data_Item> myAttributes;
	private ArrayList<Operation> myMethods;
	private ArrayList<String> mySubClass;
	
	JList<String> jClass = new JList<>(modelClass);
	JList<String> jAttributes= new JList<>(modelAttr);
	JList<String> jMethods= new JList<>(modelMeth);
	JList<String> jSubClass= new JList<>(modelSub);
	JList<String> jAggregations= new JList<>(modelAgr);
	JList<String> jDetails= new JList<>(modelDet);
	ArrayList<JList<String>> allList = new ArrayList<JList<String>>();
	ArrayList<DefaultListModel<String>> allModelList = new ArrayList<DefaultListModel<String>>();

	public ParsInterface(){	

		//basic appearance 
		this.setTitle("Parseur");
		this.setPreferredSize(new Dimension(1000, 1000));
		this.setSize(1000, 1000);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null); 
		this.getContentPane().setBackground(new Color(240,248,255));
		
		this.getContentPane().add(containerNorth);
		

		//event listener for the list of Classes
		jClass.addListSelectionListener(new ListSelectionListener(){
		    public void valueChanged(ListSelectionEvent e) {
		    	if(e.getValueIsAdjusting()) {
		    		return;
		    	} 
		    	
		    	int selectedIndex =jClass.getSelectedIndex();
		    	

		    	modelAttr.clear();
		    	Class_dec selectedClass =  myClasses.get(selectedIndex);
		    	myAttributes =(ArrayList<Data_Item>) selectedClass.getAttributes();
		    	
		    	
		    	for(int i=0; i< myAttributes.size(); i++)
		    		modelAttr.addElement(myAttributes.get(i).getType() + " : " + myAttributes.get(i).getIdentifier());
		    		
		    	
		    	modelMeth.clear();
		    	myMethods =(ArrayList<Operation>) selectedClass.getOperations();
		    	for(int i=0; i< myMethods.size(); i++)
		    		modelMeth.addElement(myMethods.get(i).getType() + ": " + myMethods.get(i).getIdentifier());
		    
		    	
		    	mySubClass =(ArrayList<String>) selectedClass.getSubclasses();
		    	modelSub.clear();
		    	if(mySubClass!=null) {
	    		
		    		for(int i=0; i< mySubClass.size(); i++) {
			    		modelSub.addElement(mySubClass.get(i));
			    	}
		    	}
		    	modelDet.clear();
		    	modelDet.addElement(selectedClass.getDetails());
		    	//System.out.println(selectedClass.getDetails().split("\n"));
//		    	JLabel detailsLabel = new JLabel();
//		    	detailsLabel.setText(selectedClass.getDetails());
//		    	details.add(detailsLabel);
		    	
		    	
//		    	my =(ArrayList<String>) selectedClass.getSubclasses();
//		    	for(int i=0; i< myAggregations.size(); i++) {
//		    		modelAgr.addElement(myAggregations.get(i));
//		    	}
//		    	for(int i=0; i< myAssociations.size(); i++) {
//		    		modelAgr.addElement(myAssociations.get(i));
//		    	}
		    }
		});
		
		initAllLists();
		initStyling();
		initFormating();
		
		this.getContentPane().add(classes);
		this.getContentPane().add(attributes);
		this.getContentPane().add(methods);
		this.getContentPane().add(sousClasses);
		this.getContentPane().add(associations);
		this.getContentPane().add(details);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
		this.setVisible(true);		
	}

	private void initAllLists() {
		allList.add(jClass);
		allList.add(jAttributes);
		allList.add(jAggregations);
		allList.add(jDetails);
		allList.add(jMethods);
		allList.add(jSubClass);
		
		allModelList.add(modelAgr);
		allModelList.add(modelAttr);
		allModelList.add(modelClass);
		allModelList.add(modelDet);
		allModelList.add(modelMeth);
		allModelList.add(modelSub);
	}
	
	private void clearAllList()
	{
		for(int i=0 ;i<allModelList.size();i++)
			allModelList.get(i).clear();
	}

	private void initFormating() {
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
		
	}

	private void initStyling() {
		
		// Set list styling
		jClass.setFont(new Font("Areal", Font.BOLD, 20));
		jAttributes.setFont(new Font("Areal", Font.PLAIN, 14));
		jMethods.setFont(new Font("Areal", Font.PLAIN, 14));
		jSubClass.setFont(new Font("Areal", Font.PLAIN, 14));
		jAggregations.setFont(new Font("Areal", Font.PLAIN, 14));
		jDetails.setFont(new Font("Areal", Font.PLAIN, 14));

		//set list container styling
		classes.setLayout(new BorderLayout());
		attributes.setLayout(new BorderLayout());
		methods.setLayout(new BorderLayout());
		sousClasses.setLayout(new BorderLayout());
		associations.setLayout(new BorderLayout());
		details.setLayout(new BorderLayout());
		
		//set a background color
		containerNorth.setBackground(new Color(240,248,255));
		
		// select zone
		selectFile.setPreferredSize(new Dimension(200, 50));
		selectFile.addActionListener(this);

		file.setBorder(BorderFactory.createLineBorder(Color.black));
		containerNorth.setLayout(new BorderLayout(10, 10));
		containerNorth.setPreferredSize(new Dimension(900, 100));
		containerNorth.add(selectFile, BorderLayout.WEST);
		containerNorth.add(file, BorderLayout.CENTER);
		containerNorth.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		containerNorth.setBounds(0, 0, 1000, 100);
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
			File inputFile = chooseFile();
			
			if(inputFile==null)
				return;
			
			String fullPath = inputFile.getAbsolutePath();
			file.setText(fullPath);	
			String toParse = "";
			
			try {
				toParse = scanFile(inputFile);
			} catch (IOException e) {
				clearAllList();
				e.printStackTrace();
			}
			
			System.out.println("une fois icitte");
			Modelable model = Parser.getModel(toParse);
			
			if(model instanceof ParsingError) 
			{
				
				new JOptionPane();
				JOptionPane.showMessageDialog(null, ((ParsingError)model).getMessage(), 
						"Erreur", JOptionPane.ERROR_MESSAGE);
				toParse="";
				model=null;
				inputFile=null;
				return;
			}
			else 
			{
				//load all classes by default
				myClasses = (ArrayList<Class_dec>) ((Model)model).getList_dec();
				modelClass.clear();
				for(Class_dec c : myClasses) 
				{
					modelClass.addElement(c.getIdentifier());
				}

				classes.add(jClass);	
			}	
		}
		
	}
	
	//open a window dialog
	public File chooseFile() 
	{
		FileSystemView openSystem = FileSystemView.getFileSystemView(); 
		File defaut = openSystem.getDefaultDirectory();  
		JFileChooser defaultChooser = new JFileChooser(); 
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Ucd Files", "txt", "text", "ucd",".ucd");
		defaultChooser.setAcceptAllFileFilterUsed(false);
		defaultChooser.setFileFilter(filter);
		defaultChooser.showOpenDialog(getParent());
		
		try {
			File fc = defaultChooser.getSelectedFile();
			return fc;
		}catch(Exception er)
		{
			
			er.printStackTrace();
		}
		clearAllList();
		return null;
	}

}


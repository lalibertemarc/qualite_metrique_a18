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




public class ParsInterface extends JFrame{
	
	static final long serialVersionUID = 1L;
	private JPanel containerNorth = new JPanel();
	private JTextField filePathTextField = new JTextField();
	private JButton selectFile = new JButton("Load File");
	
	private PanelContainer classesPanelContainer = new PanelContainer("Classes");
	private PanelContainer attributesPanelContainer = new PanelContainer("Attributs");
	private PanelContainer methodsPanelContainer = new PanelContainer("Methodes");
	private PanelContainer sousClassesPanelContainer = new PanelContainer("Sub Classes");
	private PanelContainer associationsPanelContainer = new PanelContainer("Associations/Agregation");
	private PanelContainer detailsPanelContainer = new PanelContainer("Details");
	
	private DefaultListModel<String> adatperClassDec = new DefaultListModel<String>();
	private DefaultListModel<String> adapterlAttributes = new DefaultListModel<String>();
	private DefaultListModel<String> adapterOperations = new DefaultListModel<String>();
	private DefaultListModel<String> adapterSubClasses = new DefaultListModel<String>();
	private DefaultListModel<String> adapterAggregetionsAssociations = new DefaultListModel<String>();
	private DefaultListModel<String> adapterDetails = new DefaultListModel<String>();

	JList<String> jListClass = new JList<>(adatperClassDec);
	JList<String> jListAttributes= new JList<>(adapterlAttributes);
	JList<String> jListMethods= new JList<>(adapterOperations);
	JList<String> jListSubClass= new JList<>(adapterSubClasses);
	JList<String> jListAggregations= new JList<>(adapterAggregetionsAssociations);
	JList<String> jListDetails= new JList<>(adapterDetails);
	
	private ArrayList<Class_dec> myClasses;
	private ArrayList<Data_Item> myAttributes;
	private ArrayList<Operation> myMethods;
	private ArrayList<String> mySubClass;
	
	Modelable mainModel;
	
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
		jListClass.addListSelectionListener(new ListSelectionListener(){
		    public void valueChanged(ListSelectionEvent e) {
		    	if(e.getValueIsAdjusting()) {
		    		return;
		    	} 
		    	
		    	int selectedIndex =jListClass.getSelectedIndex();
		    	

		    	adapterlAttributes.clear();
		    	Class_dec selectedClass =  myClasses.get(selectedIndex);
		    	myAttributes =(ArrayList<Data_Item>) selectedClass.getAttributes();
		    	
		    	
		    	for(int i=0; i< myAttributes.size(); i++)
		    		adapterlAttributes.addElement(myAttributes.get(i).getType() + " : " + myAttributes.get(i).getIdentifier());
		    		
		    	
		    	adapterOperations.clear();
		    	myMethods =(ArrayList<Operation>) selectedClass.getOperations();
		    	for(int i=0; i< myMethods.size(); i++)
		    		adapterOperations.addElement(myMethods.get(i).getType() + ": " + myMethods.get(i).getIdentifier());
		    
		    	
		    	mySubClass =(ArrayList<String>) selectedClass.getSubclasses();
		    	adapterSubClasses.clear();
		    	if(mySubClass!=null) {
	    		
		    		for(int i=0; i< mySubClass.size(); i++) {
			    		adapterSubClasses.addElement(mySubClass.get(i));
			    	}
		    	}
		    	adapterDetails.clear();
		    	adapterDetails.addElement(selectedClass.getDetails());
		    	
		    	initAggAssAdapter(selectedClass);
		    	
		    	
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
		
		this.getContentPane().add(classesPanelContainer);
		this.getContentPane().add(attributesPanelContainer);
		this.getContentPane().add(methodsPanelContainer);
		this.getContentPane().add(sousClassesPanelContainer);
		this.getContentPane().add(associationsPanelContainer);
		this.getContentPane().add(detailsPanelContainer);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
		this.setVisible(true);		
	}

	private void initAllLists() {
		allList.add(jListClass);
		allList.add(jListAttributes);
		allList.add(jListAggregations);
		allList.add(jListDetails);
		allList.add(jListMethods);
		allList.add(jListSubClass);
		
		allModelList.add(adapterAggregetionsAssociations);
		allModelList.add(adapterlAttributes);
		allModelList.add(adatperClassDec);
		allModelList.add(adapterDetails);
		allModelList.add(adapterOperations);
		allModelList.add(adapterSubClasses);
	}
	
	private void clearAllList()
	{
		for(int i=0 ;i<allModelList.size();i++)
			allModelList.get(i).clear();
	}

	private void initFormating() {
		classesPanelContainer.add(jListClass);
		attributesPanelContainer.add(jListAttributes);
		methodsPanelContainer.add(jListMethods);
		sousClassesPanelContainer.add(jListSubClass);
		associationsPanelContainer.add(jListAggregations);
		detailsPanelContainer.add(jListDetails);
		
		classesPanelContainer.setBounds(10, 100, 300, 850);
		attributesPanelContainer.setBounds(320, 100, 300, 260);
		methodsPanelContainer.setBounds(630, 100, 360, 260);
		sousClassesPanelContainer.setBounds(320, 370, 300, 240);
		associationsPanelContainer.setBounds(630, 370, 360, 240);
		detailsPanelContainer.setBounds(320, 630, 660, 300);
		
	}

	private void initStyling() {
		
		// Set list styling
		jListClass.setFont(new Font("Areal", Font.BOLD, 20));
		jListAttributes.setFont(new Font("Areal", Font.PLAIN, 14));
		jListMethods.setFont(new Font("Areal", Font.PLAIN, 14));
		jListSubClass.setFont(new Font("Areal", Font.PLAIN, 14));
		jListAggregations.setFont(new Font("Areal", Font.PLAIN, 14));
		jListDetails.setFont(new Font("Areal", Font.PLAIN, 14));

		//set list container styling
		classesPanelContainer.setLayout(new BorderLayout());
		attributesPanelContainer.setLayout(new BorderLayout());
		methodsPanelContainer.setLayout(new BorderLayout());
		sousClassesPanelContainer.setLayout(new BorderLayout());
		associationsPanelContainer.setLayout(new BorderLayout());
		detailsPanelContainer.setLayout(new BorderLayout());
		
		//set a background color
		containerNorth.setBackground(new Color(240,248,255));
		
		// select zone
		selectFile.setPreferredSize(new Dimension(200, 50));
		selectFile.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				File inputFile = chooseFile();
				
				if(inputFile==null)
					return;
				
				String fullPath = inputFile.getAbsolutePath();
				filePathTextField.setText(fullPath);	
				String toParse = "";
				
				try {
					toParse = scanFile(inputFile);
				} catch (IOException er) {
					clearAllList();
					er.printStackTrace();
				}
			
				Modelable model = Parser.getModel(toParse);
				
				System.out.println(model.getIdentifier());
				if(model instanceof ParsingError) 
				{
					new JOptionPane();
					JOptionPane.showMessageDialog(null, ((ParsingError)model).getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
					clearAllList();
				}
				else 
				{
					mainModel = model;
					//load all classes by default
					myClasses = (ArrayList<Class_dec>) ((Model)model).getList_dec();
					adatperClassDec.clear();
					for(Class_dec c : myClasses) 
					{
						adatperClassDec.addElement(c.getIdentifier());
					}

					classesPanelContainer.add(jListClass);	
				}	
			}
		});

		filePathTextField.setBorder(BorderFactory.createLineBorder(Color.black));
		containerNorth.setLayout(new BorderLayout(10, 10));
		containerNorth.setPreferredSize(new Dimension(900, 100));
		containerNorth.add(selectFile, BorderLayout.WEST);
		containerNorth.add(filePathTextField, BorderLayout.CENTER);
		containerNorth.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		containerNorth.setBounds(0, 0, 1000, 100);
	}

	private void initAggAssAdapter(Class_dec c) {
		
		String classId = c.getIdentifier();
		ArrayList<Aggregation> agg = (ArrayList<Aggregation>) ((Model)mainModel).getAggregations();
		ArrayList<Association> ass = (ArrayList<Association>) ((Model)mainModel).getAssociations();
		adapterAggregetionsAssociations.clear();
		
		for(int i=0;i<agg.size();i++) 
		{
			if(agg.get(i).getContainer().getClass_dec().equals(classId))
			{
				String partName = agg.get(i).getParts().getClass_dec();
				adapterAggregetionsAssociations.addElement("(A) P _ "+ partName);
			}
			if(agg.get(i).getParts().getClass_dec().equals(classId)) 
			{
				String roleName = agg.get(i).getContainer().getClass_dec();
				adapterAggregetionsAssociations.addElement("(A) C _ "+roleName);
			}
		}
//		
//		for(int i=0;i<ass.size();i++)
//		{
//			adapterAggregetionsAssociations.addElement(i+"");
//			Association assNow = ass.get(i);
//			if(classId.equals(assNow.getRole1().getClass_dec())); 
//			{
////				adapterAggregetionsAssociations.addElement(classId);
////				adapterAggregetionsAssociations.addElement(assNow.getRole1().getClass_dec());
//				String elementToAdd = "(R) " + 
//						classId 
//						+" "+ assNow.getIdentifier() 
//						+" "+  assNow.getRole2().getMultiplicity() 
//						+" "+ assNow.getRole2().getClass_dec();
//				
////				if(!adapterAggregetionsAssociations.contains(elementToAdd))
////				{
//					adapterAggregetionsAssociations.addElement(elementToAdd);
//				//}
//				
//						 
//			}
//			if(assNow.getRole2().getClass_dec().equals(classId)); 
//			{
//				adapterAggregetionsAssociations.addElement(
//						"(R) " + 
//						classId 
//						+" "+ assNow.getIdentifier() 
//						+" "+  assNow.getRole1().getMultiplicity() 
//						+" " + assNow.getRole1().getClass_dec() );
//			}
		
		
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
	
	
	//open a window dialog
	public File chooseFile() 
	{
		filePathTextField.setText("");
		JFileChooser defaultChooser = new JFileChooser(); 
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Ucd Files", "txt", "text", "ucd",".ucd");
		defaultChooser.setAcceptAllFileFilterUsed(false);
		defaultChooser.setFileFilter(filter);
		defaultChooser.showOpenDialog(getParent());
		File fc;
		
		try {
			fc = defaultChooser.getSelectedFile();
			return fc;
		}catch(Exception er)
		{
			er.printStackTrace();
		}
		clearAllList();
		return null;
	}

}


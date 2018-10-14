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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import testParsing.Parser;
import packageModels.Aggregation;
import packageModels.Association;
import packageModels.Class_dec;
import packageModels.Data_Item;
import packageModels.Model;
import packageModels.Modelable;
import packageModels.Operation;
import packageModels.ParsingError;


public class ParseInterface extends JFrame{
	
	static final long serialVersionUID = 1L;
	private JPanel containerNorth = new JPanel();
	private JTextField filePathTextField = new JTextField();
	private JButton selectFile = new JButton("Load File");
	private JButton calcMetric = new JButton("Calculate Metrics");
	
	private PanelContainer classesPanelContainer = new PanelContainer("Classes");
	private PanelContainer attributesPanelContainer = new PanelContainer("Attributs");
	private PanelContainer methodsPanelContainer = new PanelContainer("Methodes");
	private PanelContainer sousClassesPanelContainer = new PanelContainer("Sub Classes");
	private PanelContainer associationsPanelContainer = new PanelContainer("Associations/Relations");
	private PanelContainer detailsPanelContainer = new PanelContainer("Details");
	private PanelContainer metricsPanelContainer = new PanelContainer("Metrics");
	
	
	private DefaultListModel<String> adatperClassDec;
	private DefaultListModel<String> adapterAttributes ;
	private DefaultListModel<String> adapterOperations ;
	private DefaultListModel<String> adapterSubClasses;
	private DefaultListModel<String> adapterAggregetionsAssociations ;
	private DefaultListModel<String> adapterDetails ;
	private DefaultListModel<String> adapterMetrics ;
	
	
	JList<String> jListClass ;
	JList<String> jListAttributes;
	JList<String> jListMethods;
	JList<String> jListSubClass;
	JList<String> jListAggregations;
	JList<String> jListDetails;
	JList<String> jListMetrics;
	
	private ArrayList<Class_dec> myClasses;
	private ArrayList<Data_Item> myAttributes;
	private ArrayList<Operation> myMethods;
	private ArrayList<String> mySubClass;
	private ArrayList<String> myMetrics;
	Modelable mainModel;
	Class_dec selectedClass;
	
	ArrayList<JList<String>> allList = new ArrayList<JList<String>>();
	ArrayList<DefaultListModel<String>> allModelList = new ArrayList<DefaultListModel<String>>();

	public ParseInterface(){	

		//basic appearance 
		this.setTitle("Parseur");
		//this.setPreferredSize(new Dimension(800, 800));
		this.setSize(1050, 800);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null); 
		this.getContentPane().setBackground(new Color(240,248,255));
		this.getContentPane().add(containerNorth);
		
		initJList();
		
		selectFile.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				clearAllList();
				selectedClass=null;
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
					adapterDetails.clear();
					fillDetails(model.getDetails());
				}	
			}
		});

		calcMetric.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		jListClass.addListSelectionListener(new ListSelectionListener(){
		    public void valueChanged(ListSelectionEvent e) {
		    	if(e.getValueIsAdjusting()) {
		    		return;
		    	} 
		    	
		    	int selectedIndex =jListClass.getSelectedIndex();
		    	
		    	if(selectedIndex==-1)
		    		return;
		    	
		    	adapterAttributes.clear();
		    	selectedClass =  ((Model)mainModel).getList_dec().get(selectedIndex);
		    	jListAttributes.clearSelection();
		    	jListMethods.clearSelection();
		    	jListSubClass.clearSelection();
		    	jListDetails.clearSelection();
		    	myAttributes =(ArrayList<Data_Item>) selectedClass.getAttributes();
		    	
		    	for(int i=0; i< myAttributes.size(); i++)
		    		adapterAttributes.addElement(myAttributes.get(i).getIdentifier() + " : " + myAttributes.get(i).getType());
		    		
		    	adapterOperations.clear();
		    	myMethods =(ArrayList<Operation>) selectedClass.getOperations();
		    	for(int i=0; i< myMethods.size(); i++)
		    		adapterOperations.addElement(myMethods.get(i).getIdentifier() + ":" + myMethods.get(i).getType());
		    
		    	mySubClass =(ArrayList<String>) selectedClass.getSubclasses();
		    	adapterSubClasses.clear();
		    	if(mySubClass!=null) {
	    		
		    		for(int i=0; i< mySubClass.size(); i++) {
			    		adapterSubClasses.addElement(mySubClass.get(i));
			    	}
		    	}
		    	adapterDetails.clear();
		    	fillDetails(selectedClass.getDetails());
		    	initAggAssAdapter(selectedClass);
		    	
		    }	
		});
		
		jListMethods.addListSelectionListener( new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				int i = jListMethods.getSelectedIndex();
				
				if(i==-1)
					return;
				
				jListAttributes.clearSelection();
				jListAggregations.clearSelection();
				jListSubClass.clearSelection();
				fillDetails(selectedClass.getDetails());
				
				String element = adapterOperations.getElementAt(i);
				scanDetails(element);	
			}
			
		});

		jListAttributes.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				int i = jListAttributes.getSelectedIndex();				
				
				if(i==-1)
					return;
				
				jListMethods.clearSelection();
				jListAggregations.clearSelection();
				jListSubClass.clearSelection();
				fillDetails(selectedClass.getDetails());
				
				String element = adapterAttributes.getElementAt(i);
				scanDetails(element);
			}
		});
		
		jListSubClass.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				adapterDetails.clear();
				adapterDetails.addElement(((Model)mainModel).getSubClassDetails());
			}
			
		});
		
		jListAggregations.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int i = jListAggregations.getSelectedIndex();
				
				if(i==-1)
					return;
				
				jListMethods.clearSelection();
				jListAttributes.clearSelection();
				jListDetails.clearSelection();
				jListSubClass.clearSelection();
				String element = adapterAggregetionsAssociations.getElementAt(i);
				if(element.contains("(A)"))
				{
					adapterDetails.clear();
					//TODO find corresponding aggregation, not hardcoded first one
					String[] elementToScan = element.split(" _ ");
					
					fillDetails(((Model)mainModel).getAggregations().get(0).getDetails());
					scanDetails(elementToScan[1]);
				}
				
				else if(element.contains("(R)"))
				{
					adapterDetails.clear();
					String[] elementToScan = element.split(" ");
					String assoId = elementToScan[1];
					for(int j = 0;j<((Model)mainModel).getAssociations().size();j++)
					{
						if(((Model)mainModel).getAssociations().get(j).getIdentifier().equals(assoId)){
							fillDetails(((Model)mainModel).getAssociations().get(j).getDetails());
							scanDetails(selectedClass.getIdentifier());
						}
					}
				}
			}		
		});
		
		jListMetrics.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				
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
		this.getContentPane().add(metricsPanelContainer);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
		this.setVisible(true);		
	}

	private void initJList() {
		adatperClassDec = new DefaultListModel<String>();
		adapterAttributes = new DefaultListModel<String>();
		adapterOperations = new DefaultListModel<String>();
		adapterSubClasses = new DefaultListModel<String>();
		adapterAggregetionsAssociations = new DefaultListModel<String>();
		adapterDetails = new DefaultListModel<String>();
		adapterMetrics = new DefaultListModel<String>();

		jListClass = new JList<>(adatperClassDec);
		jListAttributes= new JList<>(adapterAttributes);
		jListMethods= new JList<>(adapterOperations);
		jListSubClass= new JList<>(adapterSubClasses);
		jListAggregations= new JList<>(adapterAggregetionsAssociations);
		jListDetails= new JList<>(adapterDetails);	
		jListMetrics = new JList<>(adapterMetrics);
	}

	private void initAllLists() {
		allList.add(jListClass);
		allList.add(jListAttributes);
		allList.add(jListAggregations);
		allList.add(jListDetails);
		allList.add(jListMethods);
		allList.add(jListSubClass);
		
		allModelList.add(adatperClassDec);
		allModelList.add(adapterAggregetionsAssociations);
		allModelList.add(adapterAttributes);	
		allModelList.add(adapterDetails);
		allModelList.add(adapterOperations);
		allModelList.add(adapterSubClasses);
	}
	
	private void clearAllList()
	{
		for(int i =0 ;i<allList.size();i++)
		{
			allList.get(i).clearSelection();
		}
		for(int i=0 ;i<allModelList.size();i++)
		{
			allModelList.get(i).clear();
		}
			
	}
	
	private void fillDetails(String d)
	{
		String[] details = d.split("\n");
		adapterDetails.clear();
		for(int i = 0;i<details.length;i++)
		{
			adapterDetails.addElement(details[i]);
		}
	}
	private void scanDetails(String el)
	{
		for(int i= 0;i<adapterDetails.size();i++)
		{
			if(adapterDetails.getElementAt(i).contains(el))
			{
				jListDetails.setSelectedIndex(i);
				return;
			}
		}
	}
	
	private void initFormating() {
		classesPanelContainer.add(jListClass);
		attributesPanelContainer.add(jListAttributes);
		methodsPanelContainer.add(jListMethods);
		sousClassesPanelContainer.add(jListSubClass);
		associationsPanelContainer.add(jListAggregations);
		detailsPanelContainer.add(new JScrollPane(jListDetails));
		metricsPanelContainer.add(jListMetrics);
		
		classesPanelContainer.setBounds(10, 100, 230, 650);
		attributesPanelContainer.setBounds(250, 100, 240, 210);
		methodsPanelContainer.setBounds(500, 100, 280, 210);
		sousClassesPanelContainer.setBounds(250, 320, 240, 210);
		associationsPanelContainer.setBounds(500, 320, 280, 210);
		detailsPanelContainer.setBounds(250, 540, 530, 210);
		metricsPanelContainer.setBounds(800, 100, 230, 650);
		
	}

	private void initStyling() {
		
		// Set list styling
		jListClass.setFont(new Font("Areal", Font.BOLD, 20));
		jListAttributes.setFont(new Font("Areal", Font.PLAIN, 14));
		jListMethods.setFont(new Font("Areal", Font.PLAIN, 14));
		jListSubClass.setFont(new Font("Areal", Font.PLAIN, 14));
		jListAggregations.setFont(new Font("Areal", Font.PLAIN, 14));
		jListDetails.setFont(new Font("Areal", Font.PLAIN, 14));
		jListMetrics.setFont(new Font("Areal", Font.PLAIN, 14));
		//set list container styling
		classesPanelContainer.setLayout(new BorderLayout());
		attributesPanelContainer.setLayout(new BorderLayout());
		methodsPanelContainer.setLayout(new BorderLayout());
		sousClassesPanelContainer.setLayout(new BorderLayout());
		associationsPanelContainer.setLayout(new BorderLayout());
		detailsPanelContainer.setLayout(new BorderLayout());
		metricsPanelContainer.setLayout(new BorderLayout());
		
		//set a background color
		containerNorth.setBackground(new Color(240,248,255));
		
		// select zone
		selectFile.setPreferredSize(new Dimension(220, 50));
		calcMetric.setPreferredSize(new Dimension(220, 50));
		
		filePathTextField.setBorder(BorderFactory.createLineBorder(Color.black));
		filePathTextField.setPreferredSize(new Dimension(600,50));
		containerNorth.setLayout(new BorderLayout(10, 10));
		containerNorth.add(selectFile, BorderLayout.WEST);
		containerNorth.add(calcMetric,BorderLayout.EAST);
		containerNorth.add(filePathTextField, BorderLayout.CENTER);
		containerNorth.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		containerNorth.setBounds(0, 0, 1030, 100);
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
		
		if(selectedClass.hasAssociation())
		{
			for(int i=0;i<selectedClass.getAssoList().size();i++)
			{
				adapterAggregetionsAssociations.addElement(c.getAssoList().get(i));
			}
		}
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
		  return inputFile.replace(" \n", "\n");
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


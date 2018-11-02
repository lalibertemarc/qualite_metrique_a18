package parsingUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import packageModels.Aggregation;
import packageModels.Association;
import packageModels.Class_dec;
import packageModels.Data_Item;
import packageModels.Model;
import packageModels.Modelable;
import packageModels.Operation;
import packageModels.ParsingError;
import testParsing.Parser;

/**
 * The Class ParseInterface.
 * Main Swing UI for the project.
 */
public class ParseInterface extends JFrame{
	
	/** The Constant serialVersionUID. */
	static final long serialVersionUID = 1L;
	
	/** The container north. */
	private JPanel containerNorth = new JPanel();
	
	/** The file path text field. */
	private JTextField filePathTextField = new JTextField();
	
	/** The select file. */
	private JButton selectFile = new JButton("Load File");
	
	/** The calc metric. */
	private JButton calcMetric = new JButton("Save CSV");
	
	/** The classes panel container. */
	private PanelContainer classesPanelContainer = new PanelContainer("Classes");
	
	/** The attributes panel container. */
	private PanelContainer attributesPanelContainer = new PanelContainer("Attributs");
	
	/** The methods panel container. */
	private PanelContainer methodsPanelContainer = new PanelContainer("Methodes");
	
	/** The sous classes panel container. */
	private PanelContainer sousClassesPanelContainer = new PanelContainer("Sub Classes");
	
	/** The associations panel container. */
	private PanelContainer associationsPanelContainer = new PanelContainer("Associations/Relations");
	
	/** The details panel container. */
	private PanelContainer detailsPanelContainer = new PanelContainer("Details");
	
	/** The metrics panel container. */
	private PanelContainer metricsPanelContainer = new PanelContainer("Metrics");
	
	
	/** The adatper for class dec. */
	private DefaultListModel<String> adatperClassDec;
	
	/** The adapter for attributes. */
	private DefaultListModel<String> adapterAttributes ;
	
	/** The adapter for operations. */
	private DefaultListModel<String> adapterOperations ;
	
	/** The adapter for sub classes. */
	private DefaultListModel<String> adapterSubClasses;
	
	/** The adapter for aggregetions//associations. */
	private DefaultListModel<String> adapterAggregetionsAssociations ;
	
	/** The adapter for details. */
	private DefaultListModel<String> adapterDetails ;
	
	/** The adapter for metrics. */
	private DefaultListModel<String> adapterMetrics ;
	
	
	/** The j list class. */
	JList<String> jListClass ;
	
	/** The j list attributes. */
	JList<String> jListAttributes;
	
	/** The j list methods. */
	JList<String> jListMethods;
	
	/** The j list sub class. */
	JList<String> jListSubClass;
	
	/** The j list aggregations. */
	JList<String> jListAggregations;
	
	/** The j list details. */
	JList<String> jListDetails;
	
	/** The j list metrics. */
	JList<String> jListMetrics;
	
	/** The my classes. */
	private ArrayList<Class_dec> myClasses;
	
	/** The my attributes. */
	private ArrayList<Data_Item> myAttributes;
	
	/** The my methods. */
	private ArrayList<Operation> myMethods;
	
	/** The my sub class. */
	private ArrayList<String> mySubClass;
	
	/** The my metrics. */
	private ArrayList<String> myMetrics;
	
	/** The main model. */
	Modelable mainModel;
	
	/** The selected class. */
	Class_dec selectedClass;
	
	/** The all list. */
	ArrayList<JList<String>> allList = new ArrayList<JList<String>>();
	
	/** The all model list. */
	ArrayList<DefaultListModel<String>> allModelList = new ArrayList<DefaultListModel<String>>();
	
	/** The mainFile used for UI */
	File mainFile;

	/**
	 * Instantiates a new Parser Interface.
	 */
	public ParseInterface(){	

		//basic appearance 
		this.setTitle("Parseur");
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
					((Model)mainModel).setSubClassesFlags();
					((Model)mainModel).setAllClassesTypes();
					((Model)mainModel).setOtherOperations();
					((Model)mainModel).SetAllSubClasses();
					((Model)mainModel).setAggregations();
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
				
				String csvFile = "Nom de Classe, ANA, NOM, NOA, ITC, ETC, CAC, DIT, CLD, NOC, NOD\n";
				csvFile += selectedClass.getIdentifier()+", ";
				csvFile += selectedClass.getAverageMethodArgumentCount()+", ";
				csvFile += selectedClass.getMethodCount()+", ";
				csvFile += selectedClass.getAttributeCount()+", ";
				csvFile += selectedClass.getModelableArgumentCount()+", ";
				csvFile += selectedClass.getTimesUsedAsArgument()+", ";
				csvFile += selectedClass.getAssociationCount()+", ";
				csvFile += selectedClass.getLongestPathLengthToRoot()+", ";
				csvFile += selectedClass.getLongestPathLengthtoLeaf()+", ";
				csvFile += selectedClass.getDirectSubClassCount()+", ";
				csvFile += selectedClass.getSubClassCount()+", ";
				
				//String fileName = selectedClass.getIdentifier()+"Metrics.csv";
				String fileName = mainFile.getParent()+ "/"+selectedClass.getIdentifier()+"Metrics.csv";
				
				BufferedWriter bw = null;
				FileWriter fw = null;

				try {

					String content = csvFile;
					fw = new FileWriter(fileName);
					bw = new BufferedWriter(fw);
					bw.write(content);

				} catch (IOException er) {

					er.printStackTrace();

				} finally {
					try {
						if (bw != null)
							bw.close();
						if (fw != null)
							fw.close();
						new JOptionPane();
						JOptionPane.showMessageDialog(null, "File saved in same directory as your ucd file", "Completed", JOptionPane.PLAIN_MESSAGE);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
				
				
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
		    	fillMetrics(selectedClass);
		    	
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
				String classId = selectedClass.getIdentifier();
				for(int i=0;i<((Model)mainModel).getSubClassDetails().size();i++)
				{
					String element = ((Model)mainModel).getSubClassDetails().get(i);
					String[] toScan = element.split("SUBCLASSES");
					if(toScan[0].contains(" "+classId))
					{
						adapterDetails.addElement(((Model)mainModel).getSubClassDetails().get(i));
					}
				}
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
					// elementToScan[1] will be Class_dec Id to find.
					String[] elementToScan = element.split(" _ ");
					String classIDtoFind = elementToScan[1];
					
					// aggId is the Container||Parts of the aggregation
					String aggId = elementToScan[0].split(" ")[1];
					
					int aggregationIndex = 0;
					//find corresponding aggregation in model
					for(int j=0;j<((Model)mainModel).getAggregations().size();j++)
					{
						if(aggId.equals("C") && ((Model)mainModel).getAggregations().get(j).getContainer().getClass_dec().equals(classIDtoFind))
						{
							aggregationIndex = j;
							break;
						}
						if(aggId.equals("P") && ((Model)mainModel).getAggregations().get(j).getParts().getClass_dec().equals(classIDtoFind))
						{
							aggregationIndex = j;
							break;
						}
					}
					/*
					 * I admit, this part is totally convoluted and ugly
					 * In the best case scenario, we should have put aggregations in class_dec attributes like get/set Aggregation
					 * and put a find corresponding agg method there.
					 * 
					 * we thought it was simpler to put the aggregation list in model because the aggregation has 2
					 * class_dec identifier. so it was to prevent duplicates,
					 * 
					 * so I guess it was either the parser or the UI part that was bound to be convoluted
					 * 
					 * Marc
					 * */
					
					fillDetails(((Model)mainModel).getAggregations().get(aggregationIndex).getDetails());
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
				adapterDetails.clear();
				int i = jListMetrics.getSelectedIndex();
				if(i==-1)
					return;
				String metric = adapterMetrics.getElementAt(i);
				
				if(metric.contains("ANA"))
					adapterDetails.addElement("<html>Nombre moyen d’arguments des méthodes locales pour la classe ci.");
				if(metric.contains("NOM"))
					adapterDetails.addElement("<html>Nombre de méthodes locales/héritées de la classe ci.");
				if(metric.contains("NOA"))
					adapterDetails.addElement("<html>Nombre d’attributs locaux/hérités de la classe ci.");
				if(metric.contains("ITC"))
					adapterDetails.addElement("<html>Nombre de fois où d’autres classes du diagramme apparaissent<br> comme types des arguments des méthodes de ci.</html>");
				if(metric.contains("ETC"))
					adapterDetails.addElement("<html>Nombre de fois où ci apparaît comme type des arguments dans<br> les méthodes des autres classes du diagramme.</html>");
				if(metric.contains("CAC"))
					adapterDetails.addElement("<html>Nombre d’associations (incluant les agrégations) locales/héritées<br> auxquelles participe une classe ci.</html>");
				if(metric.contains("DIT"))
					adapterDetails.addElement("<html>Taille du chemin le plus long reliant une classe ci à une classe<br> <b>racine</b> dans le graphe d’héritage.</html>");
				if(metric.contains("CLD"))
					adapterDetails.addElement("<html>Taille du chemin le plus long reliant une classe ci à une classe <br><b>feuille</b> dans le graphe d’héritage.</html>");
				if(metric.contains("NOC"))
					adapterDetails.addElement("Nombre de sous-classes directes de ci.");
				if(metric.contains("NOD"))
					adapterDetails.addElement("Nombre de sous-classes directes et indirectes de ci.");
				
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

	/**
	 * Inits the J lists.
	 */
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

	/**
	 * Inits the all lists.
	 */
	private void initAllLists() {
		allList.add(jListClass);
		allList.add(jListAttributes);
		allList.add(jListAggregations);
		allList.add(jListDetails);
		allList.add(jListMethods);
		allList.add(jListSubClass);
		allList.add(jListMetrics);
		
		allModelList.add(adatperClassDec);
		allModelList.add(adapterAggregetionsAssociations);
		allModelList.add(adapterAttributes);	
		allModelList.add(adapterDetails);
		allModelList.add(adapterOperations);
		allModelList.add(adapterSubClasses);
		allModelList.add(adapterMetrics);
	}

	/**
	 * Fill metrics.
	 *
	 * @param selectedClass the selected class
	 */
	private void fillMetrics(Class_dec selectedClass) 
	{
		adapterMetrics.clear();
		
		for(int i=0;i<selectedClass.getAllMetrics().size();i++)
		{
			adapterMetrics.addElement(selectedClass.getAllMetrics().get(i));
		}
		
	}
	
	/**
	 * Clear all lists.
	 */
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
	
	/**
	 * Fill details.
	 *
	 * @param d the d
	 */
	private void fillDetails(String d)
	{
		String[] details = d.split("\n");
		adapterDetails.clear();
		for(int i = 0;i<details.length;i++)
		{
			adapterDetails.addElement(details[i]);
		}
	}
	
	/**
	 * Scan details to set selected index at the correct line in details container.
	 *
	 * @param el the el
	 */
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
	
	/**
	 * Inits the formating.
	 */
	private void initFormating() {
		classesPanelContainer.add(jListClass);
		attributesPanelContainer.add(new JScrollPane(jListAttributes));
		methodsPanelContainer.add(new JScrollPane(jListMethods));
		sousClassesPanelContainer.add(jListSubClass);
		associationsPanelContainer.add(new JScrollPane(jListAggregations));
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

	/**
	 * Inits the styling.
	 */
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

	/**
	 * Inits the aggegation// association adapter.
	 *
	 * @param c the c
	 */
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
	
	
	/**
	 * Scan file.
	 *
	 * @param f the f
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String scanFile(File f) throws IOException
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
	
	/**
	 * Choose file.
	 *
	 * @return the file
	 */
	//open a window dialog
	public File chooseFile() 
	{
		filePathTextField.setText("");
		JFileChooser defaultChooser = new JFileChooser(); 
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Ucd Files", "txt", "text", "ucd",".ucd");
		defaultChooser.setAcceptAllFileFilterUsed(false);
		defaultChooser.setFileFilter(filter);
		defaultChooser.showOpenDialog(getParent());

		try {
			mainFile = defaultChooser.getSelectedFile();
			return mainFile;
		}catch(Exception er)
		{
			er.printStackTrace();
		}
		clearAllList();
		return null;
	}
	/**
	 * Choose path for csv filke.
	 *
	 * @return the file
	 */
	

}

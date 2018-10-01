package parsingUI;

import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


public class ParsInterface extends JFrame{
	
	private JPanel containerNorth = new JPanel();
	private JPanel containerGrid = new JPanel();
	private JPanel containerClasses = new JPanel();
	private JPanel container1 = new JPanel();
	private JPanel container2 = new JPanel();
	private JPanel container3 = new JPanel();
	private JTextField file = new JTextField();
	private PanelContainer classes = new PanelContainer("classes");
	private PanelContainer attributes = new PanelContainer("attributs");
	private PanelContainer methods = new PanelContainer("méthodes");
	private PanelContainer sousClasses = new PanelContainer("sous_Classes");
	private PanelContainer operations = new PanelContainer("Associations/Agrégation");
	private JButton selectFile = new JButton("Charger fichier");
	private JPanel container4 = new JPanel();
	private JTextArea details = new JTextArea();
	private Border border;
	private TitledBorder title;
	

	public ParsInterface(){	
		//basic appearance of a frame
		this.setTitle("Parseur");
		this.setSize(1000, 1000);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		 
		//set the 
		selectFile.setPreferredSize(new Dimension(200, 50));
		file.setBorder(BorderFactory.createLineBorder(Color.black));
		containerNorth.setLayout(new BorderLayout(10,10));
		containerNorth.setPreferredSize(new Dimension(900, 100));
		containerNorth.add(selectFile, BorderLayout.WEST);
		containerNorth.add(file);
		containerNorth.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.getContentPane().add(containerNorth, BorderLayout.NORTH);
		
		//the container of classes
		classes.setPreferredSize(new Dimension(300, 800));
		containerClasses.add(classes);
		containerClasses.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.getContentPane().add(containerClasses, BorderLayout.WEST); 
		
		//the containers off attributes and methods
		attributes.setPreferredSize(new Dimension(250, 250));
		methods.setPreferredSize(new Dimension(250, 250));
		container1.setLayout(new BorderLayout(50,50));
		container1.setBorder(BorderFactory.createEmptyBorder(25, 20, 5, 20));
		container1.add(attributes, BorderLayout.WEST);
		container1.add(methods);
		
		//containers of sub_classes on relations
		sousClasses.setPreferredSize(new Dimension(250, 200));
		operations.setPreferredSize(new Dimension(250, 200));
		container2.setLayout(new BorderLayout(50,50));
		container2.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		container2.add(sousClasses, BorderLayout.WEST);
		container2.add(operations);
		
		//container of details
		details.setPreferredSize(new Dimension(600, 200));
		border = BorderFactory.createLineBorder(Color.gray, 2);
		title = BorderFactory.createTitledBorder(
                border, "Details");
		title.setTitlePosition(TitledBorder.ABOVE_TOP);
		title.setTitleFont(new Font("tahoma",Font.BOLD,24));
		title.setTitleColor(Color.DARK_GRAY);
		container4.setBorder(title);
		container4.add(details);
		container3.setBorder(BorderFactory.createEmptyBorder(0, 20, 35, 20));
		container3.add(container4);
		
		//assemblage of containers
		containerGrid.setLayout(new BorderLayout(20,20));
		containerGrid.add(container1, BorderLayout.NORTH);
		containerGrid.add(container2, BorderLayout.CENTER);
		containerGrid.add(container3, BorderLayout.SOUTH);
		
		//set a background color
		containerNorth.setBackground(new Color(240,248,255));
		containerClasses.setBackground(new Color(240,248,255));
		container1.setBackground(new Color(240,248,255));
		container2.setBackground(new Color(240,248,255));
		container3.setBackground(new Color(240,248,255));
		container4.setBackground(new Color(240,248,255));
		containerGrid.setBackground(new Color(240,248,255));
		this.getContentPane().add(containerGrid, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
		this.setVisible(true);
	}
}


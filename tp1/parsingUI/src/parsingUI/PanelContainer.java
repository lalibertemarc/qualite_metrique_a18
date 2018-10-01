package parsingUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.*;
//import javax.swing.border.TitledBorder;

public class PanelContainer extends JPanel{
	private JList classes;
	private Border border;
	private TitledBorder title;
	
	public PanelContainer(String legend) {
		
		//border= BorderFactory.createLoweredBevelBorder();
		border = BorderFactory.createLineBorder(Color.darkGray, 2);
		title = BorderFactory.createTitledBorder(
                border, legend);
		title.setTitlePosition(TitledBorder.ABOVE_TOP);
		title.setTitleFont(new Font("tahoma",Font.BOLD,24));
		//title.setTitleColor(Color.yellow);
		
		this.setBackground(new Color(240,248,255));
		this.setBorder(title);
	}	
	
	@SuppressWarnings("unused")
	private JList<Object> getJList(){
		return this.classes;
	}
	@SuppressWarnings("unused")
	private void getJList(JList<Object> list) {
		this.classes = list;
	}
}

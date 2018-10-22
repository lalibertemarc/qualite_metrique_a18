package parsingUI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.*;


//import javax.swing.border.TitledBorder;

public class PanelContainer extends JPanel{
	
	private Border border;
	private TitledBorder title;	
	
	public PanelContainer(String legend) {
		border = BorderFactory.createLineBorder(Color.darkGray, 2);
		title = BorderFactory.createTitledBorder(
                border, legend);
		title.setTitlePosition(TitledBorder.ABOVE_TOP);
		title.setTitleFont(new Font("tahoma",Font.BOLD,24));
		this.setBorder(title);
		this.setBackground(new Color(240,248,255));
	}
}

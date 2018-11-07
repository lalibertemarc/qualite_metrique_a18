package parsingUI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.*;

/**
 * The Class PanelContainer.
 */
public class PanelContainer extends JPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The border. */
	private Border border;
	
	/** The title. */
	private TitledBorder title;	
	
	/**
	 * Instantiates a new panel container.
	 *
	 * @param legend the legend
	 */
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

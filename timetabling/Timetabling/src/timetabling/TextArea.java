package timetabling;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;



public class TextArea {
	
	public static JTextArea LOG = new JTextArea(100,200);
	
	 public void addTextArea(Container pane, int x, int y)
	    {
		 	GridBagConstraints c = new GridBagConstraints();
		    c.fill = GridBagConstraints.HORIZONTAL;
		    c.gridx = x;
		    c.gridy = y;
			c.gridwidth = 2;
			c.gridheight = 3;
			c.insets = new Insets(10,10,0,0);
			
			JPanel middlePanel = new JPanel ();
	        middlePanel.setBorder( new TitledBorder ( new EtchedBorder (), "Log" ) );
	        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
	        
	        
	        //Create the log first, because the action listeners
	        //need to refer to it.
	        
	        LOG  = new JTextArea(15,20);
	        LOG.setMargin(new Insets(5,5,5,5));
	        LOG.setEditable(false);
	        JScrollPane logScrollPane = new JScrollPane(LOG);
	        
	        logScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	        middlePanel.add ( logScrollPane );
			
		    pane.add(middlePanel, c);
	    }
}

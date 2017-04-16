package timetabling;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class Button {
	
	 public JButton addButton(Container pane, String description, int x, int y)
	    {
		 	JButton button = new JButton(description);
		 	GridBagConstraints c = new GridBagConstraints();
		    c.fill = GridBagConstraints.HORIZONTAL;
		    c.gridx = x;
		    c.gridy = y;
		    c.insets = new Insets(10,10,0,0);
		    pane.add(button, c);
		    
		    return button;
	    }
	 
	 public JButton addButton(Container pane, String description, int x, int y,int w)
	    {
		 	JButton button = new JButton(description);
		 	GridBagConstraints c = new GridBagConstraints();
		    c.fill = GridBagConstraints.HORIZONTAL;
		    c.gridx = x;
		    c.gridy = y;
		    c.gridwidth = y;
		    c.insets = new Insets(10,10,0,0);
		    pane.add(button, c);
		    
		    return button;
	    }
	 
	 public JButton addButton(Container pane, JButton button, int x, int y)
	    {
		 	GridBagConstraints c = new GridBagConstraints();
		    c.fill = GridBagConstraints.HORIZONTAL;
		    c.gridx = x;
		    c.gridy = y;
		    c.insets = new Insets(10,10,0,0);
		    pane.add(button, c); 
		    return button;
	    }
   
	 public void limparLog(JButton button, JTextArea log){  
		  button.addActionListener(new ActionListener() {

		      @Override
		      public void actionPerformed(ActionEvent e) {
		    	  TextArea.LOG.setText("");
		      }
	     
		  });
		  
	 }
//      public void addLinstener(JButton button){
//    	  
//    	  button.addActionListener(new ActionListener() {
//
//              @Override
//              public void actionPerformed(ActionEvent e) {
//            	
//             }
//          });	  
//    }
}
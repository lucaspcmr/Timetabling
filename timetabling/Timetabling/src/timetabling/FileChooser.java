package timetabling;
 import timetabling.Filetomem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;


public class FileChooser extends JPanel  {
	
	static private final String newline = "\n";
	JButton openButton, saveButton;
	JFileChooser fc;
	private static int i=0;
    public FileChooser() {
    	 

        //Create a file chooser
        fc = new JFileChooser();
 
        //Uncomment one of the following lines to try a different
        //file selection mode.  The first allows just directories
        //to be selected (and, at least in the Java look and feel,
        //shown).  The second allows both files and directories
        //to be selected.  If you leave these lines commented out,
        //then the default mode (FILES_ONLY) will be used.
        //
        //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
 
        //Create the open button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        openButton = new JButton("Open a File...",
                                 createImageIcon("images/open.gif"));
        openFile(openButton);
 
        //Create the save button.
        saveButton = new JButton("Save a File...",
                                 createImageIcon("images/save.gif"));
        saveFile(saveButton);
    }
 

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = FileChooser.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    public JButton getSalvar(){
    	return saveButton;
    }
    
    public JButton getCarregar(){
    	return openButton;
    }
 
  //Handle save button action.
    private void saveFile(JButton button){  
    	  button.addActionListener(new ActionListener() {

    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	        	 if (e.getSource() == saveButton) {
    	                int returnVal = fc.showSaveDialog(FileChooser.this);
    	                if (returnVal == JFileChooser.APPROVE_OPTION) {
    	                    File file = fc.getSelectedFile();
    	                    file = new File( file.toString() + ".txt" );
    	                    try {
    	                    	FileWriter fileW = new FileWriter (file,false);//arquivo para escrita
    	                    	BufferedWriter buffW = new BufferedWriter (fileW);
    	                    	
    	                    	//do something
    	                    	
    	                    	buffW.close ();
    	                    	
    	    					file.createNewFile();
    	    				} catch (IOException e1) {
    	    					// TODO Auto-generated catch block
    	    					e1.printStackTrace();
    	    				}
    	                    
    	                    //This is where a real application would save the file.
    	                    TextArea.LOG.append("Saving: " + file.getName() + "." + newline);
    	                } else {
    	                	TextArea.LOG.append("Save command cancelled by user." + newline);
    	                }}}
    	        	
    	    });
  	  }
        
    private void openFile(JButton button){  
    	  button.addActionListener(new ActionListener() {

    		  @Override
    	        public void actionPerformed(ActionEvent e){
           	  //Handle open button action.
              if (e.getSource() == openButton) {
                 
            	  int returnVal = fc.showOpenDialog(FileChooser.this);
                  
                  if (returnVal == JFileChooser.APPROVE_OPTION) {
                      File file = fc.getSelectedFile();
                      /*LEITURA*/
                     
					try {
						TextArea.LOG.append("Opening: " + file.getName() + "." + newline);
						Main.fileR = new FileReader(file);
                                                if(i==0){
                                                Filetomem.sort(Main.fileR);
                                                i++;
                                                TextArea.LOG.append("Abra o arquivo de restrições"+newline);
                                                }
                                                else{
						 
                                                 Main.fileR=new FileReader(file);
                                                 Filetomemrest.sort(Main.fileR);}
	                      
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}//arquivo para ser lido
					catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
      
                  } 
                  else {
                	  TextArea.LOG.append("Open command cancelled by user." + newline);
                  }
              	}              
    		  }  
    	  });
    }
}
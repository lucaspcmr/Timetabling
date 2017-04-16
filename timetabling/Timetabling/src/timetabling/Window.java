package timetabling;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JToolBar.Separator;




public class Window {
	
	 public static void addComponentsToPane(Container pane) {   	
	    	
		    //Janela
		    pane.setLayout(new GridBagLayout());
		    Label label = new Label(); 
		    Separator separator = new Separator();
		    Button button= new Button();
		    TextArea textArea = new TextArea();
		    Panel panel = new Panel();
		    FileChooser fc = new FileChooser();
		         
		    button.addButton(pane, fc.getSalvar(), 0,1);
//		    fc.salvarArquivo(fc.getSalvar());
		    
		    button.addButton(pane, fc.getCarregar(), 0,2);
//		    fc.openArquivo(fc.getCarregar());	    
		    textArea.addTextArea(pane,0, 3);
		    
		   JButton b = button.addButton(pane, "Limpar Log", 0,6);
		    button.limparLog(b,TextArea.LOG);
		    
		    }
}

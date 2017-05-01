package timetabling;
import algoritmoGenetico.AlgoritimoGenetico;
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
//                  fc.getSalvar().setEnabled(false);
//		    fc.salvarArquivo(fc.getSalvar());
		    
		    button.addButton(pane, fc.getCarregar(), 0,2);
//		    fc.openArquivo(fc.getCarregar());	    
		    textArea.addTextArea(pane,0, 3);
		    
		   //JButton b = button.addButton(pane, "Limpar Log", 0,6);
		   // button.limparLog(b,TextArea.LOG);
                    
                    JButton b = button.addButton(pane, "Open Horario .csv", 0,6);
                    fc.openFileHorario(b);
                    
                    b = button.addButton(pane, "show Horario .csv", 1,6);
                    button.addLinstenerHorarioCSV(b);
                    //fc.openFileHorario(b);
		    //button.limparLog(b,TextArea.LOG);
		    
                    //gerarHorario teste tabela
                    JButton horario =  new JButton("Horario");
                    button.addButton(pane, horario, 1,2);
                    button.addLinstenerHorario(horario);//modificar para gerar horario desejado
                    horario.setEnabled(false);
                    
                    JButton ag =  new JButton("AG");
                    button.addButton(pane, ag, 1,1);//iniciar AG
                    button.addLinstenerAG(ag,horario);
                   
                    
		    }
}

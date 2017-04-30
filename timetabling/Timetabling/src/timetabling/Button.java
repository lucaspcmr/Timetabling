package timetabling;

import algoritmoGenetico.AlgoritimoGenetico;
import algoritmoGenetico.DADOS;
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
      public void addLinstenerHorario(JButton button){
    	  
    	  button.addActionListener(new ActionListener() {

              @Override
              public void actionPerformed(ActionEvent e) {
            	TabelaHorario tabela= new TabelaHorario();
                tabela.display();
             }
          });	  
    }
      
    public void addLinstenerAG(JButton button,JButton horario){
    	  
    	  button.addActionListener(new ActionListener() {

              @Override
              public void actionPerformed(ActionEvent e) {
//               int isValid = 0; 
//               String numeroIndividuos = "0";
//               String geracoes = "0";
//               String mutacao = "0";
//               
//               while(isValid == 0){   
//                 numeroIndividuos = JOptionPane.showInputDialog("Digite o numero de Individuos");
//                   try {
//                       int value = Integer.parseInt(numeroIndividuos);
//                       if(value >=0){
//                        DADOS.INDIVIDUOS = value;
//                        isValid = 1;
//                       }
//                       else{
//                       JOptionPane.showMessageDialog(null,"Valor Invalido!!! Valor deve ser inteiro positivo.");
//                       }
//                       
//                   } catch (NumberFormatException erro) {
//                       JOptionPane.showMessageDialog(null,"Valor Invalido!!! Valor deve ser inteiro positivo.");
//                 }                    
//               }
//               isValid = 0;
//               while(isValid == 0){   
//                 geracoes = JOptionPane.showInputDialog("Digite o numero de Gerações");
//                   try {
//                       int value = Integer.parseInt(geracoes);
//                       if(value>=0){
//                        DADOS.GERACOES = value;
//                        isValid = 1;
//                       }
//                       else{
//                       JOptionPane.showMessageDialog(null,"Valor Invalido!!! Valor deve ser inteiro positivo.");
//                       }
//                   } catch (NumberFormatException erro) {
//                       JOptionPane.showMessageDialog(null,"Valor Invalido!!! Valor deve ser inteiro positivo.");
//                 }                    
//               }
//              
//               isValid = 0;
//               while(isValid == 0){   
//                  mutacao = JOptionPane.showInputDialog("Taxa de mutação (valor deve ser 0 a 100 indicando porcentagem)");
//                   try {
//                       int value = Integer.parseInt(mutacao);
//                       if(value>0 && value<=100){
//                        DADOS.MUTACAO = value;
//                        isValid = 1;
//                       }
//                       else{
//                       JOptionPane.showMessageDialog(null,"Valor Invalido!!!");
//                       }
//                   } catch (NumberFormatException erro) {
//                       JOptionPane.showMessageDialog(null,"Valor Invalido!!!");
//                 }                    
//               }
                 
               //  button.setEnabled(false);
                 AlgoritimoGenetico.startAG();
                 //button.setEnabled(true);
                 //horario.setEnabled(true);
             }
          });	  
    }
}
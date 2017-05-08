package timetabling;

import algoritmoGenetico.AlgoritmoGenetico;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

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
      
    public void addLinstenerHorarioCSV(JButton button){
    	  
    	  button.addActionListener(new ActionListener() {

              @Override
              public void actionPerformed(ActionEvent e) {
            	TabelaHorarioCSV tabela= new TabelaHorarioCSV();
                
                tabela.display();
             }
          });	  
    }
      
    public void addLinstenerAG(final JButton button,final JButton horario){
    	  
    	  button.addActionListener(new ActionListener() {

              @Override
              public void actionPerformed(ActionEvent e) {
               int isValid = 0; 
               String leitura = "";
               int numeroIndividuos = 0;
               int geracoes = 0;
               int taxaMutacao = 0;
               boolean elitismo = false;
               int taxaCrossover = 0;
               int crossover = 0;
               int selecao = 0;
               
//               while(isValid == 0){   
//                 leitura = JOptionPane.showInputDialog("Digite o TAMANHO DA POPULAÇÃO a ser gerado no Algoritmo Genético?\n Observações: (a) Deve ser um número natural, maior ou igual a 2, e multipo de 2.");
//                   try {
//                       int value = Integer.parseInt(leitura);
//                       if(value >=2 && value%2 == 0){  
//                           
//                            numeroIndividuos= value;
//                            isValid = 1;
//
//                       }
//                       else{
//                       JOptionPane.showMessageDialog(null,"Valor Invalido!!!");
//                       }
//                       
//                   } catch (NumberFormatException erro) {
//                       JOptionPane.showMessageDialog(null,"Valor Invalido!!!");
//                 }                    
//               }
//               isValid = 0;
//               while(isValid == 0){   
//                 leitura = JOptionPane.showInputDialog("Digite o NÚMERO MÁXIMO DE GERAÇÕES a ser aplicado ao Algoritmo Genético?");
//                   try {
//                       int value = Integer.parseInt(leitura);
//                       if(value>=0){
//                        geracoes = value;
//                        isValid = 1;
//                       }
//                       else{
//                       JOptionPane.showMessageDialog(null,"Valor Invalido!!!");
//                       }
//                   } catch (NumberFormatException erro) {
//                       JOptionPane.showMessageDialog(null,"Valor Invalido!!!");
//                 }                    
//               }
//               
//               isValid = 0;
//               while(isValid == 0){   
//                 leitura = JOptionPane.showInputDialog("Digite a TAXA DE RECOMBINAÇÃO (ou Crossover)\n(valor deve ser 0 a 100 indicando porcentagem).");
//                   try {
//                       int value = Integer.parseInt(leitura);
//                       if(value>=0){
//                        taxaCrossover = value;
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
//                  leitura = JOptionPane.showInputDialog("Digite a TAXA DE MUTAÇÃO (valor deve ser 0 a 100 indicando porcentagem)");
//                   try {
//                       int value = Integer.parseInt(leitura);
//                       if(value>0 && value<=100){
//                        taxaMutacao = value;
//                        isValid = 1;
//                       }
//                       else{
//                       JOptionPane.showMessageDialog(null,"Valor Invalido!!!");
//                       }
//                   } catch (NumberFormatException erro) {
//                       JOptionPane.showMessageDialog(null,"Valor Invalido!!!");
//                 }                    
//               }
//               
//               isValid = 0;
//                while(isValid == 0){   
//                  leitura = JOptionPane.showInputDialog("Deverá ser empregado ELITISMO (S = sim, N = não)? ");
//                   try {
//                       
//                       if(leitura.trim().equalsIgnoreCase("s") || leitura.trim().equalsIgnoreCase("n")){                     
//                           if(leitura.trim().equalsIgnoreCase("s")){
//                               elitismo = true;
//                           } 
//                               isValid = 1;
//                       }
//                       else{
//                        JOptionPane.showMessageDialog(null,"Valor Invalido!!!");
//                       }
//                    }
//                    catch (NumberFormatException erro) {
//                       JOptionPane.showMessageDialog(null,"Valor Invalido!!!");
//                 }                    
//               }
//                
//                isValid = 0;
//               while(isValid == 0){   
//                  leitura = JOptionPane.showInputDialog("Digite 1 para Seleção Roleta ou 2 para Seleção Torneio");
//                   try {
//                       int value = Integer.parseInt(leitura);
//                       if(value==1 || value==2){
//                        selecao = value;
//                        isValid = 1;
//                       }
//                       else{
//                       JOptionPane.showMessageDialog(null,"Valor Invalido!!!");
//                       }
//                   } catch (NumberFormatException erro) {
//                       JOptionPane.showMessageDialog(null,"Valor Invalido!!!");
//                 }                    
//               }
//               
//               isValid = 0;
//               while(isValid == 0){   
//                  leitura = JOptionPane.showInputDialog("Digite 1 para One Point Crossover, 2 para Two Point Crossover ou 3 para Uniform Crossover");
//                   try {
//                       int value = Integer.parseInt(leitura);
//                       if(value==1 || value==2 || value==3){
//                        crossover = value;
//                        isValid = 1;
//                       }
//                       else{
//                       JOptionPane.showMessageDialog(null,"Valor Invalido!!!");
//                       }
//                   } catch (NumberFormatException erro) {
//                       JOptionPane.showMessageDialog(null,"Valor Invalido!!!");
//                 }                    
//               }
               
                 
                 button.setEnabled(false);
                 AlgoritmoGenetico.startAG(elitismo, numeroIndividuos, geracoes, taxaMutacao, taxaCrossover, selecao, crossover);
                 button.setEnabled(true);
                 horario.setEnabled(true);
    
             }
          });	  
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetabling;
import algoritmoGenetico.AlgoritimoGenetico;
import algoritmoGenetico.Gene;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import objetos.Turma;
/**
 *
 * @author HOME
 */
public class TabelaHorario {
private static  int Linha;
private static  int Coluna;
private static List<Turma> turmas;
private static String table[][];

TabelaHorario(){
    Linha = 15;
    Coluna = 32;
    table = new String[Linha][Coluna];
    turmas = AlgoritimoGenetico.getTurmas();
}   
    public void display() {
        
       JPanel panel = new JPanel();
       //panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Table Title",TitledBorder.CENTER,TitledBorder.TOP));
       
        JFrame f = new JFrame("Horario");   
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel p = new JPanel();
        JScrollPane scrollPane = new JScrollPane(p);
        f.setPreferredSize(new Dimension(300, 400));
        f.add(scrollPane, BorderLayout.CENTER);
        
        addTable(p,0,0,"ECM","1 Periodo");
        addTable(p,10,0,"ECM","2 Periodo");
        addTable(p,20,0,"ECM","3 Periodo");
        addTable(p,30,0,"ECM","4 Periodo");
        addTable(p,40,0,"ECM","5 Periodo");
        addTable(p,50,0,"ECM","6 Periodo");
        addTable(p,60,0,"ECM","7 Periodo");
        addTable(p,70,0,"ECM","8 Periodo");
        addTable(p,80,0,"ECM","9 Periodo");
        addTable(p,90,0,"ECM","10 Periodo");
        addTable(p,100,0,"ECM","11 Periodo");
        addTable(p,120,0,"ECM","12 Periodo");
        
        addTable(p,0,10,"ECN","1 Periodo");
        addTable(p,10,10,"ECN","2 Periodo");
        addTable(p,20,10,"ECN","3 Periodo");
        addTable(p,30,10,"ECN","4 Periodo");
        addTable(p,40,10,"ECN","5 Periodo");
        addTable(p,50,10,"ECN","6 Periodo");
        addTable(p,60,10,"ECN","7 Periodo");
        addTable(p,70,10,"ECN","8 Periodo");
        addTable(p,80,10,"ECN","9 Periodo");
        addTable(p,90,10,"ECN","10 Periodo");
        addTable(p,100,10,"ECN","11 Periodo");
        addTable(p,120,10,"ECN","12 Periodo");
        
        addTable(p,0,20,"EE","1 Periodo");
        addTable(p,10,20,"EE","2 Periodo");
        addTable(p,20,20,"EE","3 Periodo");
        addTable(p,30,20,"EE","4 Periodo");
        addTable(p,40,20,"EE","5 Periodo");
        addTable(p,50,20,"EE","6 Periodo");
        addTable(p,60,20,"EE","7 Periodo");
        addTable(p,70,20,"EE","8 Periodo");
        addTable(p,80,20,"EE","9 Periodo");
        addTable(p,90,20,"EE","10 Periodo");
        
        addTable(p,0,30,"EM","1 Periodo");
        addTable(p,10,30,"EM","2 Periodo");
        addTable(p,20,30,"EM","3 Periodo");
        addTable(p,30,30,"EM","4 Periodo");
        addTable(p,40,30,"EM","5 Periodo");
        addTable(p,50,30,"EM","6 Periodo");
        addTable(p,60,30,"EM","7 Periodo");
        addTable(p,70,30,"EM","8 Periodo");
        addTable(p,80,30,"EM","9 Periodo");
        addTable(p,90,30,"EM","10 Periodo");
        
        
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
    }
      
    public static String[][] getData(){
        table = getTimeSlots(table,7,22);
                 
        return table;
    }

        public static String[] getColunas(){
            String col[] = new String[7];
            col[0] = "Timeslot";
            int j=0; 
                col[1] = "SEG";
                col[2] = "TER";
                col[3] = "QUA";
                col[4] = "QUI";
                col[5] = "SEX";
                col[6] = "SAB";
        return col;
    }
        
    public static String[][] getTimeSlots(String table[][],int inicio,int fim){
        int j = 0;
        for (int i = inicio; i < fim; i++) {
            table[j][0] = i+":59";
            j++;
        }
        return table;
    }
    
    public void addTable(JPanel pane,int x,int y,String curso,String periodo){
                    JPanel panel = new JPanel();
                    String tabela[][] = new String[15][7];
                    
                    String data[][] = getData();//modificar aqui
                    String col[] = getColunas();
                    DefaultTableModel model = new DefaultTableModel(data,col);
                    
                    JTable table = new JTable(model) {
            @Override
            public Dimension getPreferredScrollableViewportSize() {
                return new Dimension(1000, 240);
                     }
            };
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        panel.add(new JScrollPane(table));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),curso+" "+periodo,TitledBorder.CENTER,TitledBorder.TOP));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = x;
	c.gridy = y;
        c.gridwidth = 2;
	c.gridheight = 3;
	c.insets = new Insets(10,10,0,0);
	pane.add(panel, c);
    }
}

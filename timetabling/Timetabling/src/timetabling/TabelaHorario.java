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
import java.util.ArrayList;
import java.util.Hashtable;
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
private Hashtable <Integer, Integer[]> ponto;//hash map para retornar o timeslot na tabela de horarios

Object obj[];//lista horario de cada curso tamanho 4

TabelaHorario(){
    
    obj = new Object[4];//numero de horarios para os cursos como é 4 cursos
    obj[0] = new ArrayList<JTable>();//horarios da engenharia de computação matutino
    obj[1] = new ArrayList<JTable>();//horarios da engenharia de computação noturno
    obj[2] = new ArrayList<JTable>();//horarios engenharia eletrica
    obj[3] = new ArrayList<JTable>();//horario engenharia mecanica
    
    ponto =  new Hashtable <Integer,Integer[]>();
    gerarHashTimeslot(ponto);
    turmas = AlgoritimoGenetico.getTurmas();
    
//    Integer i [] = ponto.get(34);
//    System.out.println("L: "+i[0]+" C: "+i[1]);
}   
    public void display() {
        
       JPanel panel = new JPanel();
       
        JFrame f = new JFrame("Horario");   
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel p = new JPanel();
        JScrollPane scrollPane = new JScrollPane(p);
        f.setPreferredSize(new Dimension(300, 400));
        f.add(scrollPane, BorderLayout.CENTER);
        
        List<JTable> table = (List<JTable>) obj[0];
        table.add(addTable(p,0,0,"ECM","1 Periodo",0,0));
        table.add(addTable(p,10,0,"ECM","2 Periodo",0,1));
        table.add(addTable(p,20,0,"ECM","3 Periodo",0,2));
        table.add(addTable(p,30,0,"ECM","4 Periodo",0,3));
        table.add(addTable(p,40,0,"ECM","5 Periodo",0,4));
        table.add(addTable(p,50,0,"ECM","6 Periodo",0,5));
        table.add(addTable(p,60,0,"ECM","7 Periodo",0,6));
        table.add(addTable(p,70,0,"ECM","8 Periodo",0,7));
        table.add(addTable(p,80,0,"ECM","9 Periodo",0,8));
        table.add(addTable(p,90,0,"ECM","10 Periodo",0,9));
        
        table = (List<JTable>) obj[1];
        table.add(addTable(p,0,10,"ECN","1 Periodo",1,0));
        table.add(addTable(p,10,10,"ECN","2 Periodo",1,1));
        table.add(addTable(p,20,10,"ECN","3 Periodo",1,2));
        table.add(addTable(p,30,10,"ECN","4 Periodo",1,3));
        table.add(addTable(p,40,10,"ECN","5 Periodo",1,4));
        table.add(addTable(p,50,10,"ECN","6 Periodo",1,5));
        table.add(addTable(p,60,10,"ECN","7 Periodo",1,6));
        table.add(addTable(p,70,10,"ECN","8 Periodo",1,7));
        table.add(addTable(p,80,10,"ECN","9 Periodo",1,8));
        table.add(addTable(p,90,10,"ECN","10 Periodo",1,9));
        table.add(addTable(p,100,10,"ECN","11 Periodo",1,10));
        table.add(addTable(p,120,10,"ECN","12 Periodo",1,11));
        
        table = (List<JTable>) obj[2];
        table.add(addTable(p,0,20,"EE","1 Periodo",2,0));
        table.add(addTable(p,10,20,"EE","2 Periodo",2,1));
        table.add(addTable(p,20,20,"EE","3 Periodo",2,2));
        table.add(addTable(p,30,20,"EE","4 Periodo",2,3));
        table.add(addTable(p,40,20,"EE","5 Periodo",2,4));
        table.add(addTable(p,50,20,"EE","6 Periodo",2,5));
        table.add(addTable(p,60,20,"EE","7 Periodo",2,6));
        table.add(addTable(p,70,20,"EE","8 Periodo",2,7));
        table.add(addTable(p,80,20,"EE","9 Periodo",2,8));
        table.add(addTable(p,90,20,"EE","10 Periodo",2,9));
        
        table = (List<JTable>) obj[3];
        table.add(addTable(p,0,30,"EM","1 Periodo",3,0));
        table.add(addTable(p,10,30,"EM","2 Periodo",3,1));
        table.add(addTable(p,20,30,"EM","3 Periodo",3,2));
        table.add(addTable(p,30,30,"EM","4 Periodo",3,3));
        table.add(addTable(p,40,30,"EM","5 Periodo",3,4));
        table.add(addTable(p,50,30,"EM","6 Periodo",3,5));
        table.add(addTable(p,60,30,"EM","7 Periodo",3,6));
        table.add(addTable(p,70,30,"EM","8 Periodo",3,7));
        table.add(addTable(p,80,30,"EM","9 Periodo",3,8));
        table.add(addTable(p,90,30,"EM","10 Periodo",3,9));
        
        
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
    }
    
    //gerar a tabela em branco
    public static String[][] getData(){
       String table[][] = new String[15][7];
       table = getTimeSlots(table,7,22);
                 
        return table;
    }
    //gerar o titula da tabela
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
            table[j][0] =i+":00 - "+ i+":59";
            j++;
        }
        return table;
    }
    
    //adicionar uma tabela ao JFrame
    public JTable addTable(JPanel pane,int x,int y,String curso,String periodo,int _curso,int _periodo){
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
        
        return table;
    }
    
    //gerar o map para dado um codigo do timeslot mostrar qual slot no horario ele corresponde
    public void gerarHashTimeslot(Hashtable <Integer, Integer[]> ponto){
        int seg[] = {32,46};
        int ter[] = {56,70};
        int qua[] = {80,94};
        int qui[] = {104,118};
        int sex[] = {128,142};
        int sab[] = {152,166};
        int j=0;
        for (int i = seg[0]; i <= seg[1]; i++) {
            Integer p[] = new Integer[2];
            p[0] = new Integer(j);
            p[1] = new Integer(0);
            ponto.put(i, p);
            j++;
        }
        
        j=0;
        for (int i = ter[0]; i <= ter[1]; i++) {
            Integer p[] = new Integer[2];
            p[0] = new Integer(j);
            p[1] = new Integer(1);
            ponto.put(i, p);
            j++;
        }
        
        j=0;
        for (int i = qua[0]; i <= qua[1]; i++) {
            Integer p[] = new Integer[2];
            p[0] = new Integer(j);
            p[1] = new Integer(2);
            ponto.put(i, p);
            j++;
        }
        
        j=0;
        for (int i = qui[0]; i <= qui[1]; i++) {
            Integer p[] = new Integer[2];
            p[0] = new Integer(j);
            p[1] = new Integer(3);
            ponto.put(i, p);
            j++;
        }
        
        j=0;
        for (int i = sex[0]; i <= sex[1]; i++) {
            Integer p[] = new Integer[2];
            p[0] = new Integer(j);
            p[1] = new Integer(4);
            ponto.put(i, p);
            j++;
        }
        
        j=0;
        for (int i = sab[0]; i <= sab[1]; i++) {
            Integer p[] = new Integer[2];
            p[0] = new Integer(j);
            p[1] = new Integer(5);
            ponto.put(i, p);
            j++;
        }
    }
}

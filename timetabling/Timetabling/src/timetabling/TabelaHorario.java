/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetabling;
import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.Gene;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.table.TableModel;

import static objetos.Cursos.ENGENHARIA_COMPUTACAO_MATUTINO;
import static objetos.Cursos.ENGENHARIA_COMPUTACAO_NOTURNO;
import static objetos.Cursos.ENGENHARIA_ELETRICA;
import static objetos.Cursos.ENGENHARIA_MECANICA;
import objetos.Disciplinas;
import objetos.Docentes;
import objetos.Salas;

import objetos.Timeslot;
import objetos.Turma;

/**
 *
 * @author HOME
 */
public class TabelaHorario {
private static  int Linha;
private static  int Coluna;

private static Hashtable <Integer, Integer[]> ponto;//hash map para retornar o timeslot na tabela de horarios
private static Object obj[];//lista horario de cada curso tamanho 4

private static void initTabela(){
    
    obj = new Object[4];//numero de horarios para os cursos como é 4 cursos
    obj[0] = new ArrayList<>();//horarios da engenharia de computação matutino
    obj[1] = new ArrayList<>();//horarios da engenharia de computação noturno
    obj[2] = new ArrayList<>();//horarios engenharia eletrica
    obj[3] = new ArrayList<>();//horario engenharia mecanica
    
    ponto =  new Hashtable <>();
    gerarHashTimeslot(ponto);
   
//    Integer i [] = ponto.get(34);
//    System.out.println("L: "+i[0]+" C: "+i[1]);
}   
    public void display() {
       initTabela();
       JPanel panel = new JPanel();
       
        JFrame f = new JFrame("Horario");   
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel p = new JPanel();
        JScrollPane scrollPane = new JScrollPane(p);
        f.setPreferredSize(new Dimension(300, 400));
        f.add(scrollPane, BorderLayout.CENTER);
        
        List<JTable> table = (List<JTable>) obj[0];
        table.add(addTable(p,0,0,"ECM","1 Periodo",ENGENHARIA_COMPUTACAO_MATUTINO,1));
        table.add(addTable(p,10,0,"ECM","2 Periodo",ENGENHARIA_COMPUTACAO_MATUTINO,2));
        table.add(addTable(p,20,0,"ECM","3 Periodo",ENGENHARIA_COMPUTACAO_MATUTINO,3));
        table.add(addTable(p,30,0,"ECM","4 Periodo",ENGENHARIA_COMPUTACAO_MATUTINO,4));
        table.add(addTable(p,40,0,"ECM","5 Periodo",ENGENHARIA_COMPUTACAO_MATUTINO,5));
        table.add(addTable(p,50,0,"ECM","6 Periodo",ENGENHARIA_COMPUTACAO_MATUTINO,6));
        table.add(addTable(p,60,0,"ECM","7 Periodo",ENGENHARIA_COMPUTACAO_MATUTINO,7));
        table.add(addTable(p,70,0,"ECM","8 Periodo",ENGENHARIA_COMPUTACAO_MATUTINO,8));
        table.add(addTable(p,80,0,"ECM","9 Periodo",ENGENHARIA_COMPUTACAO_MATUTINO,9));
        table.add(addTable(p,90,0,"ECM","10 Periodo",ENGENHARIA_COMPUTACAO_MATUTINO,10));
        
        table = (List<JTable>) obj[1];
        table.add(addTable(p,0,10,"ECN","1 Periodo",ENGENHARIA_COMPUTACAO_NOTURNO,1));
        table.add(addTable(p,10,10,"ECN","2 Periodo",ENGENHARIA_COMPUTACAO_NOTURNO,2));
        table.add(addTable(p,20,10,"ECN","3 Periodo",ENGENHARIA_COMPUTACAO_NOTURNO,3));
        table.add(addTable(p,30,10,"ECN","4 Periodo",ENGENHARIA_COMPUTACAO_NOTURNO,4));
        table.add(addTable(p,40,10,"ECN","5 Periodo",ENGENHARIA_COMPUTACAO_NOTURNO,5));
        table.add(addTable(p,50,10,"ECN","6 Periodo",ENGENHARIA_COMPUTACAO_NOTURNO,6));
        table.add(addTable(p,60,10,"ECN","7 Periodo",ENGENHARIA_COMPUTACAO_NOTURNO,7));
        table.add(addTable(p,70,10,"ECN","8 Periodo",ENGENHARIA_COMPUTACAO_NOTURNO,8));
        table.add(addTable(p,80,10,"ECN","9 Periodo",ENGENHARIA_COMPUTACAO_NOTURNO,9));
        table.add(addTable(p,90,10,"ECN","10 Periodo",ENGENHARIA_COMPUTACAO_NOTURNO,10));
        table.add(addTable(p,100,10,"ECN","11 Periodo",ENGENHARIA_COMPUTACAO_NOTURNO,11));
        table.add(addTable(p,120,10,"ECN","12 Periodo",ENGENHARIA_COMPUTACAO_NOTURNO,12));
        
        table = (List<JTable>) obj[2];
        table.add(addTable(p,0,20,"EE","1 Periodo",ENGENHARIA_ELETRICA,1));
        table.add(addTable(p,10,20,"EE","2 Periodo",ENGENHARIA_ELETRICA,2));
        table.add(addTable(p,20,20,"EE","3 Periodo",ENGENHARIA_ELETRICA,3));
        table.add(addTable(p,30,20,"EE","4 Periodo",ENGENHARIA_ELETRICA,4));
        table.add(addTable(p,40,20,"EE","5 Periodo",ENGENHARIA_ELETRICA,5));
        table.add(addTable(p,50,20,"EE","6 Periodo",ENGENHARIA_ELETRICA,6));
        table.add(addTable(p,60,20,"EE","7 Periodo",ENGENHARIA_ELETRICA,7));
        table.add(addTable(p,70,20,"EE","8 Periodo",ENGENHARIA_ELETRICA,8));
        table.add(addTable(p,80,20,"EE","9 Periodo",ENGENHARIA_ELETRICA,9));
        table.add(addTable(p,90,20,"EE","10 Periodo",ENGENHARIA_ELETRICA,10));
        
        table = (List<JTable>) obj[3];
        table.add(addTable(p,0,30,"EM","1 Periodo",ENGENHARIA_MECANICA,1));
        table.add(addTable(p,10,30,"EM","2 Periodo",ENGENHARIA_MECANICA,2));
        table.add(addTable(p,20,30,"EM","3 Periodo",ENGENHARIA_MECANICA,3));
        table.add(addTable(p,30,30,"EM","4 Periodo",ENGENHARIA_MECANICA,4));
        table.add(addTable(p,40,30,"EM","5 Periodo",ENGENHARIA_MECANICA,5));
        table.add(addTable(p,50,30,"EM","6 Periodo",ENGENHARIA_MECANICA,6));
        table.add(addTable(p,60,30,"EM","7 Periodo",ENGENHARIA_MECANICA,7));
        table.add(addTable(p,70,30,"EM","8 Periodo",ENGENHARIA_MECANICA,8));
        table.add(addTable(p,80,30,"EM","9 Periodo",ENGENHARIA_MECANICA,9));
        table.add(addTable(p,90,30,"EM","10 Periodo",ENGENHARIA_MECANICA,10));
        
        
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
    }
    
    //gerar a tabela em branco
    public static String[][] getData(int curso,int periodo){
       String table[][] = new String[15][7];
       table = getTimeSlots(table,7,22);
       
       Gene genes[] = AlgoritmoGenetico.getCromossomo();
        
       for (int i = 0; i < genes.length; i++) {
            Gene gene = genes[i];
            int disciplina = gene.getDisciplina();
            int professor  = gene.getProfessor();
            int sala       = gene.getSala();
            int timeslot   = gene.getTimeslot();
            
            Integer key = Disciplinas.D.get(disciplina);
            String cursoDisciplina = Disciplinas.disciplinacurso.get(key);
            int codigoTimeslot = timeslot + 1;
            if(curso == Integer.valueOf(cursoDisciplina).intValue()){
                String periodoDisciplina = Disciplinas.disciplinaperiodo.get(key);
                if(periodo == Integer.valueOf(periodoDisciplina).intValue()){                 
                     if(ponto.get(codigoTimeslot) != null){
                        Integer p[] = ponto.get(new Integer(codigoTimeslot));
//                        System.out.println("Timeslot"+timeslot);
//                        System.out.println(" p0:"+p[0] + " p1:"+p[1]);
                        table[p[0]][p[1]] = "["+Disciplinas.disciplinadesc.get(key) +" | "+Docentes.docentenome.get((professor+1)+"")+" | "+Salas.saladesc.get((sala+1)+"")+ "]";
                    }
                   
                }
                
            }
  
        }                
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
                    
                    String data[][] = getData(_curso,_periodo);//modificar aqui
                    String col[] = getColunas();
                    DefaultTableModel model = new DefaultTableModel(data,col);
                    
                    JTable table = new JTable(model) {
            @Override
            public Dimension getPreferredScrollableViewportSize() {
                return new Dimension(1500, 240);
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
    public static void gerarHashTimeslot(Hashtable <Integer, Integer[]> ponto){
        int seg[] = {32,46};
        int ter[] = {56,70};
        int qua[] = {80,94};
        int qui[] = {104,118};
        int sex[] = {128,142};
        int sab[] = {152,156};
        int j=0;
        
        for (int i = seg[0]; i <= seg[1]; i++) {
            Integer p[] = new Integer[2];
            p[0] = new Integer(j);
            p[1] = new Integer(1);
            ponto.put(new Integer(i), p);
            j++;
        }
        
        j=0;
        for (int i = ter[0]; i <= ter[1]; i++) {
            Integer p[] = new Integer[2];
            p[0] = new Integer(j);
            p[1] = new Integer(2);
            ponto.put(new Integer(i), p);
            j++;
        }
        
        j=0;
        for (int i = qua[0]; i <= qua[1]; i++) {
            Integer p[] = new Integer[2];
            p[0] = new Integer(j);
            p[1] = new Integer(3);
            ponto.put(new Integer(i), p);
            j++;
        }
        
        j=0;
        for (int i = qui[0]; i <= qui[1]; i++) {
            Integer p[] = new Integer[2];
            p[0] = new Integer(j);
            p[1] = new Integer(4);
            ponto.put(new Integer(i), p);
            j++;
        }
        
        j=0;
        for (int i = sex[0]; i <= sex[1]; i++) {
            Integer p[] = new Integer[2];
            p[0] = new Integer(j);
            p[1] = new Integer(5);
            ponto.put(new Integer(i), p);
            j++;
        }
        
        j=0;
        for (int i = sab[0]; i <= sab[1]; i++) {
            Integer p[] = new Integer[2];
            p[0] = new Integer(j);
            p[1] = new Integer(6);
            ponto.put(new Integer(i), p);
            j++;
        }
    }
}

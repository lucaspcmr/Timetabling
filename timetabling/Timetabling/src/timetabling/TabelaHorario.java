/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetabling;
import algoritmoGenetico.AlgoritimoGenetico;
import algoritmoGenetico.Gene;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import objetos.Turma;
/**
 *
 * @author HOME
 */
public class TabelaHorario {
private static  int N =32;
private static  int C =23;
private static List<Turma> turmas;

TabelaHorario(){

    turmas = AlgoritimoGenetico.getTurmas();
    int vetor[] = getSizeTabela(turmas);
    N = vetor[0];
    C = vetor[1];
}
    public void display() {
        JFrame f = new JFrame("Horario");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TableModel model = new AbstractTableModel() {
            //private static final int N = 32;
            

            @Override
            public int getRowCount() {
                return N;
            }

            @Override
            public int getColumnCount() {
                return C;
            }

            @Override
            public Object getValueAt(int rowIndex, int colIndex) {
                //do something
                //String str = getValue();
                return "R" + rowIndex + ":C" + colIndex;
                //return str;
            }
        };
        
        JTable table = new JTable(model) {
            @Override
            public Dimension getPreferredScrollableViewportSize() {
                return new Dimension(320, 240);
            }
        };
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        f.add(new JScrollPane(table));
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
    }
    
    public String getValue(int rowIndex, int colIndex){
        //do something
        //dado uma linha e coluna identeificar onde
        //cada turma no array de turmas deve ser colocadas
        return "R" + rowIndex + ":C" + colIndex;
    }
    
    public int[] getSizeTabela(List<Turma> turmas){
        int vetor[] = new int[2];
        vetor[0] = 32;
        vetor[1] = 60;
        return  vetor;
    }

}

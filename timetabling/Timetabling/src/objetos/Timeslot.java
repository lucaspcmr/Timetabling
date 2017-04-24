package objetos;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import timetabling.FileChooser;
import timetabling.TextArea;

public class Timeslot {
 
    public static ArrayList<String> T;
    
    public static List<String> matutino ;
    public static List<String> vespertino;
    public static List<String> noturno;
    public static Hashtable <String,Integer> timeslotID;    
    
    public Timeslot(){
    T=new ArrayList<String>();//Array de timeslots 
    matutino = new ArrayList<String>();
    timeslotID = new Hashtable <String,Integer>();
    criaux();
    gerarTimeSlotID(timeslotID);
}
   void criaux() {
        int aux1;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                aux1 = (i + 1) * 24 + 8 + j;
                T.add(Integer.toString(aux1));
                
            }
            if (i != 5) {
                for (int k = 0; k < 8; k++) {
                    aux1 = (i + 1) * 24 + 15 + k;
                    T.add(Integer.toString(aux1));
                                    }
            }
        }
   System.out.println(T); 
   }
   
   public static void gerarTimeSlotID(Hashtable <String,Integer> timeslot){
       for (int i = 0; i < T.size(); i++) {
           timeslot.put(T.get(i), i);
       }
   }
   
//   //gerar o map para dado um codigo do timeslot mostrar qual slot no horario ele corresponde
//    public void gerarTimeslotMatutino( ){
//        int seg[] = {32,46};
//        int ter[] = {56,70};
//        int qua[] = {80,94};
//        int qui[] = {104,118};
//        int sex[] = {128,142};
//        int sab[] = {152,166};
//        
////        int j=0;
////        for (int i = seg[0]; i <= seg[1]; i++) {
////            Integer p[] = new Integer[2];
////            p[0] = new Integer(j);
////            p[1] = new Integer(0);
////            ponto.put(i, p);
////            j++;
////        }
////        
////        j=0;
////        for (int i = ter[0]; i <= ter[1]; i++) {
////            Integer p[] = new Integer[2];
////            p[0] = new Integer(j);
////            p[1] = new Integer(1);
////            ponto.put(i, p);
////            j++;
////        }
////        
////        j=0;
////        for (int i = qua[0]; i <= qua[1]; i++) {
////            Integer p[] = new Integer[2];
////            p[0] = new Integer(j);
////            p[1] = new Integer(2);
////            ponto.put(i, p);
////            j++;
////        }
////        
////        j=0;
////        for (int i = qui[0]; i <= qui[1]; i++) {
////            Integer p[] = new Integer[2];
////            p[0] = new Integer(j);
////            p[1] = new Integer(3);
////            ponto.put(i, p);
////            j++;
////        }
////        
////        j=0;
////        for (int i = sex[0]; i <= sex[1]; i++) {
////            Integer p[] = new Integer[2];
////            p[0] = new Integer(j);
////            p[1] = new Integer(4);
////            ponto.put(i, p);
////            j++;
////        }
////        
////        j=0;
////        for (int i = sab[0]; i <= sab[1]; i++) {
////            Integer p[] = new Integer[2];
////            p[0] = new Integer(j);
////            p[1] = new Integer(5);
////            ponto.put(i, p);
////            j++;
//        }
//    }
}


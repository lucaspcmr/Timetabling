package objetos;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import timetabling.FileChooser;
import timetabling.TextArea;

public class Timeslot {
 
    //Lista de timeslot seu codigo (seu id na mascara de solução é seu indice no List)
    public static ArrayList<String> T;
    
    //Listas de ids para os timeslots
    public static List<Integer> matutino ;
    public static List<Integer> vespertino;
    public static List<Integer> noturno;
    public static List<Integer> sabado;
    public static Hashtable <String,Integer> timeslotID;    
    
    
    public Timeslot(){
    T=new ArrayList<String>();//Array de timeslots valores reais sua posição no array e seu id de indentificação
    
    matutino = new ArrayList<Integer>();//id do timeslots
    vespertino = new ArrayList<Integer>();//ids do timeslots
    noturno = new ArrayList<Integer>();//id dos timeslots
    sabado = new ArrayList<Integer>();//id dos timeslots
    timeslotID = new Hashtable <String,Integer>();//id dos timeslots facilitar as buscas
    
    criaux();//Cria o array T
    
    gerarTimeSlotID(timeslotID);
    gerarTimeslotMatutino();
    gerarTimeslotMatutinoSabado();
    gerarTimeslotVespertino();
    gerarTimeslotNoturno();
    
    
    
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

   //gerar o map para dado um codigo do timeslot mostrar qual slot no horario ele corresponde
    public void gerarTimeslotMatutinoSabado( ){
        int sab[] = {152,156};
            
        for (int i = sab[0]; i <= sab[1]; i++) {
            sabado.add(timeslotID.get(i));
        }
    }
   
   //gerar o map para dado um codigo do timeslot mostrar qual slot no horario ele corresponde
    public void gerarTimeslotMatutino( ){
        int seg[] = {32,36};
        int ter[] = {56,60};
        int qua[] = {80,84};
        int qui[] = {104,108};
        int sex[] = {128,132};
        int sab[] = {152,156};
        
        for (int i = seg[0]; i <= seg[1]; i++) {
            matutino.add(timeslotID.get(i));
        }
        
        for (int i = ter[0]; i <= ter[1]; i++) {
          matutino.add(timeslotID.get(i));
        }
        
        
        for (int i = qua[0]; i <= qua[1]; i++) {
            matutino.add(timeslotID.get(i));
        }
        
        
        for (int i = qui[0]; i <= qui[1]; i++) {
            matutino.add(timeslotID.get(i));
        }
        
        
        for (int i = sex[0]; i <= sex[1]; i++) {
           matutino.add(timeslotID.get(i));
        }
        
        for (int i = sab[0]; i <= sab[1]; i++) {
            matutino.add(timeslotID.get(i));
        }
    }
    
    //gerar o map para dado um codigo do timeslot mostrar qual slot no horario ele corresponde
    public void gerarTimeslotVespertino( ){
        int seg[] = {38,42};
        int ter[] = {62,66};
        int qua[] = {86,90};
        int qui[] = {110,114};
        int sex[] = {134,138};
        int sab[] = {158,162};
        
        for (int i = seg[0]; i <= seg[1]; i++) {
            vespertino.add(timeslotID.get(i));
        }
        
        for (int i = ter[0]; i <= ter[1]; i++) {
          vespertino.add(timeslotID.get(i));
        }
        
        
        for (int i = qua[0]; i <= qua[1]; i++) {
            vespertino.add(timeslotID.get(i));
        }
        
        
        for (int i = qui[0]; i <= qui[1]; i++) {
            vespertino.add(timeslotID.get(i));
        }
        
        
        for (int i = sex[0]; i <= sex[1]; i++) {
           vespertino.add(timeslotID.get(i));
        }
        
        for (int i = sab[0]; i <= sab[1]; i++) {
            vespertino.add(timeslotID.get(i));
        }
    }
    
     //gerar o map para dado um codigo do timeslot mostrar qual slot no horario ele corresponde
    public void gerarTimeslotNoturno( ){
        int seg[] = {43,46};
        int ter[] = {67,70};
        int qua[] = {91,94};
        int qui[] = {115,118};
        int sex[] = {139,142};
        
        for (int i = seg[0]; i <= seg[1]; i++) {
            noturno.add(timeslotID.get(i));
        }
        
        for (int i = ter[0]; i <= ter[1]; i++) {
          noturno.add(timeslotID.get(i));
        }
        
        
        for (int i = qua[0]; i <= qua[1]; i++) {
            noturno.add(timeslotID.get(i));
        }
        
        
        for (int i = qui[0]; i <= qui[1]; i++) {
            noturno.add(timeslotID.get(i));
        }
        
        
        for (int i = sex[0]; i <= sex[1]; i++) {
           noturno.add(timeslotID.get(i));
        }
    }
    
     
}


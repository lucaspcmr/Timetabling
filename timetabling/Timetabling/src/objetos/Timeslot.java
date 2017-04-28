package objetos;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import timetabling.FileChooser;
import timetabling.TextArea;

//testes
public class Timeslot {
    /*
    matutino: das 07h as 12h, de segunda a sexta-feira, e aos s´abados das 07h as 12h. 
    Este turno possui codigo igual a 1(um);
    vespertino: das 13h as 18h, de segunda a sexta-feira, e aos s´abados das 07h as 12h. 
    Turno cujo codigo e igual a 2 dois);
    noturno: das 18h as 22h, de segunda a sexta-feira, e aos s´abados das 07h as 12h. 
    Turno cujo codigo e igual a 4(quatro).
    */
    
    // codigo do dia da semana ao qual o timeslot corresponde
    public static int DOMINGO = 1;
    public static int SEGUNDA = 2;
    public static int TERCA   = 3;
    public static int QUARTA  = 4;
    public static int QUINTA  = 5;
    public static int SEXTA   = 6;
    public static int SABADO  = 7;
    
    
    private static int numeroTimeslots = 168;//quantidade de timeslots total

    public static ArrayList<String> T;//Array de timeslots valores reais sua posição no array
    
    //Listas de codigos para os timeslots usado para as restrições dos cursos
    public static List<Integer> matutino ;//codigo dos timeslots
    public static List<Integer> vespertino;//codigo dos timeslots
    public static List<Integer> noturno;//codigo dos timeslots
    public static List<Integer> sabado;//codigo dos timeslots
    public static List<Integer> almoco;//codigo dos almoco
    
    public Timeslot(){
    T=new ArrayList<String>();
    
    matutino = new ArrayList<Integer>();
    vespertino = new ArrayList<Integer>();
    noturno = new ArrayList<Integer>();
    sabado = new ArrayList<Integer>();
    
    criaux();//Cria o array T
    
    //gerarTimeSlotID(timeslotID);
    gerarTimeslotMatutino();
    gerarTimeslotMatutinoSabado();
    gerarTimeslotVespertino();
    gerarTimeslotNoturno();
    gerarTimeslotAlmoco();
    
}
   void criaux() {
        int aux1;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                aux1 = (i + 1) * 24 + 8 + j;
                T.add(Integer.toString(aux1));
                
            }
            if (i != 5) {
                for (int k = 0; k < 9; k++) {
                    aux1 = (i + 1) * 24 + 14 + k;
                    T.add(Integer.toString(aux1));
                                    }
            }
        }
   //System.out.println(T); 
   }
   
   public static void gerarTimeSlotID(Hashtable <String,Integer> timeslot){
       for (int i = 0; i < T.size(); i++) {
           timeslot.put(T.get(i), i);
       }
   }

   //gerar timeslot para o periodo matutino de sabado
    public void gerarTimeslotMatutinoSabado( ){
        int sab[] = {152,156};
            
        for (int i = sab[0]; i <= sab[1]; i++) {
            sabado.add(i);
        }
    }
   
   //gerar timeslot para o periodo matutino
    public void gerarTimeslotMatutino( ){
        int seg[] = {32,36};
        int ter[] = {56,60};
        int qua[] = {80,84};
        int qui[] = {104,108};
        int sex[] = {128,132};
        int sab[] = {152,156};
        
        for (int i = seg[0]; i <= seg[1]; i++) {
            matutino.add(i);
        }
        
        for (int i = ter[0]; i <= ter[1]; i++) {
          matutino.add(i);
        }
        
        
        for (int i = qua[0]; i <= qua[1]; i++) {
            matutino.add(i);
        }
        
        
        for (int i = qui[0]; i <= qui[1]; i++) {
            matutino.add(i);
        }
        
        
        for (int i = sex[0]; i <= sex[1]; i++) {
           matutino.add(i);
        }
        
        for (int i = sab[0]; i <= sab[1]; i++) {
            matutino.add(i);
        }
    }
    
    //gerar timeslot para o periodo vespertino
    public void gerarTimeslotVespertino( ){
        int seg[] = {38,42};
        int ter[] = {62,66};
        int qua[] = {86,90};
        int qui[] = {110,114};
        int sex[] = {134,138};
        int sab[] = {158,162};
        
        for (int i = seg[0]; i <= seg[1]; i++) {
            vespertino.add(i);
        }
        
        for (int i = ter[0]; i <= ter[1]; i++) {
          vespertino.add(i);
        }
        
        
        for (int i = qua[0]; i <= qua[1]; i++) {
            vespertino.add(i);
        }
        
        
        for (int i = qui[0]; i <= qui[1]; i++) {
            vespertino.add(i);
        }
        
        
        for (int i = sex[0]; i <= sex[1]; i++) {
           vespertino.add(i);
        }
        
        for (int i = sab[0]; i <= sab[1]; i++) {
            vespertino.add(i);
        }
    }
    
     //gerar timeslot para o periodo noturno
    public void gerarTimeslotNoturno( ){
        int seg[] = {43,46};
        int ter[] = {67,70};
        int qua[] = {91,94};
        int qui[] = {115,118};
        int sex[] = {139,142};
        
        for (int i = seg[0]; i <= seg[1]; i++) {
            noturno.add(i);
        }
        
        for (int i = ter[0]; i <= ter[1]; i++) {
          noturno.add(i);
        }
        
        
        for (int i = qua[0]; i <= qua[1]; i++) {
            noturno.add(i);
        }
        
        
        for (int i = qui[0]; i <= qui[1]; i++) {
            noturno.add(i);
        }
        
        
        for (int i = sex[0]; i <= sex[1]; i++) {
           noturno.add(i);
        }
    }

    //gerar timeslot para o horario de almoço
    public void gerarTimeslotAlmoco( ){
        int seg[] = {37};
        int ter[] = {61};
        int qua[] = {85};
        int qui[] = {109};
        int sex[] = {133};
        
        for (int i = seg[0]; i <= seg[1]; i++) {
            almoco.add(i);
        }
        
        for (int i = ter[0]; i <= ter[1]; i++) {
          almoco.add(i);
        }
        
        
        for (int i = qua[0]; i <= qua[1]; i++) {
            almoco.add(i);
        }
        
        
        for (int i = qui[0]; i <= qui[1]; i++) {
            almoco.add(i);
        }
        
        
        for (int i = sex[0]; i <= sex[1]; i++) {
           almoco.add(i);
        }
    }
    /**
     * @return the numeroTimeslots
     */
    public static int getNumeroTimeslots() {
        return numeroTimeslots;
    }
    
     
}


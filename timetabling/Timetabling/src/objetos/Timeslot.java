package objetos;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Hashtable;
import java.util.List;


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
    
    //private static int numeroTimeslots = 168;//quantidade de timeslots total
    private static int numeroTimeslots = 156;//quantidade de timeslots total
    
    public static ArrayList<String> T;//Array de timeslots valores reais sua posição no array
    
    //Listas de codigos para os timeslots usado para as restrições dos cursos
    private static List<Integer> matutino ;//codigo dos timeslots
    private static List<Integer> vespertino;//codigo dos timeslots
    private static List<Integer> noturno;//codigo dos timeslots
    private static List<Integer> sabado;//codigo dos timeslots
    private static List<Integer> almoco;//codigo dos almoco
    private static List<Integer> inicio;//codigo inicio dos timeslots

    
    public Timeslot(){

    T=new ArrayList<String>();

    
    criaux();//Cria o array T
  
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
   
   public static void timeSlotsPeriodo(){

        matutino   = new ArrayList<Integer>();
        vespertino = new ArrayList<Integer>();
        noturno    = new ArrayList<Integer>();
        sabado     = new ArrayList<Integer>();
        almoco     = new ArrayList<Integer>();
        inicio     = new ArrayList<Integer>();
        
        gerarTimeslotMatutino();
        gerarTimeslotMatutinoSabado();
        gerarTimeslotVespertino();
        gerarTimeslotNoturno();
        gerarTimeslotAlmoco();
   }
   
   public static void gerarTimeSlotID(Hashtable <String,Integer> timeslot){
       for (int i = 0; i < T.size(); i++) {
           timeslot.put(T.get(i), i);
       }
   }

   //gerar timeslot para o periodo matutino de sabado
    public static void gerarTimeslotMatutinoSabado( ){
        int sab[] = {152,156};
            
        for (int i = sab[0]; i <= sab[1]; i++) {
            getSabado().add(i);
        }
    }
   
   //gerar timeslot para o periodo matutino adiciona o codigo
    public static void gerarTimeslotMatutino( ){
        int seg[] = {32,36};
        int ter[] = {56,60};
        int qua[] = {80,84};
        int qui[] = {104,108};
        int sex[] = {128,132};
        int sab[] = {152,156};
        
        for (int i = seg[0]; i <= seg[1]; i++) {
            getMatutino().add(i);
        }
        
        for (int i = ter[0]; i <= ter[1]; i++) {
            getMatutino().add(i);
        }
        
        
        for (int i = qua[0]; i <= qua[1]; i++) {
            getMatutino().add(i);
        }
        
        
        for (int i = qui[0]; i <= qui[1]; i++) {
            getMatutino().add(i);
        }
        
        
        for (int i = sex[0]; i <= sex[1]; i++) {
            getMatutino().add(i);
        }
        
        for (int i = sab[0]; i <= sab[1]; i++) {
            getMatutino().add(i);
        }
    }
    
    //gerar timeslot para o periodo vespertino adiciona o codigo
    public static void gerarTimeslotVespertino( ){
        int seg[] = {38,42};
        int ter[] = {62,66};
        int qua[] = {86,90};
        int qui[] = {110,114};
        int sex[] = {134,138};
        
        for (int i = seg[0]; i <= seg[1]; i++) {
            getVespertino().add(i);
        }
        
        for (int i = ter[0]; i <= ter[1]; i++) {
            getVespertino().add(i);
        }
        
        
        for (int i = qua[0]; i <= qua[1]; i++) {
            getVespertino().add(i);
        }
        
        
        for (int i = qui[0]; i <= qui[1]; i++) {
            getVespertino().add(i);
        }
        
        
        for (int i = sex[0]; i <= sex[1]; i++) {
            getVespertino().add(i);
        }
        
    }
    
     //gerar timeslot para o periodo noturno adiciona o codigo
    public static void gerarTimeslotNoturno( ){
        int seg[] = {43,46};
        int ter[] = {67,70};
        int qua[] = {91,94};
        int qui[] = {115,118};
        int sex[] = {139,142};
        
        for (int i = seg[0]; i <= seg[1]; i++) {
            getNoturno().add(i);
        }
        
        for (int i = ter[0]; i <= ter[1]; i++) {
            getNoturno().add(i);
        }
        
        
        for (int i = qua[0]; i <= qua[1]; i++) {
            getNoturno().add(i);
        }
        
        
        for (int i = qui[0]; i <= qui[1]; i++) {
            getNoturno().add(i);
        }
        
        
        for (int i = sex[0]; i <= sex[1]; i++) {
            getNoturno().add(i);
        }
    }

    //gerar timeslot para o horario de almoço adiciona o codigo
    public static void gerarTimeslotAlmoco( ){
        int seg[] = {37};
        int ter[] = {61};
        int qua[] = {85};
        int qui[] = {109};
        int sex[] = {133};
        
        for (int i = seg[0]; i <= seg[0]; i++) {
            getAlmoco().add(i);
        }
        
        for (int i = ter[0]; i <= ter[0]; i++) {
            getAlmoco().add(i);
        }
        
        
        for (int i = qua[0]; i <= qua[0]; i++) {
            getAlmoco().add(i);
        }
        
        
        for (int i = qui[0]; i <= qui[0]; i++) {
            getAlmoco().add(i);
        }
        
        
        for (int i = sex[0]; i <= sex[0]; i++) {
            getAlmoco().add(i);
        }
    }
    
        //gerar timeslot para o horario de almoço adiciona o codigo
    public static void gerarTimeslotInicio( ){
        int domSeg[] = {1,31};

        
        for (int i = domSeg[0]; i <= domSeg[1]; i++) {
            getInicio().add(i);
        }
    }
    /**
     * @return the numeroTimeslots
     */
    public static int getNumeroTimeslots() {
        return numeroTimeslots;
    }

    /**
     * @return the matutino
     */
    public static List<Integer> getMatutino() {
        return matutino;
    }

    /**
     * @return the vespertino
     */
    public static List<Integer> getVespertino() {
        return vespertino;
    }

    /**
     * @return the noturno
     */
    public static List<Integer> getNoturno() {
        return noturno;
    }

    /**
     * @return the sabado
     */
    public static List<Integer> getSabado() {
        return sabado;
    }

    /**
     * @return the almoco
     */
    public static List<Integer> getAlmoco() {
        return almoco;
    }

    /**
     * @return the inicio
     */
    public static List<Integer> getInicio() {
        return inicio;
    }
    
     
}


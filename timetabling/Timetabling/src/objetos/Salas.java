package objetos;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import timetabling.Filetomem;

public class Salas {
        //Tipos de sala da aplicação
        public static int SALA_COMUM                   = 1;
        public static int LABORATORIO_DE_INFORMATICA   = 2;
        public static int LABORATORIO_ESPECIFICO_EC    = 3;
        public static int LABORATORIO_ESPECIFICO_EE    = 4;
        public static int LABORATORIO_ESPECIFICO_EM    = 5;
        public static int LABORATORIO_ESPECIFICO_GERAL = 6;
        public static int LABORATORIO_OUTRO            = 7;
                
        private static int numeroSala;
	public static Hashtable<String, String> salasigla;
	public static Hashtable<String, String> saladesc;
	public static Hashtable<String, String> salatipo;
	public static Hashtable<String, String> salacap;

        public static ArrayList<String> S;//lista com as siglas das salas

	BufferedReader buffR=Filetomem.buffR;
        
        private static List<SalaRestricao> salaRestricao= new ArrayList<>();
        
        
public Salas(){
	salasigla=new Hashtable <>();
	saladesc=new Hashtable <>();
	salatipo=new Hashtable <>();
	salacap=new Hashtable <>();
        S=new ArrayList<>();
	try {
		buffR.readLine();
		buffR.readLine();
		String str,str1,str2,str3;
		do{
		str=buffR.readLine();
		if(str.charAt(0)!='/'){
		str1=str.substring(0,str.indexOf(","));
		str2=str.substring(str.indexOf(",")+1,str.length());
		str3=str2.substring(0,str2.indexOf(","));
		salasigla.put(str1, str3);
		str2=str2.substring(str2.indexOf(",")+1,str2.length());
		str3=str2.substring(0,str2.indexOf(","));
		saladesc.put(str1, str3);
		str2=str2.substring(str2.indexOf(",")+1,str2.length());
		str3=str2.substring(0,str2.indexOf(","));
		salatipo.put(str1, str3);
		str2=str2.substring(str2.indexOf(",")+1,str2.length());
		str3=str2.substring(0,str2.length());
		salacap.put(str1, str3);
		}
		}while(str.charAt(0)!='/');
		buffR.readLine();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        numeroSala = salasigla.size();
        criaux();
}
void criaux(){
            int quantidade;
            quantidade=salasigla.size();
            
            for(int i=1;i<quantidade;i++){
                S.add(salasigla.get(Integer.toString(i)));
                }
            
            }
   
        


    public static void gerarListaSalaRestricao(){
        //do something
    }

    public static List<SalaRestricao> getSalaRestricao(){
        return salaRestricao;
    }

    /**
     * @return the numeroSala
     */
    public static int getNumeroSala() {
        return numeroSala;
    }

}

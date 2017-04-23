package objetos;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import static objetos.Disciplinas.D;
import static objetos.Disciplinas.D2;
import static objetos.Disciplinas.disciplinaCHP;
import static objetos.Disciplinas.disciplinaCHT;
import static objetos.Disciplinas.disciplinatipop;
import static objetos.Disciplinas.disciplinatipot;
import static objetos.Disciplinas.quantidade;

import timetabling.Filetomem;

public class Salas {
	public static Hashtable<String, String> salasigla;
	public static Hashtable<String, String> saladesc;
	public static Hashtable<String, String> salatipo;
	public static Hashtable<String, String> salacap;
        public static ArrayList<String> S;
	BufferedReader buffR=Filetomem.buffR;
        
        private static List<SalaRestricao> salaRestricao= new ArrayList<SalaRestricao>();
        
        
public Salas(){
	salasigla=new Hashtable <String,String>();
	saladesc=new Hashtable <String,String>();
	salatipo=new Hashtable <String,String>();
	salacap=new Hashtable <String,String>();
        S=new ArrayList<String>();
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

}

package objetos;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import timetabling.Filetomem;

public class Disciplinas {
        public static Hashtable <Integer,String> disciplinacodigo;
	public static Hashtable <Integer,String> disciplinacurso;
	public static Hashtable <Integer,String> disciplinaperiodo;
	public static Hashtable <Integer,String> disciplinasigla;
	public static Hashtable <Integer,String> disciplinadesc;	
	public static Hashtable <Integer,String> disciplinaCHT;
	public static Hashtable <Integer,String> disciplinatipot;
	public static Hashtable <Integer,String> disciplinaCHP;
	public static Hashtable <Integer,String> disciplinatipop;
	BufferedReader buffR=Filetomem.buffR;
        public static int quantidade;
        public static ArrayList<Integer> D;//lista de disciplina
        public static ArrayList<Integer> D2;//tipo de sala cada disciplina
        
        //Lista de disciplinas com restriçoes e lista de disciplinas com
        private static List<DisciplinaRestricao> disciplinaRestricao= new ArrayList<DisciplinaRestricao>();
              
	public Disciplinas(){
                disciplinacodigo=new Hashtable <Integer,String>();
		disciplinacurso=new Hashtable <Integer,String>();
		disciplinaperiodo=new Hashtable <Integer,String>();
		disciplinasigla=new Hashtable <Integer,String>();
		disciplinadesc=new Hashtable <Integer,String>();
		disciplinaCHT=new Hashtable <Integer,String>();
		disciplinatipot=new Hashtable <Integer,String>();
		disciplinaCHP=new Hashtable <Integer,String>();
		disciplinatipop=new Hashtable <Integer,String>();
                D=new ArrayList<Integer>();//disciplina
                D2=new ArrayList<Integer>();//tipo da sala
		try {
                    int i=1;
			buffR.readLine();
			buffR.readLine();
			String str,str1,str2,str3;
			do{
			str=buffR.readLine();
			if(str.charAt(0)!='/'){
			str1=str.substring(0,str.indexOf(","));
			str2=str.substring(str.indexOf(",")+1,str.length());
			str3=str2.substring(0,str2.indexOf(","));
                        disciplinacodigo.put(i,str1);
			disciplinacurso.put(i, str3);
			str2=str2.substring(str2.indexOf(",")+1,str2.length());
			str3=str2.substring(0,str2.indexOf(","));
			disciplinaperiodo.put(i, str3);
			str2=str2.substring(str2.indexOf(",")+1,str2.length());
			str3=str2.substring(0,str2.indexOf(","));
			disciplinasigla.put(i, str3);
			str2=str2.substring(str2.indexOf(",")+1,str2.length());
			str3=str2.substring(0,str2.indexOf(","));
			disciplinadesc.put(i, str3);
			str2=str2.substring(str2.indexOf(",")+1,str2.length());
			str3=str2.substring(0,str2.indexOf(","));
			disciplinaCHT.put(i, str3);
			str2=str2.substring(str2.indexOf(",")+1,str2.length());
			str3=str2.substring(0,str2.indexOf(","));
			disciplinatipot.put(i, str3);
			str2=str2.substring(str2.indexOf(",")+1,str2.length());
			str3=str2.substring(0,str2.indexOf(","));
			disciplinaCHP.put(i, str3);
			str2=str2.substring(str2.indexOf(",")+1,str2.length());
			str3=str2.substring(0,str2.length());
			disciplinatipop.put(i, str3);
			}
                        i++;
			}while(str.charAt(0)!='/');
			buffR.readLine();
                        quantidade=disciplinacurso.size();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                
	criaux();
        }
        void criaux(){
            int carga;
            int salat;
            int salap;
            for(int i=1;i<quantidade;i++){
                carga=0;
                carga=Integer.valueOf(disciplinaCHT.get(i));
                salat=Integer.valueOf(disciplinatipot.get(i));
                carga=carga+Integer.valueOf(disciplinaCHP.get(i));
                salap=Integer.valueOf(disciplinatipop.get(i));
                carga=carga;
                for(int j=0;j<carga;j++){
                    D.add(i);
                    if(salat!=0)
                        D2.add(salat);
                    if(salap!=0)
                        D2.add(salap);
                }
            }
        }
        
    public static void gerarListaDisciplinaRestricao(){
        //do something
    }   
    
    public static List<DisciplinaRestricao> getDisciplinaRestricao(){
        return disciplinaRestricao;
    }
   
}

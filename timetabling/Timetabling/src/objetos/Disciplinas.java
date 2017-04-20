package objetos;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Hashtable;

import timetabling.Filetomem;

public class Disciplinas {
	public static Hashtable <String,String> disciplinacurso;
	public static Hashtable <String,String> disciplinaperiodo;
	public static Hashtable <String,String> disciplinasigla;
	public static Hashtable <String,String> disciplinadesc;	
	public static Hashtable <String,String> disciplinaCHT;
	public static Hashtable <String,String> disciplinatipot;
	public static Hashtable <String,String> disciplinaCHP;
	public static Hashtable <String,String> discplinatipop;
	BufferedReader buffR=Filetomem.buffR;
        int a;
	public Disciplinas(){
		disciplinacurso=new Hashtable <String,String>();
		disciplinaperiodo=new Hashtable <String,String>();
		disciplinasigla=new Hashtable <String,String>();
		disciplinadesc=new Hashtable <String,String>();
		disciplinaCHT=new Hashtable <String,String>();
		disciplinatipot=new Hashtable <String,String>();
		disciplinaCHP=new Hashtable <String,String>();
		discplinatipop=new Hashtable <String,String>();
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
			disciplinacurso.put(str1, str3);
			str2=str2.substring(str2.indexOf(",")+1,str2.length());
			str3=str2.substring(0,str2.indexOf(","));
			disciplinaperiodo.put(str1, str3);
			str2=str2.substring(str2.indexOf(",")+1,str2.length());
			str3=str2.substring(0,str2.indexOf(","));
			disciplinasigla.put(str1, str3);
			str2=str2.substring(str2.indexOf(",")+1,str2.length());
			str3=str2.substring(0,str2.indexOf(","));
			disciplinadesc.put(str1, str3);
			str2=str2.substring(str2.indexOf(",")+1,str2.length());
			str3=str2.substring(0,str2.indexOf(","));
			disciplinaCHT.put(str1, str3);
			str2=str2.substring(str2.indexOf(",")+1,str2.length());
			str3=str2.substring(0,str2.indexOf(","));
			disciplinatipot.put(str1, str3);
			str2=str2.substring(str2.indexOf(",")+1,str2.length());
			str3=str2.substring(0,str2.indexOf(","));
			disciplinaCHP.put(str1, str3);
			str2=str2.substring(str2.indexOf(",")+1,str2.length());
			str3=str2.substring(0,str2.length());
			discplinatipop.put(str1, str3);
			}
			}while(str.charAt(0)!='/');
			buffR.readLine();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package objetos;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Hashtable;

import timetabling.Filetomem;

public class Salas {
	public static Hashtable<String, String> salasigla;
	public static Hashtable<String, String> saladesc;
	public static Hashtable<String, String> salatipo;
	public static Hashtable<String, String> salacap;
	BufferedReader buffR=Filetomem.buffR;
public Salas(){
	salasigla=new Hashtable <String,String>();
	saladesc=new Hashtable <String,String>();
	salatipo=new Hashtable <String,String>();
	salacap=new Hashtable <String,String>();
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
	
}
}

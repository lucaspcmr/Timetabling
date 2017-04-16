package timetabling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Hashtable;

import objetos.Cursos;
import objetos.Disciplinas;
import objetos.Docentes;
import objetos.Estudantes;
import objetos.Salas;

public class Filetomem {
	public static BufferedReader buffR;
	public static Hashtable<String, String> hash;
public static void sort (FileReader fileR) {
	buffR = new BufferedReader (fileR);//arquivo buferizado
	
	 //do something
     try {
		while (buffR.ready())
		 {
			
		  String str = buffR.readLine();
		if(str.equals("TIMESLOT")){
			timeslot();
			
		}

		if(str.equals("CURSO"))
		new Cursos();

		if(str.equals("TIPO DE SALA"));
		
		if(str.equals("SALA"))
			new Salas();

		if(str.equals("DISCIPLINA"))
			new Disciplinas();

		if(str.equals("ESTUDANTE"))
			new Estudantes();

		if(str.equals("DOCENTE"))
			new Docentes();	
		}
		

		  
		 
	

		buffR.close ();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
}
public static void timeslot(){
	Hashtable <String, String> timeslot=new Hashtable <String,String>();
	try {
		buffR.readLine();
		buffR.readLine();
		String str;
		do{
		str=buffR.readLine();
		if(str.charAt(0)!='/'){
		timeslot.put(str.substring( 0, str.indexOf(",")), str.substring(str.indexOf(",")+1, str.indexOf(",")+2));
		}
		}while(str.charAt(0)!='/');System.out.println(str);
		buffR.readLine();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

}



	}





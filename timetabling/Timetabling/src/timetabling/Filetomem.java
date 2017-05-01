package timetabling;

import algoritmoGenetico.Gene;
import algoritmoGenetico.Individuo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import objetos.Cursos;
import objetos.Disciplinas;
import objetos.Docentes;
import objetos.Estudantes;
import objetos.Salas;
import objetos.Turma;

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

public static void sortHorario (FileReader fileR) {
	buffR = new BufferedReader (fileR);//arquivo buferizado
	
     try {
        while (buffR.ready()) {
		//System.out.println("asdfasdf");	
		String str = buffR.readLine();
		if(str.equals("HORARIO")){
			horario();
			break;
		}
		
	}
        buffR.close ();
     }catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
}

public static void horario(){
	List<Turma> turmas = new ArrayList<Turma>();
	try {
		buffR.readLine();
		String str;
                
                
		do{
		
                str=buffR.readLine(); 
		if(str.charAt(0)!='/'){	
                   String [] aux =  str.split(",");
                   Turma turma = new Turma();
                   
                  
                   String disciplina = aux[0];
                   String timeslot   = aux[1];
                   String professor  = aux[2];
                   String sala       = aux[3];
                   String curso      = aux[4];
                   String periodo    = aux[5];
                   
                   
                   turma.setDisciplina(disciplina);
                   turma.setTimeslot(Integer.valueOf(timeslot));
                   turma.setProfessor(professor);
                   turma.setSala(sala);
                   turma.setCurso(Integer.valueOf(curso));
                   turma.setPeriodo(periodo);
                   turmas.add(turma);
                   
                   
                   //System.out.println(str);
		}
		}while(str.charAt(0)!='/');System.out.println(str);
		buffR.readLine();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        TabelaHorarioCSV.turmas = turmas;
}

}




	





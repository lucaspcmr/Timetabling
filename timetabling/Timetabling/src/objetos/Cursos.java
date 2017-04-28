package objetos;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Hashtable;

import timetabling.Filetomem;

public class Cursos {
        //codigo dos quatro cursos que a aplicação deve manipular
        public static int ENGENHARIA_COMPUTACAO_MATUTINO = 1; //diurno manha e a tarde
        public static int ENGENHARIA_COMPUTACAO_NOTURNO  = 2;
        public static int ENGENHARIA_ELETRICA            = 3;
        public static int ENGENHARIA_MECANICA            = 4;
        
        // codigo Turnos que identifica em quais turnos do dia o curso oferece disciplinas
        public static int MATUTINO                    = 1;
        public static int VESPERTINO                  = 2;
        public static int MATUTINO_VESPERTINO         = 3;
        public static int NOTURNO                     = 4;
        public static int MATUTINO_NOTURNO            = 5;
        public static int VESPERTINO_NOTURNO          = 6;
        public static int MATUTINO_VESPERTINO_NOTURNO = 7;
    
        private static int numeroCursos;
        
	public static Hashtable<String, String> cursodesc;
	public static Hashtable<String, String> cursoperiodos;
	public static Hashtable<String, String> cursoturnos;
	BufferedReader buffR=Filetomem.buffR;
	public Cursos(){
		cursodesc=new Hashtable <String,String>();
		cursoperiodos=new Hashtable <String,String>();
		cursoturnos=new Hashtable <String,String>();
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
			cursodesc.put(str1, str3);
			str2=str2.substring(str2.indexOf(",")+1,str2.length());
			str2=str2.substring(str2.indexOf(",")+1,str2.length());
			str3=str2.substring(0,str2.indexOf(","));
			cursoperiodos.put(str1, str3);
			str2=str2.substring(str2.indexOf(",")+1,str2.length());
			str3=str2.substring(0,1);
			cursoturnos.put(str1, str3);
			}
			}while(str.charAt(0)!='/');
			buffR.readLine();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                numeroCursos = cursodesc.size();
	}

    /**
     * @return the numeroCursos
     */
    public static int getNumeroCursos() {
        return numeroCursos;
    }
	
}

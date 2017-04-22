/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetabling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.AlunoDisciplina;
import objetos.DisciplinaRestricao;
import objetos.DocenteRestricao;
import objetos.SalaRestricao;
import static timetabling.Filetomem.buffR;

/**
 *
 * @author Lucas
 */
public class Filetomemrest {

    public static List<DisciplinaRestricao> discirest;
    public static List<SalaRestricao> salarest;
    public static List<DocenteRestricao> docrest;

    public static void sort(FileReader fileR) {
        buffR = new BufferedReader(fileR);//arquivo buferizado
        discirest = new ArrayList<DisciplinaRestricao>();
        docrest=new ArrayList<DocenteRestricao>();
        salarest=new ArrayList<SalaRestricao>();
        //do something
        try {
            while (buffR.ready()) {
                String str = buffR.readLine();
                if (str.equals("DISCIPLINA")) {
                    disciplina();
                }
                if (str.equals("DOCENTE")) {
                    Docente();
                }
                if (str.equals("SALA")) {
                    sala();
                }

            }

            buffR.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static void disciplina() {
        String str, str1, str2, str3;
        

        int i = 0,j=0;

        try {
            str=buffR.readLine();
            while((str.charAt(0)) == '/')
                str=buffR.readLine();
            
            do {
                int virg = 0;
                if ((str.charAt(0)) != '/') {
                    discirest.add(new DisciplinaRestricao());
                    str1 = str.substring(0, str.indexOf(","));
                    str2 = str.substring(str.indexOf(",") + 1, str.length());
                    str3 = str2.substring(0, str2.indexOf(","));
                    discirest.get(j).setDisciplina(Integer.parseInt(str1));
                    discirest.get(j).setTimeslot(Integer.parseInt(str3));
                    str2 = str2.substring(str2.indexOf(",") + 1, str2.length());
                    for (i = 0; i < str2.length(); i++) {
                        if ((str2.charAt(i)) == ',') {
                            virg++;
                        }
                    }
                    for (i = 0; i <= virg; i++) {
                        if (i == virg) {
                            str3 = str2.substring(0, str2.length());
                        } else {
                            str3 = str2.substring(0, str2.indexOf(","));
                            str2 = str2.substring(str2.indexOf(",") + 1, str2.length());
                        }
                        discirest.get(j).setTimeslot(Integer.parseInt(str3));
                    }
                     System.out.println(discirest.get(j).getDisciplina());
                System.out.println(discirest.get(j).getTimeslot());
                }
                j++;
                str=buffR.readLine();
            } while (str.charAt(0) != '/');
        } catch (IOException ex) {
            Logger.getLogger(Filetomemrest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void Docente() {
        String str, str1, str2, str3;
        

        int i=0,j = 0;

        try {
             str=buffR.readLine();
            while((str.charAt(0)) == '/')
                str=buffR.readLine();
            do {
                int virg = 0;
                
                if ((str.charAt(0)) != '/') {
                    docrest.add(new DocenteRestricao());
                    str1 = str.substring(0, str.indexOf(","));
                    str2 = str.substring(str.indexOf(",") + 1, str.length());
                    str3 = str2.substring(0, str2.indexOf(","));
                    docrest.get(j).setDocente(Integer.parseInt(str1));
                    docrest.get(j).setTimeslot(Integer.parseInt(str3));
                    str2 = str2.substring(str2.indexOf(",") + 1, str2.length());
                    for (i = 0; i < str2.length(); i++) {
                        if ((str2.charAt(i)) == ',') {
                            virg++;
                        }
                    }
                    for (i = 0; i <= virg; i++) {
                        if (i == virg) {
                            str3 = str2.substring(0, str2.length());
                        } else {
                            str3 = str2.substring(0, str2.indexOf(","));
                            str2 = str2.substring(str2.indexOf(",") + 1, str2.length());
                        }
                        docrest.get(j).setTimeslot(Integer.parseInt(str3));
                        
                    }
               
                j++;
                }
               str=buffR.readLine();
            } while (str.charAt(0) != '/');
        } catch (IOException ex) {
            Logger.getLogger(Filetomemrest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void sala() {
        String str, str1, str2, str3;
        

        int i = 0,j=0;

        try {
            str=buffR.readLine();
            while((str.charAt(0)) == '/')
                str=buffR.readLine();
            do {
                int virg = 0;
                
                if ((str.charAt(0)) != '/') {
                    salarest.add(new SalaRestricao());
                    str1 = str.substring(0, str.indexOf(","));
                    str2 = str.substring(str.indexOf(",") + 1, str.length());
                    str3 = str2.substring(0, str2.indexOf(","));
                    salarest.get(j).setSala(Integer.parseInt(str1));
                    salarest.get(j).setTimeslot(Integer.parseInt(str3));
                    str2 = str2.substring(str2.indexOf(",") + 1, str2.length());
                    for (i = 0; i < str2.length(); i++) {
                        if ((str2.charAt(i)) == ',') {
                            virg++;
                        }
                    }
                    for (i = 0; i <= virg; i++) {
                        if (i == virg) {
                            str3 = str2.substring(0, str2.length());
                        } else {
                            str3 = str2.substring(0, str2.indexOf(","));
                            str2 = str2.substring(str2.indexOf(",") + 1, str2.length());
                        }
                        salarest.get(j).setTimeslot(Integer.parseInt(str3));
                    }
                     System.out.println(salarest.get(j).getSala());
                System.out.println(salarest.get(j).getTimeslot());
                }
                j++;
                str=buffR.readLine();
            } while (str.charAt(0) != '/');
        } catch (IOException ex) {
            Logger.getLogger(Filetomemrest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

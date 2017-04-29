package objetos;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import timetabling.Filetomem;

public class Estudantes {
    //maximo de disciplinas por por aluno
    private static int MAXIMO_DISCIPLINAS_ALUNOS = 10;
    //numero de alunos
    private static int numeroAlunos;
    
    public static Hashtable<String, String> estudantenome;
    public static Hashtable<String, String> estudantedisc1;
    public static Hashtable<String, String> estudantedisc2;
    public static Hashtable<String, String> estudantedisc3;
    public static Hashtable<String, String> estudantedisc4;
    public static Hashtable<String, String> estudantedisc5;
    public static Hashtable<String, String> estudantedisc6;
    public static Hashtable<String, String> estudantedisc7;
    public static Hashtable<String, String> estudantedisc8;
    public static Hashtable<String, String> estudantedisc9;
    public static Hashtable<String, String> estudantedisc10;
    BufferedReader buffR = Filetomem.buffR;

    public static ArrayList<String> E;//lista com os nomes dos alunos

    //Lista Alunos Disciplina
    private static List<AlunoDisciplina> listAlunoDisciplina = new ArrayList<AlunoDisciplina>();
    

    public Estudantes() {
        estudantenome = new Hashtable<String, String>();
        estudantedisc1 = new Hashtable<String, String>();
        estudantedisc2 = new Hashtable<String, String>();
        estudantedisc3 = new Hashtable<String, String>();
        estudantedisc4 = new Hashtable<String, String>();
        estudantedisc5 = new Hashtable<String, String>();
        estudantedisc6 = new Hashtable<String, String>();
        estudantedisc7 = new Hashtable<String, String>();
        estudantedisc8 = new Hashtable<String, String>();
        estudantedisc9 = new Hashtable<String, String>();
        estudantedisc10 = new Hashtable<String, String>();
        E = new ArrayList<String>();
        try {
            buffR.readLine();
            buffR.readLine();
            String str, str1, str2, str3;
            int virg = 0, i, j = 0;
            do {
                virg = 0;
                str = buffR.readLine();
                if (str.charAt(0) != '/') {
                    listAlunoDisciplina.add(new AlunoDisciplina());
                    str1 = str.substring(0, str.indexOf(","));
                    E.add(str1);
                    str2 = str.substring(str.indexOf(",") + 1, str.length());
                    str3 = str2.substring(0, str2.indexOf(","));
                    estudantenome.put(str1, str3);
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
                        if (!str3.equals("")) {
                            switch (i) {
                                case 0:
                                    estudantedisc1.put(str1, str3);
                                    break;
                                case 1:
                                    estudantedisc2.put(str1, str3);
                                    break;
                                case 2:
                                    estudantedisc3.put(str1, str3);
                                    break;
                                case 3:
                                    estudantedisc4.put(str1, str3);
                                    break;
                                case 4:
                                    estudantedisc5.put(str1, str3);
                                    break;
                                case 5:
                                    estudantedisc6.put(str1, str3);
                                    break;
                                case 6:
                                    estudantedisc7.put(str1, str3);
                                    break;
                                case 7:
                                    estudantedisc8.put(str1, str3);
                                    break;
                                case 8:
                                    estudantedisc9.put(str1, str3);
                                    break;
                                case 9:
                                    estudantedisc10.put(str1, str3);
                                    break;
                            }
                            listAlunoDisciplina.get(j).setAluno(Integer.parseInt(str1));
                            listAlunoDisciplina.get(j).setDisciplina(Integer.parseInt(str3));
                        }

                    }

                    j++;
                }
            } while (str.charAt(0) != '/');
            buffR.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        numeroAlunos = estudantenome.size();
//        criaux();
    }

//    void criaux() {
//        int quantidade;
//        quantidade = estudantenome.size();
//
//        for (int i = 1; i < quantidade; i++) {
//            E.add(estudantenome.get(Integer.toString(i)));
//        }
//
//    }

    public static List<AlunoDisciplina> getAlunosDisciplinas() {

        return listAlunoDisciplina;
    }

    public static int getNumeroAlunos() {

        return numeroAlunos;
    }
}

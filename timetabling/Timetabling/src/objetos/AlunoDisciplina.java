/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.util.ArrayList;

/**
 *
 * @author HugoEduardo
 */
public class AlunoDisciplina {
    private int aluno;  //codigo  do aluno
    private ArrayList<Integer> disciplina=new ArrayList<Integer>();//codigo da disciplina

    /**
     * @return the aluno
     */
    public int getAluno() {
        return aluno;
    }

    /**
     * @param aluno the aluno to set
     */
    public void setAluno(int aluno) {
        this.aluno = aluno;
    }

    /**
     * @return the disciplina
     */
    public ArrayList getDisciplina() {
        return disciplina;
    }

    /**
     * @param disciplina the disciplina to set
     */
    public void setDisciplina(int disciplina) {
        this.disciplina.add(disciplina);
    }
    
    
    
}

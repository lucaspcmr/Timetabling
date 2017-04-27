/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HugoEduardo
 */
public class AlunoDisciplinaID {
    private int aluno;  //indice do vetor do aluno
    private List<Integer> disciplina;//indice do vetor da disciplina

    AlunoDisciplinaID(){
        disciplina = new ArrayList<Integer>();
    }
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
    public List<Integer> getDisciplina() {
        return disciplina;
    }

    /**
     * @param disciplina the disciplina to set
     */
    public void setDisciplina(List<Integer> disciplina) {
        this.disciplina = disciplina;
    }
    
    
    
}

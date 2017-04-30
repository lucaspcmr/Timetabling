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
 * @author HOME
 */
public class DisciplinaAluno {
    private int disciplina;
    private int timeslot;
    private List<Integer> alunos;
    
    public DisciplinaAluno(){
        alunos = new ArrayList<Integer>();
    }  

    /**
     * @return the disciplina
     */
    public int getDisciplina() {
        return disciplina;
    }

    /**
     * @param disciplina the disciplina to set
     */
    public void setDisciplina(int disciplina) {
        this.disciplina = disciplina;
    }

    /**
     * @return the alunos
     */
    public List<Integer> getAlunos() {
        return alunos;
    }

    /**
     * @param alunos the alunos to set
     */
    public void setAlunos(List<Integer> alunos) {
        this.alunos = alunos;
    }

    /**
     * @return the timeslot
     */
    public int getTimeslot() {
        return timeslot;
    }

    /**
     * @param timeslot the timeslot to set
     */
    public void setTimeslot(int timeslot) {
        this.timeslot = timeslot;
    }
    
    
}

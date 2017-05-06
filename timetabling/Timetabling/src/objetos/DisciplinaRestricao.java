/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.util.ArrayList;

/**
 *
 * @author Home
 */
public class DisciplinaRestricao {
    private int disciplina;//codigo da disciplina
    private ArrayList<Integer> timeslot;  //codigo do timeslot
public DisciplinaRestricao(){
    timeslot=new ArrayList<>();
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
     * @return the timeslot
     */
    public ArrayList getTimeslot() {
        return timeslot;
    }

    /**
     * @param timeslot the timeslot to set
     */
    public void setTimeslot(int timeslot) {
        this.timeslot.add(timeslot);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author HugoEduardo
 */
public class DisciplinaRestricaoID {
    private int disciplina;//indice do vetor disciplina
    private int timeslot;  //indice do timeslot

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

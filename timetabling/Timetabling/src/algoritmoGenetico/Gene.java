/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmoGenetico;

/**
 *
 * @author Home
 */
public class Gene implements Cloneable{
    private int sala;//id da sala
    private int professor;//id do professor
    private int timeslot;//id do time slot
    private int disciplina;//id da disciplina

    /**
     * @return the sala
     */
    public int getSala() {
        return sala;
    }

    /**
     * @param sala the sala to set
     */
    public void setSala(int sala) {
        this.sala = sala;
    }

    /**
     * @return the professor
     */
    public int getProfessor() {
        return professor;
    }

    /**
     * @param professor the professor to set
     */
    public void setProfessor(int professor) {
        this.professor = professor;
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
    
    @Override
    public Gene clone() throws CloneNotSupportedException{
        return (Gene) super.clone();
    }
    
}

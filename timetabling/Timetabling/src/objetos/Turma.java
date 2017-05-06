/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;



/**
 *
 * @author HOME
 */

//ordenacao deve ser pelo codigo do curso e timeslot
public class Turma implements Comparable<Turma>{
    //Classe usada para gerar a tabela de horarios
    
    
    private int curso;     //codigo curso
    private int timeslot;
    
    private String disciplina;//codigo diciplina
    private String professor;
    private String sala;
    private String periodo;

    @Override
    public int compareTo(Turma obj) {
        
        //ordenar pelo curso
       if (this.getCurso() > obj.getCurso()) {
            return 1;
        } else if (this.getCurso() < obj.getCurso()) {
            return -1;
        } else {
            //ordenar pelo timeslot
            if (this.getTimeslot()> obj.getTimeslot()) {
                return 1;
            } else if (this.getTimeslot() < obj.getTimeslot()) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    /**
     * @return the curso
     */
    public int getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(int curso) {
        this.curso = curso;
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
    public String getDisciplina() {
        return disciplina;
    }

    /**
     * @param disciplina the disciplina to set
     */
    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    /**
     * @return the professor
     */
    public String getProfessor() {
        return professor;
    }

    /**
     * @param professor the professor to set
     */
    public void setProfessor(String professor) {
        this.professor = professor;
    }

    /**
     * @return the sala
     */
    public String getSala() {
        return sala;
    }

    /**
     * @param sala the sala to set
     */
    public void setSala(String sala) {
        this.sala = sala;
    }

    /**
     * @return the periodo
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
}

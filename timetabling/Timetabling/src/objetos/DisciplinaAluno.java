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
public class DisciplinaAluno implements Comparable<DisciplinaAluno>{
    private int disciplina;
    private int timeslot;
    private String sala;
    private String professor;
    private List<Integer> alunos;
    private int curso;     //codigo curso
    
    public DisciplinaAluno(){
        alunos = new ArrayList<>();
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
    
    @Override
    public int compareTo(DisciplinaAluno obj) {
        
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


    
    
}

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
public class DocenteRestricao {
    private int docente; //codigo de docente
    private ArrayList<Integer> timeslot;//codigo de timeslot
public DocenteRestricao(){
    timeslot=new ArrayList<Integer>();
}
    /**
     * @return the docente
     */
    public int getDocente() {
        return docente;
    }

    /**
     * @return the timeslot
     */
    public ArrayList getTimeslot() {
        return timeslot;
    }

    /**
     * @param docente the docente to set
     */
    public void setDocente(int docente) {
        this.docente = docente;
    }

    /**
     * @param timeslot the timeslot to set
     */
    public void setTimeslot(int timeslot) {
        this.timeslot.add(timeslot);
    }
    
}

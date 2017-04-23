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
public class SalaRestricao {
    private int sala;//codigo  da sala
    private ArrayList<Integer> timeslot;//codigo do timeslot
public SalaRestricao(){
    timeslot=new ArrayList<Integer>();
}
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

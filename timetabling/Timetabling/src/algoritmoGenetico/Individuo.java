package algoritmoGenetico;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aluno
 */
public class Individuo implements Comparable<Individuo>{

    /**
     * @return the horarioValido
     */
    public boolean isHorarioValido() {
        return horarioValido;
    }
    
    //
    // Tipos de mutaÃ§Ãµes (mutation) que estÃ£o disponÃ­veis:
    //
    public static final int     NO_MUTATION               = 0;   // NÃ£o aplica mutaÃ§Ã£o
    
    private Gene chromossomo[];
    private int fitness;
    private boolean horarioValido;
    
   public Individuo() {      
        //dosomething
   }

    
   public Individuo(Gene genes[], int mutacaoEscolhida){
        
        switch (mutacaoEscolhida) {
            case  NO_MUTATION:
                fitness = Solucao.calculaFitnees(genes);
                horarioValido = Solucao.isHorarioValido();
                chromossomo = genes;
            break;
                //implementar mutação escolhida
            default:            
                fitness = Solucao.calculaFitnees(genes);
                horarioValido = Solucao.isHorarioValido();
                chromossomo = genes;
        }
        
    }
    
    public Gene[] getGenes(){
        return chromossomo;
    }
    
    public int getFitness(){
        return fitness;
    }   
    
    public int size(){
        return chromossomo.length;
    }

    @Override
    public int compareTo(Individuo ind1) {//metodo de comparação do fitness
        if (this.getFitness() > ind1.getFitness()) {
            return 1;
        } else if (this.getFitness() < ind1.getFitness()) {
            return -1;
        } else {
            return 0;
        }

    }
}

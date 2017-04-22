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
public class Individuo {
    
    //
    // Tipos de mutaÃ§Ãµes (mutation) que estÃ£o disponÃ­veis:
    //
    public static final int     NO_MUTATION               = 0;   // NÃ£o aplica mutaÃ§Ã£o
    
    private Gene chromossomo[];
    private int fitness;
    
   public Individuo() {      
        //dosomething
   }
    
    Individuo(Gene genes[], int mutacaoEscolhida){
        
        switch (mutacaoEscolhida) {
            case  NO_MUTATION:
                Solucao.initSolucao();
                fitness = Solucao.CalculaFitnees(genes);
                chromossomo = genes;
            break;
                //implementar mutação escolhida
            default:
                Solucao.initSolucao();
                fitness = Solucao.CalculaFitnees(genes);
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
}

package algoritimoGenetico;
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
    
    public static final int     COLUNA                    = 4;   // NÃ£o aplica mutaÃ§Ã£o
    
    int chromossomo[][];
    int fitness;
    
   public Individuo() {      
        //dosomething
   }
    
    Individuo(int genes[][], int mutacaoEscolhida,int n){
        
        switch (mutacaoEscolhida) {
            case  NO_MUTATION:
                Solucao.initSolucao();
                for (int i = 0; i < n; i++) {
                    int aux[] = new int[4];
                    aux[0] = genes[1][i];
                    aux[0] = genes[2][i];
                    aux[0] = genes[3][i];
                    aux[0] = genes[4][i];                           
                    Solucao.CalculaFitness(aux);
                }
             fitness = Solucao.getFitness();
            break;
                //implementar mutação escolhida
            default:
                Solucao.initSolucao();
                for (int i = 0; i < n; i++) {
                    int aux[] = new int[4];
                    aux[0] = genes[1][i];
                    aux[0] = genes[2][i];
                    aux[0] = genes[3][i];
                    aux[0] = genes[4][i];                           
                    Solucao.CalculaFitness(aux);
                }
             fitness = Solucao.getFitness();
        }  
    }
    
    public int[][] getGenes(){
        return chromossomo;
    }
    
    public int getFitness(){
        return fitness;
    }   
    
}

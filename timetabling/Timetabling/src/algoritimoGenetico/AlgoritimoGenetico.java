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
public class AlgoritimoGenetico {
    
    //Retorna um vetor de Individuos, fazendo o crossOver (One-Point)
    //Metodo deve passar dois individuos e o tamanho desses individuos
    public static Individuo[] crossover(Individuo individuo1, Individuo individuo2,int n) {
        
        int         pontoCorte;                   
        Random       r;
        int[][]       geneFilho1, geneFilho2,genePai1,genePai2; 
        Individuo[]  filhos;       
        r           = new Random();
        
        // Gera os DOIS descendentes, inicialmente NULOS.
        filhos = new Individuo[2];
        //
        // Cria os genes dos filhos, faz copia dos genes dos pais
        //
        geneFilho1 = new int[Individuo.COLUNA][n];
        geneFilho2 = new int[Individuo.COLUNA][n];
        genePai1   = individuo1.getGenes();
        genePai2   = individuo2.getGenes();
        
        //one-point crossover
        pontoCorte = r.nextInt(n + 1);
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < pontoCorte; j++) {
                geneFilho1[i][j]  = genePai1[i][j];
            }
        }
        
        for (int i = 0; i < 4; i++) {
            for (int j = pontoCorte; j < n; j++) {
                geneFilho1[i][j]  = genePai2[i][j];
            }
        }
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < pontoCorte; j++) {
                geneFilho2[i][j]  = genePai2[i][j];
            }
        }
        
        for (int i = 0; i < 4; i++) {
            for (int j = pontoCorte; j < n; j++) {
                geneFilho2[i][j]  = genePai1[i][j];
            }
        }
        
        
        filhos[0] = new Individuo(geneFilho1, Individuo.NO_MUTATION,n);
        filhos[1] = new Individuo(geneFilho2, Individuo.NO_MUTATION,n);

        return (filhos);
    }
}

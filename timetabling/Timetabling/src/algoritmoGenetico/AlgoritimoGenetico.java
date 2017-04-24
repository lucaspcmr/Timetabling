


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmoGenetico;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import objetos.Disciplinas;
import objetos.Turma;
/**
 *
 * @author Aluno
 */
public class AlgoritimoGenetico {
    private static List<Turma> turmas;//turma
    private static List<Gene> cromossomo;//solução melhor individuo do AG
    
    AlgoritimoGenetico(){
        turmas = new ArrayList<Turma>();
        cromossomo = new ArrayList<Gene>();
    }
    
    //Retorna um vetor de Individuos, fazendo o crossOver (One-Point)
    //Metodo deve passar dois individuos e o tamanho desses individuos
    public static Individuo[] crossover(Individuo individuo1, Individuo individuo2) {
        
        int size;
        int         pontoCorte;                   
        Random       r;
        Gene[]     geneFilho1, geneFilho2,genePai1,genePai2; 
        Individuo[]  filhos;       
        r           = new Random();
        
        // Gera os DOIS descendentes, inicialmente NULOS.
        filhos = new Individuo[2];
        //
        // Cria os genes dos filhos, faz copia dos genes dos pais
        //
        size = individuo1.size();
        geneFilho1 = new Gene[size];
        geneFilho2 = new Gene[size];
        genePai1   = individuo1.getGenes();
        genePai2   = individuo2.getGenes();
        
        //one-point crossover
        pontoCorte = r.nextInt(size);
        
        //Primeiro filho
        for (int j = 0; j < pontoCorte; j++) {
            geneFilho1[j]  = genePai1[j];
        }
            
        for (int j = pontoCorte; j < size; j++) {
            geneFilho1[j]  = genePai2[j];
        }
        
        //Segundo filho
        for (int j = 0; j < pontoCorte; j++) {
            geneFilho2[j]  = genePai2[j];
        }
        
        for (int j = pontoCorte; j < size; j++) {
            geneFilho2[j]  = genePai1[j];
        }
        
        //Criar um vetor de individuos (gerado os dois filhos)
        filhos[0] = new Individuo(geneFilho1, Individuo.NO_MUTATION);
        filhos[1] = new Individuo(geneFilho2, Individuo.NO_MUTATION);

        return (filhos);
    }
    
    // codigo principal do algoritimo genetico
    public static void startAG(){
        
    }
    
    public static void gerarTurmas(){
        //do something
        
    }
     
     public static List<Turma> getTurmas(){
         gerarTurmas();
        //do something
        return turmas;
    }
     
     
}

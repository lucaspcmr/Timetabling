/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmoGenetico;

import java.util.ArrayList;
import static java.util.Collections.sort;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import objetos.Disciplinas;
import objetos.Docentes;
import objetos.Salas;
import objetos.Timeslot;
import objetos.Turma;
import timetabling.Filetomemrest;

/**
 *
 * @author Aluno
 */
public class AlgoritimoGenetico {

    private static Gene cromossomo[];//solução melhor individuo do AG
    private static Solucao solucao;

    public static void init() {
        solucao.construirMapSolucao(Timeslot.getNumeroTimeslots(), Salas.getNumeroSala(), Docentes.getNumeroProfessores(), Disciplinas.getNumeroDisciplinas());
    }

    //Retorna um vetor de Individuos, fazendo o crossOver (One-Point)
    //Metodo deve passar dois individuos e o tamanho desses individuos
    public static Individuo[] crossover(Individuo individuo1, Individuo individuo2) {

        int size;
        int pontoCorte;
        Random r;
        Gene[] geneFilho1, geneFilho2, genePai1, genePai2;
        Individuo[] filhos;
        r = new Random();

        // Gera os DOIS descendentes, inicialmente NULOS.
        filhos = new Individuo[2];
        //
        // Cria os genes dos filhos, faz copia dos genes dos pais
        //
        size = individuo1.size();
        geneFilho1 = new Gene[size];
        geneFilho2 = new Gene[size];
        genePai1 = individuo1.getGenes();
        genePai2 = individuo2.getGenes();

        //one-point crossover
        pontoCorte = r.nextInt(size);

        //Primeiro filho
        for (int j = 0; j < pontoCorte; j++) {
            geneFilho1[j] = genePai1[j];
        }

        for (int j = pontoCorte; j < size; j++) {
            geneFilho1[j] = genePai2[j];
        }

        //Segundo filho
        for (int j = 0; j < pontoCorte; j++) {
            geneFilho2[j] = genePai2[j];
        }

        for (int j = pontoCorte; j < size; j++) {
            geneFilho2[j] = genePai1[j];
        }

        //Criar um vetor de individuos (gerado os dois filhos)
        filhos[0] = new Individuo(geneFilho1, Individuo.NO_MUTATION);
        filhos[1] = new Individuo(geneFilho2, Individuo.NO_MUTATION);

        return (filhos);
    }

    // codigo principal do algoritimo genetico
    public static void startAG() {
        //testando a validação das soluções geradas
        //criando individuos aleatorios
        init();
        for (int i = 0; i < 1000; i++) {
        init();
        Individuo individuo = Populacao.criaIndividuo( Disciplinas.getNumeroDisciplinas(), Salas.getNumeroSala(),Docentes.getNumeroProfessores() , Timeslot.getNumeroTimeslots() );
        cromossomo = individuo.getGenes();
//        
//        for (int i = 0; i < cromossomo.length; i++) {
//            Gene gene = cromossomo[i];
//        }

        System.out.println("Geração:"+i+"   Fitness = "+individuo.getFitness() + " Horario Valido: "+individuo.isHorarioValido());
        }
       
    }

    public static Populacao selecao(Populacao pop1) {
        int fittotal;
        double aux;
        int tam=pop1.getampopulacao();
        Random rnd = new Random();
        Float selected;
        float seccao;
        ArrayList<Float> weight;
        Populacao novapop=new Populacao();
        weight = new ArrayList<>();
        for(int j=0;j<tam/2;j++){//escolhe metade dos individuos da população
        fittotal = 0;
        aux = 0.0;
        sort(pop1.populacao);//coloca população em ordem de fitness
        for (int i = 1; i <= pop1.getampopulacao(); i++) {
            fittotal = fittotal + pop1.populacao.get(i).getFitness();//calcula a soma de todos os fitness
        }
        for (int i = 1; i <= Populacao.getampopulacao(); i++) {
            seccao = (pop1.populacao.get(i).getFitness()) / fittotal;//calcula a porcentagem da roleta para cada individuo
            weight.add(seccao);
        }
        selected = rnd.nextFloat();
        int i = 1;
        
        while (i <= tam + 1) {
            if (selected >= aux && selected <= aux + weight.get(i)) {//encontra a posicao do numero gerado randomicamente
                break;
            }
            aux = aux + weight.get(i);
            i++;
        }
        novapop.populacao.add(pop1.populacao.get(i));//adiciona o elemento escolhido em uma nova população
        pop1.populacao.remove(i);//remove o elemento da população antiga
        }
        pop1=null;
        return novapop;
    }

    /**
     * @return the cromossomo
     */
    public static Gene[] getCromossomo() {
        return cromossomo;
    }

}

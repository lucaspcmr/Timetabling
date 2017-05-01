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
import timetabling.TextArea;

/**
 *
 * @author Aluno
 */
public class AlgoritimoGenetico {

    private static Gene cromossomo[];         //solução melhor individuo do AG
    private static Individuo melhorIndividuo; //Melhor individuo
    private static Solucao solucao;           //instancia de solução
    private static List<String> mensagem = new ArrayList<String>();

    //metodo para iniciar a classe solução
    //necessario para inicializar os objetos 
    //que são necessario para os calculos
    //e inicializar o tamanho do mapa de solução
    //assim como setar todas as restrições dos arquivos lidos
    private static void init() {
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
    public static void startAG(boolean elitismo,int numeroIndividuos,int numeroGeracoes,int taxaMutacao,int taxaCrossover) {
        //necessario para processar o texto no LOG
          new Thread() {
			@Override
			public void run() {
                            TextArea.LOG.setText(""); //Limpar o Log
                            init(); //necessario para iniciar o mapa de soluções
                            
//obs:RAQUEL
//O codigo abaixo foi implementado para testes da classe solução
//toda a logica do Algoritimo genetico deve ser implementado nessa parte
//pode deletar ou comentar o codigo 
//------------------------------------------------------------------------------------------------------- 
                            for (int i = 0; i < 1000; i++) {
                            //do something
                            Individuo individuo = Populacao.criaIndividuo( Disciplinas.getNumeroDisciplinas(), Salas.getNumeroSala(),Docentes.getNumeroProfessores() , Timeslot.getNumeroTimeslots() );
                            cromossomo = individuo.getGenes();
                            //Debug do individuo pegando seu fitness
                            TextArea.LOG.append("Geração:"+i+"   Fitness = "+individuo.getFitness() + " Horario Valido: "+individuo.isHorarioValido()+"\n");
                            TextArea.LOG.setCaretPosition(TextArea.LOG.getText().length() ); // scroll rolando dinamicamente
                            
                            if(melhorIndividuo ==null)
                                melhorIndividuo = individuo;
                             else{
                                  if(individuo.getFitness() > melhorIndividuo.getFitness() )
                                         melhorIndividuo = individuo;
                                 }
                            }
                            
                            //mostrar o melhor individuo
                            TextArea.LOG.append("---------------------------------"+"\n");
                            TextArea.LOG.append("Melhor Fitness = "+melhorIndividuo.getFitness() + " Horario Valido: "+melhorIndividuo.isHorarioValido()+"\n");
                            TextArea.LOG.setCaretPosition(  TextArea.LOG.getText().length() );
                            cromossomo = melhorIndividuo.getGenes(); 
//----------------------------------------------------------------------------------------------
			}
		}.start();    
        
    }
    
    public static void escreverLog(String str){
        TextArea.LOG.append(str);
    }
    
    //Metodo de seleção tipo roleta
    //dada uma população    (lista de individuos)
    public static List<Individuo> selecao(List<Individuo> pop1) {
        int fittotal;
        double aux;
        int tam= pop1.size();
        Random rnd = new Random();
        Float selected;
        float seccao;
        ArrayList<Float> weight;
        List<Individuo> novapop=new ArrayList<Individuo>();//nova populacao
        weight = new ArrayList<>();
        
       // for(int j=0;j<tam/2;j++){//escolhe metade dos individuos da população
       for(int j=0;j<2;j++){//Selecionar dois individuos da população 
            fittotal = 0;
            aux = 0.0;
            sort(pop1);//coloca população em ordem de fitness
            
            for (int i = 1; i <= pop1.size(); i++) {
                fittotal = fittotal + pop1.get(i).getFitness();//calcula a soma de todos os fitness
            }
            
            for (int i = 1; i <= Populacao.getampopulacao(); i++) {
                seccao = (pop1.get(i).getFitness()) / fittotal;//calcula a porcentagem da roleta para cada individuo
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
            
            novapop.add(pop1.get(i));//adiciona o elemento escolhido em uma nova população
            pop1.remove(i);//remove o elemento da população antiga
        }
        pop1=null;
        return novapop;
    }
    
    //metodo para criar uma nova população
    //selecionando se tem elitismo ou não
    //ou seja, se o melhor individuo da população anterior
    //for maior que o ultimo individuo da nova população
    //entao adiciono o melhor individuo na nova geração
    //e o individuo menos apto é removido
    // Se o ELITISMO tiver sendo utilizado, 
    //    então o indivíduo com MAIOR APTIDÃO será mantido na "nova" geração da população.
    // 
    // Lembre-se: após ordenação da população, o indivíduo com maior aptidão encontra-se na posição "0" (zero) 
    //            desta população (é claro que pode haver "empate" e haver outros indivíduos com igual aptidão 
    //            nas posições 1, 2, ... Entretanto, apenas o indivíduo que ocupa a posição "0" - zero é mantido)
    public static List<Individuo> novaGeracao(boolean elitismo,List<Individuo> populacao){
        
        selecao(populacao); //correção necessario a correção do metodo
        
        List<Individuo> novaPopulacao = new ArrayList<Individuo>();
        int size = populacao.size();
        size = size/2;
        //
        // Insere novos indivíduos na "nova" população até atingir o tamanho máximo permitido para ela.
        //
        for (int i = 0; i < size ; i++) {
            Individuo ind[] = crossover(Populacao.populacao.get(i),Populacao.populacao.get(i+1));
            novaPopulacao.add(ind[0]);
            novaPopulacao.add(ind[1]);
        }
        
        
        if(elitismo){
            sort(populacao);
            sort(novaPopulacao);

            int fitnessPopulacao     = populacao.get(0).getFitness();
            int fitnessNovaPopulacao = novaPopulacao.get(novaPopulacao.size()-1).getFitness();

            if(fitnessPopulacao > fitnessNovaPopulacao ){
                Individuo individuo = populacao.get(0);
                novaPopulacao.remove(novaPopulacao.size()-1);
                novaPopulacao.add(individuo);
            }
            sort(novaPopulacao);
        }
        else{
            sort(novaPopulacao);
        }
        
        Populacao.populacao = novaPopulacao; // seta na população
        
        return novaPopulacao;//retorna a referencia da população
    }
    
    
    /**
     * @return the cromossomo
     */
    public static Gene[] getCromossomo() {
        return cromossomo;
    }
    
    

}

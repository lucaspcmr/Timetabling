/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Raquel
 */
package algoritmoGenetico;

import java.util.ArrayList;
import static java.util.Collections.sort;
import java.util.List;
import java.util.Random;
import objetos.Disciplinas;
import objetos.Docentes;
import objetos.Salas;
import objetos.Timeslot;
import timetabling.TextArea;

public class AlgoritmoGenetico {

    private static Gene cromossomo[];         //solução melhor individuo do AG
    private static Individuo melhorIndividuo; //Melhor individuo
    private static Solucao solucao;           //instancia de solução
    private static List<String> mensagem = new ArrayList<>();
    private static int selecao;
    private static int crossover;    
    private static boolean elitismo;
    private static int numeroIndividuos;
    private static int numeroGeracoes;
    private static int taxaMutacao;
    private static int taxaCrossover;
    
    
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
    public static Individuo[] onePointCrossover(Individuo individuo1, Individuo individuo2) {

        int size;
        int pontoCorte;
        Random r;
        Gene[] geneFilho1, geneFilho2, genePai1, genePai2;
        Individuo[] filhos;
        r = new Random();

        // Gera os DOIS descendentes, inicialmente NULOS.
        filhos = new Individuo[2];
        
        // Cria os genes dos filhos, faz copia dos genes dos pais
        size = individuo1.size();
        geneFilho1 = new Gene[size];
        geneFilho2 = new Gene[size];
        genePai1 = individuo1.getGenes();
        genePai2 = individuo2.getGenes();

        //one-point onePointCrossover
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
        filhos[0] = new Individuo(geneFilho1, Individuo.MUTATION);
        filhos[1] = new Individuo(geneFilho2, Individuo.MUTATION);

        return (filhos);
    }
    
    //Retorna um vetor de Individuos, fazendo o crossOver (Two-Point)
    //Metodo deve passar dois individuos e o tamanho desses individuos
    public static Individuo[] twoPointCrossover(Individuo individuo1, Individuo individuo2) {

        int size;
        int pontoCorte1, pontoCorte2;
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

        //two-point onePointCrossover
        pontoCorte1 = r.nextInt(size);
        pontoCorte2 = r.nextInt(size);
        int maior, menor;
        
        
       //ordenando pontos de corte
        if(pontoCorte1 > pontoCorte2){
            maior = pontoCorte1;
            menor = pontoCorte2;
        }
        else if (pontoCorte1 == pontoCorte2){
            pontoCorte1++;
            maior = pontoCorte1;
            menor = pontoCorte2;
        }
        else{
            maior = pontoCorte2;
            menor = pontoCorte1;
        }

        //preenche lado esquerdo filho
        for (int j = 0; j < pontoCorte1; j++) {
            geneFilho1[j] = genePai1[j];
            geneFilho2[j] = genePai2[j];
        }
        
        
        //preenche núcleo filho (inverte núcleos dos pais)
        for (int j = pontoCorte1; j < pontoCorte2; j++) {
            geneFilho1[j] = genePai2[j];
            geneFilho2[j] = genePai1[j];
        }

        //preenche lado direito filho
        for (int j = pontoCorte2; j < size; j++) {
            geneFilho1[j] = genePai1[j];
            geneFilho2[j] = genePai2[j];
        }

        //Criar um vetor de individuos (gerado os dois filhos)
        filhos[0] = new Individuo(geneFilho1, Individuo.MUTATION);
        filhos[1] = new Individuo(geneFilho2, Individuo.MUTATION);

        return (filhos);
    }
    
    //Retorna um vetor de Individuos, fazendo o crossOver uniforme
    //Metodo deve passar dois individuos e o tamanho desses individuos
    public static Individuo[] uniformCrossover(Individuo individuo1, Individuo individuo2) {

        int size;
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

         for (int i = 0; i < size; i++) {
             int random = r.nextInt(2);
             
             if(random == 0){
                 geneFilho1[i] = genePai2[i];
                 geneFilho2[i] = genePai1[i];
             }
             else{
                 geneFilho1[i] = genePai1[i];
                 geneFilho2[i] = genePai2[i];
             }
         }
        
        //Criar um vetor de individuos (gerado os dois filhos)
        filhos[0] = new Individuo(geneFilho1, Individuo.MUTATION);
        filhos[1] = new Individuo(geneFilho2, Individuo.MUTATION);

        return (filhos);
    }
    
    // codigo principal do algoritimo genetico
    public static void startAG(boolean elitismo,int numeroIndividuos,int numeroGeracoes,int taxaMutacao,int taxaCrossover, int selecao, int crossover) {
    AlgoritmoGenetico.elitismo          = elitismo;
    AlgoritmoGenetico.numeroIndividuos  = numeroIndividuos;
    AlgoritmoGenetico.numeroGeracoes    = numeroGeracoes;
    AlgoritmoGenetico.taxaMutacao       = taxaMutacao;
    AlgoritmoGenetico.taxaCrossover     = taxaCrossover;  
    AlgoritmoGenetico.selecao           = selecao;
    AlgoritmoGenetico.crossover         = crossover;
    
//necessario para processar o texto no LOG       
          new Thread() {
			@Override
			public void run() {
                            TextArea.LOG.setText(""); //Limpar o Log
                            init(); //necessario para iniciar o mapa de soluções
                         
                        //cria população
                         Populacao.criaPopulacao(AlgoritmoGenetico.numeroIndividuos, Disciplinas.getNumeroDisciplinas(), Salas.getNumeroSala(), Docentes.getNumeroProfessores(), Timeslot.getNumeroTimeslots());
                         
                         //ordena populaçao
                         sort(Populacao.populacao);
                         
                         //individuoCompara recebe o individuo mais apto da população
                         Individuo individuoCompara = Populacao.populacao.get(0);
                         
                         int contadorGeracoes = 0;
                         
                         AlgoritmoGenetico.melhorIndividuo = individuoCompara;
                         AlgoritmoGenetico.cromossomo = individuoCompara.getGenes();
                         
                         //enquanto o resultado não alcança o valor mínimo aceito
                         while (individuoCompara.getFitness() != 0){                         
                            //Debug do individuo pegando seu fitness
                            TextArea.LOG.append("Geração:" + contadorGeracoes +"   Fitness = "+ individuoCompara.getFitness() + " Horario Valido: "+individuoCompara.isHorarioValido()+"\n");
                            TextArea.LOG.setCaretPosition(TextArea.LOG.getText().length() ); // scroll rolando dinamicamente
                            Populacao.criaNovaGeracao(AlgoritmoGenetico.elitismo, AlgoritmoGenetico.getTaxaMutacao(), AlgoritmoGenetico.taxaCrossover);
                            sort(Populacao.populacao);
                            
                            individuoCompara = Populacao.populacao.get(0);
                            AlgoritmoGenetico.melhorIndividuo = individuoCompara;
                            AlgoritmoGenetico.cromossomo = individuoCompara.getGenes();
                         
                            contadorGeracoes++;
                            
                            if(contadorGeracoes > AlgoritmoGenetico.numeroGeracoes)
                                break;
                         }
                          
                         //mostrar o melhor individuo
                        TextArea.LOG.append("---------------------------------"+"\n");
                        TextArea.LOG.append("Melhor Fitness = "+melhorIndividuo.getFitness() + " Horario Valido: "+melhorIndividuo.isHorarioValido()+"\n");
                        TextArea.LOG.setCaretPosition(  TextArea.LOG.getText().length() );
                                     
			}
		}.start();    
        
    }
    
    public static void escreverLog(String str){
        TextArea.LOG.append(str);
    }
    
    //Metodo de seleção tipo roleta
    //dada uma população    (lista de individuos)
    public static List<Individuo> selecaoRoleta(List<Individuo> pop1) {
        int fittotal;
        double aux;
        int tam= pop1.size();
        Random rnd = new Random();
        Float selected;
        float seccao;
        ArrayList<Float> weight;
        List<Individuo> novapop=new ArrayList<>();//nova populacao
        weight = new ArrayList<>();
        
       // for(int j=0;j<tam/2;j++){//escolhe metade dos individuos da população
       for(int j=0;j<2;j++){//Selecionar dois individuos da população 
            fittotal = 0;
            aux = 0.0;
            sort(pop1);//coloca população em ordem de fitness
            
            for (int i = 0; i < tam; i++) {
                fittotal = fittotal + pop1.get(i).getFitness();//calcula a soma de todos os fitness
            }
            
            for (int i = 0; i < tam; i++) {
                seccao = (pop1.get(i).getFitness()) / fittotal;//calcula a porcentagem da roleta para cada individuo
                weight.add(seccao);
            }
            
            selected = rnd.nextFloat();
            int i = 0;

            while (i < tam -1) {
                if (selected >= aux && selected <= aux + weight.get(i)) {//encontra a posicao do numero gerado randomicamente
                    break;
                }
                aux = aux + weight.get(i);
                i++;
            }
            
            novapop.add(pop1.get(i));//adiciona o elemento escolhido em uma nova população
            //pop1.remove(i);//remove o elemento da população antiga
        }
        pop1=null;
        return novapop;
    }
    
    //Metodo de seleção tipo torneio
    //dada uma população    (lista de individuos)
    public static List<Individuo> selecaoTorneio (List<Individuo> pop1){
        Random random = new Random();
        
        int individuo1, individuo2, individuo3;
        
        //sorteando posições dos individuos
        individuo1 = random.nextInt(pop1.size());
        do{
        individuo2 = random.nextInt(pop1.size());} while(individuo2 == individuo1);
        do{        
        individuo3 = random.nextInt(pop1.size());} while(individuo3 == individuo1 || individuo3 == individuo2);
        
        
        List<Individuo> listaTorneio = new ArrayList<>();
        
        //insere individuos na lista de torneio
        listaTorneio.add(pop1.get(individuo1));
        listaTorneio.add(pop1.get(individuo2));
        listaTorneio.add(pop1.get(individuo3));
        
        //ordena por valor de aptidão
        sort(listaTorneio);
        
        //remove menos apto
        listaTorneio.remove(2);
        
        //retorna lista com os 2 mais aptos
        return listaTorneio;        
    }
    
    /**
     * @return the cromossomo
     */
    public static Gene[] getCromossomo() {
        return cromossomo;
    }

    /**
     * @return the taxaMutacao
     */
    public static int getTaxaMutacao() {
        return taxaMutacao;
    }

    /**
     * @return the selecao
     */
    public static int getSelecao() {
        return selecao;
    }

    /**
     * @return the crossover
     */
    public static int getCrossover() {
        return crossover;
    }
    
    

}
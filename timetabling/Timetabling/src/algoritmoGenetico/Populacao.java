/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmoGenetico;

/**
 *
 * @author raquel
 */
import java.util.ArrayList;
import static java.util.Collections.sort;
import java.util.List;
import java.util.Random;

public class Populacao {
    
    //cria lista de indivíduos = população
    public static List<Individuo> populacao = new ArrayList<>();
    
    //cria indivíduo aleatório
    public static Individuo criaIndividuo(int n_disciplinas, int n_salas, int n_docentes, int n_timeslot) {
    
        Random random = new Random();
        
        int sizeGene;
        sizeGene = n_disciplinas;
        
        Gene genes[] = new Gene[sizeGene];
        
        int sizeDocentes = n_docentes;
        int sizeSalas = n_salas;
        int sizeTimeslot = n_timeslot;
        
        int sorteioDocente;
        int sorteioSala;
        int sorteioTimeslot;
        
        //seta valores aleatórios para docente, sala e timeslot para cada disciplina i
        for (int i = 0; i < sizeGene; i++){
            Gene gene = new Gene();
            
            sorteioDocente = random.nextInt(sizeDocentes);
            sorteioSala = random.nextInt(sizeSalas);
            sorteioTimeslot = random.nextInt(sizeTimeslot);
            
            gene.setProfessor(sorteioDocente);
            gene.setSala(sorteioSala);
            gene.setTimeslot(sorteioTimeslot);
            gene.setDisciplina(i);
            
            genes[i] = gene;
        }
        
        Solucao.validaGene(genes); //criar genes validos para professor, sala inicial
        
        Individuo individuo = new Individuo(genes,Individuo.NO_MUTATION);
                return individuo;
    }
    
    //adiciona os indivíduos criados na população
    public static void criaPopulacao(int n_individuos, int n_disciplinas, int n_salas, int n_docentes, int n_timeslot){
    
        for (int i = 0 ; i < n_individuos ; i++){
        
            populacao.add(criaIndividuo(n_disciplinas, n_salas, n_docentes, n_timeslot));
            
        }
    }
    
    //cria nova geração
    public static void criaNovaGeracao(boolean elitismo, int taxaMutacao, int taxaCrossover){
            
        //ordena populacao pelo valor do fitness -> maior para o menor
        sort(Populacao.populacao);
        
        Random r = new Random();
        List novaPopulacao = new ArrayList<>();
        
        //se houver elitismo, primeira posição da lista novaPopulacao recebe primeira posição da lista populacao
        if(elitismo){
            Individuo ind1 = copyIndividuo(populacao.get(0));
            Individuo ind2 = copyIndividuo(populacao.get(1));
                  
            novaPopulacao.add(ind1);
            novaPopulacao.add(ind2);            
        }
        
        //realizar onePointCrossover enquanto tamanho da nova população for menor que o da população original
        while (novaPopulacao.size() < Populacao.getampopulacao()){
            
            List<Individuo> pais = new ArrayList<>();
            
            if(AlgoritmoGenetico.getSelecao() == 1){
                pais = AlgoritmoGenetico.selecaoRoleta(populacao);
            }
            else if(AlgoritmoGenetico.getSelecao() == 2){
                pais = AlgoritmoGenetico.selecaoTorneio(populacao);
            }        
            
            Individuo[] filhos = new Individuo[2];
            
            //se o valor sorteado for <= que a taxa de onePointCrossover: filhos recebem o onePointCrossover dos pais 
            if(r.nextInt() <= taxaCrossover){
                if(AlgoritmoGenetico.getCrossover() == 1){
                    filhos = AlgoritmoGenetico.onePointCrossover(pais.get(0),pais.get(1));
                }
                else if(AlgoritmoGenetico.getCrossover() == 2){
                    filhos = AlgoritmoGenetico.twoPointCrossover(pais.get(0),pais.get(1));
                }
                else if(AlgoritmoGenetico.getCrossover() == 3){
                    filhos = AlgoritmoGenetico.uniformCrossover(pais.get(0),pais.get(1));
                }
            }
            
            //senão, mantêm o valor dos pais nos filhos sem alteração
            else{
            
                filhos[0] = pais.get(0);
                filhos[1] = pais.get(1);
            }
            
            //inclui filhos na geração
            novaPopulacao.add(filhos[0]);
            novaPopulacao.add(filhos[1]);
        }
        
        populacao = novaPopulacao;
        
    }
    
    public static Individuo copyIndividuo(Individuo individuo){
        int size = individuo.getGenes().length;
        
            Gene genes[] =  new Gene[size]; 
            for (int i = 0; i < size; i++) {
                Gene gene = new Gene();
                Gene aux = individuo.getGenes()[i];
                
                gene.setDisciplina(aux.getDisciplina());
                gene.setProfessor(aux.getProfessor());
                gene.setSala(aux.getSala());
                gene.setTimeslot(aux.getTimeslot());               
                genes[i] = gene;               
            }
           Individuo ind = new Individuo(genes,Individuo.NO_MUTATION);      
        
           return ind;
    }
    
    public static int getampopulacao(){//tamanho da população
        return populacao.size();
    }
    
}
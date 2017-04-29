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
import java.util.List;
import java.util.Random;
import objetos.AlunoDisciplina;
import objetos.Disciplinas;

public class Populacao {
        public static ArrayList<Individuo> populacao = new ArrayList<>();
    
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
        
        //Solucao.validaGene(genes,1); //criar genes validos para professor, sala inicial
        
        Individuo individuo = new Individuo(genes,Individuo.NO_MUTATION);
                return individuo;
    }
    
    public static void criaPopulacao(int n_disciplinas, int n_salas, int n_docentes, int n_timeslot){
    
        for (int i = 0 ; i < n_disciplinas ; i++){
        
            populacao.add(criaIndividuo(n_disciplinas, n_salas, n_docentes, n_timeslot));
            
        }
    }
    
//    public static AlunoDisciplinaID criaListAlunoDisciplinaID (List<AlunoDisciplina> alunoDisc, ArrayList<Integer> codDisc){
//    
//        int sizeAlunoDisc = alunoDisc.size();
//        int sizeDisc = codDisc.size();
//        
//        for(int i = 0; i < sizeAlunoDisc; i++){
//            for (int j = 0; j < sizeDisc ; j++){
//                
//            }
//        }
//        
//        return null;
//    }

    public static int getampopulacao(){//tamanho da população
        return populacao.size();
    }
    
}
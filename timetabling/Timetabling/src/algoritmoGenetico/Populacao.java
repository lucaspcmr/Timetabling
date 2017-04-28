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
    
    public static Individuo criaIndividuo(List<Integer> disciplinas, List<Integer> salas, List<String> docentes, List<String> timeslot) {
    
        Random random = new Random();
        
        int sizeGene;
        sizeGene = disciplinas.size();
        
        Gene genes[] = new Gene[sizeGene];
        
        int sizeDocentes = docentes.size();
        int sizeSalas = salas.size();
        int sizeTimeslot = timeslot.size();
        
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
        //Solucao.validaGene(genes); //criar genes validos ???
        
        Individuo individuo = new Individuo(genes,Individuo.NO_MUTATION);
                return individuo;
    }
    
    public static void criaPopulacao(int sizeIndividuo, List<Integer> disciplinas, List<Integer> salas, List<String> docentes, List<String> timeslot){
    
        for (int i = 0 ; i < sizeIndividuo ; i++){
        
            populacao.add(criaIndividuo(disciplinas, salas, docentes, timeslot));
            
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
package algoritmoGenetico;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import objetos.AlunoDisciplina;
import objetos.AlunoDisciplinaID;
import objetos.DisciplinaRestricao;
import objetos.DisciplinaRestricaoID;
import objetos.DisciplinaTimeSlot;
import objetos.DisciplinaTimeSlotID;
import objetos.Disciplinas;
import objetos.DocenteRestricao;
import objetos.DocenteRestricaoID;
import objetos.Docentes;
import objetos.Estudantes;
import objetos.SalaRestricao;
import objetos.SalaRestricaoID;
import objetos.Salas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aluno
 */
/*
cromossomo[] = {[s,p,t,d];....;[s,p,h,d]};
(uma matriz de classes do tipo Gene,Classe genes,a qual, possui sala,professor,timeslot,disciplina).
os valores de sala,professor,horario,disciplina corresponde ao indice da matrix sendo usado
os indices como id unicos uma e um vetor desses ids [s,p,h,d]
*/

public class Solucao {
    
   private static int HARDCONSTRAINT = -10;//penalidade hardconstraint
   private static int SOFTCONSTRAINT = -1;//penalidade softconstraint
   
   private static byte salas       [][];      // mascara  sala-horario usada validar solução
   private static byte professores [][];      // mascara  professor-horario usada validar solução
   private static byte disciplinas [][];      // mascara  disciplina-horario usada validar solução
   
   //usado como auxiliar para calcular o tamando das matrizes
   private static int n_timeslots;  // numero de timeslots
   private static int n_salas;      //numero de salas
   private static int n_professores;//numero de professores
   private static int n_disciplinas;//numero de disciplinas
   
   //Hashtable, usada para verificar softconstrants de aluno horario
   //e capacidade de sala
   public static Hashtable <Integer, DisciplinaTimeSlotID> disciplinaTimeSlot ;
    
    //Mascara de soluÃ§oes estrutura que contrem todas as mascaras 
    private static Object solucao[];
    
    //inicializa com timeslots, numero de salas e numero de professores
   public static void construirMapSolucao(int n_timeslots,int n_salas,int n_professores,int n_disciplinas){
       
        disciplinaTimeSlot = new Hashtable <Integer,DisciplinaTimeSlotID>();
        //tamanho da matriz
        Solucao.n_timeslots            = n_timeslots; 
        Solucao.n_salas                = n_salas;
        Solucao.n_professores          = n_professores;
        Solucao.n_disciplinas          = n_disciplinas;
             
        //matrizes
         salas        = new byte[n_salas][n_timeslots];        //sala - horario
         professores  = new byte[n_professores][n_timeslots];  //professores - timeslot
         disciplinas  = new byte[n_disciplinas][n_timeslots];  //disciplinas - timeslot
         
         initSolucao();
         
         solucao = new Object[5];
         solucao[0] = salas;
         solucao[1] = professores;
         solucao[2] = disciplinas;
         
         
    }
    
    //iniciar a marcara de validaÃ§Ã£o de schedule todos as matrizes
    //soluÃ§Ã£o do tipo (sala,horario),(professor,horario)
    public static void initSolucao(){

        for (int i = 0; i < n_timeslots; i++) {
            for (int j = 0; j < n_salas; j++) {
                salas[i][j] = 0;
            }
        }
        
       for (int i = 0; i < n_timeslots; i++) {
            for (int j = 0; j < n_professores; j++) {
                professores[i][j] = 0;
            }
        } 
       
      for (int i = 0; i < n_timeslots; i++) {
            for (int j = 0; j < n_disciplinas; j++) {
                disciplinas[i][j] = 0;
            }
        } 
       //variaveis populçaõ criadas setar as restricoes no Mapa de soluções
//       setRestricoesProfessores(Populacao.getDocenteRestricao()); 
//       setRestricoesDisciplinas(Populacao.getDisciplinaRestricao()); 
//       setRestricoesSalas(Populacao.getSalaRestricao()); 
       
       disciplinaTimeSlot.clear();//limpar HashTable
    }
    
    //vector [sala,professor,timeslot,disciplina]
    //coloca valor no mapa de soluções
    public static void setValor(Gene gene){
        
        int sala = gene.getSala();
        int professor = gene.getProfessor();
        int timeslot = gene.getTimeslot();
        int disciplina = gene.getDisciplina();
        
        salas [sala][timeslot] = 1; 
        professores [professor][timeslot]   = 1;
        disciplinas [disciplina][timeslot]  = 1;  
        
        //seta no hashmap  como chave o id da disciplina e salva o timeslot e o id da disciplina
        disciplinaTimeSlot.put(disciplina, new DisciplinaTimeSlotID(disciplina, timeslot));
        
    }
    
    //retorna se o ponto jÃ¡ esta colocado na mascara de soluÃ§Ã£o
    //e seta esse ponto na mascara de soluÃ§Ã£o
    public static boolean isValorValido(Gene gene){
        boolean valor = true;
        int sala = gene.getSala();
        int professor = gene.getProfessor();
        int timeslot = gene.getTimeslot();
        int disciplina = gene.getDisciplina();
         
        if(salas[sala][timeslot] == 1 || salas[sala][timeslot]==-1)
            valor = false;
        if(professores[professor][timeslot]  == 1 && professores[professor][timeslot] == -1)
            valor = false;
        if(disciplinas[disciplina][timeslot]  == 1 && disciplinas[disciplina][timeslot] == -1)
            valor = false;
       
        if(valor == true){//se o ponto for valido adiciona na mascara e retorna verdadeiro
            setValor(gene);
            return true;
        }
        
        return false;
    }
    
    //verifica se o professor e valido
    public static boolean isProfessorValido(Gene gene){
        boolean retorno = false;
        int sala = gene.getSala();
        int professor = gene.getProfessor();
        int timeslot = gene.getTimeslot();
        int disciplina = gene.getDisciplina();
               
        if(professores[professor][timeslot]  == 0)
            retorno = true;
           
        return retorno;
    }
    
    //verifica se a sala Ã© valida
    public static boolean isSalaValido(Gene gene){
        boolean retorno = false;
        int sala = gene.getSala();
        int professor = gene.getProfessor();
        int timeslot = gene.getTimeslot();
        int disciplina = gene.getDisciplina();
         
        if(salas[sala][timeslot] == 0 )
            retorno = true;
        
        
        return retorno;
    }
    
    //retorna se o ponto jÃ¡ esta colocado na mascara de soluÃ§Ã£o
    //e seta esse ponto na mascara de soluÃ§Ã£o
    public static boolean isDisciplinaValido(Gene gene){
        boolean retorno = false;
        int sala = gene.getSala();
        int professor = gene.getProfessor();
        int timeslot = gene.getTimeslot();
        int disciplina = gene.getDisciplina();
         
        if(disciplinas[disciplina][timeslot]  == 0)
            retorno = true;
  
        return retorno;
    }
    
    //Função que calcula a função fitnees
    public static int CalculaFitnees(Gene genes[]){
        int fitness = 0;
        
        for (int i = 0; i < genes.length; i++) {
            fitness = fitness + VerificaPonto(genes[i]);
            //lista aluno(id) disciplina(id) e o numero real de alunos
            //fitness = fitness +softConstraints(Populacao.getAlunosDisciplinas(),Populacao.getNumeroAlunos());
        }
        
        return fitness;
    }
    
    //FunÃ§Ã£o para calcular a validade da solução
    //inserindo valores na soluÃ§Ã£o, se nÃ£o estiver preenchida o espaÃ§o de soluÃ§Ã£o
    //nÃ£o pontua o fitness, e seta o valor no espaÃ§o de soluÃ§Ãµes retornando verdadeiro
    //caso contrario pontua negativamente o horario
    public static int VerificaPonto(Gene gene){
        int fitness = 0;
        boolean valor = true;
        int sala = gene.getSala();
        int professor = gene.getProfessor();
        int timeslot = gene.getTimeslot();
        int disciplina = gene.getDisciplina();
        
        //Hards Constraints -- penalidade maior
        if(salas[sala][timeslot] == 1 || salas[sala][timeslot] == -1 ){ // sala ja esta sendo utilizada naquele horario
            fitness = fitness + HARDCONSTRAINT;
            valor = false;
        }
        if(professores[professor][timeslot]  == 1 || professores[professor][timeslot] == -1){//professor ja esta ministrando aula naquele horario
            fitness = fitness + HARDCONSTRAINT;
            valor = false;
        }
        if(disciplinas[disciplina][timeslot]  == 1 && disciplinas[disciplina][timeslot] == -1){
            fitness = fitness + HARDCONSTRAINT;
            valor = false;
         }
         
        if(valor == true)
            setValor(gene);
            
        return fitness;
    }
       
    //implementar restriÃ§oes dos professores setando ja na mascara 
    //de soluÃ§oes, setando -1 no espaÃ§o de soluÃ§oes
    //mostarndo uma soluÃ§Ã£o impossivel
    //recebe a lista de docentes e uma lista de restriçoes
    public static void setRestricoesProfessores(List<DocenteRestricaoID> docente){
        for (int i = 0; i < docente.size(); i++) {
            DocenteRestricaoID aux = docente.get(i);
            professores[aux.getDocente()][aux.getTimeslot()] = -1;
        }
    }
    
    //implementar restriÃ§oes as disciplinas setando ja na mascara 
    //de soluÃ§oes, setando -1 no espaÃ§o de soluÃ§oes
    //mostarndo uma soluÃ§Ã£o impossivel
    public static void setRestricoesDisciplinas(List<DisciplinaRestricaoID> disciplina){
        for (int i = 0; i < disciplina.size(); i++) {
            DisciplinaRestricaoID aux = disciplina.get(i);
            disciplinas[aux.getDisciplina()][aux.getTimeslot()] = -1;
        }
    }
    
    
    //implementar restriÃ§oes para as salas setando ja na mascara 
    //de soluÃ§oes, setando -1 no espaÃ§o de soluÃ§oes
    //mostarndo uma soluÃ§Ã£o impossivel
     public static void setRestricoesSalas(List<SalaRestricaoID> sala){
        for (int i = 0; i < sala.size(); i++) {
            SalaRestricaoID aux = sala.get(i);
            salas[aux.getSala()][aux.getTimeslot()] = -1;
        } 
    }
     
     //valida softconstraint de estudantes,
     //quantos estudantes estão matriculados em uma disciplina
     //retornando a função fitness
     public static int softConstraints(List<AlunoDisciplinaID> alunos,int n_alunos){
         int fitness = 0;
         int[] capacidade = new int[n_salas];//verificar capacidade depois
         int[][] alunosTimeslot = new int[n_alunos][n_timeslots];
         
         for (int i = 0; i < alunos.size(); i++) { 
            //fitness = fitness + calculaSoftConstraints(alunosTimeslot,capacidade,alunos.get(i));
         }
         
         return fitness;
         
     }
     
     //verifica se é possivel inserir um aluno em uma disciplina
     public static int calculaSoftConstraints(int[][] mascara,int [] capacidade, AlunoDisciplinaID aluno){
         
         //retorna a disciplina que o aluno quer cursar e o seu timeslot
         DisciplinaTimeSlotID disc = disciplinaTimeSlot.get(aluno.getDisciplina());
         int timeslot = disc.getTimeslot();
         
         //verifica se o aluno esta disponivel naquele horario e 
         //verifica se tem vaga na disciplina
         if(mascara[aluno.getAluno()][timeslot]  != 1 ){//aluno esta disponivel naquele horario
            if(capacidade[timeslot]<50){//verifica se tem vaga na sala
                mascara[aluno.getAluno()][timeslot] = 1;
                capacidade[timeslot] = capacidade[timeslot] +1; 
                return 0;
            }
         }
        
         return SOFTCONSTRAINT;
     }
     
//retorna um time slot para a professores dado o id do professor na mascara de solucao
     public static int  timeSlotLivreProfessor(int professor){
         List<Integer> timeslots = new ArrayList<Integer>();
         int retorno = -1;
         for (int i = 0; i < n_timeslots; i++) {
             if(professores[professor][i] == 0)
                timeslots.add(i);//adiciona o id do professor
         }
         
         Random r = new Random();
         int ponto = r.nextInt(timeslots.size());//seleciona um valor aleatorio
         retorno = timeslots.get(ponto);
         return retorno;
     }
//retorna um time slot para a sala dado o id da sala na mascara de solucao
    public static int timeSlotLivreSala(int sala){
         List<Integer> timeslots = new ArrayList<Integer>();
         int retorno = -1;
         for (int i = 0; i < n_timeslots; i++) {
             if(salas[sala][i] == 0)
                timeslots.add(i);//adiciona o id da sala disponivel
         }
         
         Random r = new Random();
         int ponto = r.nextInt(timeslots.size());//seleciona um ponto aleatorio
         retorno = timeslots.get(ponto);
         return retorno;
     }
//retorna um time slot para a disciplina dado o id da disciplina na mascara de solucao
    public static int timeSlotLivreDisciplina(int disciplina){
         List<Integer> timeslots = new ArrayList<Integer>();
         int retorno = -1;
         for (int i = 0; i < n_timeslots; i++) {
             if(disciplinas[disciplina][i] == 0)
                timeslots.add(i);//adiciona o id da disciplina
         }
         
         Random r = new Random();
         int ponto = r.nextInt(timeslots.size());//seleciona um valor aleatorio
         retorno = timeslots.get(ponto);
         return retorno;
     }
        
}

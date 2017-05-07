package algoritmoGenetico;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import objetos.AlunoDisciplina;
import objetos.Cursos;
import objetos.DisciplinaAluno;

import objetos.DisciplinaRestricao;
import objetos.Disciplinas;
import objetos.DocenteRestricao;
import objetos.Docentes;
import objetos.Estudantes;
import objetos.SalaRestricao;
import objetos.Salas;
import objetos.Timeslot;
import timetabling.Filetomemrest;

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
// valores na mascara de solução 1 = alocado, 0 disponivel, -1 horario invalido
public class Solucao {

    
   private static final int  HARDCONSTRAINT  = -10;//penalidade hardconstraint
   private static final int  SOFTCONSTRAINT  = -1; //penalidade softconstraint
   
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
   public static Hashtable <Integer, Integer> disciplinaTimeSlot ;            //HashMap para pegar o timeslot de uma determinada disciplina
   public static Hashtable <Integer, Integer> quantidadeProfessorDisciplina ; //HashMap para validar a quantidade de disciplinas por professor
   public static Hashtable <Integer, Integer> disciplinaSala;                 //disciplina - sala
   public static Hashtable <Integer, Integer> disciplinaProfessor;            //disciplina - professor
   
   //Lista das salas para cada tipo
    private static List<Integer> salaComum;
    private static List<Integer> laboratorioInformatica;
    private static List<Integer> laboratorioEspecificoEC;
    private static List<Integer> laboratorioEspecificoEE;
    private static List<Integer> laboratorioEspecificoEM;
    private static List<Integer> laboratorioEspecificoEG;
    private static List<Integer> laboratorioOutro;
    
    private static Hashtable <Integer, List<Integer>> disciplinaProfessores;//retorna os professores que pode ministrar uma determinada disciplina
    
    private static boolean horarioValido;//variavel setada para verificar se nenhuma hardconstraint foi afetada
                                         //mostrando um horario valido
    //inicializa com timeslots, numero de salas e numero de professores
   public static void construirMapSolucao(int n_timeslots,int n_salas,int n_professores,int n_disciplinas){
      
         horarioValido = false;
         
        //tamanho da matriz
        Solucao.n_timeslots            = n_timeslots; 
        Solucao.n_salas                = n_salas;
        Solucao.n_professores          = n_professores;
        Solucao.n_disciplinas          = n_disciplinas;
            
        //matrizes
         salas        = new byte[n_salas][n_timeslots];        //sala - horario
         professores  = new byte[n_professores][n_timeslots];  //professores - timeslot
         disciplinas  = new byte[n_disciplinas][n_timeslots];  //disciplinas - timeslot
         
        //inicializar variaveis  
        salaComum               = new ArrayList<Integer>();
        laboratorioInformatica  = new ArrayList<Integer>();
        laboratorioEspecificoEC = new ArrayList<Integer>();
        laboratorioEspecificoEE = new ArrayList<Integer>();
        laboratorioEspecificoEM = new ArrayList<Integer>();
        laboratorioEspecificoEG = new ArrayList<Integer>();
        laboratorioOutro        = new ArrayList<Integer>();
        
        disciplinaTimeSlot            = new Hashtable <>();
        quantidadeProfessorDisciplina = new Hashtable <>();
        disciplinaProfessores         = new Hashtable <>();
        disciplinaSala                = new Hashtable <>();
        disciplinaProfessor           = new Hashtable <>();
        
        //iniciar Hashtable disciplina professoros com uma lista
        for (int i = 0; i < n_disciplinas; i++) {
            List<Integer> aux = new ArrayList<>();
            getDisciplinaProfessor().put(new Integer(i), aux);
        }
        
         Timeslot.timeSlotsPeriodo();//iniciar estruturas do timeslot
         preencherMascaraRestricoes();//preecher mascara com as retriçoes apenas de horarios
         tipoSalaSalaDisciplina();    //preencher listas de salas
         preencherDisciplinaProfessores();      //criar hashtable disciplina lista de professores que podem ministrar aquela disciplinas
        //as restrições precisam ser setadas apenas uma vez na mascara
        restricaoHorarioCurso();
        setRestricoesProfessores(Filetomemrest.docrest); 
        setRestricoesDisciplinas(Filetomemrest.discirest); 
        setRestricoesSalas(Filetomemrest.salarest); 
             
    }
    
  //inicializa as mascaras
    private static void preencherMascaraRestricoes(){

        for (int i = 0; i < n_timeslots; i++) {
            for (int j = 0; j < n_salas; j++) {
                salas[j][i] = 0;
            }
        }
        
       for (int i = 0; i < n_timeslots; i++) {
            for (int j = 0; j < n_professores; j++) {
                professores[j][i] = 0;
            }
        } 
       
      for (int i = 0; i < n_timeslots; i++) {
            for (int j = 0; j < n_disciplinas; j++) {
                disciplinas[j][i] = 0;
            }
        } 
    }
   
    //iniciar a marcara de validaÃ§Ã£o de schedule todos as matrizes
    //soluÃ§Ã£o do tipo (sala,horario),(professor,horario)
    //metodo mais demorado
    public static void initSolucao(){

        for (int i = 0; i < n_timeslots; i++) {
            for (int j = 0; j < n_salas; j++) {
                if(salas[j][i] == 1){
                    salas[j][i] = 0;
                }
                
            }
        }
        
       for (int i = 0; i < n_timeslots; i++) {
            for (int j = 0; j < n_professores; j++) {
                 if(professores[j][i] == 1){
                     professores[j][i] = 0;
                }
               
            }
        } 
       
      for (int i = 0; i < n_timeslots; i++) {
            for (int j = 0; j < n_disciplinas; j++) {
                if(disciplinas[j][i] == 1){
                     disciplinas[j][i] = 0;
                }
                
            }
        } 

       limparHashTable();
       
    }
     
    //iniciar a mascara de solução
    //dado um individuo ja preenchido
    //metodo mais rapido
     public static void initSolucaoIndividuo(Gene[] genes){
         
         for (int i = 0; i < genes.length; i++) {
            Gene gene = genes[i];
            
            int disciplina = gene.getDisciplina();//id disciplina
            int professor  = gene.getProfessor();//id professor
            int timeslot   = gene.getTimeslot();//id timeslot
            int sala       = gene.getSala();//id sala
            
            if(disciplinas[disciplina][timeslot] == 1){
                disciplinas[disciplina][timeslot] = 0;
            }
            
            if(professores[professor][timeslot] == 1){
                professores[professor][timeslot] = 0;
            }
            
            if(salas[sala][timeslot] == 1){
                salas[sala][timeslot] = 0;
            }
                       
         }
        limparHashTable();  
    }
    
    //limpar Hashtables utilizadas para cada solução
    private static void limparHashTable(){
       disciplinaTimeSlot.clear();//limpar HashTable
       quantidadeProfessorDisciplina.clear();//limpar hashtable
       disciplinaSala.clear();
       disciplinaProfessor.clear();
       
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
        disciplinaTimeSlot.put(disciplina,timeslot);
        disciplinaSala.put(disciplina,sala);
        disciplinaProfessor.put(disciplina, professor);
        
        //seta a quantidade de professore por disciplina
        //ou seja o professor seleciona tem mais uma disciplina na grade dele
        if(quantidadeProfessorDisciplina.get(professor) !=null)
           quantidadeProfessorDisciplina.put(professor,quantidadeProfessorDisciplina.get(professor)+1);
        else
           quantidadeProfessorDisciplina.put(professor,1);
        
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
        if(professores[professor][timeslot]  == 1 && professores[professor][timeslot] == -1){              
            valor = false;    
        }
        else{
             if(quantidadeProfessorDisciplina.get(professor) !=null)
                if(quantidadeProfessorDisciplina.get(professor) >= Docentes.MAXIMO_DISCIPLINAS_PROFESSOR)
                    valor =false;
        }
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
        boolean retorno = true;
        int sala = gene.getSala();
        int professor = gene.getProfessor();
        int timeslot = gene.getTimeslot();
        int disciplina = gene.getDisciplina();
               
        if(professores[professor][timeslot]  == 0){
            //se aquele professor tiver ministrado mais do que a quantidade possivel
            if(quantidadeProfessorDisciplina.get(professor) !=null)
                if(quantidadeProfessorDisciplina.get(professor) >= Docentes.MAXIMO_DISCIPLINAS_PROFESSOR)
                    retorno = false;
        }
          
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
    
    //Função que calcula a função fitnees verificar choque de horarios
    public static int calculaFitness(Gene genes[]){
        horarioValido = true;
        int fitness = 0;
        initSolucaoIndividuo(genes);//iniciar mascara de solução
        
        //Hardconstraits conflito de horarios
        for (int i = 0; i < genes.length; i++) {
            fitness = fitness + VerificaPonto(genes[i]);
        }
        
        //Softconstraits alunos choque de horario e capacidade disciplina
        if(horarioValido){  
            //se o horario for valido o fitness == 0
            //nesse caso calcula as softconstrants
            //para esta aplicação
            //não há hardconstrant para choque de estudantes
            //para um mesmo horario a mascara de estudantes ja esta
            //validando essa constrant
            //no caso so tem as softsconstrants para estudantes matriculados
            //em uma disciplina , onde o maximo de penalidade é a quantidade 
            //de alunos
            fitness = fitness +softConstraints(Estudantes.getAlunosDisciplinas(),Estudantes.getNumeroAlunos());
        }
        else{
            // se o horario não for valido soma mais a quantidade de estudante
            //pois o maximo de softconstrants que pode ocorrer 
            //é se nenhum estudante for matriculado então soma a penalidade
            //maxima das softconstrants
            //fitnes = Somatorio das hardsconstrants + o maximo de penalidade das softsconstrants
            //para esse caso
            fitness =fitness - Estudantes.getNumeroAlunos();//by Raquel hue
        }
        
        
        return fitness;
    }
    
    //FunÃ§Ã£o para calcular a validade da solução
    //inserindo valores na soluÃ§Ã£o, se nÃ£o estiver preenchida o espaÃ§o de soluÃ§Ã£o
    //nÃ£o pontua o fitness, e seta o valor no espaÃ§o de soluÃ§Ãµes retornando verdadeiro
    //caso contrario pontua negativamente o horario
    //Hards Constraints -- penalidade maior
    public static int VerificaPonto(Gene gene){
        int fitness = 0;
        boolean valor = true;
        int sala = gene.getSala();
        int professor = gene.getProfessor();
        int timeslot = gene.getTimeslot();
        int disciplina = gene.getDisciplina();
        
        
        if(salas[sala][timeslot] == 1 || salas[sala][timeslot] == -1 ){ // sala ja esta sendo utilizada naquele horario
            fitness = fitness + HARDCONSTRAINT;
            valor = false;
            horarioValido = false;
        }
        if(professores[professor][timeslot]  == 1 || professores[professor][timeslot] == -1){//professor ja esta ministrando aula naquele horario
            fitness = fitness + HARDCONSTRAINT;
            valor = false;
            horarioValido = false;
        }
        if(disciplinas[disciplina][timeslot]  == 1 && disciplinas[disciplina][timeslot] == -1){
            fitness = fitness + HARDCONSTRAINT;
            valor = false;
            horarioValido = false;
         }
         
        if(valor == true)
            setValor(gene);
            
        return fitness;
    }
       
    //implementar restriÃ§oes dos professores setando ja na mascara 
    //de soluÃ§oes, setando -1 no espaÃ§o de soluÃ§oes
    //mostarndo uma soluÃ§Ã£o impossivel
    //recebe a lista de docentes e uma lista de restriçoes
    private static void setRestricoesProfessores(List<DocenteRestricao> docente){
        
        for (int i = 0; i < docente.size(); i++) {
            DocenteRestricao aux = docente.get(i);//get codigo Objeto
            List timeslot        = aux.getTimeslot();//codigos do timeslot
            int professor        = aux.getDocente() -1;//id do professor
            
            for (int j = 0; j < n_timeslots; j++) {//iniciar timeslots indisponiveis para o professor
                professores[professor][j] = -1; 
            }
            
            for (int j = 0; j < timeslot.size(); j++) {//rodar a lista de codigos dos timeslots
                int id = (int) timeslot.get(j) -1 ;//id do timeslot codigo -1
                professores[professor][id] = 0;
            }
            
        }
    }
    
    //implementar restriÃ§oes as disciplinas setando ja na mascara 
    //de soluÃ§oes, setando -1 no espaÃ§o de soluÃ§oes
    //mostarndo uma soluÃ§Ã£o impossivel
    public static void setRestricoesDisciplinas(List<DisciplinaRestricao> disciplina){
        
        for (int j = 0; j < disciplina.size(); j++) {
                
        DisciplinaRestricao restricao = disciplina.get(j);//pegar o objeto
        List<Integer> ids = getRestricoesDisciplinasIds(restricao.getDisciplina());//retorna os ids da disciplina
         //setar as restrições
            List<Integer> timeslots = restricao.getTimeslot();//pegas os codigos dos timeslots
            
                //inicializar as disciplinas
                 for (int i = 0; i < ids.size(); i++) {
                     int id  = ids.get(i);
                     for (int k = 0; k < n_timeslots; k++) {                   
                         disciplinas[id][k]  =-1;
                     }
                 }

                 for (int i = 0; i < ids.size(); i++) {
                     for (int k = 0; k < timeslots.size(); k++) {
                         int idTimeslot = timeslots.get(k).intValue() -1;
                         int idDisciplina = ids.get(i).intValue();  
                         disciplinas[idDisciplina][idTimeslot]  = 0;//setar o valor disponivel
                     }
                 }
  
        }
        
    }
    
    //retorna os ids das disciplinas o codigo da disciplina disciplina restrição
     public static List<Integer> getRestricoesDisciplinasIds(int codigoRestricao){
         
         List<Integer> ids = new ArrayList<Integer>();//lista dos ids das disciplinas
               
        for (int i = 0; i < Disciplinas.D.size(); i++) {//i == id
            Integer key = Disciplinas.D.get(i);//key valor de D, id == indice io id i tem a chave get(i)
            String codigoDisciplina = Disciplinas.disciplinacodigo.get(key);//pegar o codigo da disciplina
            int codigoDisciplinaInt = Integer.valueOf(codigoDisciplina.trim());//codigo disciplina 
            //
            if(codigoRestricao == codigoDisciplinaInt ){
               //System.out.println("Codigo: "+codigoDisciplina +" codigoDisciplinaInt:"+codigoDisciplinaInt);
                ids.add(i);//adiciona o id da disciplina dado o codigo
            }
        }
     
        return ids;
     }
    
    //implementar restriÃ§oes para as salas setando ja na mascara 
    //de soluÃ§oes, setando -1 no espaÃ§o de soluÃ§oes
    //mostarndo uma soluÃ§Ã£o impossivel
     private static void setRestricoesSalas(List<SalaRestricao> _salas){
         
         for (int i = 0; i < _salas.size(); i++) {
            SalaRestricao aux = _salas.get(i);//get Objeto
            List timeslot = aux.getTimeslot();//codigos do timeslot
            int sala = aux.getSala()-1;//id da sala
            
            for (int j = 0; j < n_timeslots; j++) {//iniciar timeslots indisponiveis para as salas
                salas[sala][j] = -1; 
            }
            
            for (int j = 0; j < timeslot.size(); j++) {//rodar a lista de codigos dos timeslots
                int id = (int) timeslot.get(j) -1 ;//id do timeslot
                salas[sala][id] = 2;
            }
            
        }
    }
     
     //valida softconstraint de estudantes,
     //quantos estudantes estão matriculados em uma disciplina
     //retornando a função fitness
     public static int softConstraints(List<AlunoDisciplina> alunos,int n_alunos){
         int fitness = 0;
         int[][] alunosTimeslot = new int[n_alunos][n_timeslots];//mascara estudantes validar choque de horarios
         int[][] disciplinaAluno = new int[n_disciplinas][n_alunos];//mascara para a capacidade preenchida pelos estudantes
        
         for (int i = 0; i < alunos.size(); i++) { 
            fitness = fitness + calculaSoftConstraintsAluno(alunosTimeslot,disciplinaAluno,alunos.get(i));
         }
         
         fitness = fitness + calculaSoftConstraintsCapacidadeSala(n_alunos,disciplinaAluno);
         
         return fitness;
     }
          
      //verifica se é possivel inserir um aluno em um slot de uma disciplina
     public static int calculaSoftConstraintsAluno(int[][] mascara,int[][] mascaraDisciplinas, AlunoDisciplina aluno){
      
         //retorna a disciplina que o aluno quer cursar e o seu timeslot
         int fitness = 0;
         List<Integer> ids = new ArrayList<>();
         List<Integer> disciplinasAluno = aluno.getDisciplina();//lista de disciplinas que o aluno quer cursar
         
         for (int k = 0; k < disciplinasAluno.size(); k++) {

             List<Integer> aux = getRestricoesDisciplinasIds(disciplinasAluno.get(k).intValue());//pegar os ids das disciplinas dado o codigo
             
             for (int j = 0; j < aux.size(); j++) {
                 ids.add(aux.get(j));
             }
             
             if(ids.size()>0){
                preencherHorarioEstudante(ids,mascara,mascaraDisciplinas,aluno);  
             }
              ids.clear();//limpa o array de ids para proxima disciplina
         }
         
         return fitness;
     }
     
    //tenta matricular um estudante em uma disciplina, caso contrario desmatricula
    private static int preencherHorarioEstudante(List<Integer> ids, int[][] mascara,int [][] mascaraDisciplinas, AlunoDisciplina aluno){
            int fitness = 0;
            for (int i = 0; i < ids.size(); i++) {//id da disciplina
                     Integer disciplinaTimeslot = disciplinaTimeSlot.get(ids.get(i));//pegar o timeslot daquela disciplina


                     if(disciplinaTimeslot!=null){//se for igual nulo não foi possivel inserir a disciplina no timeslot
                         int timeslot = disciplinaTimeslot;//id do timeslot
                         
                        //verifica se o aluno esta disponivel naquele horario e 
                        //verifica se tem vaga na disciplina
                        int alunoID = aluno.getAluno() - 1;

                        if(mascara[alunoID][timeslot]  == 0 ){//aluno esta disponivel naquele horario
                               mascara[alunoID][timeslot] = 1;
                               mascaraDisciplinas[ids.get(i)][alunoID] = 1;
                        }
                        else{
                           fitness = fitness + SOFTCONSTRAINT;
                           limparHorariosAluno(ids,mascara,alunoID);
                           limparDisciplinaAluno(ids,mascaraDisciplinas,alunoID);
                           break;
                        }

                     }
                     else{
                         int alunoID = aluno.getAluno() - 1;
                         fitness = fitness + SOFTCONSTRAINT;
                         limparHorariosAluno(ids,mascara,alunoID);
                         limparDisciplinaAluno(ids,mascaraDisciplinas,alunoID);
                         break;
                     }

                 }
            return fitness;
     }
     
     //Limpar os horario dado uma lista de ids
     private static void limparHorariosAluno( List<Integer> ids,int[][] mascara, int alunoId){
         for (int l = 0; l < ids.size(); l++) {
             Integer timeslot = disciplinaTimeSlot.get(ids.get(l));
            if(timeslot !=null){         
                mascara[alunoId][timeslot] =0;
            }
        }
     }
     
     //Limpar os horario dado uma lista de ids
     private static void limparDisciplinaAluno( List<Integer> ids,int[][] mascara, int alunoId){
         for (int l = 0; l < ids.size(); l++) {
                mascara[ids.get(l)][alunoId] = 0;
            }
     }
     
 
     
     
     //verifica se é possivel inserir todos os alunos em uma sala
     //matricular estudantes
     public static int calculaSoftConstraintsCapacidadeSala(int n_alunos,int[][] disciplinaAlunos){
         int fitness = 0;
         int quantidade = 0;
         
         for (int i = 0; i < n_disciplinas; i++) {
             quantidade = 0;
         if(disciplinaSala.get(i) !=null){//verifica sala no timeslot i     
                
                for (int j = 0; j < n_alunos; j++) {//quantidade de alunos naquele timeslot
                    if(disciplinaAlunos[i][j] == 1)
                        quantidade ++;
                }

                int    idSala  = disciplinaSala.get(i);//ve o id da sala no timeslot
                String keySala = (idSala+1)+"";//ve o id da sala no timeslot
                String capacidade = Salas.salacap.get(keySala);//pega a capacidade da sala

                if(quantidade > Integer.valueOf(capacidade) ){//se a quantidade for maior que a capacidade
                    int valor = quantidade - Integer.valueOf(capacidade);
                    fitness = fitness + SOFTCONSTRAINT*valor;
                }
            }
         }
         
         return fitness;
     }
     
//retorna um time slot para a professores dado o id do professor na mascara de solucao
     public static int  timeSlotLivreProfessor(int professor){
         List<Integer> timeslots = new ArrayList<>();
         int retorno = -1;
         for (int i = 0; i < n_timeslots; i++) {
             if(professores[professor][i] == 0)
                timeslots.add(i);//adiciona o id do professor
         }
         
         if(timeslots.size() != 0){
            Random r = new Random();
            int ponto = r.nextInt(timeslots.size());//seleciona um valor aleatorio
            retorno = timeslots.get(ponto);
         }
     
         return retorno;
     }
     
     //retorna uma lista de timeslots para a professores dado o id do professor na mascara de solucao
     public static List<Integer>  timeSlotLivreProfessorList(int professor){
         List<Integer> timeslots = new ArrayList<>();
         for (int i = 0; i < n_timeslots; i++) {
             if(professores[professor][i] == 0)
                timeslots.add(i);//adiciona o id do professor
         }
         
         
         return timeslots;
     }
     
//retorna uma time slot para a sala dado o id da sala na mascara de solucao
    public static int timeSlotLivreSala(int sala){
         List<Integer> timeslots = new ArrayList<>();
         int retorno = -1;
         for (int i = 0; i < n_timeslots; i++) {
             if(salas[sala][i] == 0)
                timeslots.add(i);//adiciona o id da sala disponivel
         }
         if(timeslots.size() !=0){
            Random r = new Random();
            int ponto = r.nextInt(timeslots.size());//seleciona um ponto aleatorio
            retorno = timeslots.get(ponto);
         }
         
         return retorno;
     }
    
    //retorna uma lista de  timeslots livres para a sala dado o id da sala na mascara de solucao
    public static List<Integer> timeSlotLivreSalaList(int sala){
         List<Integer> timeslots = new ArrayList<>();
         for (int i = 0; i < n_timeslots; i++) {
             if(salas[sala][i] == 0)
                timeslots.add(i);//adiciona o id do timeslot da sala disponivel
         }
         return timeslots;
     }
//retorna um time slot para a disciplina dado o id da disciplina na mascara de solucao
    public static int timeSlotLivreDisciplina(int disciplina){
         List<Integer> timeslots = new ArrayList<>();
         int retorno = -1;
         for (int i = 0; i < n_timeslots; i++) {
             if(disciplinas[disciplina][i] == 0)
                timeslots.add(i);//adiciona o id do timeslot da disciplina
         }
         if(timeslots.size() !=0){
            Random r = new Random();
            int ponto = r.nextInt(timeslots.size());//seleciona um valor aleatorio
            retorno = timeslots.get(ponto);
         }
         
         return retorno;
     }
    
    //retorna uma lista de timeslots para a disciplina dado o id da disciplina na mascara de solucao
    public static List<Integer> timeSlotLivreDisciplinaList(int disciplina){
         List<Integer> timeslots = new ArrayList<Integer>();

         for (int i = 0; i < n_timeslots; i++) {
             //System.out.print(" "+disciplinas[disciplina][i]);
             if(disciplinas[disciplina][i] == 0)
                timeslots.add(i);//adiciona o id do timeslot da disciplina
         }
        // System.out.println("");
         return timeslots;
     }
    
        public static List<Integer> timeSlotDisciplinaList(int disciplina){
         List<Integer> timeslots = new ArrayList<>();

         for (int i = 0; i < n_timeslots; i++) {
             
             if(disciplinas[disciplina][i] == 0 || disciplinas[disciplina][i] == 1)
                timeslots.add(i);//adiciona o id do timeslot da disciplina
         }
         return timeslots;
     }
    
    //cria listas auxiliares para separar todas as salas pelo seu tipo
    private static void tipoSalaSalaDisciplina(){
        
        for (int i = 0; i < n_salas; i++) {
            
        String tipoSalaStr = Salas.salatipo.get((i+1)+"");//procura a sala
        int tipoSala = Integer.valueOf(tipoSalaStr);//pega o tipo da sala
        
        //adiciona o id da sala nas listas auxiliares
        if(tipoSala == Salas.SALA_COMUM)
             salaComum.add(i);
        else if(tipoSala == Salas.LABORATORIO_DE_INFORMATICA)
             laboratorioInformatica.add(i);
        else if(tipoSala == Salas.LABORATORIO_ESPECIFICO_EC)
             laboratorioEspecificoEC.add(i);
        else if(tipoSala == Salas.LABORATORIO_ESPECIFICO_EE)
             laboratorioEspecificoEE.add(i);
        else if (tipoSala == Salas.LABORATORIO_ESPECIFICO_EM)
             laboratorioEspecificoEM.add(i);
        else if( tipoSala == Salas.LABORATORIO_ESPECIFICO_GERAL)
             laboratorioEspecificoEG.add(i);
        else if( tipoSala == Salas.LABORATORIO_OUTRO)
             laboratorioOutro.add(i);
        }    
    }
    
    //retorna todas as possiveis salas que pode ser usada para dado o tipo da sala
    public static List<Integer>  getSalaDisciplina(int tipoSala){
        
        if(tipoSala == Salas.SALA_COMUM)
            return salaComum;
        else if(tipoSala == Salas.LABORATORIO_DE_INFORMATICA)
            return laboratorioInformatica;
        else if(tipoSala == Salas.LABORATORIO_ESPECIFICO_EC)
            return laboratorioEspecificoEC;
        else if(tipoSala == Salas.LABORATORIO_ESPECIFICO_EE)
            return laboratorioEspecificoEE;
        else if (tipoSala == Salas.LABORATORIO_ESPECIFICO_EM)
            return laboratorioEspecificoEM;
        else if( tipoSala == Salas.LABORATORIO_ESPECIFICO_GERAL)
            return laboratorioEspecificoEG;
        else if( tipoSala == Salas.LABORATORIO_OUTRO)
            return laboratorioOutro;
        
        return null;
    }
    
    //preenche a lista de professores disciplinas
    private static void preencherDisciplinaProfessores(){
        discipinaProfessores(Docentes.docentedisc1);
        discipinaProfessores(Docentes.docentedisc2);
        discipinaProfessores(Docentes.docentedisc3);
        discipinaProfessores(Docentes.docentedisc4);
        discipinaProfessores(Docentes.docentedisc5);
    }
    
    //preenche a lista de professores disciplinas
    private static void discipinaProfessores(Hashtable <String, String> docentedisc){
              
        //inicializar o hashMap
            
        for (int i = 0; i < n_professores; i++) {
            String disciplina =    docentedisc.get((i+1)+"");
            
            if(disciplina !=null){//caso o professor não tenha a primeira disciplina
                List<Integer> ids = getRestricoesDisciplinasIds(Integer.valueOf(disciplina));//id da disciplina dado o codigo dela
                
                for (int j = 0; j < ids.size(); j++) {//ids das disciplinas
                    Integer id = new Integer(ids.get(j));//id da disciplina
                   
                    List<Integer> aux = getDisciplinaProfessor().get(id);//pega a lista referente aquele id                   
                    aux.add(i);//aducionando o professor                  
                    getDisciplinaProfessor().put(id, aux);
                }
            }
            
        }
         
    }
    
    //retorna a lista de professores que podem dar aquela disciplina dado o id da disciplina
    public static List<Integer> professorDisciplina(int disciplina){
        Integer id = new Integer(disciplina);
        return getDisciplinaProfessor().get(id);
    }
    //verifica o tipo de curso que é a disciplina 
    //colocando na mascara de solução os horarios que não pode ser ofertadas as disciplinas
    //exemplo o curso de Eng COmputação matutino so pode ofertar disciplinas
    //no horarios de seg - sab das 7:00 as 12:00
    //o metodo verifica o tipo de disciplina pertence 
    //a um tipo de curso setando os horarios invalidos no 
    //mapa de solução
    public static void restricaoHorarioCurso(){
        //id da disciplina na mascara de solução vai ser i
        for (int i = 0; i < Disciplinas.D.size(); i++) {
            String str = Disciplinas.disciplinacurso.get(Disciplinas.D.get(i));//pega a o codigo da disciplina dado o id i
            String turnoDis = Cursos.cursoturnos.get(str).trim();//turno da disciplina i
            
            horarioDisciplinaRestricao(turnoDis,i);
        }
        
        //setar restrição almoço para professor e salas
        
        horarioAlmocoRestricaoProfessores();
        horarioAlmocoRestricaoSalas();
                    
    }
    
    public static void horarioDisciplinaRestricao(String turnoDisciplina,int idDisciplina){
        
        if(Integer.valueOf(turnoDisciplina) == Cursos.MATUTINO ){
                horarioVespertinoRestricaoDisciplinas(idDisciplina);
                horarioNoturnoRestricaoDisciplinas(idDisciplina);                          
            }        
            else if(Integer.valueOf(turnoDisciplina) == Cursos.VESPERTINO){
                horarioMatutinoRestricaoDisciplinas(idDisciplina);
                horarioNoturnoRestricaoDisciplinas(idDisciplina);
                horarioSabadoDisponivelDisciplinas(idDisciplina);
            }          
            else if(Integer.valueOf(turnoDisciplina) == Cursos.MATUTINO_VESPERTINO){
                horarioNoturnoRestricaoDisciplinas(idDisciplina);
            }
            else if(Integer.valueOf(turnoDisciplina) == Cursos.NOTURNO){
                horarioMatutinoRestricaoDisciplinas(idDisciplina);
                horarioVespertinoRestricaoDisciplinas(idDisciplina);
                horarioSabadoDisponivelDisciplinas(idDisciplina);
            }
            else if(Integer.valueOf(turnoDisciplina) == Cursos.MATUTINO_NOTURNO){
                horarioVespertinoRestricaoDisciplinas(idDisciplina);
            }     
            else if(Integer.valueOf(turnoDisciplina) == Cursos.VESPERTINO_NOTURNO){
                horarioMatutinoRestricaoDisciplinas(idDisciplina);
                horarioSabadoDisponivelDisciplinas(idDisciplina);
            }
            
            horarioAlmocoRestricaoDisciplinas(idDisciplina);
    
    }
    
    public static void horarioVespertinoRestricaoDisciplinas(int id){
        List<Integer> aux = Timeslot.getVespertino();//codigos do timeslot              
                for (int j = 0; j < aux.size(); j++) {
                    int idTimeslot = aux.get(j) -1; 
                    disciplinas[id][idTimeslot] = -1;
         }
    }
    
      public static void horarioNoturnoRestricaoDisciplinas(int id){
        List<Integer> aux = Timeslot.getNoturno();
                
        for (int j = 0; j < aux.size(); j++) {
                    int idTimeslot = aux.get(j) -1; 
                    disciplinas[id][idTimeslot] = -1;
        } 
    }
    
    public static void horarioMatutinoRestricaoDisciplinas(int id){
         List<Integer> aux = Timeslot.getMatutino();
                for (int j = 0; j < aux.size(); j++) {
                    int idTimeslot = aux.get(j) -1; 
                    disciplinas[id][idTimeslot] = -1;
                }
    }
   
    public static void horarioSabadoDisponivelDisciplinas(int id){
        List<Integer> aux = Timeslot.getSabado();
        for (int j = 0; j < aux.size(); j++) {
            int idTimeslot = aux.get(j) -1; 
            disciplinas[id][idTimeslot] = 0;
        }
    }
    
    public static void horarioAlmocoRestricaoDisciplinas(int id){
        List<Integer> aux = Timeslot.getAlmoco();
            for (int j = 0; j < aux.size(); j++) {
                int idTimeslot = aux.get(j) -1; 
                disciplinas[id][idTimeslot] = -1;  
        } 
    }
    
    public static void horarioAlmocoRestricaoProfessores(){
        List<Integer> aux = Timeslot.getAlmoco();
        for (int j = 0; j < n_professores; j++) {
            for (int i = 0; i < aux.size(); i++) {
                int idTimeslot = aux.get(i) -1; 
                professores[j][idTimeslot] = -1;  
            }        
        }
    }
   
    public static void horarioAlmocoRestricaoSalas(){
       List<Integer> aux = Timeslot.getAlmoco();
       for (int j = 0; j < n_salas; j++) {
            for (int i = 0; i < aux.size(); i++) {
                int idTimeslot = aux.get(i) -1; 
                salas[j][idTimeslot] = -1;  
            }  
        } 
    }   
    
    //Metodo usado para validar Gene
    //Validando sala que aquela disciplina pode ser ministrada
    //validando professor que pode ministrar aquela disciplina
    //O timeslot sera gerado aleatoriamente para um horario disponivel da disciplina
    //para um horario disponivel daquela disciplina
    //a mutação pode trocar o professor, sala ou a logica do timeslot
    public static void validaGene(Gene[] genes){
     
        initSolucao();
        
        Random random = new Random();
        
        int sizeGene = genes.length;
            
        int sizeDocentes = 0;
        int sizeSalas    = 0;
        int sizeTimeslot = 0;
        
        int sorteioDocente = 0;
        int sorteioSala = 0;
        int sorteioTimeslot = 0;
        
        List<Gene> aux = new ArrayList<Gene>();
        
        for (int i = 0; i < sizeGene; i++) {
            aux.add(genes[i]);
        }
                
        for (int i = 0; i < sizeGene; i++){
            //fazer uma selecao aleatoria para setar na mascara de solução
            Random r = new Random();
            int select = r.nextInt(aux.size());
            Gene gene = aux.get(select);
 //           Gene gene = genes[i];

            //verifica se o genes é valido
            boolean valor = isValorValido(gene);
            
            if(valor == false){
                
                //pega a disciplina do gene
                int disciplina = gene.getDisciplina();
               
                //lista dos professores que podem ministrar aquela disciplina
                List<Integer> listaProfessores = getDisciplinaProfessor().get(new Integer(disciplina));
                Integer tipoSala = Disciplinas.D2.get(disciplina);//tipo da sala para aquela disciplina
                //Lista de salas que aquela disciplina pode ser ministrada
                List<Integer> listaSalas      = getSalaDisciplina(tipoSala);
                
                List<Integer> listaTimeslotDisciplina = timeSlotLivreDisciplinaList(disciplina);

                sizeDocentes = listaProfessores.size();//pega os professores para a disciplina
                sizeSalas    = listaSalas.size();//pega as salas para a disciplina
                sizeTimeslot = listaTimeslotDisciplina.size();//pega os timeslots disponivel da disciplina
                
                //se o meu timeslot é valido
                boolean isDisciplina = Solucao.isDisciplinaValido(gene);
                
                 //se tem timeslot disponivel da disciplina
                //e e o timeslot da disciplina é invalido
                if(sizeTimeslot>0 ){
                    
                    if(!isDisciplina){
                        for (int j = 0; j < listaTimeslotDisciplina.size(); j++) 
                           gene.setTimeslot(listaTimeslotDisciplina.get(j));//timeslot da disciplina   
                           isDisciplina = Solucao.isDisciplinaValido(gene);

                           if(isDisciplina)
                               break;
                        }
                    }
                
                boolean isprofessor = Solucao.isProfessorValido(gene);
                boolean isSala= Solucao.isSalaValido(gene);
                  
                //se tem professor para ministrar aquela disciplina
                //e o timeslot do professor atual é invalido
                //então troca o professor aleatoriamente
                if(sizeDocentes != 0 ){//se diferente de zero atualiza o gene
                     if( !isprofessor ){
                         
                         for (int j = 0; j < sizeDocentes; j++) {
                             gene.setProfessor(listaProfessores.get(j));
                             isprofessor = Solucao.isProfessorValido(gene);
                             if(isprofessor)
                                 break;
                         }
                         
                        if(!isprofessor){ 
                            sorteioDocente = random.nextInt(sizeDocentes);
                            gene.setProfessor(listaProfessores.get(sorteioDocente));
                        }
                        
                     }
                }
                else{
                    for (int j = 0; j < n_professores; j++) {                                                
                        gene.setProfessor(j);
                        isprofessor = Solucao.isProfessorValido(gene);
                        if(isprofessor)
                         break;
                    }                 
                }
                
                //se tem sala para ministrar aquela disciplina
                //e o timeslot da sala atual é invalido
                //então troca a sala aleatoriamente
                if(sizeSalas !=0 ){//se diferente de zero atualiza o gene
                     
                    if(!isSala ){
                        for (int j = 0; j < sizeSalas; j++) {
                                 gene.setSala(listaSalas.get(j));
                                 isSala = Solucao.isSalaValido(gene);
                                 if(isSala)
                                     break;
                             }

                            if(!isSala){
                                    sorteioSala = random.nextInt(sizeSalas);
                                    gene.setProfessor(listaSalas.get(sorteioSala));
                                  }   


                            }
                        }
                    }
                  
                //seta na mascara o novo gente                
                valor = isValorValido(gene); //setar na mascara ???
                aux.remove(select);
            }
                    
    }
    
    /**
     * @return the disciplinaProfessor
     * retorna hash com os professores dado um id
     */
    public static Hashtable <Integer, List<Integer>> getDisciplinaProfessor() {
        return disciplinaProfessores;
    }
   
    public static List<DisciplinaAluno> getListaAlunosMatriculados(Gene[] genes,List<AlunoDisciplina> alunos){
         int n_alunos = Estudantes.getNumeroAlunos();

         int[][] alunosTimeslot = new int[n_alunos][n_timeslots];//mascara estudantes
         int[][] disciplinaAluno = new int[n_disciplinas][n_alunos];
         
         calculaFitness(genes);//preencher objetos necessarios
         
      
         for (int i = 0; i < alunos.size(); i++) { 
             //preencher objetos necessarios apenas para preencher os objetos
             //nao vai precisar do fitness
            preencherTabelaAluno(alunosTimeslot,disciplinaAluno,alunos.get(i));
            
         }
         
        List<DisciplinaAluno> aux = insereEstudante( n_alunos, disciplinaAluno);
         
        return aux;
    }
    

      //verifica se é possivel inserir um aluno em um slot de uma disciplina
     private static void preencherTabelaAluno(int[][] mascara,int[][] mascaraDisciplinas, AlunoDisciplina aluno){
         
         //retorna a disciplina que o aluno quer cursar e o seu timeslot
         List<Integer> disciplinasAluno = aluno.getDisciplina();//lista de disciplinas que o aluno quer cursar
         
         for (int k = 0; k < disciplinasAluno.size(); k++) {
             
             List<Integer> ids = getRestricoesDisciplinasIds(disciplinasAluno.get(k));//pegar os ids das disciplinas dado o codigo
                      
             if(ids.size()>0){
                preencherHorarioEstudante(ids,mascara,mascaraDisciplinas,aluno);
             }
      
         }
         
       
        
     }
     
      //verifica se é possivel inserir todos os alunos em uma sala
     public static List<DisciplinaAluno> insereEstudante(int n_alunos, int[][] disciplinaAlunos){
        
         
        List<DisciplinaAluno> disciplinasAlunos = new ArrayList<DisciplinaAluno>();
        int quantidade = 0;
         
         for (int i = 0; i < n_disciplinas; i++) {
             quantidade = 0;
         if(disciplinaSala.get(i) !=null){//verifica sala no timeslot i     
                
                int    idSala  = disciplinaSala.get(i);//ve o id da sala no timeslot
                String keySala = (idSala+1)+"";//ve o id da sala no timeslot
                String capacidade = Salas.salacap.get(keySala);//pega a capacidade da sala
                List<Integer> alunos = new ArrayList<Integer>();
                for (int j = 0; j < n_alunos; j++) {//quantidade de alunos naquele timeslot
                    if(disciplinaAlunos[i][j] == 1){
                        quantidade ++;
                        alunos.add(j+1);//setando o codigo do aluno
                        if(quantidade >= Integer.valueOf(capacidade)){
                            break;
                        }
                        
                    }
        
                }
                
                DisciplinaAluno aux = new DisciplinaAluno();
                Integer key = Disciplinas.D.get(i);
                String codigoDisciplina = Disciplinas.disciplinacodigo.get(key);
                Integer sala = disciplinaSala.get(i);
                String nomeSala = Salas.salasigla.get(""+(sala+1));
              
                Integer timeslot = disciplinaTimeSlot.get(i);
                Integer professor = disciplinaProfessor.get(i);
                String professorNome =Docentes.docentenome.get(""+(professor+1));
                String curso = Disciplinas.disciplinacurso.get(key);
                
                aux.setDisciplina(Integer.valueOf(codigoDisciplina.trim()));//setar o codigo da disciplina
                aux.setAlunos(alunos);//setar o codigo do aluno
                aux.setCurso(Integer.valueOf(curso.trim()));
                aux.setTimeslot(timeslot+1);//inserir o codigo do timeslot
                aux.setSala(nomeSala);//inserir o codigo da sala
                aux.setProfessor(professorNome);//inserir o codigo do professor
                disciplinasAlunos.add(aux);//inserir a lista de codigos dos alunos

            }
         }
         
         return disciplinasAlunos;
     }

    /**
     * @return the horarioValido
     */
    public static boolean isHorarioValido() {
        return horarioValido;
    }

}


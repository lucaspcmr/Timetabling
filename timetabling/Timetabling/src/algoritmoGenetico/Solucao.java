package algoritmoGenetico;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import objetos.AlunoDisciplina;
import objetos.Cursos;
import objetos.DisciplinaRestricao;
import objetos.DisciplinaTimeSlot;
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
   public static Hashtable <Integer, Integer> disciplinaTimeSlot ;
   public static Hashtable <Integer, Integer> professorQuantidadeDisciplina ;
   public static Hashtable <Integer, Integer> timeSlotSala ;
   public static Hashtable <Integer, List<Integer>> disciplinaProfessor;
   
    private static List<Integer> salaComum;
    private static List<Integer> laboratorioInformatica;
    private static List<Integer> laboratorioEspecificoEC;
    private static List<Integer> laboratorioEspecificoEE;
    private static List<Integer> laboratorioEspecificoEM;
    private static List<Integer> laboratorioEspecificoEG;
    private static List<Integer> laboratorioOutro;
    
    //Mascara de soluÃ§oes estrutura que contrem todas as mascaras 
    private static Object solucao[];
    
    //inicializa com timeslots, numero de salas e numero de professores
   public static void construirMapSolucao(int n_timeslots,int n_salas,int n_professores,int n_disciplinas){
       
        disciplinaTimeSlot = new Hashtable <Integer,Integer>();
        professorQuantidadeDisciplina = new Hashtable <Integer,Integer>();
        timeSlotSala = new Hashtable <Integer,Integer>();
        disciplinaProfessor = new Hashtable <Integer,List<Integer>>();
        
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
         
         solucao = new Object[3];
         solucao[0] = salas;
         solucao[1] = professores;
         solucao[2] = disciplinas;
         
        salaComum               = new ArrayList<Integer>();
        laboratorioInformatica  = new ArrayList<Integer>();
        laboratorioEspecificoEC = new ArrayList<Integer>();
        laboratorioEspecificoEE = new ArrayList<Integer>();
        laboratorioEspecificoEM = new ArrayList<Integer>();
        laboratorioEspecificoEG = new ArrayList<Integer>();
        laboratorioOutro        = new ArrayList<Integer>();
         
         preencherMascaraRestricoes();
         tipoSalaSalaDisciplina();
         
    }
    
  //colocar as restrições dos professores, salas, disciplinas e cursos
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
        //as restrições precisam ser setadas apenas uma vez na mascara
        //variaveis populçaõ criadas setar as restricoes no Mapa de soluções
        setRestricoesProfessores(Filetomemrest.docrest); 
        setRestricoesDisciplinas(Filetomemrest.discirest); 
        setRestricoesSalas(Filetomemrest.salarest); 

        restricaoHorarioCurso();
        disciplinaTimeSlot.clear();//limpar HashTable
        timeSlotSala.clear();;//limpar hashtable
        professorQuantidadeDisciplina.clear();//limpar hashtable      
    }
   
    //iniciar a marcara de validaÃ§Ã£o de schedule todos as matrizes
    //soluÃ§Ã£o do tipo (sala,horario),(professor,horario)
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

       disciplinaTimeSlot.clear();//limpar HashTable
       timeSlotSala.clear();;//limpar hashtable
       professorQuantidadeDisciplina.clear();//limpar hashtable
       
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
        timeSlotSala.put(timeslot, sala);
        
        //seta a quantidade de professores
        if(professorQuantidadeDisciplina.get(professor) !=null)
           professorQuantidadeDisciplina.put(professor,professorQuantidadeDisciplina.get(professor)+1);
        else
           professorQuantidadeDisciplina.put(professor,1);
        
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
             if(professorQuantidadeDisciplina.get(professor) !=null)
                if(professorQuantidadeDisciplina.get(professor) >= Docentes.MAXIMO_DISCIPLINAS_PROFESSOR)
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
            if(professorQuantidadeDisciplina.get(professor) !=null)
                if(professorQuantidadeDisciplina.get(professor) >= Docentes.MAXIMO_DISCIPLINAS_PROFESSOR)
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
    
    //Função que calcula a função fitnees
    public static int calculaFitnees(Gene genes[]){
        int fitness = 0;
        initSolucao();
        //Hardconstraits
        for (int i = 0; i < genes.length; i++) {
            fitness = fitness + VerificaPonto(genes[i]);
        }
        //Softconstraits
        fitness = fitness +softConstraints(Estudantes.getAlunosDisciplinas(),Estudantes.getNumeroAlunos());
        
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
    public static void setRestricoesProfessores(List<DocenteRestricao> docente){
        
        for (int i = 0; i < docente.size(); i++) {
            DocenteRestricao aux = docente.get(i);//get codigo Objeto
            List timeslot = aux.getTimeslot();//codigos do timeslot
            int professor = aux.getDocente() -1;//id do professor
            
            for (int j = 0; j < n_timeslots; j++) {//iniciar timeslots indisponiveis para o professor
                professores[professor][j] = -1; 
            }
            
            for (int j = 0; j < timeslot.size(); j++) {//rodar a lista de codigos dos timeslots
                int id = (int) timeslot.get(j) -1 ;//id do timeslot
                professores[professor][id] = 0;
            }
            
        }
    }
    
    //implementar restriÃ§oes as disciplinas setando ja na mascara 
    //de soluÃ§oes, setando -1 no espaÃ§o de soluÃ§oes
    //mostarndo uma soluÃ§Ã£o impossivel
    public static void setRestricoesDisciplinas(List<DisciplinaRestricao> disciplina){
        
        for (int j = 0; j < disciplina.size(); j++) {
                
        DisciplinaRestricao restricao = disciplina.get(j);
        List<Integer> ids = getRestricoesDisciplinasIds(restricao.getDisciplina());
        
        //inicializar as disciplinas
            for (int i = 0; i < ids.size(); i++) {
                for (int k = 0; k < n_timeslots; k++) {
                    disciplinas[ids.get(i)][k]  =-1;
                }
            }
            //setar as restrições
            List<Integer> timeslots = restricao.getTimeslot();
            for (int i = 0; i < ids.size(); i++) {
                for (int k = 0; k < timeslots.size(); k++) {
                    int idTimeslot = timeslots.get(k) -1;
                    disciplinas[ids.get(i)][idTimeslot]  =0;
                }
            }
            
        }
    }
    
    //retorna os ids das disciplinas o codigo da disciplina disciplina restrição
     public static List<Integer> getRestricoesDisciplinasIds(int codigoRestricao){
         
         List<Integer> ids = new ArrayList<Integer>();//lista dos ids das disciplinas
        
        for (int i = 0; i < Disciplinas.D.size(); i++) {
            Integer key = Disciplinas.D.get(i);//key valor de D, id == indice
            String codigoDisciplina = Disciplinas.disciplinacodigo.get(key+"");
            int codigoDisciplinaInt = Integer.valueOf(codigoDisciplina);
            if(codigoRestricao == codigoDisciplinaInt )
                ids.add(i);
        }
     
        return ids;
     }
    
    //implementar restriÃ§oes para as salas setando ja na mascara 
    //de soluÃ§oes, setando -1 no espaÃ§o de soluÃ§oes
    //mostarndo uma soluÃ§Ã£o impossivel
     public static void setRestricoesSalas(List<SalaRestricao> _salas){
         for (int i = 0; i < _salas.size(); i++) {
            SalaRestricao aux = _salas.get(i);//get codigo Objeto
            List timeslot = aux.getTimeslot();//codigos do timeslot
            int sala = aux.getSala()-1;//id da sala
            
            for (int j = 0; j < n_timeslots; j++) {//iniciar timeslots indisponiveis para as salas
                salas[sala][j] = -1; 
            }
            
            for (int j = 0; j < timeslot.size(); j++) {//rodar a lista de codigos dos timeslots
                int id = (int) timeslot.get(j) -1 ;//id do timeslot
                salas[sala][id] = 0;
            }
            
        }
    }
     
     //valida softconstraint de estudantes,
     //quantos estudantes estão matriculados em uma disciplina
     //retornando a função fitness
     public static int softConstraints(List<AlunoDisciplina> alunos,int n_alunos){
         int fitness = 0;
         int[][] alunosTimeslot = new int[n_alunos][n_timeslots];//mascara estudantes
         
         for (int i = 0; i < alunos.size(); i++) { 
            fitness = fitness + calculaSoftConstraintsAluno(alunosTimeslot,alunos.get(i));
         }
         
         fitness = fitness + calculaSoftConstraintsCapacidadeSala(n_alunos,alunosTimeslot);
         
         return fitness;
     }
     
     
     
     //verifica se é possivel inserir um aluno em uma disciplina
     public static int calculaSoftConstraintsAluno(int[][] mascara, AlunoDisciplina aluno){
         
         //retorna a disciplina que o aluno quer cursar e o seu timeslot
         int fitness = 0;
         List<Integer> ids = new ArrayList<Integer>();
         List<Integer> disciplinasAluno = aluno.getDisciplina();
         
         for (int i = 0; i < disciplinasAluno.size(); i++) {
             List<Integer> aux = getRestricoesDisciplinasIds(disciplinasAluno.get(i));
             for (int j = 0; j < aux.size(); j++) {
                 ids.add(aux.get(j));
             }
         }
         
         for (int i = 0; i < ids.size(); i++) {
             Integer disc = disciplinaTimeSlot.get(ids.get(i));
             int timeslot = disc.intValue();
         
            //verifica se o aluno esta disponivel naquele horario e 
            //verifica se tem vaga na disciplina
            int alunoID = aluno.getAluno() - 1;
            
            if(mascara[alunoID][timeslot]  != 1 ){//aluno esta disponivel naquele horario
                   mascara[aluno.getAluno()][timeslot] = 1;   
            }
            else
                 fitness = fitness + SOFTCONSTRAINT;
         
         }

         return fitness;
     }
     
     
     //verifica se é possivel inserir todos os alunos em uma sala
     public static int calculaSoftConstraintsCapacidadeSala(int n_alunos,int[][] alunosTimeslot){
         int fitness = 0;
         int quantidade = 0;
         
         
         for (int i = 0; i < n_timeslots; i++) {
             quantidade = 0;
         if(timeSlotSala.get(i) !=null){//verifica sala no timeslot i     
                for (int j = 0; j < n_alunos; j++) {//quantidade de alunos naquele timeslot
                    if(alunosTimeslot[j][i] == 1)
                        quantidade ++;
                }

                int idSala = timeSlotSala.get(i);//ve o id da sala no timeslot
                String capacidade = Salas.salacap.get((idSala+1)+"");//pega a capacidade da sala

                if(quantidade > Integer.valueOf(capacidade) ){//se a quantidade for maior que a capacidade
                    fitness = fitness + SOFTCONSTRAINT;
                }
            }
         }
         
         return fitness;
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
    
    //cria listas auxiliares para separar todas as salas pelo seu tipo
    private static void tipoSalaSalaDisciplina(){
        
        for (int i = 0; i < n_salas; i++) {
            
        String tipoSalaStr = Salas.salatipo.get((i+1)+"");
        int tipoSala = Integer.valueOf(tipoSalaStr);
        
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
    private void professorDisciplina(){
        
        //inicializar o hashMap
        for (int i = 0; i < n_disciplinas; i++) {
            disciplinaProfessor.put(i, new ArrayList<Integer>());
        }
        
        for (int i = 0; i < Docentes.docentedisc1.size(); i++) {
         String disciplina =    Docentes.docentedisc1.get((i+1)+"");
            List<Integer> ids = getRestricoesDisciplinasIds(Integer.valueOf(disciplina));
            for (int j = 0; j < ids.size(); j++) {
                Integer id = new Integer(ids.get(j));
                List<Integer> aux = disciplinaProfessor.get(id);
                aux.add(i);
                disciplinaProfessor.put(ids.get(j), aux);
            }
        }
        
        for (int i = 0; i < Docentes.docentedisc2.size(); i++) {
         String disciplina =    Docentes.docentedisc2.get((i+1)+"");
            List<Integer> ids = getRestricoesDisciplinasIds(Integer.valueOf(disciplina));
            for (int j = 0; j < ids.size(); j++) {
                Integer id = new Integer(ids.get(j));
                List<Integer> aux = disciplinaProfessor.get(id);
                aux.add(i);
                disciplinaProfessor.put(ids.get(j), aux);
            }
        }
        
        for (int i = 0; i < Docentes.docentedisc3.size(); i++) {
         String disciplina =    Docentes.docentedisc3.get((i+1)+"");
            List<Integer> ids = getRestricoesDisciplinasIds(Integer.valueOf(disciplina));
            for (int j = 0; j < ids.size(); j++) {
                Integer id = new Integer(ids.get(j));
                List<Integer> aux = disciplinaProfessor.get(id);
                aux.add(i);
                disciplinaProfessor.put(ids.get(j), aux);
            }
        }
        
        for (int i = 0; i < Docentes.docentedisc4.size(); i++) {
         String disciplina =    Docentes.docentedisc4.get((i+1)+"");
            List<Integer> ids = getRestricoesDisciplinasIds(Integer.valueOf(disciplina));
            for (int j = 0; j < ids.size(); j++) {
                Integer id = new Integer(ids.get(j));
                List<Integer> aux = disciplinaProfessor.get(id);
                aux.add(i);
                disciplinaProfessor.put(ids.get(j), aux);
            }
        }
        
        for (int i = 0; i < Docentes.docentedisc5.size(); i++) {
         String disciplina =    Docentes.docentedisc5.get((i+1)+"");
            List<Integer> ids = getRestricoesDisciplinasIds(Integer.valueOf(disciplina));
            for (int j = 0; j < ids.size(); j++) {
                Integer id = new Integer(ids.get(j));
                List<Integer> aux = disciplinaProfessor.get(id);
                aux.add(i);
                disciplinaProfessor.put(ids.get(j), aux);
            }
        }
    
    }
    
    //retorna a lista de professores que podem dar aquela disciplina dado o id da disciplina
    public static List<Integer> professorDisciplina(int disciplina){
        Integer id = new Integer(disciplina);
        return disciplinaProfessor.get(id);
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
            String str = Disciplinas.disciplinacurso.get(Disciplinas.D.get(i));
            String turnoDis = Cursos.cursoturnos.get(str);
            
            if(Integer.valueOf(turnoDis) == Cursos.MATUTINO ){
                List<Integer> aux = Timeslot.vespertino;              
                for (int j = 0; j < aux.size(); j++) {
                    disciplinas[i][aux.get(j)] = -1;
                }
                aux = Timeslot.noturno;
                for (int j = 0; j < aux.size(); j++) {
                    disciplinas[i][aux.get(j)] = -1;
                }             
            }
            
            else if(Integer.valueOf(turnoDis) == Cursos.VESPERTINO){
                List<Integer> aux = Timeslot.matutino;
                for (int j = 0; j < aux.size(); j++) {
                    disciplinas[i][aux.get(j)] = -1;
                }
                aux = Timeslot.noturno;
                for (int j = 0; j < aux.size(); j++) {
                    disciplinas[i][aux.get(j)] = -1;
                }
            }
            
            else if(Integer.valueOf(turnoDis) == Cursos.MATUTINO_VESPERTINO){
                List<Integer> aux = Timeslot.noturno;
                for (int j = 0; j < aux.size(); j++) {
                    disciplinas[i][aux.get(j)] = -1;
                }
            }

            
            else if(Integer.valueOf(turnoDis) == Cursos.NOTURNO){
                List<Integer> aux = Timeslot.matutino;
                for (int j = 0; j < aux.size(); j++) {
                    disciplinas[i][aux.get(j)] = -1;
                }
                
                aux = Timeslot.vespertino;
                for (int j = 0; j < aux.size(); j++) {
                    disciplinas[i][aux.get(j)] = -1;
                }
                aux = Timeslot.sabado;
                for (int j = 0; j < aux.size(); j++) {
                    disciplinas[i][aux.get(j)] = 0;
                }
            }
            
            else if(Integer.valueOf(turnoDis) == Cursos.MATUTINO_NOTURNO){
                List<Integer> aux = Timeslot.vespertino;
                for (int j = 0; j < aux.size(); j++) {
                    disciplinas[i][aux.get(j)] = -1;
                }
            }
            
            else if(Integer.valueOf(turnoDis) == Cursos.VESPERTINO_NOTURNO){
                List<Integer> aux = Timeslot.matutino;
                for (int j = 0; j < aux.size(); j++) {
                    disciplinas[i][aux.get(j)] = -1;
                }
                
                aux = Timeslot.sabado;
                for (int j = 0; j < aux.size(); j++) {
                    disciplinas[i][aux.get(j)] = 0;
                }
            }

            List<Integer> aux = Timeslot.almoco;
            for (int j = 0; j < aux.size(); j++) {
                disciplinas[i][aux.get(j)] = -1;
            }  
        }
    }
    
    public static void validaGene(Gene[] genes){
        Random random = new Random();
        
        int sizeGene = genes.length;
            
        int sizeDocentes = 0;
        int sizeSalas    = 0;
        int sizeTimeslot = 0;
        
        int sorteioDocente;
        int sorteioSala;
        int sorteioTimeslot;
                
        for (int i = 0; i < sizeGene; i++){
            Gene gene = genes[i];
            
            //sorteioDocente = random.nextInt(sizeDocentes);
            //sorteioSala = random.nextInt(sizeSalas);
            //sorteioTimeslot = random.nextInt(sizeTimeslot);
            
            //gene.setProfessor(sorteioDocente);
            //gene.setSala(sorteioSala);
            //gene.setTimeslot(sorteioTimeslot);
            //gene.setDisciplina(i);
            
        } 
    }
        
}

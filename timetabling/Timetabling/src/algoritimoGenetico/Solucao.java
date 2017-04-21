package algoritimoGenetico;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aluno
 */
public class Solucao {
   private static byte salas       [][];      // mascara  sala-horario
   private static byte professores [][];      // mascara  professor-horario
   private static byte disciplinas [][];      // mascara  disciplina-horario
    
   private static int n_timeslots; // numero de timeslots
   private static int n_salas;//numero de salas
   private static int n_professores;//numero de professores
   private static int n_disciplinas;//numero de professores
    
    //Mascara de soluÃ§oes estrutura que contrem todas as mascaras 
   private static Object solucao[];
   private static int fitness;
    
    //inicializa com timeslots, numero de salas e numero de professores
    Solucao(int n_timeslots,int n_salas,int n_professores,int n_disciplinas){
        
        fitness = 0;
        //tamanho da matriz
        this.n_timeslots            = n_timeslots; 
        this.n_salas                = n_salas;
        this.n_professores          = n_professores;
        this.n_disciplinas          = n_disciplinas;
             
        //matrizes
         salas        = new byte[n_salas][n_timeslots];        //sala - horario
         professores  = new byte[n_professores][n_timeslots];  //professores - timeslot
         disciplinas  = new byte[n_disciplinas][n_timeslots];  //disciplinas - timeslot
         
         initSolucao();
         
         solucao = new Object[5];
         solucao[0] = salas;
         solucao[1] = professores;
         solucao[1] = disciplinas;
         
         
    }
    
    //iniciar a marcara de validaÃ§Ã£o de schedule todos as matrizes
    //soluÃ§Ã£o do tipo (sala,horario),(professor,horario)
    public static void initSolucao(){
        fitness = 0;
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
       
       setRestricoesProfessores(); 
       setRestricoesDisciplinas(); 
    }
    
    //vector [sala,timeslot,professor]
    public static void setValor(int vector[]){
        
        int sala = vector[1];
        int timeslot = vector[0];
        int professor = vector[2];
        int disciplina = vector[3];
        
        salas [sala][timeslot] = 1; 
        professores [professor][timeslot]   = 1;
        disciplinas [disciplina][timeslot]  = 1;  
    }
    
    //retorna se o ponto jÃ¡ esta colocado na mascara de soluÃ§Ã£o
    //e seta esse ponto na mascara de soluÃ§Ã£o
    public static boolean isValorValido(int vetor[]){
        boolean valor = false;
        int timeslot  = vetor[0];
        int sala      = vetor[1];
        int professor = vetor[2];
        int disciplina = vetor[3];
         
        if(salas[sala][timeslot] != 1 )
            valor = true;
        if(professores[professor][timeslot]  != 1 && professores[professor][timeslot] != -1)
            valor = true;
        if(disciplinas[disciplina][timeslot]  != 1 && disciplinas[disciplina][timeslot] != -1)
            valor = true;
       
        if(valor == true){
            setValor(vetor);
            return true;
        }
        
        return false;
    }
    
    //verifica se o professor e valido
    public static boolean isProfessorValido(int vetor[]){
        boolean retorno = false;
        int timeslot  = vetor[0];
        int sala      = vetor[1];
        int professor = vetor[2];
        int disciplina = vetor[3];
               
        if(professores[professor][timeslot]  != 1 && professores[professor][timeslot] != -1)
            retorno = true;
           
        return retorno;
    }
    
    //verifica se a sala Ã© valida
    public static boolean isSalaValido(int vetor[]){
        boolean retorno = false;
        int timeslot  = vetor[0];
        int sala      = vetor[1];
        int professor = vetor[2];
        int disciplina = vetor[3];
         
        if(salas[sala][timeslot] != 1 )
            retorno = true;
        
        
        return retorno;
    }
    
    //retorna se o ponto jÃ¡ esta colocado na mascara de soluÃ§Ã£o
    //e seta esse ponto na mascara de soluÃ§Ã£o
    public static boolean isDisciplinaValido(int vetor[]){
        boolean retorno = false;
        int timeslot  = vetor[0];
        int sala      = vetor[1];
        int professor = vetor[2];
        int disciplina = vetor[3];
         
        if(disciplinas[disciplina][timeslot]  != 1 && disciplinas[disciplina][timeslot] != -1)
            retorno = true;
  
        return retorno;
    }
    
    //FunÃ§Ã£o para calcular a funÃ§Ã£o de fitness
    //inserindo valores na soluÃ§Ã£o, se nÃ£o estiver preenchida o espaÃ§o de soluÃ§Ã£o
    //nÃ£o pontua o fitness, e seta o valor no espaÃ§o de soluÃ§Ãµes retornando verdadeiro
    //caso contrario pontua negativamente o horario
    //retonando falso
    public static boolean CalculaFitness(int vetor[]){
        boolean valor = false;
        int timeslot  = vetor[0];
        int sala      = vetor[1];
        int professor = vetor[2];
        int disciplina = vetor[3];
        
        //Hards Constraints -- penalidade maior
        if(salas[sala][timeslot] == 1 ){ // sala ja esta sendo utilizada naquele horario
            fitness = fitness -10;
            valor = false;
        }
        if(professores[professor][timeslot]  == 1){//professor ja esta ministrando aula naquele horario
            fitness = fitness -10;
            valor = false;
        }
        if(disciplinas[disciplina][timeslot]  != 1 && disciplinas[disciplina][timeslot] != -1){
            fitness = fitness -10;
            valor = false;
         }
         
        
        if(valor == true)
            setValor(vetor);
            
        return false;
    }
    
    public static int getFitness(){
        return fitness;
    }
    
    //implementar restriÃ§oes dos professores setando ja na mascara 
    //de soluÃ§oes, setando -1 no espaÃ§o de soluÃ§oes
    //mostarndo uma soluÃ§Ã£o impossivel
    public static void setRestricoesProfessores(){
    
    }
    
    //implementar restriÃ§oes as disciplinas setando ja na mascara 
    //de soluÃ§oes, setando -1 no espaÃ§o de soluÃ§oes
    //mostarndo uma soluÃ§Ã£o impossivel
    public static void setRestricoesDisciplinas(){
    
    }
    
}

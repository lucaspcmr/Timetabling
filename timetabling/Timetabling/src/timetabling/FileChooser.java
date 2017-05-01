package timetabling;
import algoritmoGenetico.AlgoritimoGenetico;
import algoritmoGenetico.Gene;
import algoritmoGenetico.Solucao;
import timetabling.Filetomem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.sort;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import objetos.DisciplinaAluno;
import objetos.Disciplinas;
import objetos.Docentes;
import objetos.Estudantes;
import objetos.Salas;
import objetos.Turma;


public class FileChooser extends JPanel  {
	
	static private final String newline = "\n";
	JButton openButton, saveButton;
	JFileChooser fc;
	private static int i=0;
    public FileChooser() {
    	 

        //Create a file chooser
        fc = new JFileChooser();
 
        //Uncomment one of the following lines to try a different
        //file selection mode.  The first allows just directories
        //to be selected (and, at least in the Java look and feel,
        //shown).  The second allows both files and directories
        //to be selected.  If you leave these lines commented out,
        //then the default mode (FILES_ONLY) will be used.
        //
        //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
 
        //Create the open button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        openButton = new JButton("Open a File...",
                                 createImageIcon("images/open.gif"));
        openFile(openButton);
 
        //Create the save button.
        saveButton = new JButton("Save a File...",
                                 createImageIcon("images/save.gif"));
        saveFile(saveButton);
    }
 

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = FileChooser.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    public JButton getSalvar(){
    	return saveButton;
    }
    
    public JButton getCarregar(){
    	return openButton;
    }
 
  //Handle save button action.
    private void saveFile(JButton button){  
    	  button.addActionListener(new ActionListener() {

    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	        	 if (e.getSource() == saveButton) {
    	                int returnVal = fc.showSaveDialog(FileChooser.this);
    	                if (returnVal == JFileChooser.APPROVE_OPTION) {
    	                    File file = fc.getSelectedFile();
    	                    file = new File( file.toString() + ".csv" );
    	                    try {
    	                    	FileWriter fileW = new FileWriter (file,false);//arquivo para escrita
    	                    	BufferedWriter buffW = new BufferedWriter (fileW);
    	                    	
                                Gene genes[] = AlgoritimoGenetico.getCromossomo();
                                List<Turma> turmas = new ArrayList<Turma>();
                                
                                for (int i = 0; i < genes.length; i++) {
                                    Gene gene = genes[i];
                                    int disciplina = gene.getDisciplina();
                                    int professor  = gene.getProfessor();
                                    int sala       = gene.getSala();
                                    int timeslot   = gene.getTimeslot();

                                    Integer key = Disciplinas.D.get(disciplina);
                                    String cursoDisciplina    = Disciplinas.disciplinacurso.get(key);
                                    String disciplinaCodigo   = Disciplinas.disciplinacodigo.get(key);
                                    String professorCodigo    = (professor +1)+"";
                                    String salaCodigo         = (sala+1)+"";
                                    String periodo            = Disciplinas.disciplinaperiodo.get(key);
                                    
                                    Turma turma = new Turma();
                                    
                                    turma.setCurso(Integer.valueOf(cursoDisciplina).intValue());
                                    turma.setDisciplina(disciplinaCodigo);
                                    turma.setPeriodo(periodo);
                                    turma.setProfessor(professorCodigo);
                                    turma.setSala(salaCodigo);//
                                    turma.setTimeslot(timeslot+1);//codigo do timslot
                                    turmas.add(turma);
                                    }
                                    sort(turmas);//coloca turmas em ordem
                                    
                                    String comentario = "//---------------------\n";
                                           buffW.write(comentario);
                                           comentario = "//Horários Gerados\n";
                                           buffW.write(comentario);
                                           comentario = "//disposição dos codigo abaixo\n";
                                           buffW.write(comentario);
                                           comentario = "//Curso,Periodo,Código da Disciplina, Timeslot, Docente Sala,\n";
                                           buffW.write(comentario);
                                           comentario = "//---------------------\n";
                                           buffW.write(comentario);
                                           comentario = "//---------------------\n";
                                           buffW.write(comentario);
                                           comentario = "HORARIO\n";
                                           buffW.write(comentario);
                                           comentario = "//---------------------\n";
                                           buffW.write(comentario);
                                    for (int j = 0; j < turmas.size(); j++) {
                                    
                                        String disciplina         = turmas.get(j).getDisciplina();
                                        String timeslot           = turmas.get(j).getTimeslot()+"";
                                        String professor          = turmas.get(j).getProfessor();
                                        String sala               = turmas.get(j).getSala();
                                        String curso              = turmas.get(j).getCurso()+"";
                                        String periodo            = turmas.get(j).getPeriodo();
                                        
                                        String nomeProfessor = Docentes.docentenome.get(professor);
                                        String nomeSala      = Salas.salasigla.get(sala);

                                        String linha = disciplina+","+timeslot+","+nomeProfessor+","+nomeSala+","+curso+","+periodo+"\n";
                                        buffW.write(linha);
                                     }
                                    
                                           comentario = "//---------------------\n";
                                           buffW.write(comentario);
                                           comentario = "ESTUDANTE\n";
                                           buffW.write(comentario);
                                           comentario = "//Código da Disciplina, Timeslot, Nome Docente, Sigla Sala, Lista de alunos\n";
                                           buffW.write(comentario);
                                           comentario = "//---------------------\n";
                                           buffW.write(comentario);
                                           
                                          List<DisciplinaAluno> aux = Solucao.getListaAlunosMatriculados(genes,Estudantes.getAlunosDisciplinas());
                                          sort(aux);
                                          
                                          for (int j = 0; j < aux.size(); j++) {
                                              List<Integer> alunos = aux.get(j).getAlunos();
                                              int disciplinaCodigo  = aux.get(j).getDisciplina();
                                              if(alunos.size() >0){
                                                String linha = disciplinaCodigo+",";
                                                linha = linha+aux.get(j).getTimeslot()+",";
                                                linha = linha+aux.get(j).getProfessor()+",";
                                                linha = linha+aux.get(j).getSala();
                                                
                                                for (int k = 0; k < alunos.size();k++) {
                                                    linha = linha+","+alunos.get(k);
                                                }
                                                linha = linha +"\n";
                                                buffW.write(linha);
                                              }
                                            }
                                    
                                        buffW.close ();
                                        file.createNewFile();
    	    				} catch (IOException e1) {
    	    					// TODO Auto-generated catch block
    	    					e1.printStackTrace();
    	    				}
    	                    
    	                    //This is where a real application would save the file.
    	                    TextArea.LOG.append("Saving: " + file.getName() + "." + newline);
    	                } else {
    	                	TextArea.LOG.append("Save command cancelled by user." + newline);
    	                }}}
    	        	
    	    });
  	  }
        
    private void openFile(JButton button){  
    	  button.addActionListener(new ActionListener() {

    		  @Override
    	        public void actionPerformed(ActionEvent e){
           	  //Handle open button action.
              if (e.getSource() == openButton) {
                 
            	  int returnVal = fc.showOpenDialog(FileChooser.this);
                  
                  if (returnVal == JFileChooser.APPROVE_OPTION) {
                      File file = fc.getSelectedFile();
                      /*LEITURA*/
                     
					try {
						TextArea.LOG.append("Opening: " + file.getName() + "." + newline);
						Main.fileR = new FileReader(file);
                                                if(i==0){
                                                Filetomem.sort(Main.fileR);
                                                i++;
                                                TextArea.LOG.append("Abra o arquivo de restrições"+newline);
                                                }
                                                else{
						 
                                                 Main.fileR=new FileReader(file);
                                                 Filetomemrest.sort(Main.fileR);}
	                      
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}//arquivo para ser lido
					catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
      
                  } 
                  else {
                	  TextArea.LOG.append("Open command cancelled by user." + newline);
                  }
              	}              
    		  }  
    	  });
    }
    
    public void openFileHorario(JButton button){  
    	  button.addActionListener(new ActionListener() {

    		  @Override
    	        public void actionPerformed(ActionEvent e){
           	  //Handle open button action.              
            	  int returnVal = fc.showOpenDialog(FileChooser.this);
                  
                  if (returnVal == JFileChooser.APPROVE_OPTION) {
                      File file = fc.getSelectedFile();
                      /*LEITURA*/
                     
					try {
                                            TextArea.LOG.append("Opening: " + file.getName() + "." + newline);					                                                                                
                                            Filetomem.sortHorario(new FileReader(file));
	                      
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}//arquivo para ser lido
					catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
      
                  } 
                  else {
                	  TextArea.LOG.append("Open command cancelled by user." + newline);
                  }
              	              
    		  }  
    	  });
    }
}
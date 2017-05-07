package timetabling;


import java.awt.Dimension;
import java.awt.EventQueue;

import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Main {

    public static FileReader fileR;

    private static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("Timetabling Problem");
        //frame.setPreferredSize(new Dimension(400, 500));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Window windown = new Window();
        
        //Set up the content pane.
        windown.addComponentsToPane(frame.getContentPane());
        TextArea.LOG.append("Abra o arquivo de informações"+"\n");
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
//                while(true){
//                    TextArea.LOG.append("1");
//                }
            }
        });
              
    }
}

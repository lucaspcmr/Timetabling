package objetos;

import java.io.BufferedReader;
import java.util.ArrayList;

import timetabling.FileChooser;
import timetabling.TextArea;

public class Timeslot {
 
    public static ArrayList<String> T;
    public Timeslot(){
    T=new ArrayList<String>();//Array de timeslots 
    criaux();
}
   void criaux() {
        int aux1;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                aux1 = (i + 1) * 24 + 8 + j;
                T.add(Integer.toString(aux1));
                
            }
            if (i != 5) {
                for (int k = 0; k < 8; k++) {
                    aux1 = (i + 1) * 24 + 15 + k;
                    T.add(Integer.toString(aux1));
                                    }
            }
        }
   System.out.println(T); 
   }
}


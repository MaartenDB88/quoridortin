package domein;

import java.util.*;

public class Spelbord {

    private vak[][] bord;
    private String ASCII_Spelbord = "";
    private int dimensie;
    private String VerkeerdeKeuze = "\nNiet juist!!\nKies een optie uit de lijst!.";

    public Spelbord(int d) {
        dimensie = d;
        bord = new vak[dimensie][dimensie];


        for (int i = 0; i < d; i++) {

            for (int j = 0; j < d; j++) {

                bord[i][j] = new vak(i, j, false, null);
//                bord.add(new vak(i, j, false, null));

            }//end nested for
        }//end for

        dimensie = d;

    }//end constructor

    //GET SPEELBORD()
    public vak[][] getSpeelBord() {

        return bord;
    }
    private static double size() {
        // TODO Auto-generated method stub
        return 0;
    }

    public String toString() {
        String result = "";

        for (int y = 0; y < dimensie ; y++) {
            for (int x = 0; x < dimensie; x++) {
                result = result +  bord[x][y].toString();
            }
             result = result + "\n";
        }
    
    return result ;
}

}


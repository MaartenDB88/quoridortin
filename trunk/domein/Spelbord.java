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

        if ((d != 9 && d != 11)) {
            throw new IllegalArgumentException(VerkeerdeKeuze);
        }


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

    //GET SURROUNDINGS()
//    public vak[] getSurroundings(int x, int y) {
//
//
//        /* 0 Boven
//         * 1 Rechts
//         * 2 Onder
//         * 3 Links
//         * 4 Boven (over pion)
//         * 5 Rechts (over pion)
//         * 6 Onder (over pion)
//         * 7 Links (over pion)
//         */
//
//        vak surroundings[] = new vak[8];
//
//        for (vak v : bord) {
//
//
//            //BOVEN JUMP
//            if (v.getX() == (x - 2) && v.getY() == y) {
//
//                surroundings[ 4] = v;
//            }//end if
//
//            //BOVEN
//            if (v.getX() == (x - 1) && v.getY() == y) {
//
//                surroundings[ 0] = v;
//            }//end if
//
//            //LINKS JUMP
//            if (v.getX() == x && v.getY() == (y - 2)) {
//
//                surroundings[ 7] = v;
//            }//end if
//
//            //LINKS
//            if (v.getX() == x && v.getY() == (y - 1)) {
//
//                surroundings[ 3] = v;
//            }//end if
//
//            //RECHTS
//            if (v.getX() == x && v.getY() == (y + 1)) {
//
//                surroundings[ 1] = v;
//            }//end if
//
//            //RECHTS JUMP
//            if (v.getX() == x && v.getY() == (y + 2)) {
//
//                surroundings[ 5] = v;
//            }//end if
//
//            //ONDER
//            if (v.getX() == (x + 1) && v.getY() == y) {
//
//                surroundings[ 2] = v;
//            }//end if
//
//            //ONDER JUMP
//            if (v.getX() == (x + 2) && v.getY() == y) {
//
//                surroundings[ 6] = v;
//            }//end if
//
//        }//end for
//
//        return surroundings;
//    }//end method getSurroundings
    //TOSTRING()
	/*creeert een ascii versie van het speelbord*/
    /*public String toString(){


    int size = (int)( Math.sqrt( Spelbord.size() ) );


    //KOLOM NUMMERS
    String kolomNummers = "\t";

    for( int count = 0; count < size; count++){

    kolomNummers = kolomNummers + "  " + count + " " ;
    }//end for
    kolomNummers = kolomNummers + "\n\n";



    //BOVENSTE LIJN
    String bovensteLijn = "\t";

    for( int count = 1; count <= size*4; count++){

    bovensteLijn = bovensteLijn + "-";
    }//end for
    bovensteLijn = bovensteLijn + "\n0\t";



    //ZET VAKKEN OP HET BORD
    ASCII_Spelbord = "";
    for( vak v : bord ){

    ASCII_Spelbord = ASCII_Spelbord + v.toString( size );
    }//end for


    return kolomNummers + bovensteLijn + ASCII_Spelbord;

    }//end method toString
     */
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


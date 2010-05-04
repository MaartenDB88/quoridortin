package domein;

public class vak {

    private boolean muur;
    private Pion pion;
    private int x, y;

    public vak(int x, int y, boolean muur, Pion p) {
        this.x = x;
        this.y = y;
        pion = p;
        this.muur = muur;
    }

    public boolean isMuur() {
        return muur;
    }

    public void setMuur(boolean muur) {
        this.muur = muur;
    }

    public Pion getPion() {
        return pion;
    }

    public void setPion(Pion pion) {
        this.pion = pion;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //TOSTRING()
		/*geeft ascii symbool weer naargelang de status van het vak*/
    public String toString(int size) {

        //ASCII VAK
        String ASCII_Vak = "| ";

        //RIJ DIVIDER
        String rijDivider = "\t";

        for (int count = 1; count <= size * 4; count++) {

            rijDivider = rijDivider + "-";
        }//end for

        //VOLGEND RIJNUMMER
        String rijNummer = "\n";
        int rijNr = getX() + 1;

        if (rijNr < size) {
            rijNummer = rijNummer + rijNr + "\t";
        }//end if

        //MUUR
        if (isMuur() == true) {

            ASCII_Vak = ASCII_Vak + "X ";
        }//end if
        else {

            //PION
            if (getPion() != null) {
                ASCII_Vak = ASCII_Vak + getPion().getSymbool() + " ";
            }//end nested if
            //LEEG VAK
            else {

                ASCII_Vak = ASCII_Vak + "  ";
            }//end nested else
        }//end else

        //RIJSLUITER VAK
        if ((getY() + 1) % size == 0 && getY() != 0) {
            ASCII_Vak = ASCII_Vak + "|\n" + rijDivider + rijNummer;

        }//end if

        return ASCII_Vak;
    }//end method toString

    public String toString() {
        String result = "";

        if (getPion() != null) {
            result = pion.toString();
        } else if (muur == true) {
            result = "[x]";
        } else {
            result = "[ ]";
        }
        return result;


    }
}


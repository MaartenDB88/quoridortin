package gui;

import domein.Domeincontroller;
import domein.Pion;
import domein.Spel;
import domein.Speler;


import java.util.*;

public class ConsoleInterface {

    private int aantalSpelers;
    private String invalid_choice = "Verkeerde keuze";
    private String wrong_format = "gelieve iets in te geven van het juiste gegevenstype";
    private Domeincontroller domeinController;
    String nieuwSpelOudSpelKeuzeString = "1: Start nieuw spel. \n2. Laad oud spel.";

    public ConsoleInterface(Domeincontroller domeincontroller) {
        domeinController = domeincontroller;


    }

    public void run() {
        Scanner Input = new Scanner(System.in);
        printToConsole("-- Welkom gelieve een keuze te maken:--");
        printToConsole(nieuwSpelOudSpelKeuzeString);
        int keuze = Input.nextInt();
        if (keuze == 1) {
            dimensie();
            aantalSpelers();
            naamNpion();

            //domeinController.beginNieuwSpel(aantal);
            printToConsole("Nieuw spel start.");
        } else {
            printToConsole("Het Spel wordt geladen.");
        }



        startGame();

    }

    private void startGame() {
        boolean gameEnded = false;
        Scanner input = new Scanner(System.in);
        int keuze;
        while (!gameEnded) {
            printBord();
            printToConsole(domeinController.getNextGameStep());
            gameEnded = domeinController.goToNextGameStep();
            keuze = input.nextInt();
        }
    }
    //selecteren van aantal spelers

    private void aantalSpelers() {
        int aantal = 0;
        Scanner spelers = new Scanner(System.in);
        boolean Loop = true;
        do {

            printToConsole("Met hoeveel spelers wilt u spelen? u kan kiezen uit 2 of 4.");
            aantal = spelers.nextInt();
            if (aantal == 2 || aantal == 4) {
                Loop = false;
            }
            aantalSpelers = aantal;
        } while (Loop);

    }

    private void printToConsole(String message) {
        System.out.println(message);
    }
    // de dimensie afmetingen worden gekozen

    private void dimensie() {
        System.out.println("welke afmetingen wilt u" + "\n1. 9x9\n2. 11x11");
        Scanner bordGrootte = new Scanner(System.in);
        int bordKeuze = bordGrootte.nextInt();
        int d = 0;

        if (bordKeuze == 1) {
            d = 9;
        }
        if (bordKeuze == 2) {
            d = 11;
        }

        domeinController.createBord(d);

    }
    // geeft het bord weer met de gekozen afmeting

    private void printBord() {
        String bord = domeinController.getBordPaneel();
        System.out.print(bord);

    }

    private void foutboodschapWeergeven(String boodschap) {
        System.out.print(boodschap);
    }

    private void naamNpion() {
        List<String[]> pionInfo = domeinController.getPionInfo();
        List<Speler> spelers = new ArrayList<Speler>();
        for (int counter = 0; counter < aantalSpelers; counter++) {

            // SELECTEER NAAM
            printToConsole("Gelieve een naam in te geven");
            Scanner naamInput = new Scanner(System.in);
            String naamKeuze = naamInput.nextLine();
            Speler speler;

            boolean nameAlreadyExists = false;
            nameAlreadyExists = domeinController.controleerSpelerNaam(spelers, naamKeuze);

            if (!nameAlreadyExists) {
                speler = new Speler(counter, naamKeuze);
                spelers.add(speler);
            } else {
                while (nameAlreadyExists) {

                    printToConsole("Naam bestaat al, gelieve een nieuwe naam in te geven.");
                    naamKeuze = naamInput.nextLine();
                    nameAlreadyExists = domeinController.controleerSpelerNaam(spelers, naamKeuze);
                }
                speler = new Speler(counter, naamKeuze);
                spelers.add(speler);
            }
            //PION
            boolean continueLoop = true;

            do {
                System.out.printf("\n%s%s\n\n", "Speler " + naamKeuze, " Gelieve een kleur te kiezen");
                int i = 1;
                for (String[] pion : pionInfo) {

                    System.out.println("\t" + (i) + ". " + pion[ 0]);
                    i++;
                }//end for
                System.out.printf("Maak uw keuze: \n");
                Scanner pionScanner = new Scanner(System.in);

                try {
                    int pionKeuze = pionScanner.nextInt();
                    String gekozenPion = pionInfo.get(pionKeuze - 1)[0];
                    pionInfo.remove(pionKeuze - 1);
                    System.out.println();
                    speler.setPion(new Pion(Integer.toString(counter), gekozenPion));
                    continueLoop = false;
                } catch (InputMismatchException e) {
                    foutboodschapWeergeven(wrong_format);
                } catch (IndexOutOfBoundsException e) {
                    foutboodschapWeergeven(invalid_choice);
                }
            } while (continueLoop);



        }
        domeinController.maakNieuweSpeler(spelers);

    }
}















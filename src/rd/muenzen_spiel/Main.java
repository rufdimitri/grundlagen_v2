package rd.muenzen_spiel;

/*
    ------
    Spielregeln:
    Anfang: es liegen N Münzen auf dem Tisch
    2 Personen ziehen Münzen: 1-3 Münzen jedes Mal
    Wer die letzte Münze nimmt, hat gewonnen

    ----
    Pseudocode:

    Eingabe einer Anfangszahl Münzen durch den Menschen (ggf. Zufall)

    Wiederhole solange kein Spielende
        rechner_zieht()

        Wenn keine Münze mehr liegt dann
            Rechner gewinnt
            Spielende
        Ende Wenn

        mensch_zieht()

        Wenn keine Münze mehr liegt dann
            Mensch gewinnt
            Spielende
        Ende Wenn
    Ende Wiederhole


 */

import rd.console_colors.ConsoleColors;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random random = new Random();

    static String color(String text, String consoleColor, String consoleColorBackground) {
        return consoleColor + consoleColorBackground + text + ConsoleColors.RESET;
    }


    static int rechner_zieht(int m, int klugProzent) {
        int rest = m % 4;
        int ziehenZahl = random.nextInt(1, 4);

        if (random.nextInt(100) <= klugProzent) {
            if (rest >= 1) {
                ziehenZahl = rest;
            }
        }

        System.out.println("Rechner zieht " + ziehenZahl);
        return ziehenZahl;
    }

    static int mensch_zieht(int m) {
        System.out.println(color("Bitte deine Münzwahl (1, 2, 3 eingeben): ", ConsoleColors.BLACK, ConsoleColors.CYAN_BACKGROUND));
        Scanner in = new Scanner(System.in);
        int ziehenZahl = in.nextInt();
        System.out.println("Du ziehst " + ziehenZahl + " Münzen");
        return ziehenZahl;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println(color("Mit wie vielen Münzen fangen wir an:", ConsoleColors.BLACK, ConsoleColors.CYAN_BACKGROUND));
            int muenzen = in.nextInt();

            while (true) {
                System.out.println("Münzen auf dem Tisch: " + muenzen);
                //Rechner zieht
                muenzen -= rechner_zieht(muenzen, 50);
                if (muenzen <= 0) {
                    System.out.println("Rechner gewonnen");
                    break;
                }

                System.out.println("Münzen auf dem Tisch: " + muenzen);

                muenzen -= mensch_zieht(muenzen);
                if (muenzen <= 0) {
                    System.out.println("Du hast gewonnen");
                    break;
                }
            }
            System.out.println("---------------- \n\nSpielende.");
        }
    }
}


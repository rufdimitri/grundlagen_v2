package rd.tannenbaum;

import rd.console_colors.ConsoleColors;

public class Main {
    public static void main(String[] args) {
        int baumgroesse = 21;
        int leerzeichenAnzahl = baumgroesse/2;
        for (int sternchenAnzahl = 1; sternchenAnzahl <= baumgroesse; sternchenAnzahl+=2, leerzeichenAnzahl--) {

            System.out.print(ConsoleColors.CYAN_BACKGROUND);
            for (int s = 0; s < leerzeichenAnzahl; s++) {
                System.out.print(" ");
            }
            System.out.print(ConsoleColors.GREEN_BACKGROUND);
            for (int s = 0; s < sternchenAnzahl; s++) {
                System.out.print("*");
            }
            System.out.print(ConsoleColors.CYAN_BACKGROUND);
            for (int s = 0; s < leerzeichenAnzahl; s++) {
                System.out.print(" ");
            }
            System.out.print(ConsoleColors.RESET);

            System.out.println();
        }
    }
}

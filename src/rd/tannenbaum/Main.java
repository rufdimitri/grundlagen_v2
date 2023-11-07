package rd.tannenbaum;

public class Main {
    public static void main(String[] args) {
        int baumgroesse = 21;
        int leerzeichenAnzahl = baumgroesse/2;
        for (int sternchenAnzahl = 1; sternchenAnzahl <= baumgroesse; sternchenAnzahl+=2, leerzeichenAnzahl--) {
            for (int s = 0; s < leerzeichenAnzahl; s++) {
                System.out.print(" ");
            }
            for (int s = 0; s < sternchenAnzahl; s++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

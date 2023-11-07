package rd.binary_operator;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static void printBinDez(int value) {
        System.out.printf("Binär: %10s \t Dezimal: %10s\n", Integer.toBinaryString(value), value);
    }

    public static void main(String[] args) {
        int value = 0b1001;
        printBinDez(value);
        for (int i = 0; i < 3; i++) {
            value = value << 1;
            System.out.println("<< 1");
            printBinDez(value);
        }

        for (int i = 0; i < 6; i++) {
            value = value >> 1;
            System.out.println(">> 1");
            printBinDez(value);
        }

    }
}
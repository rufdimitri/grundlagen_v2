package rd.password_generator;

import javax.swing.*;
import java.security.SecureRandom;
import java.util.Scanner;

public class MainInPasswordGenerator {
    public static void main(String[] args) {
        String abcUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String abcLower = abcUpper.toLowerCase();
        String numbers = "0123456789";
        String asciiSpecial = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
        SecureRandom random = new SecureRandom();

        boolean useAbcUpper;
        boolean useAbcLower;
        boolean useNumbers;
        boolean useSpecial;
        String custom;
        int length;

        try (final Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Use alphabetical uppercase? (j/n) " + abcUpper + ": ");
                useAbcUpper = scanner.nextLine().trim().equalsIgnoreCase("j");
                System.out.print("Use alphabetical lowercase? (j/n) " + abcLower + ": ");
                useAbcLower = scanner.nextLine().trim().equalsIgnoreCase("j");
                System.out.print("Use numbers? (j/n) " + numbers + ": ");
                useNumbers = scanner.nextLine().trim().equalsIgnoreCase("j");
                System.out.print("Use special characters? (j/n) " + asciiSpecial + ": ");
                useSpecial = scanner.nextLine().trim().equalsIgnoreCase("j");
                System.out.print("Type custom characters (or leave empty to skip): ");
                custom = scanner.nextLine().trim();

                System.out.print("Password length: ");
                length = Integer.parseInt(scanner.nextLine().trim());

                StringBuilder characters = new StringBuilder();
                if (useAbcUpper) characters.append(abcUpper);
                if (useAbcLower) characters.append(abcLower);
                if (useNumbers) characters.append(numbers);
                if (useSpecial) characters.append(asciiSpecial);
                characters.append(custom);

                System.out.println("results:");
                for (int n = 0; n < 10; ++n) {
                    trying:
                    for (int tryN = 0; ; ++tryN) {
                        StringBuilder password = new StringBuilder();
                        boolean isAbcUpperUsed = false;
                        boolean isAbcLowerUsed = false;
                        boolean isNumbersUsed = false;
                        boolean isSpecialUsed = false;
                        boolean isCustomUsed = false;
                        for (int i = 0; i < length; ++i) {
                            char char1 = characters.charAt(random.nextInt(characters.length()));
                            password.append(char1);
                        }
                        for (int i = 0; i < password.length(); ++i) {
                            if (!isAbcUpperUsed) {
                                isAbcUpperUsed = abcUpper.indexOf(password.charAt(i)) >= 0;
                            }
                            if (!isAbcLowerUsed) {
                                isAbcLowerUsed = abcLower.indexOf(password.charAt(i)) >= 0;
                            }
                            if (!isNumbersUsed) {
                                isNumbersUsed = numbers.indexOf(password.charAt(i)) >= 0;
                            }
                            if (!isSpecialUsed) {
                                isSpecialUsed = asciiSpecial.indexOf(password.charAt(i)) >= 0;
                            }
                            if (!isCustomUsed) {
                                isCustomUsed = custom.indexOf(password.charAt(i)) >= 0;
                            }
                        }
                        if ((!useAbcUpper || isAbcUpperUsed)
                                && (!useAbcLower || isAbcLowerUsed)
                                && (!useNumbers || isNumbersUsed)
                                && (!useSpecial || isSpecialUsed)
                                && (custom.isEmpty() || isCustomUsed)
                        ) {
                            password.insert(0, (n+1)+ "  ");
//                            password.append(" (generated after ").append(tryN+1).append(" try)");
                            System.out.println(password);
                            break trying;
                        } else if (tryN > 1000) {
                            System.out.println("Could not generate password. Try with other parameters, e.g. make it longer than " + length);
                            break trying;
                        }
                    }
                }
                System.out.print("Try agiain? (j/n) ");
                if (!scanner.nextLine().trim().equalsIgnoreCase("j")) {
                    System.exit(0);
                }
            }
        }


    }
}

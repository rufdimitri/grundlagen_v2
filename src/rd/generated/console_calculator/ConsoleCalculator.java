package rd.generated.console_calculator;

import java.util.Scanner;

public class ConsoleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the first number: ");
        double num1 = getDoubleInput(scanner);

        System.out.print("Enter the operator (+, -, *, /): ");
        char operator = getOperatorInput(scanner);

        System.out.print("Enter the second number: ");
        double num2 = getDoubleInput(scanner);

        double result;
        switch (operator) {
            case '+':
                result = num1 + num2;
                System.out.println("Result: " + num1 + " " + operator + " " + num2 + " = " + result);
                break;
            case '-':
                result = num1 - num2;
                System.out.println("Result: " + num1 + " " + operator + " " + num2 + " = " + result);
                break;
            case '*':
                result = num1 * num2;
                System.out.println("Result: " + num1 + " " + operator + " " + num2 + " = " + result);
                break;
            case '/':
                if (num2 == 0) {
                    System.out.println("Error: Division by zero is not allowed.");
                } else {
                    result = num1 / num2;
                    System.out.println("Result: " + num1 + " " + operator + " " + num2 + " = " + result);
                }
                break;
            default:
                System.out.println("Error: Invalid operator.");
        }

        scanner.close();
    }

    private static double getDoubleInput(Scanner scanner) {
        while (true) {
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next(); // discard the invalid input
            }
        }
    }

    private static char getOperatorInput(Scanner scanner) {
        while (true) {
            String input = scanner.next();
            if (input.length() == 1 && (input.charAt(0) == '+' || input.charAt(0) == '-' || input.charAt(0) == '*' || input.charAt(0) == '/')) {
                return input.charAt(0);
            } else {
                System.out.print("Invalid operator. Please enter +, -, *, or /: ");
            }
        }
    }
}

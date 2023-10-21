package maman12_1;

import java.util.ArrayList;
import java.util.Scanner;

public class PolynomMain {
    private Polynom getPolynomFromInput() {
        // Get input from the user and create a polynom from it.
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] inputArray = input.split(",");
        ArrayList<Double> coefficients = new ArrayList<Double>();
        ArrayList<Integer> exponents = new ArrayList<Integer>();
        for (int i = 0; i < inputArray.length; i += 2) {
            coefficients.add(Double.parseDouble(inputArray[i]));
            exponents.add(Integer.parseInt(inputArray[i + 1]));
        }
        return new Polynom(coefficients, exponents);
    }

    public static void main(String[] args) {
        // Create two polynoms and print their sum, difference and derivative.
        PolynomMain polynomMain = new PolynomMain();
        System.out.println("Enter a polynom in the form of <coefficient>,<exponent> list. e.g. for 3x^2 -2x +2, enter 3,2,-2,1,2,0:");
        Polynom a = polynomMain.getPolynomFromInput();
        System.out.println("a = " + a);
        System.out.println("Enter another polynom:");
        Polynom b = polynomMain.getPolynomFromInput();
        System.out.println("b = " + b);

        System.out.println("a + b = " + a.plus(b));
        System.out.println("a - b = " + a.minus(b));
        System.out.println("b - a = " + b.minus(a));
        System.out.println("a' = " + a.derivative());
        System.out.println("b' = " + b.derivative());
    }
}

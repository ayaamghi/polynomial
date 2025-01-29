package edu.guilford;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class drives the Polynomial project.
 * It tests all aspects of the Polynomial class definition.
 * It gives the input and displays the result.
 * For example, testing the addition of the two Polynomial objects p1 and p2
 * could result in output like:
 * Testing addition of polynomials
 * Polynomial 1: 1.0x^3 + 2.5x^2 + 3.3x^1 + 4.6x^0
 * Polynomial 2: 4.1x^2 + 5.6x^1 + 6.8x^0
 * Addition result
 * 1.0x^3 + 6.6x^2 + 8.9x^3 + 11.4x^4
 * Comparison result
 * Polynomial 1.compareTo(Polynomial 2) = 1
 * Be sure that you test all constructors, all getters and setters, and all
 * aspects of the compareTo method.
 * 
 * @author Ayaam Ghimire
 */
public class PolynomialDriver {
    public static void main(String[] args) {
        // Test constructors
        System.out.println("Testing constructors:");
        ArrayList<Double> coeff1 = new ArrayList<>(Arrays.asList(1.0, 2.5, 3.3, 4.6));
        Polynomial p1 = new Polynomial(coeff1);
        System.out.println("Polynomial 1: " + p1);

        Polynomial p2 = new Polynomial(3);
        ArrayList<Double> coeff2 = new ArrayList<>(Arrays.asList(4.1, 5.6, 6.8));
        p2.setCoefficients(coeff2);
        System.out.println("Polynomial 2: " + p2);

        // Test getters and setters
        System.out.println("\nTesting getters and setters:");
        System.out.println("Degree of Polynomial 1: " + p1.getDegree());
        System.out.println("Coefficients of Polynomial 1: " + p1.getCoefficients());

        p1.setCoefficients(new ArrayList<>(Arrays.asList(2.0, 3.5, 4.3, 5.6)));
        System.out.println("Updated Polynomial 1: " + p1);

        // Test addition
        System.out.println("\nTesting addition of polynomials:");
        System.out.println("Polynomial 1: " + p1);
        System.out.println("Polynomial 2: " + p2);
        Polynomial sum = p1.add(p2);
        System.out.println("Addition result: " + sum);

        // Test subtraction
        System.out.println("\nTesting subtraction of polynomials:");
        Polynomial difference = p1.subtract(p2);
        System.out.println("Subtraction result: " + difference);

        // Test multiplication
        System.out.println("\nTesting multiplication of polynomials:");
        Polynomial product = p1.multiply(p2);
        System.out.println("Multiplication result: " + product);

        // Test compareTo
        System.out.println("\nTesting compareTo method:");
        System.out.println("Comparison result: Polynomial 1.compareTo(Polynomial 2) = " + p1.compareTo(p2));

        // Additional tests for compareTo
        Polynomial p3 = new Polynomial(12);
        Polynomial p4 = new Polynomial();
        System.out.println("Comparison result: Polynomial 3.compareTo(Polynomial 4) = " + p3.compareTo(p4));

        Polynomial p5 = new Polynomial(new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0)));
        System.out.println("Comparison result: Polynomial 3.compareTo(Polynomial 5) = " + p3.compareTo(p5));
    }
}
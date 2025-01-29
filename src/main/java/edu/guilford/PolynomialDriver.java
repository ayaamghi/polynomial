package edu.guilford;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This class drives the Polynomial project.
 * It tests all aspects of the Polynomial class definition.
 * It gives the input and displays the result.
 * 
 * The driver program tests:
 * - All constructors
 * - All getters and setters
 * - toString method
 * - Arithmetic operations: Addition, Subtraction, Multiplication
 * - compareTo method
 * - Sorting a list of polynomials
 * 
 * @author 
 */
public class PolynomialDriver {
    public static void main(String[] args) {
        // Test Constructors
        testConstructors();

        // Test Getters and Setters
        testGettersAndSetters();

        // Test toString Method
        testToString();

        // Test Arithmetic Operations: Addition, Subtraction, Multiplication
        testArithmeticOperations();

        // Test compareTo Method
        testCompareTo();

        // Create, Sort, and Display a List of Random Polynomials
        testSortingPolynomials();
    }

    /**
     * Tests all constructors of the Polynomial class.
     */
    private static void testConstructors() {
        System.out.println("=== Testing Constructors ===");

        // 1. Default Constructor
        System.out.println("\n-- Default Constructor --");
        Polynomial defaultPoly = new Polynomial();
        System.out.println("Default Polynomial: " + defaultPoly);
        System.out.println("Degree: " + defaultPoly.getDegree());
        System.out.println("Coefficients: " + defaultPoly.getCoefficients());

        // 2. Coefficient-Based Constructor
        System.out.println("\n-- Coefficient-Based Constructor --");
        ArrayList<Double> coeffs = new ArrayList<>(Arrays.asList(1.0, 2.5, 3.3, 4.6));
        Polynomial coeffPoly = new Polynomial(coeffs);
        System.out.println("Coefficient Polynomial: " + coeffPoly);
        System.out.println("Degree: " + coeffPoly.getDegree());
        System.out.println("Coefficients: " + coeffPoly.getCoefficients());

        // 3. Degree-Based Constructor
        System.out.println("\n-- Degree-Based Constructor (degree = 3) --");
        Polynomial degreePoly = new Polynomial(3);
        System.out.println("Degree Polynomial: " + degreePoly);
        System.out.println("Degree: " + degreePoly.getDegree());
        System.out.println("Coefficients: " + degreePoly.getCoefficients());
    }

    /**
     * Tests all getters and setters of the Polynomial class.
     */
    private static void testGettersAndSetters() {
        System.out.println("\n=== Testing Getters and Setters ===");

        // Initialize Polynomial with specific coefficients
        ArrayList<Double> initialCoeffs = new ArrayList<>(Arrays.asList(2.0, 3.5, 4.3, 5.6));
        Polynomial poly = new Polynomial(initialCoeffs);
        System.out.println("\nInitial Polynomial: " + poly);
        System.out.println("Degree: " + poly.getDegree());
        System.out.println("Coefficients: " + poly.getCoefficients());

        // Test getCoefficient
        System.out.println("\n-- Testing getCoefficient --");
        int degreeToGet = 2;
        double coeff = poly.getCoefficient(degreeToGet);
        System.out.println("Coefficient for x^" + degreeToGet + ": " + coeff);

        // Test setCoefficients
        System.out.println("\n-- Testing setCoefficients --");
        ArrayList<Double> newCoeffs = new ArrayList<>(Arrays.asList(5.0, 6.5, 7.3, 8.6, 9.9));
        System.out.println("Setting new coefficients to: " + newCoeffs);
        poly.setCoefficients(newCoeffs);
        System.out.println("Updated Polynomial after setCoefficients: " + poly);
        System.out.println("Degree after update: " + poly.getDegree());
        System.out.println("Coefficients after update: " + poly.getCoefficients());

        // Test setCoefficient for specific exponents within current degree
        System.out.println("\n-- Testing setCoefficient within current degree --");
        int exponentToSet = 3;
        double newValue = 10.0;
        System.out.println("Setting coefficient for x^" + exponentToSet + " to " + newValue);
        poly.setCoefficient(exponentToSet, newValue);
        System.out.println("Updated Polynomial after setCoefficient: " + poly);
        System.out.println("Degree after setCoefficient: " + poly.getDegree());
        System.out.println("Coefficients after setCoefficient: " + poly.getCoefficients());

        // Test setCoefficient beyond current degree
        System.out.println("\n-- Testing setCoefficient beyond current degree --");
        exponentToSet = 6;
        newValue = 15.0;
        System.out.println("Attempting to set coefficient for x^" + exponentToSet + " to " + newValue);
        try {
            poly.setCoefficient(exponentToSet, newValue);
            System.out.println("Updated Polynomial after setCoefficient: " + poly);
            System.out.println("Degree after setCoefficient: " + poly.getDegree());
            System.out.println("Coefficients after setCoefficient: " + poly.getCoefficients());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Cannot set coefficient for x^" + exponentToSet + " as it exceeds the current degree.");
        }

        // To successfully set a coefficient beyond current degree, extend the coefficients list first
        System.out.println("\n-- Extending coefficients to set x^6 --");
        // Extend coefficients to degree 6 by adding zeros
        ArrayList<Double> extendedCoeffs = new ArrayList<>(poly.getCoefficients());
        while (extendedCoeffs.size() <= exponentToSet) {
            extendedCoeffs.add(0.0);
        }
        poly.setCoefficients(extendedCoeffs);
        System.out.println("Extended Coefficients: " + poly.getCoefficients());

        // Now set coefficient for x^6
        System.out.println("Setting coefficient for x^" + exponentToSet + " to " + newValue);
        poly.setCoefficient(exponentToSet, newValue);
        System.out.println("Updated Polynomial after setting x^6: " + poly);
        System.out.println("Degree after setting x^6: " + poly.getDegree());
        System.out.println("Coefficients after setting x^6: " + poly.getCoefficients());
    }

    /**
     * Tests the toString method of the Polynomial class.
     */
    private static void testToString() {
        System.out.println("\n=== Testing toString Method ===");

        ArrayList<Double> coeffs = new ArrayList<>(Arrays.asList(3.0, 0.0, 2.5, 4.1));
        Polynomial poly = new Polynomial(coeffs);
        System.out.println("Polynomial: " + poly);
        // Expected Output: 4.1x^3 + 2.5x^2 + 0.0x^1 + 3.0x^0
    }

    /**
     * Tests arithmetic operations: addition, subtraction, and multiplication.
     */
    private static void testArithmeticOperations() {
        System.out.println("\n=== Testing Arithmetic Operations ===");

        // Create two polynomials for operations
        ArrayList<Double> coeffs1 = new ArrayList<>(Arrays.asList(1.0, 2.5, 3.3, 4.6));
        Polynomial p1 = new Polynomial(coeffs1);
        ArrayList<Double> coeffs2 = new ArrayList<>(Arrays.asList(4.1, 5.6, 6.8));
        Polynomial p2 = new Polynomial(coeffs2);
        System.out.println("\nPolynomial 1: " + p1);
        System.out.println("Polynomial 2: " + p2);

        // Addition
        System.out.println("\n-- Addition --");
        Polynomial sum = p1.add(p2);
        System.out.println("Addition Result: " + sum);

        // Subtraction
        System.out.println("\n-- Subtraction --");
        Polynomial difference = p1.subtract(p2);
        System.out.println("Subtraction Result: " + difference);

        // Multiplication
        System.out.println("\n-- Multiplication --");
        Polynomial product = p1.multiply(p2);
        System.out.println("Multiplication Result: " + product);
    }

    /**
     * Tests the compareTo method of the Polynomial class.
     */
    private static void testCompareTo() {
        System.out.println("\n=== Testing compareTo Method ===");

        // Polynomial 1 and Polynomial 2
        ArrayList<Double> coeffs1 = new ArrayList<>(Arrays.asList(2.0, 3.5, 4.3, 5.6));
        Polynomial p1 = new Polynomial(coeffs1);
        ArrayList<Double> coeffs2 = new ArrayList<>(Arrays.asList(4.1, 5.6, 6.8));
        Polynomial p2 = new Polynomial(coeffs2);
        System.out.println("\nPolynomial 1: " + p1);
        System.out.println("Polynomial 2: " + p2);
        int comparison1 = p1.compareTo(p2);
        System.out.println("Polynomial 1.compareTo(Polynomial 2) = " + comparison1);

        // Polynomial 3 and Polynomial 4
        Polynomial p3 = new Polynomial(12); // Degree 12
        Polynomial p4 = new Polynomial();    // Default degree -1
        System.out.println("\nPolynomial 3: " + p3);
        System.out.println("Polynomial 4: " + p4);
        int comparison2 = p3.compareTo(p4);
        System.out.println("Polynomial 3.compareTo(Polynomial 4) = " + comparison2);

        // Polynomial 3 and Polynomial 5
        ArrayList<Double> coeffs5 = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0));
        Polynomial p5 = new Polynomial(coeffs5);
        System.out.println("\nPolynomial 5: " + p5);
        int comparison3 = p3.compareTo(p5);
        System.out.println("Polynomial 3.compareTo(Polynomial 5) = " + comparison3);

        // Polynomial 6 and Polynomial 7 with same degree and coefficients
        ArrayList<Double> coeffs6 = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0));
        Polynomial p6 = new Polynomial(coeffs6);
        ArrayList<Double> coeffs7 = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0));
        Polynomial p7 = new Polynomial(coeffs7);
        System.out.println("\nPolynomial 6: " + p6);
        System.out.println("Polynomial 7: " + p7);
        int comparison4 = p6.compareTo(p7);
        System.out.println("Polynomial 6.compareTo(Polynomial 7) = " + comparison4);
    }

    /**
     * Tests sorting a list of random polynomials.
     */
    private static void testSortingPolynomials() {
        System.out.println("\n=== Testing Sorting of Random Polynomials ===");

        List<Polynomial> polyList = new ArrayList<>();

        // Creating random polynomials using degree-based constructor
        System.out.println("\n-- Creating Random Polynomials --");
        for (int i = 1; i <= 5; i++) {
            int degree = (int)(Math.random() * 10) + 1; // Degree between 1 and 10
            Polynomial randomPoly = new Polynomial(degree);
            System.out.println("Random Polynomial " + i + ": " + randomPoly);
            polyList.add(randomPoly);
        }

        // Sorting the list
        System.out.println("\n-- Sorting Polynomials by Degree and Coefficients --");
        Collections.sort(polyList);

        // Display sorted list
        System.out.println("\nSorted Polynomials:");
        for (int i = 0; i < polyList.size(); i++) {
            System.out.println("Polynomial " + (i + 1) + ": " + polyList.get(i));
        }
    }
}
package edu.guilford;

import java.util.ArrayList;
import java.util.Random;


public class Polynomial implements Comparable<Polynomial>{ 

    ArrayList<Double> coefficients = new ArrayList<>();
    double polynomialDegree;
    Random random = new Random();

    //empty polynomial
    /**
     * This is the constructor for the Polynomial class. It initializes the coefficients ArrayList all to 0 and sets the polynomialDegree to -1.
     */
    public Polynomial() {
        coefficients = new ArrayList<>();
        polynomialDegree = -1;
    }
    /*
     * Input should be ordered from lowest to highest (is reversed in logic, not in construction) 
     * 
     */
    public Polynomial(ArrayList<Double> coefficients) {
        this.coefficients = coefficients;
        this.polynomialDegree = coefficients.size() - 1; //asssume that your not skipping any degrees
    }
    public Polynomial(int polynomialDegree) {
        this.polynomialDegree = polynomialDegree;
        for (int i = 0; i <= polynomialDegree; i++) {
            coefficients.add(random.nextDouble(-15, 15));
        }
    }
    //get degree
    public double getDegree() {
        return polynomialDegree;
    }
    //get coefficients
    public ArrayList<Double> getCoefficients() {
        return coefficients;
    }
    //return coefficient for a specific degree
    public double getCoefficient(int degree) {
        return coefficients.get(degree); //again assuming that you are not skipping any degrees
    }
    //set coefficient for a specific degree
    public void setCoefficient(int degree, double value) {
        coefficients.set(degree, value);
    }
    //take in array of coefficients and update polynomial 
    public void setCoefficients(ArrayList<Double> coefficients) {
        this.coefficients = coefficients;
        this.polynomialDegree = coefficients.size() - 1;
    }
    //evaluate polynomial at a specific value
    public double evaluate(double x) {
        double result = 0;
        //evaluate the result in REVERSE order
        for (int i = (int) polynomialDegree; i >= 0; i--) {
            result += coefficients.get(i) * Math.pow(x, i);
        }
        return result;
    }
    //clear
    public void clear() {
        coefficients.clear();
        polynomialDegree = -1;
    }
    //add 
    public Polynomial add(Polynomial other) {
        ArrayList<Double> newCoefficients = new ArrayList<>();
        int minCoefficients = (int) Math.min(polynomialDegree, other.getDegree()); 
        //since we are assuming we do not skip any coefficients, add up all of the coefficients up to the min coefficient
        for (int i = 0; i <= minCoefficients; i++) {
            newCoefficients.add(getCoefficient(i) + other.getCoefficient(i));
        }
        newCoefficients.addAll(
            polynomialDegree > other.getDegree()
                ? coefficients.subList(minCoefficients + 1, coefficients.size())
                : other.getCoefficients().subList(minCoefficients + 1, other.getCoefficients().size())
        );
        
        return new Polynomial(newCoefficients);
    }
    //subtract
    public Polynomial subtract(Polynomial other){
        ArrayList<Double> newCoefficients = new ArrayList<>();
        int minCoefficients = (int) Math.min(polynomialDegree, other.getDegree()); 
        //since we are assuming we do not skip any coefficients, add up all of the coefficients up to the min coefficient
        for (int i = 0; i <= minCoefficients; i++) {
            newCoefficients.add(getCoefficient(i) - other.getCoefficient(i));
        }
        ///then just add the rest to the new coefficients list, checking which polynomial has the higher degree so we can add the correct additional degrees
        newCoefficients.addAll(
            polynomialDegree > other.getDegree()
                ? coefficients.subList(minCoefficients + 1, coefficients.size())
                : other.getCoefficients().subList(minCoefficients + 1, other.getCoefficients().size())
        );
        return new Polynomial(newCoefficients);
    }

    //toString
    @Override
    public String toString() {
        String result = "";
        //print in REVERSE order
        for (int i = (int) polynomialDegree; i >= 0; i--) {
            if (i == 0) {
                result += coefficients.get(i);
            } else {
                result += coefficients.get(i) + "x^" + i + " + ";
            }
        }
        return result;
    }
    @Override
    public int compareTo(Polynomial other) {
        // 1. If the current polynomial's degree is bigger, return 1.
        if (this.polynomialDegree > other.polynomialDegree) {
            return 1;
        }
        // 2. If the current polynomial's degree is smaller, return -1.
        if (this.polynomialDegree < other.polynomialDegree) {
            return -1;
        }
    
        // 3. If both polynomials have the same degree, check if all coefficients match.
        boolean allTheSame = true;
        for (int i = 0; i <= this.polynomialDegree; i++) {
            // Compare Double objects carefully
            if (!this.coefficients.get(i).equals(other.coefficients.get(i))) {
                allTheSame = false;
                break;
            }
        }
        // If all coefficients match, return 0
        if (allTheSame) {
            return 0;
        }
    
        // 4. If the polynomials have the same degree but differ in coefficients,
        //    compare their values at specific points to establish an ordering.
        double[] testPoints = {1, -1}; // you can add or remove points here
        for (double point : testPoints) {
            double thisValue = evaluate(point);
            double otherValue = other.evaluate(point);
            if (thisValue > otherValue) {
                return 1;
            } else if (thisValue < otherValue) {
                return -1;
            }
        }
    
        // 5. Final fallback - compare hash codes 
        //    to ensure a consistent ordering when all else fails.
        return Integer.compare(this.hashCode(), other.hashCode());
    }    

    //multiply
    public Polynomial multiply(Polynomial other) {
        ArrayList<Double> newCoefficients = new ArrayList<>();
        //initialize the new coefficients list with all 0s
        for (int i = 0; i <= polynomialDegree + other.getDegree(); i++) {
            newCoefficients.add(0.0);
        }
        //multiply each coefficient of the current polynomial with each coefficient of the other polynomial
        for (int i = 0; i <= polynomialDegree; i++) {
            for (int j = 0; j <= other.getDegree(); j++) {
                newCoefficients.set(i + j, newCoefficients.get(i + j) + coefficients.get(i) * other.getCoefficient(j));
            }
        }
        return new Polynomial(newCoefficients);


    }

 




}






    


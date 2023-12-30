package com.mycompany.calculatrice;

public class CalculatorLogic {
    private double result;
    private String operator;

    public CalculatorLogic() {
        result = 0;
        operator = "=";
    }

    public void reset() {
        result = 0;
        operator = "=";
    }

    public void calculate(double n) {
        if (operator.equals("+")) {
            result += n;
        } else if (operator.equals("-")) {
            result -= n;
        } else if (operator.equals("*")) {
            result *= n;
        } else if (operator.equals("/")) {
            if (n == 0) {
                throw new ArithmeticException("Erreur : Division par zéro");
            } else {
                result /= n;
            }
        } else if (operator.equals("=")) {
            result = n;
        }
        else if (operator.equals("%")){
            result %= n;
        }
    }

    public void setOperator(String op) {
        operator = op;
    }

    public double getResult() {
        double roundedResult = Math.round(result * 100.0) / 100.0;
        if (roundedResult == (int) roundedResult) {
            return (int) roundedResult;
        } else {
        
            return roundedResult;

        }
    }
}
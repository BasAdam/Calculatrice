package com.mycompany.calculatrice;

public class CalculatorLogic {
    private double result;
    private String operator;

    public CalculatorLogic() {
        // Initialisation du résultat à 0 et de l'opérateur à "=" lors de la création de l'instance
        result = 0;
        operator = "=";
    }

    public void reset() {
        // Réinitialisation du résultat à 0 et de l'opérateur à "="
        result = 0;
        operator = "=";
    }

    public void calculate(double n) {
        // Effectuer le calcul en fonction de l'opérateur actuel
        if (operator.equals("+")) {
            result += n;
        } else if (operator.equals("-")) {
            result -= n;
        } else if (operator.equals("*")) {
            result *= n;
        } else if (operator.equals("/")) {
            if (n == 0) {
                // Gérer l'exception de division par zéro
                throw new ArithmeticException("Erreur : Division par zéro");
            } else {
                result /= n;
            }
        } else if (operator.equals("=")) {
            // Si l'opérateur est "=", assigner directement le nombre à result
            result = n;
        } else if (operator.equals("%")) {
            // Calculer le modulo en utilisant l'opérateur "%"
            result %= n;
        }
    }

    public void setOperator(String op) {
        // Définir l'opérateur actuel
        operator = op;
    }

    public double getResult() {
        // Arrondir le résultat à deux décimales si nécessaire
        double roundedResult = Math.round(result * 100.0) / 100.0;
        if (roundedResult == (int) roundedResult) {
            // Si le résultat est un nombre entier, le renvoyer en tant qu'entier
            return (int) roundedResult;
        } else {
            // Sinon, renvoyer le résultat avec deux décimales
            return roundedResult;
        }
    }
}

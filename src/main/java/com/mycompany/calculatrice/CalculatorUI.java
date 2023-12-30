package com.mycompany.calculatrice;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorUI {

    private JFrame frame;
    private JTextField display;
    private CalculatorLogic calculatorLogic;
    private boolean calculating;

    public CalculatorUI() {
        calculatorLogic = new CalculatorLogic();
        calculating = true;

        // Création de la fenêtre principale
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);

        // Création du champ de texte pour l'affichage des nombres et des résultats
        display = new JTextField("0");
        display.setBounds(20, 20, 360, 50);
        display.setEditable(false);
        frame.add(display);

        // Création des libellés des boutons
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "%", "=", 
            "+"
        };

        int x = 20, y = 80;
        for (int i = 0; i < buttonLabels.length; i++) {
            // Création des boutons numériques et d'opération
            JButton button = new JButton(buttonLabels[i]);
            button.setBounds(x, y, 80, 40);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();
                    if (command.equals("C")) {
                        // Réinitialisation de l'affichage et de la logique du calcul
                        display.setText("0");
                        calculating = true;
                        calculatorLogic.reset();
                    } 
                    else if (command.equals("=")) {
                        // Effectuer le calcul et afficher le résultat
                        calculate(Double.parseDouble(display.getText()));
                        display.setText("" + calculatorLogic.getResult());
                        calculating = true;
                    } 
                    else if ("0123456789".contains(command)) {
                        if (calculating) {
                            // Remplacer l'affichage par le chiffre appuyé
                            display.setText(command);
                        } else {
                            // Ajouter le chiffre appuyé à l'affichage existant
                            display.setText(display.getText() + command);
                        }
                        calculating = false;
                    } 
                    else {
                        if (!calculating) {
                            // Effectuer un calcul avant d'ajouter un nouvel opérateur
                            calculate(Double.parseDouble(display.getText()));
                            displayResult(calculatorLogic.getResult());
                        }
                        calculating = true;
                        // Définir l'opérateur pour le calcul
                        calculatorLogic.setOperator(command);
                    }
                }
            });
            frame.add(button);

            x += 90;
            if (i % 4 == 3) {
                x = 20;
                y += 50;
            }
        }

        // Rendre la fenêtre visible
        frame.setVisible(true);
    }

    private void calculate(double n) {
        try {
            // Effectuer le calcul avec la logique de la calculatrice
            calculatorLogic.calculate(n);
        } catch (ArithmeticException e) {
            // Gérer l'exception de division par zéro
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    display.setText("Erreur : Division par zéro");
                }
            });
            return;
        }
        // Afficher le résultat du calcul
        displayResult(calculatorLogic.getResult());
    }

    private void displayResult(double result) {
        if (result == (long) result) {
            // Si le résultat est un nombre entier, afficher sans décimales
            display.setText(String.format("%d", (long) result));
            // Remarque : Cette ligne ne sert à rien car la condition n'est pas atteinte
            // lorsque le bouton "=" est pressé
        } else {
            // Sinon, afficher le résultat avec deux décimales
            display.setText(String.format("%.2f", result));
        }
    }
}

package com.mycompany.calculatrice;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// La classe ButtonActionListener implémente l'interface ActionListener
public class ButtonActionListener implements ActionListener {
    // Déclaration des variables d'instance
    private JTextField display;
    private CalculatorLogic calculatorLogic;
    private CalculatorUI calculatorUI;

    // Constructeur de la classe ButtonActionListener
    public ButtonActionListener(JTextField display, CalculatorLogic calculatorLogic, CalculatorUI calculatorUI) {
        // Initialisation des variables d'instance
        this.display = display;
        this.calculatorLogic = calculatorLogic;
        this.calculatorUI = calculatorUI;
    }

    // Méthode appelée lorsqu'une action est effectuée
    public void actionPerformed(ActionEvent e) {
        // Récupération de la commande de l'action
        String command = e.getActionCommand();
        // Si la commande est "C"
        if (command.equals("C")) {
            // Réinitialisation de l'affichage et du calcul
            display.setText("0");
            calculatorUI.setCalculating(true);
            calculatorLogic.reset();
        } 
        // Si la commande est "="
        else if (command.equals("=")) {
            // Calcul du résultat et affichage
            calculatorUI.calculate(Double.parseDouble(display.getText()));
            display.setText("" + calculatorLogic.getResult());
            calculatorUI.setCalculating(true);
        } 
        // Si la commande est un chiffre
        else if ("0123456789".contains(command)) {
            // Si on est en train de calculer, on remplace l'affichage par le chiffre
            // Sinon, on ajoute le chiffre à l'affichage
            if (calculatorUI.isCalculating()) {
                display.setText(command);
            } else {
                display.setText(display.getText() + command);
            }
            calculatorUI.setCalculating(false);
        } 
        // Si la commande est un opérateur
        else {
            // Si on n'est pas en train de calculer, on calcule le résultat intermédiaire
            if (!calculatorUI.isCalculating()) {
                calculatorUI.calculate(Double.parseDouble(display.getText()));
                calculatorUI.displayResult(calculatorLogic.getResult());
            }
            // On se prépare à calculer avec le nouvel opérateur
            calculatorUI.setCalculating(true);
            calculatorLogic.setOperator(command);
        }
    }
}
package com.mycompany.calculatrice;

import javax.swing.*;

public class CalculatorUI {

    // Déclaration des variables de la classe
    private JFrame frame; // Cadre de l'interface utilisateur
    private JTextField display; // Zone d'affichage des résultats
    private CalculatorLogic calculatorLogic; // Logique de la calculatrice
    private boolean calculating; // Indicateur d'une opération en cours

    // Constructeur de la classe
    public CalculatorUI() {
        calculatorLogic = new CalculatorLogic(); // Initialisation de la logique de la calculatrice
        calculating = true; // Initialisation de l'indicateur d'opération

        frame = new JFrame("Calculator"); // Création du cadre avec le titre "Calculator"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configuration de l'action par défaut lors de la fermeture du cadre
        frame.setSize(400, 400); // Définition de la taille du cadre
        frame.setLayout(null); // Définition du gestionnaire de disposition à null

        display = new JTextField("0"); // Initialisation de la zone d'affichage avec "0"
        display.setBounds(20, 20, 360, 50); // Définition de la position et de la taille de la zone d'affichage
        display.setEditable(false); // La zone d'affichage n'est pas modifiable
        frame.add(display); // Ajout de la zone d'affichage au cadre

        // Tableau des étiquettes des boutons
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "%", "=", 
            "+"
        };

        // Initialisation des coordonnées x et y pour la position des boutons
        int x = 20, y = 80;
        // Boucle pour créer et ajouter les boutons au cadre
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]); // Création du bouton
            button.setBounds(x, y, 80, 40); // Définition de la position et de la taille du bouton
            button.addActionListener(new ButtonActionListener(display, calculatorLogic, this)); // Ajout d'un écouteur d'action au bouton
            frame.add(button); // Ajout du bouton au cadre

            // Mise à jour des coordonnées x et y pour la position du prochain bouton
            x += 90;
            if (i % 4 == 3) {
                x = 20;
                y += 50;
            }
        }

        frame.setVisible(true); // Rendre le cadre visible
    }

    // Méthode pour effectuer un calcul
    public void calculate(double n) {
        try {
            calculatorLogic.calculate(n); // Tentative de calcul
        } catch (ArithmeticException e) {
            // En cas d'exception arithmétique (par exemple, division par zéro), afficher un message d'erreur
            SwingUtilities.invokeLater(() -> display.setText("Erreur : Division par zéro"));
            return;
        }
        displayResult(calculatorLogic.getResult()); // Affichage du résultat du calcul
    }

    // Méthode pour afficher un résultat
    public void displayResult(double result) {
        if (result == (long) result) {
            // Si le résultat est un nombre entier, l'afficher sans décimales
            display.setText(String.format("%d", (long) result));
        } else {
            // Sinon, l'afficher avec deux décimales
            display.setText(String.format("%.2f", result));
        }
    }

    // Méthode pour vérifier si une opération est en cours
    public boolean isCalculating() {
        return calculating;
    }

    // Méthode pour définir si une opération est en cours
    public void setCalculating(boolean calculating) {
        this.calculating = calculating;
    }
}
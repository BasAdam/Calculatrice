package com.mycompany.calculatrice;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;


public class Calculatrice extends JFrame {

    private JTextField display;
    private double result = 0;
    private String operator = "=";
    private boolean calculating = true;

    public Calculatrice() {
        initComponents();
        initializeCalculatorFunctions();
    }

    // Remove the unused method displayResult(double)
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null); // Utiliser un layout null pour la simplicité
    
        display = new JTextField("0");
        display.setEditable(false);
        display.setBounds(10, 10, 280, 50); // Ajuster la taille et la position selon les besoins
        add(display);
    
        // Initialisation des boutons
        JButton[] buttons = new JButton[18];
        String[] buttonTexts = {"CE", "%", "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+"};
        
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(buttonTexts[i]);
            if (i < 2) { // Pour les deux premiers boutons
                buttons[i].setBounds(10 + i * 70, 70, 60, 60);
            } else { // Pour les boutons restants
                buttons[i].setBounds(10 + ((i - 2) % 4) * 70, 70 + ((i - 2) / 4 + 1) * 70, 60, 60);
            }
            add(buttons[i]);
    
            // Ajouter un ActionListener pour le bouton "CE" pour réinitialiser la calculatrice
            if (buttonTexts[i].equals("CE")) {
                buttons[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        resetCalculator();
                    }
                });
            }
        }
        setSize(290, 450);
    }
    
    private void resetCalculator() {
        display.setText("0");
        result = 0;
        operator = "=";
        calculating = true;
    }
    


    private void initializeCalculatorFunctions() {
        ActionListener insert = new InsertAction();
        ActionListener command = new CommandAction();


        for (int i = 0; i < 10; i++) { // Number buttons
            getButton(String.valueOf(i)).addActionListener(insert);
        }
        getButton("+").addActionListener(command);
        getButton("-").addActionListener(command);
        getButton("*").addActionListener(command);
        getButton("/").addActionListener(command);
        getButton("=").addActionListener(command);
        
    }

    private JButton getButton(String text) {
        for (Component comp : getContentPane().getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                if (text.equals(button.getText())) {
                    return button;
                }
            }
        }
        return null;
    }

    

    private class InsertAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String input = event.getActionCommand();
            if (calculating) {
                display.setText("");
                calculating = false;
            }
            display.setText(display.getText() + input);
        }
    }

    private void displayResult(double result) {
        if (result == (long) result)
            display.setText(String.format("%d",(long)result));
        else
            display.setText(String.format("%s",result));
    }

    private class CommandAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();

            if (command.equals(".")) {
                if (!display.getText().contains(".")) {
                    display.setText(display.getText() + ".");
                }
                return;
            }

            if (calculating) {
                if (command.equals("-")) {
                     display.setText(command);
                    calculating = false;
                } else {
                    operator = command;
                    display.setText(display.getText());
                }
            } else {
                double x = Double.parseDouble(display.getText());
                calculate(x);
                operator = command;
                calculating = true;
            }
        }
    }

    private void calculate(double n) {
        if (operator.equals("/") && n == 0) {
            display.setText("Erreur : Division par zéro");
            return;
        }
    
        switch (operator) {
            case "+":
                result += n;
                break;
            case "-":
                result -= n;
                break;
            case "*":
                result *= n;
                break;
            case "/":
                result /= n;
                break;
            case "=":
                result = n;
                break;
            case "%":
                result %= n;
            break;
        }

        displayResult(result);


    

    // Ajoutez ceci dans votre méthode initComponents
    JButton decimalButton = new JButton(".");
    decimalButton.setBounds(10, 70, 50, 50); // Ajustez la taille et la position comme nécessaire
    decimalButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        }
    });
    add(decimalButton);
}

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Calculatrice().setVisible(true);
            }
        });
    }
}

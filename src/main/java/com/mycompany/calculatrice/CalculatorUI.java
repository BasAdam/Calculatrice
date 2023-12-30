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

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);

        display = new JTextField("0");
        display.setBounds(20, 20, 360, 50);
        display.setEditable(false);
        frame.add(display);

        // Creating buttons
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "%", "=", "+"
        };

        int x = 20, y = 80;
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            button.setBounds(x, y, 80, 40);
            button.addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();
                    if (command.equals("C")) {
                        display.setText("0");
                        calculating = true;
                        calculatorLogic.reset();
                    } 
                    else if (command.equals("=")) {
                        calculate(Double.parseDouble(display.getText()));
                        display.setText("" + calculatorLogic.getResult());
                        calculating = true;
                    } 
                    else if ("0123456789".contains(command)) {
                        if (calculating) {
                            display.setText(command);
                        } else {
                            display.setText(display.getText() + command);
                        }
                        calculating = false;
                    } 
                    
                    else {
                        
                        if (!calculating) {
                            calculate(Double.parseDouble(display.getText()));
                            displayResult(calculatorLogic.getResult());
                        }

                        calculating = true;
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

        frame.setVisible(true);

        
    }

    private void calculate(double n) {
        try {
            calculatorLogic.calculate(n);
        } catch (ArithmeticException e) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    display.setText("Erreur : Division par zéro");
                }
            });
            return;
        }
        displayResult(calculatorLogic.getResult());
    }
    
    
    
    private void displayResult(double result) {
        if (result == (long) result) {
            display.setText(String.format("%d", (long) result));
        } else {
            display.setText(String.format("%.2f", result));
        }
    }    

    
    
}

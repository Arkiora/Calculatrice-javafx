package fr.bts.sio.tp_calculatrice;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML private TextField display;

    private double firstOperand = 0;
    private String operator     = "";
    private boolean startNewNumber = true;

    @FXML
    private void onDigitClicked(javafx.event.ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        if (startNewNumber) {
            display.clear();
            startNewNumber = false;
        }
        display.appendText(value);
    }

    @FXML
    private void onOperatorClicked(javafx.event.ActionEvent event) {
        if (display.getText().isEmpty()) return;
        operator     = ((Button) event.getSource()).getText();
        firstOperand = Double.parseDouble(display.getText());
        startNewNumber = true;
    }

    @FXML
    private void onEqualClicked() {
        if (operator.isEmpty() || display.getText().isEmpty()) return;
        double secondOperand = Double.parseDouble(display.getText());
        double result = switch (operator) {
            case "+" -> firstOperand + secondOperand;
            case "−" -> firstOperand - secondOperand;
            case "×" -> firstOperand * secondOperand;
            case "÷" -> (secondOperand == 0) ? Double.NaN : firstOperand / secondOperand;
            default   -> secondOperand;
        };
        display.setText(formatResult(result));
        operator = "";
        startNewNumber = true;
    }

    @FXML
    private void onClearClicked() {
        display.clear();
        firstOperand = 0;
        operator = "";
        startNewNumber = true;
    }

    private String formatResult(double v) {
        return (v == (long) v) ? String.format("%d", (long) v) : String.valueOf(v);
    }
}

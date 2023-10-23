package maman13_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class CalculatorController {
    /* The controller class of the calculator application.
     */

    @FXML
    private TextArea expressionText;

    @FXML
    private TextArea currOperatorText;

    @FXML
    private GridPane buttonGrid;

    private double prevResult;
    private char operator = Character.MIN_VALUE;
    private StringBuilder currentOperandStrBuilder = new StringBuilder();

    @FXML
    void initialize() {
        // Initialize the calculator 1-9 buttons
        buttonGrid.setAlignment(Pos.CENTER);
        for (int i = 0; i < 9; i++) {
            Button numberButton = new Button();
            numberButton.setText(String.valueOf(i + 1));
            numberButton.setOnAction(this::numberPressed);
            numberButton.setPrefHeight(50);
            numberButton.setPrefWidth(50);
            numberButton.setFont(new Font(24));
            buttonGrid.add(numberButton, i % 3, 3 - i / 3);
            GridPane.setHalignment(numberButton, HPos.CENTER);
            GridPane.setValignment(numberButton, VPos.CENTER);
        }
    }

    @FXML
    void changeSignPressed(ActionEvent event) {
        // +/- button pressed. Change the sign of the current operand
        if (currentOperandStrBuilder.length() == 0) {
            return;
        }
        if (currentOperandStrBuilder.charAt(0) == '-') {
            currentOperandStrBuilder.deleteCharAt(0);
        } else {
            currentOperandStrBuilder.insert(0, "-");
        }
        updateCurrOperandText();
    }

    @FXML
    void clearPressed(ActionEvent event) {
        // Reset the calculator
        currentOperandStrBuilder.setLength(0);
        operator = Character.MIN_VALUE;
        prevResult = 0;
        expressionText.setText("");
        updateCurrOperandText();
    }

    @FXML
    void delPressed(ActionEvent event) {
        // Delete the last digit of the current operand
        if (currentOperandStrBuilder.length() > 0) {
            currentOperandStrBuilder.deleteCharAt(currentOperandStrBuilder.length() - 1);
        }
        if (currentOperandStrBuilder.length() == 1 && currentOperandStrBuilder.charAt(0) == '-') {
            currentOperandStrBuilder.deleteCharAt(0);
        }
        updateCurrOperandText();
    }

    @FXML
    void decimalPressed(ActionEvent event) {
        // Add a decimal point to the current operand
        if (operator == '=') {
            // Last action was equals. Delete the previous expression text
            reset();
        }
        if (!currentOperandStrBuilder.toString().contains(".")) {
            currentOperandStrBuilder.append(".");
        }
        updateCurrOperandText();
    }


    @FXML
    void equalsPressed(ActionEvent event) {
        // Equals button pressed. Calculate and show the result with both operands and the operator
        if (operator == Character.MIN_VALUE || operator == '=' || currentOperandStrBuilder.length() == 0) {
            return;
        }
        expressionText.setText(prevResult + " " + operator + " " + currentOperandStrBuilder.toString() + " = ");
        prevResult = calculate();
        currOperatorText.setText(Double.toString(prevResult));
        currentOperandStrBuilder.setLength(0);
        operator = '=';
    }

    @FXML
    void minusPressed(ActionEvent event) {
        handleOperatorPressed('-');
    }

    @FXML
    void plusPressed(ActionEvent event) {
        handleOperatorPressed('+');
    }

    @FXML
    void multiplyPressed(ActionEvent event) {
        handleOperatorPressed('*');
    }

    @FXML
    void dividePressed(ActionEvent event) {
        handleOperatorPressed('/');
    }

    @FXML
    void numberPressed(ActionEvent event) {
        // A number button was pressed. Add the number to the current operand
        Button pressedBtn = (Button) event.getSource();
        String btnText = pressedBtn.getText();
        if (operator == '=') {
            // Last action was equals. Delete the previous expression text
            reset();
        }
        if (currentOperandStrBuilder.length() == 1 && currentOperandStrBuilder.charAt(0) == '0') {
            // Ignore leading zeros
            currentOperandStrBuilder.setCharAt(0, btnText.charAt(0));
        } else {
            currentOperandStrBuilder.append(btnText);
        }
        updateCurrOperandText();
    }

    private void handleOperatorPressed(char newOperator) {
        // An operator button was pressed.
        // Calculate the result with the previous operator and operand and set the new operator
        prevResult = calculate();
        operator = newOperator;

        expressionText.setText(prevResult + " " + operator);
        updateCurrOperandText();
        currentOperandStrBuilder.setLength(0);
    }

    private double calculate() {
        // Calculate the result with the previous operator and operand
        if (currentOperandStrBuilder.length() == 0) {
            return prevResult;
        }
        double currentOperand = Double.parseDouble(currentOperandStrBuilder.toString());
        double result = currentOperand;
        switch (operator) {
            case '+':
                result = prevResult + currentOperand;
                break;
            case '-':
                result = prevResult - currentOperand;
                break;
            case '*':
                result = prevResult * currentOperand;
                break;
            case '/':
                result = prevResult / currentOperand;
                break;
        }
        return result;
    }

    private void reset() {
        // Reset the calculator
        currentOperandStrBuilder.setLength(0);
        operator = Character.MIN_VALUE;
        prevResult = 0;
        expressionText.setText("");
        updateCurrOperandText();
    }

    private void updateCurrOperandText() {
        currOperatorText.setText(currentOperandStrBuilder.toString());
    }

}
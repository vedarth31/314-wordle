package com.example.finalprojectwordle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    private Button resetButton;
    @FXML
    private TextField guess1letter1;
    @FXML
    private TextField guess1letter2;
    @FXML
    private TextField guess1letter3;
    @FXML
    private TextField guess1letter4;
    @FXML
    private TextField guess1letter5;
    @FXML
    private TextField guess2letter1;
    @FXML
    private TextField guess2letter2;
    @FXML
    private TextField guess2letter3;
    @FXML
    private TextField guess2letter4;
    @FXML
    private TextField guess2letter5;
    @FXML
    private TextField guess3letter1;
    @FXML
    private TextField guess3letter2;
    @FXML
    private TextField guess3letter3;
    @FXML
    private TextField guess3letter4;
    @FXML
    private TextField guess3letter5;
    @FXML
    private TextField guess4letter1;
    @FXML
    private TextField guess4letter2;
    @FXML
    private TextField guess4letter3;
    @FXML
    private TextField guess4letter4;
    @FXML
    private TextField guess4letter5;
    @FXML
    private TextField guess5letter1;
    @FXML
    private TextField guess5letter2;
    @FXML
    private TextField guess5letter3;
    @FXML
    private TextField guess5letter4;
    @FXML
    private TextField guess5letter5;
    @FXML
    private TextField guess6letter1;
    @FXML
    private TextField guess6letter2;
    @FXML
    private TextField guess6letter3;
    @FXML
    private TextField guess6letter4;
    @FXML
    private TextField guess6letter5;

    @FXML
    private Label error;

    private TextField[][] arr = new TextField[6][5];

    private int currentGuess = 0;

    @FXML
    private void initialize() {

        TextField[][] guesses = {
                {guess1letter1, guess1letter2, guess1letter3, guess1letter4, guess1letter5},
                {guess2letter1, guess2letter2, guess2letter3, guess2letter4, guess2letter5},
                {guess3letter1, guess3letter2, guess3letter3, guess3letter4, guess3letter5},
                {guess4letter1, guess4letter2, guess4letter3, guess4letter4, guess4letter5},
                {guess5letter1, guess5letter2, guess5letter3, guess5letter4, guess5letter5},
                {guess6letter1, guess6letter2, guess6letter3, guess6letter4, guess6letter5}
        };

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                arr[i][j] = guesses[i][j];
                maxLengthListener(arr[i][j]);
            }
        }

        disableAllGuessesExceptRow(0);
        resetButton.setOnAction(event -> reset());
    }

    private void disableAllGuessesExceptRow(int row) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                arr[i][j].setDisable(i != row);
            }
        }
    }

    private boolean isRowFull(int row) {
        for (int j = 0; j < 5; j++) {
            if (arr[row][j].getText().isEmpty()) {
                System.out.println(arr[row][j]);
                return false;
            }
        }
        return true;
    }

    @FXML
    private void maxLengthListener(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.length() > 1) {
                textField.setText(newValue.substring(0, 1));
            }
        });
    }

    @FXML
    private void clearTextFields() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                arr[i][j].clear();
            }
        }
    }

    @FXML
    public void reset() {
        clearTextFields();
        disableAllGuessesExceptRow(0);
        currentGuess = 0;
        System.out.println("Cleared");
    }

    @FXML
    protected void guess() {
        System.out.println(currentGuess);
        if(currentGuess >= 0 && currentGuess <= 4) {
            if (!isRowFull(currentGuess)) {
                error.setText("Please enter the full word.");
                System.out.println("Full word not entered");
            } else {
                error.setText("");
                disableAllGuessesExceptRow(currentGuess + 1);
                System.out.println("Making guess!");
                currentGuess++;
            }
        } else if (currentGuess == 5) {
            if(!isRowFull(5)) {
                error.setText("Please enter the full word.");
                System.out.println("Full word not entered.");
            } else {
                error.setText("");
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 5; j++) {
                        arr[i][j].setDisable(true);
                    }
                }
                System.out.println("Making sixth and final guess!");
            }
        }
    }

}
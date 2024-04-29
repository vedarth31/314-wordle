package com.example.finalprojectwordle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.*;
//import javafx.scene.control.Label;

public class Controller {
    @FXML private Button resetButton;
    @FXML private TextField guess1letter1;
    @FXML private TextField guess1letter2;
    @FXML private TextField guess1letter3;
    @FXML private TextField guess1letter4;
    @FXML private TextField guess1letter5;
    @FXML private TextField guess2letter1;
    @FXML private TextField guess2letter2;
    @FXML private TextField guess2letter3;
    @FXML private TextField guess2letter4;
    @FXML private TextField guess2letter5;
    @FXML private TextField guess3letter1;
    @FXML private TextField guess3letter2;
    @FXML private TextField guess3letter3;
    @FXML private TextField guess3letter4;
    @FXML private TextField guess3letter5;
    @FXML private TextField guess4letter1;
    @FXML private TextField guess4letter2;
    @FXML private TextField guess4letter3;
    @FXML private TextField guess4letter4;
    @FXML private TextField guess4letter5;
    @FXML private TextField guess5letter1;
    @FXML private TextField guess5letter2;
    @FXML private TextField guess5letter3;
    @FXML private TextField guess5letter4;
    @FXML private TextField guess5letter5;
    @FXML private TextField guess6letter1;
    @FXML private TextField guess6letter2;
    @FXML private TextField guess6letter3;
    @FXML private TextField guess6letter4;
    @FXML private TextField guess6letter5;

    @FXML private Button A;
    @FXML private Button B;
    @FXML private Button C;
    @FXML private Button D;
    @FXML private Button E;
    @FXML private Button F;
    @FXML private Button G;
    @FXML private Button H;
    @FXML private Button I;
    @FXML private Button J;
    @FXML private Button K;
    @FXML private Button L;
    @FXML private Button M;
    @FXML private Button N;
    @FXML private Button O;
    @FXML private Button P;
    @FXML private Button Q;
    @FXML private Button R;
    @FXML private Button S;
    @FXML private Button T;
    @FXML private Button U;
    @FXML private Button V;
    @FXML private Button W;
    @FXML private Button X;
    @FXML private Button Y;
    @FXML private Button Z;


    @FXML
    private Label error;

    private TextField[][] arr = new TextField[6][5];
    private Button[] keyboard = new Button[26];

    private int currGuessRow = 0;
    private int currGuessCol;

    WordValidation wordValidation = new WordValidation();
    String randomWord = wordValidation.getRandomWord();

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

        Button[] letters = {A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z};
        for(int i = 0; i < 25; i++) {
            keyboard[i] = letters[i];
        }

        disableAllGuessesExceptRow(0);
        resetButton.setOnAction(event -> reset());

//        wordValidation.loadWordsFromFile();
        System.out.println("random word: " + randomWord);
    }

    @FXML
    private void clickLetter(ActionEvent e) {
        if(currGuessCol >= 5) {
            return;
        }
        Button clicked = (Button) e.getSource();
        String guessedLetter = clicked.getText();
        arr[currGuessRow][currGuessCol].setText(guessedLetter);

        currGuessCol++;
        nextLetter(currGuessRow);
    }

    private void nextLetter(int row) {
        int nextRow = row;
        int nextColumn = 0;

        TextField focusedTextField = null;
        for (int i = 0; i < 5; i++) {
            if (arr[row][i].isFocused()) {
                focusedTextField = arr[row][i];
                break;
            }
        }

        if (focusedTextField == null) {
            nextColumn = 0;
        } else {
            for (int i = 0; i < 4; i++) {
                if (arr[row][i] == focusedTextField) {
                    nextColumn = i + 1;
                    break;
                }
            }
        }
        if (nextRow < 6 && nextColumn < 5) {
            arr[nextRow][nextColumn].requestFocus();
        }
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
        error.setText("");
        currGuessRow = 0;
        System.out.println("Cleared");
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 5; j++) {
                arr[i][j].setStyle(null);
            }
        }

        for(int i = 0; i < 25; i++) {
            keyboard[i].setStyle(null);
        }

        randomWord = wordValidation.getRandomWord();
        currGuessCol = 0;
    }

    public String getWord() {
        String word = "";
        for(int j = 0; j < 5; j++) {
            word += arr[currGuessRow][j].getText();
        }
        return word;
    }

    @FXML
    protected void guess() {

        String currGuessWord = getWord();
        currGuessCol = 0;
        System.out.println("Your guess: " + currGuessWord);
//        WordValidation wordValidation = new WordValidation();
//        if(!wordValidation.isValidWord(currGuessWord)) {
//            error.setText("That's an invalid word!");
//        } else {
            System.out.println("currRow: " + currGuessRow);
            if (currGuessRow >= 0 && currGuessRow <= 4) {
                if (!isRowFull(currGuessRow)) {
                    error.setText("Please enter the full word.");
                    System.out.println("Full word not entered");
                } else {
                    error.setText("");
                    setColor();
                    disableAllGuessesExceptRow(currGuessRow + 1);
                    System.out.println("Making guess!");
                    currGuessRow++;
                }
            } else if (currGuessRow == 5) {
                if (!isRowFull(5)) {
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
//        }
    }

    public void setColor() {

        String currGuessWord = getWord();
        Integer[] checkedWord = wordValidation.checkWord(currGuessWord.toLowerCase(), randomWord);

        for(int i = 0; i < 5; i++) {
            if(checkedWord[i] == 2) {
                arr[currGuessRow][i].setStyle("-fx-background-color: green");
                keyboard[((int) currGuessWord.charAt(i)) - 65].setStyle("-fx-background-color: green");
            } else if (checkedWord[i] == 1) {
                arr[currGuessRow][i].setStyle("-fx-background-color: orange");
                keyboard[((int) currGuessWord.charAt(i)) - 65].setStyle("-fx-background-color: orange");
            } else if (checkedWord[i] == 0) {
                arr[currGuessRow][i].setStyle("-fx-background-color: gray");
                keyboard[((int) currGuessWord.charAt(i)) - 65].setStyle("-fx-background-color: gray");
            }
        }
        checkWin(checkedWord);
    }

    public boolean checkWin(Integer[] checkedInts) {
        for(int i = 0; i < 5; i++) {
            if (checkedInts[i] != 2){
                return false;
            }
        }
        error.setText("you won!");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                arr[i][j].setDisable(true);
            }
        }
        return true;
    }

//    public static void main(String[] args) {
//
//    }
}
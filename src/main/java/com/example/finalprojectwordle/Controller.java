package com.example.finalprojectwordle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;


public class Controller {
    @FXML private Button resetButton;
    @FXML private Label guess1letter1;
    @FXML private Label guess1letter2;
    @FXML private Label guess1letter3;
    @FXML private Label guess1letter4;
    @FXML private Label guess1letter5;
    @FXML private Label guess2letter1;
    @FXML private Label guess2letter2;
    @FXML private Label guess2letter3;
    @FXML private Label guess2letter4;
    @FXML private Label guess2letter5;
    @FXML private Label guess3letter1;
    @FXML private Label guess3letter2;
    @FXML private Label guess3letter3;
    @FXML private Label guess3letter4;
    @FXML private Label guess3letter5;
    @FXML private Label guess4letter1;
    @FXML private Label guess4letter2;
    @FXML private Label guess4letter3;
    @FXML private Label guess4letter4;
    @FXML private Label guess4letter5;
    @FXML private Label guess5letter1;
    @FXML private Label guess5letter2;
    @FXML private Label guess5letter3;
    @FXML private Label guess5letter4;
    @FXML private Label guess5letter5;
    @FXML private Label guess6letter1;
    @FXML private Label guess6letter2;
    @FXML private Label guess6letter3;
    @FXML private Label guess6letter4;
    @FXML private Label guess6letter5;

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

    @FXML private Label error;

//    @FXML private Button loadButton;

    private Label[][] arr = new Label[6][5];
    private Button[] keyboard = new Button[26];
    private Integer[][] wordColors = new Integer[6][5];

    private int currGuessRow = 0;
    private int currGuessCol;

    private int numGamesPlayed = 0;

    WordValidation wordValidation = new WordValidation();
    String randomWord = wordValidation.getRandomWord();

    private int numTotalWins = 0;

    int[] guessDistribution = new int[6];

    @FXML
    private void initialize() {

        Label[][] guesses = {
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
            }
        }

        Button[] letters = {A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z};
        for(int i = 0; i < 26; i++) {
            keyboard[i] = letters[i];
        }

        resetButton.setOnAction(event -> reset());
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

        Label focusedLabel = null;
        for (int i = 0; i < 5; i++) {
            if (arr[row][i].isFocused()) {
                focusedLabel = arr[row][i];
                break;
            }
        }

        if (focusedLabel == null) {
            nextColumn = 0;
        } else {
            for (int i = 0; i < 4; i++) {
                if (arr[row][i] == focusedLabel) {
                    nextColumn = i + 1;
                    break;
                }
            }
        }
        if (nextRow < 6 && nextColumn < 5) {
            arr[nextRow][nextColumn].requestFocus();
        }
    }

    @FXML
    private void clearTextFields() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                arr[i][j].setText("");
                arr[i][j].setStyle("-fx-border-color: black");
            }
        }
    }

    @FXML
    public void reset() {
        clearTextFields();
        error.setText("");
        currGuessRow = 0;
        System.out.println("Cleared");

        for(int i = 0; i < 26; i++) {
            keyboard[i].setStyle(null);
            keyboard[i].setStyle("-fx-border-color: gray");
        }

        randomWord = wordValidation.getRandomWord();
        currGuessCol = 0;
    }

    public String getWord() {
        String word = "";
        for(int j = 0; j < 5; j++) {
            word += arr[currGuessRow][j].getText();
        }
        return word.toLowerCase();
    }

    @FXML
    protected void guess() {

        String currGuessWord = getWord();
        Integer[] checkedWord = wordValidation.checkWord(currGuessWord.toLowerCase(), randomWord);
        for(int i = 0; i < 5; i++) {
            wordColors[currGuessRow][i] = checkedWord[i];
//            System.out.println(wordColors[currGuessRow][i]);
        }
        currGuessCol = 0;
        System.out.println("Your guess: " + currGuessWord);
        WordValidation wordValidation = new WordValidation();
        if(!wordValidation.isValidWord(currGuessWord)) {
            error.setText("Please enter a valid word!");
            currGuessCol = currGuessWord.length();

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> error.setText(""));
                }
            }, 2000);
        } else {
//            System.out.println("currRow: " + currGuessRow);
            error.setText("");
            setColor(checkedWord);
            checkWin(checkedWord);
//            System.out.println("Making guess!");
            currGuessRow++;
        }
    }

    public void setColor(Integer[] checkedWord) {
        String currGuessWord = getWord().toLowerCase();
        setColor(checkedWord, currGuessRow, currGuessWord);
    }

    public void setColor(Integer [] checkedWord, int row, String currGuessWord) {

        if (!currGuessWord.isEmpty() && currGuessWord.length() == 5) {
            for(int i = 0; i < 5; i++) {
                if (checkedWord[i] == 2) {
                    arr[row][i].setStyle("-fx-background-color: green");
                    keyboard[((int) currGuessWord.charAt(i)) - 97].setStyle("-fx-background-color: green");
                } else if (checkedWord[i] == 1) {
                    arr[row][i].setStyle("-fx-background-color: orange");
                    keyboard[((int) currGuessWord.charAt(i)) - 97].setStyle("-fx-background-color: orange");
                } else if (checkedWord[i] == 0) {
                    arr[row][i].setStyle("-fx-background-color: gray");
                    keyboard[((int) currGuessWord.charAt(i)) - 97].setStyle("-fx-background-color: gray");
                }
            }
        }
    }

    public void checkWin(Integer[] checkedInts) {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            if (checkedInts[i] == 2) {
                sum++;
            }
        }

        if(sum == 5) {
            error.setText("You win!");
            numTotalWins++;
            guessDistribution[currGuessRow]++;
            numGamesPlayed++;
            displayStatsBoard(true);
        } else {
            if(currGuessRow == 5){
                numGamesPlayed++;
                displayStatsBoard(false);
            }
        }
    }

    public void displayStatsBoard(boolean win) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StatsBoard.fxml"));
            Parent root = loader.load();

            StatsBoardController statsBoardController = loader.getController();
            statsBoardController.setMainController(this);
            statsBoardController.updateStats(numGamesPlayed, win, guessDistribution, numTotalWins);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Stats Board");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void delete() {
        if(currGuessCol > 0) {
            currGuessCol--;
            arr[currGuessRow][currGuessCol].setText("");
            arr[currGuessRow][currGuessCol].setStyle("-fx-border-color: black");
        }
    }

    @FXML
    public void loadButton() {
        String filePath = "/Users/vedarth/college/CSCE314/FinalProject-Wordle/src/main/java/com/example/finalprojectwordle/savedGame.txt";

        File file = new File(filePath);

        try {

            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);

            if(file.length() != 0) {

                String word = reader.readLine();
                randomWord = word.toLowerCase();
//                System.out.println("Loaded game word: " + word);
                String currRow = reader.readLine();
                currGuessRow = Integer.parseInt(currRow);
//                System.out.println("First line: " + currRow);
                String currCol = reader.readLine();
                currGuessCol = Integer.parseInt(currCol);
//                System.out.println("Second line: " + currCol);

                int currentRow = 0;
                for (int i = 0; i < 12; i++) {
                    String wordLine = reader.readLine();
                    if (wordLine == null) break;
                    String[] characters = wordLine.split("");
                    for (int col = 0; col < characters.length && col < 5; col++) {
                        arr[currentRow][col].setText(characters[col].toUpperCase());
                    }
                    String colorsLine = reader.readLine();
                    if (colorsLine == null) break;
                    String[] colorsCharacters = colorsLine.split("");
                    for (int col = 0; col < colorsCharacters.length && col < 5; col++) {
//                        System.out.println(colorsCharacters[col]);
//                        System.out.println("row " + currentRow);
//                        System.out.println("col " + col);
                        wordColors[currentRow][col] = Integer.parseInt(colorsCharacters[col]);
                    }
                    currentRow++;
                    setColor(wordColors[i], i, wordLine);
                }

            }

            reader.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void saveButton() {
        String filePath = "/Users/vedarth/college/CSCE314/FinalProject-Wordle/src/main/java/com/example/finalprojectwordle/savedGame.txt";

        try {
            FileWriter writer = new FileWriter(filePath);

            writer.write(randomWord + "\n");
            writer.write(currGuessRow + "\n");
            writer.write(currGuessCol + "\n");

            for(int i = 0; i < 6; i++) {

                boolean x = false;
                boolean y = false;

                String currWord = "";
                String currColor = "";

                for(int j = 0; j < 5; j++) {

                    if(!arr[i][j].getText().isEmpty()) {
                        currWord += arr[i][j].getText().toLowerCase();
//                        System.out.println("curr letter: " + arr[i][j].getText().toLowerCase());
                        y = true;
                    }
                    if(wordColors[i][j] != null) {
                        currColor += wordColors[i][j];
//                        System.out.println("current letter color: " + wordColors[i][j]);
                        x = true;
                    }
                }

                if(y) {
                    writer.write(currWord + "\n");
                }
                if(x) {
                    writer.write(currColor + "\n");
                }

            }

            writer.close();

            System.out.println("Data has been written to the file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
            e.printStackTrace();
        }

    }

}
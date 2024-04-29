package com.example.finalprojectwordle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class StatsBoardController {

    @FXML
    private Label gamesPlayedLabel;

    @FXML
    private Label gamesWonLabel;

    @FXML
    private Label num1GuessWin;

    @FXML
    private Label num2GuessWin;

    @FXML
    private Label num3GuessWins;

    @FXML
    private Label num4GuessWins;

    @FXML
    private Label num5GuessWins;

    @FXML
    private Label num6GuessWins;

    @FXML
    private Button playAgainButton;


    private Controller mainController;

    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

    public void initialize() {

//        int[] guesses = mainController.getGuessDistribution();
//        int numGamesWon = mainController.getNumTotalWins();
//        System.out.println(guesses);
//        gamesWonLabel.setText("" + numGamesWon);
    }

    public void updateStats(int[] guessDistributions, int numTotalWins) {
        num1GuessWin.setText("" + guessDistributions[0]);
        num2GuessWin.setText("" + guessDistributions[1]);
        num3GuessWins.setText("" + guessDistributions[2]);
        num4GuessWins.setText("" + guessDistributions[3]);
        num5GuessWins.setText("" + guessDistributions[4]);
        num6GuessWins.setText("" + guessDistributions[5]);


        gamesWonLabel.setText(String.valueOf(numTotalWins));
    }

    @FXML
    void handlePlayAgain(ActionEvent event) {

        System.out.println(mainController == null);
        if (mainController != null) {
            mainController.reset();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

}

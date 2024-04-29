package com.example.finalprojectwordle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage statsStage; // Declare statsStage as a static variable

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = mainLoader.load();
        primaryStage.setTitle("Wordle!");
        primaryStage.setScene(new Scene(root, 508, 768));
        primaryStage.show();

//        Controller mainController = mainLoader.getController();
//
//        // Initialize statsStage
//        statsStage = new Stage();
//        FXMLLoader statsLoader = new FXMLLoader(getClass().getResource("StatsBoard.fxml"));
//        Parent statsRoot = statsLoader.load();
//        statsStage.setTitle("Stats");
//        statsStage.setScene(new Scene(statsRoot, 400, 300));
//
//        StatsBoardController statsBoardController = statsLoader.getController();
//        statsBoardController.setMainController(mainController);
//
//        statsBoardController.setGuessDistributions(mainController.getGuessDistribution());
//        statsBoardController.setNumTotalWins(mainController.getNumTotalWins());
    }

    // Method to show the stats stage
//    public static void showStatsStage() {
//        if (statsStage != null) {
//            statsStage.show();
//        } else {
//            System.out.println("Stats stage is null.");
//        }
//    }

    public static void main(String[] args) {
        launch();
    }
}

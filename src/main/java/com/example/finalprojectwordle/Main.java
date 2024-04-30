package com.example.finalprojectwordle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = mainLoader.load();
        primaryStage.setTitle("Wordle!");
        primaryStage.setScene(new Scene(root, 508, 700));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
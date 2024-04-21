package com.example.finalprojectwordle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;

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

    ArrayList<TextField> arr = new ArrayList<TextField>();

    @FXML
    private void initialize() {
        arr.add(guess1letter1);
        arr.add(guess1letter2);
        arr.add(guess1letter3);
        arr.add(guess1letter4);
        arr.add(guess1letter5);
        arr.add(guess2letter1);
        arr.add(guess2letter2);
        arr.add(guess2letter3);
        arr.add(guess2letter4);
        arr.add(guess2letter5);
        arr.add(guess3letter1);
        arr.add(guess3letter2);
        arr.add(guess3letter3);
        arr.add(guess3letter4);
        arr.add(guess3letter5);
        arr.add(guess4letter1);
        arr.add(guess4letter2);
        arr.add(guess4letter3);
        arr.add(guess4letter4);
        arr.add(guess4letter5);
        arr.add(guess5letter1);
        arr.add(guess5letter2);
        arr.add(guess5letter3);
        arr.add(guess5letter4);
        arr.add(guess5letter5);
        arr.add(guess6letter1);
        arr.add(guess6letter2);
        arr.add(guess6letter3);
        arr.add(guess6letter4);
        arr.add(guess6letter5);

        for (TextField x: arr) {
            maxLengthListener(x);
            x.setDisable(true);
        }

        guess1letter1.setDisable(false);
        guess1letter2.setDisable(false);
        guess1letter3.setDisable(false);
        guess1letter4.setDisable(false);
        guess1letter5.setDisable(false);

        resetButton.setOnAction(event -> reset());
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
        for(TextField x: arr) {
            x.clear();
        }
    }

    @FXML
    public void reset() {
        for (TextField x: arr) {
            x.setDisable(true);
        }
        clearTextFields();
        guess1letter1.setDisable(false);
        guess1letter2.setDisable(false);
        guess1letter3.setDisable(false);
        guess1letter4.setDisable(false);
        guess1letter5.setDisable(false);
        System.out.println("Cleared");
    }

    @FXML
    protected void guess1() {
        for(TextField x: arr) {
            x.setDisable(true);
        }
        guess2letter1.setDisable(false);
        guess2letter2.setDisable(false);
        guess2letter3.setDisable(false);
        guess2letter4.setDisable(false);
        guess2letter5.setDisable(false);
        System.out.println("Making first guess!");
    }

    @FXML
    protected void guess2() {
        for(TextField x: arr) {
            x.setDisable(true);
        }
        guess3letter1.setDisable(false);
        guess3letter2.setDisable(false);
        guess3letter3.setDisable(false);
        guess3letter4.setDisable(false);
        guess3letter5.setDisable(false);
        System.out.println("Making second guess!");
    }

    @FXML
    protected void guess3() {
        for(TextField x: arr) {
            x.setDisable(true);
        }
        guess4letter1.setDisable(false);
        guess4letter2.setDisable(false);
        guess4letter3.setDisable(false);
        guess4letter4.setDisable(false);
        guess4letter5.setDisable(false);
        System.out.println("Making third guess!");
    }

    @FXML
    protected void guess4() {
        for(TextField x: arr) {
            x.setDisable(true);
        }
        guess5letter1.setDisable(false);
        guess5letter2.setDisable(false);
        guess5letter3.setDisable(false);
        guess5letter4.setDisable(false);
        guess5letter5.setDisable(false);
        System.out.println("Making fourth guess!");
    }

    @FXML
    protected void guess5() {
        for(TextField x: arr) {
            x.setDisable(true);
        }
        guess6letter1.setDisable(false);
        guess6letter2.setDisable(false);
        guess6letter3.setDisable(false);
        guess6letter4.setDisable(false);
        guess6letter5.setDisable(false);
        System.out.println("Making fifth guess!");
    }
    @FXML
    protected void guess6() {
        for(TextField x: arr) {
            x.setDisable(true);
        }
        System.out.println("Making sixth and final guess!");
    }
}
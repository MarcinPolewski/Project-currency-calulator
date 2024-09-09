package com.example.currencycalculator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements  Initializable {
    @FXML
    ChoiceBox sourceCurrencyChoiceBox;
    @FXML
    TextField userValueInputTextField;
    @FXML
    ChoiceBox resultCurrencyChoiceBox;
    @FXML
    TextField resultValueTextField;
    @FXML
    Button swapButton;

    @FXML
    VBox leftVBox;

    @FXML
    VBox rightVBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // ============== setting proportions of ChoiceBoxes and Text fiels to 2:1 ==============
        //whenever height of container changes, height of nodes also does
        leftVBox.heightProperty().addListener((observable, odValue, newValue) ->{
            double containerHeight = newValue.doubleValue();
            sourceCurrencyChoiceBox.setPrefHeight(containerHeight/3.0);
            userValueInputTextField.setPrefHeight((containerHeight*2.0)/3.0);
            resultCurrencyChoiceBox.setPrefHeight(containerHeight/3.0);
            resultValueTextField.setPrefHeight((containerHeight*2.0)/3.0);
        });


    }
}

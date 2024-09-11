package com.example.currencycalculator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainScreenController implements  Initializable, ControllerInterface {
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

    @FXML
    VBox exchangeRatesVBox1;
    @FXML
    VBox exchangeRatesVBox2;
    @FXML
    VBox exchangeRatesVBox3;
    @FXML
    VBox exchangeRatesVBox4;

    private CurrencyCalculator currencyCalculator;
    private ArrayList<ExchangeRateLabel> exchangeRateLabels;

    private void changeExchangeRateseScreen(Currency newBaseCurrency)
    {

    }

    @FXML
    public void swapButtonPressed()
    {
        if(sourceCurrencyChoiceBox.getValue() != null || resultCurrencyChoiceBox.getValue() != null)
        {
            Currency c1 = (Currency)sourceCurrencyChoiceBox.getValue();
            Currency c2 = (Currency)resultCurrencyChoiceBox.getValue();

            resultCurrencyChoiceBox.setValue(c1);
            sourceCurrencyChoiceBox.setValue(c2);
        }
    }

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

    @Override
    public void processingAfterInitialization() {
        // ============ set contents of Choice Boxes
        sourceCurrencyChoiceBox.getItems().setAll(currencyCalculator.getCurrencies());
        resultCurrencyChoiceBox.getItems().setAll(currencyCalculator.getCurrencies());

        // setting default value of the boxes
        if(currencyCalculator.getCurrencies().size() >= 2)
        {
            sourceCurrencyChoiceBox.setValue(currencyCalculator.getCurrencies().get(0));
            resultCurrencyChoiceBox.setValue(currencyCalculator.getCurrencies().get(1));
        }

        // ============ implement exchange rates screen
        //changeExchangeRateseScreen((Currency)sourceCurrencyChoiceBox.getValue());

        Currency baseCurrency = (Currency)sourceCurrencyChoiceBox.getValue();

        VBox[] containers = {exchangeRatesVBox1, exchangeRatesVBox2, exchangeRatesVBox3, exchangeRatesVBox4};
        int containerIt = 0;

        for(Currency currency : currencyCalculator.getCurrencies())
        {
            if(containerIt >= containers.length)
            {
                containerIt = 0;
            }

            ExchangeRateLabel label = new ExchangeRateLabel(baseCurrency, currency);
            containers[containerIt].getChildren().add(label);
            ++containerIt;

        }
    }

    @Override
    public void setCurrencyCalculator(CurrencyCalculator cc) {
        this.currencyCalculator = cc;

    }
}

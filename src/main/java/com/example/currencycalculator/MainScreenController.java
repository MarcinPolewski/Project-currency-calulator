package com.example.currencycalculator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainScreenController implements  Initializable, ControllerInterface {
    @FXML
    ChoiceBox<Currency> sourceCurrencyChoiceBox;
    @FXML
    TextField userValueInputTextField;
    @FXML
    ChoiceBox<Currency> resultCurrencyChoiceBox;
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
    private ArrayList<ExchangeRateLabel> exchangeRatesLabels = new ArrayList<>();

    private void changeExchangeRatesScreen(Currency newBaseCurrency)
    {
        for(ExchangeRateLabel label : exchangeRatesLabels)
        {
            label.setBaseCurrency(newBaseCurrency);
        }
    }

    @FXML
    public void swapButtonPressed()
    {
        if(sourceCurrencyChoiceBox.getValue() != null || resultCurrencyChoiceBox.getValue() != null)
        {
            Currency c1 = sourceCurrencyChoiceBox.getValue();
            Currency c2 = resultCurrencyChoiceBox.getValue();

            resultCurrencyChoiceBox.setValue(c1);
            sourceCurrencyChoiceBox.setValue(c2);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // ============== setting proportions of ChoiceBoxes and Text fields to 2:1 ==============
        //whenever height of container changes, height of nodes also does
        leftVBox.heightProperty().addListener((observable, odValue, newValue) ->{
            double containerHeight = newValue.doubleValue();
            sourceCurrencyChoiceBox.setPrefHeight(containerHeight/3.0);
            userValueInputTextField.setPrefHeight((containerHeight*2.0)/3.0);
            resultCurrencyChoiceBox.setPrefHeight(containerHeight/3.0);
            resultValueTextField.setPrefHeight((containerHeight*2.0)/3.0);
        });
    }

    private void updateResultTextField()
    {
        if(!userValueInputTextField.getText().isEmpty())
        {
            BigDecimal startValue = new BigDecimal(userValueInputTextField.getText());
            Currency startCurrency = sourceCurrencyChoiceBox.getValue();
            Currency endCurrency = resultCurrencyChoiceBox.getValue();

            if(startCurrency != null && endCurrency != null)
            {
                BigDecimal resultVal = (currencyCalculator.performConversion(startValue, startCurrency, endCurrency));
                resultValueTextField.setText(resultVal.toString());
            }
        }
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
        Currency baseCurrency = sourceCurrencyChoiceBox.getValue();

        VBox[] containers = {exchangeRatesVBox1, exchangeRatesVBox2, exchangeRatesVBox3, exchangeRatesVBox4};
        int containerIt = 0;

        for(Currency currency : currencyCalculator.getCurrencies())
        {
            if(containerIt >= containers.length)
            {
                containerIt = 0;
            }

            ExchangeRateLabel label = new ExchangeRateLabel(baseCurrency, currency);
            exchangeRatesLabels.add(label);
            containers[containerIt].getChildren().add(label);
            ++containerIt;
        }

        // =========== add listener to choice boxes and user input text field
        sourceCurrencyChoiceBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null)
            {
                changeExchangeRatesScreen(newValue);
                updateResultTextField();
            }
        });

        resultCurrencyChoiceBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null)
            {
                updateResultTextField();
            }
        });

        userValueInputTextField.textProperty().addListener((observable, oldValue, newValue) ->{
            if(!newValue.isEmpty())
            {
                // clean text field out of chars other than numbers and a dot
                boolean wasDotSpotted = false;

                StringBuilder builder = new StringBuilder();
                for(char c: newValue.toCharArray())
                {
                    if('0'<=c && c<='9')
                    {
                        builder.append(c);
                    }
                    else if(c == '.' && (!wasDotSpotted))
                    {
                        wasDotSpotted=true;
                        builder.append(c);
                    }
                }
                userValueInputTextField.setText(builder.toString());

                updateResultTextField();
            }
            else
            {
                resultValueTextField.clear();
            }
        } );
    }

    @Override
    public void setCurrencyCalculator(CurrencyCalculator cc) {
        this.currencyCalculator = cc;

    }
}

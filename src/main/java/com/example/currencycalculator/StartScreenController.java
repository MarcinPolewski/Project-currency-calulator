package com.example.currencycalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class StartScreenController implements Initializable, ControllerInterface {
    @FXML
    ProgressIndicator progressIndicator;

    private CurrencyCalculator currencyCalculator;

    private void switchSceneToMain()
    {
        CurrencyCalcScene newScene;
        try{
            newScene = LocalConnectionHandler.loadScene(Scenes.MAIN);
        } catch (IOException e)
        {
            System.out.println("Error has occured during loading .fxml file of main screen");
            // @TODO prompt user about an error
            return;
        }
        ((ControllerInterface)newScene.getController()).processingAfterInitialization();

        Stage stage = (Stage)progressIndicator.getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @Override
    public void processingAfterInitialization()
    {
        // add listener to currencyCalculator
        currencyCalculator.areAssetsLoadedProperty().addListener(((observableValue, aBoolean, t1) -> {
            switchSceneToMain();
        }));

        // try to add currencies
        try{
            currencyCalculator.loadCurreneciesFromServer();
        } catch(IOException e1)
        {
            Alert failedToLoadAlert = new Alert(Alert.AlertType.ERROR);
            failedToLoadAlert.setTitle("Loading error");
            failedToLoadAlert.setHeaderText("Application was unable to reach server for current data. Would you like to load archival data?");
            failedToLoadAlert.setContentText(e1.toString());

            ButtonType loadArchival = new ButtonType("load archival");
            ButtonType quit = new ButtonType("quit");
            failedToLoadAlert.getButtonTypes().setAll(loadArchival, quit);
            Optional<ButtonType> result = failedToLoadAlert.showAndWait();
            if(result.get() == loadArchival)
            {
                try{
                    currencyCalculator.loadArchivalCurrencies();
                } catch (IOException e2)
                {
                    Alert failedToLoadArchival = new Alert(Alert.AlertType.ERROR);
                    failedToLoadAlert.setTitle("Loading error");
                    failedToLoadArchival.setHeaderText("Application was unable to read locally stored archival data");
                    failedToLoadArchival.setContentText(e2.toString());
                }

            }
            else{
                ((Stage)(progressIndicator.getScene().getWindow())).close();
            }

        }
    }

    @Override
    public void setCurrencyCalculator(CurrencyCalculator cc) {
        this.currencyCalculator = cc;
    }
}
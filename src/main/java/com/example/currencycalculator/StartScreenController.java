package com.example.currencycalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartScreenController implements Initializable, ControllerInterface {
    @FXML
    Button loadArchiveDataButton;

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
        //Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage stage = (Stage)loadArchiveDataButton.getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }
    @FXML
    void loadArchiveDataButtonPressed(ActionEvent event)
    {
        // @TODO read archival data here
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    void startLoadingAssets()
    {
        // add listener to currencyCalculator
        currencyCalculator.areAssetsLoadedProperty().addListener(((observableValue, aBoolean, t1) -> {
            switchSceneToMain();
        }));

        // try to add currencies
        try{
            currencyCalculator.loadCurreneciesFromServer();
            System.out.println("assets loaded");
        } catch(IOException e)
        {
            // @TODO propmp user that loading assets from server failed and ask i he wants to use archival
            System.out.println("loading failed");
        }
    }

    @Override
    public void setCurrencyCalculator(CurrencyCalculator cc) {
        this.currencyCalculator = cc;
    }
}
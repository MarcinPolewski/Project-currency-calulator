package com.example.currencycalculator;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;

public class Main extends Application {
    @Override
    public void start(Stage stage)  {

        // ================= initialize essential classes =================
        CurrencyCalculator currencyCalculator = new CurrencyCalculator();
        LocalConnectionHandler.setCurrencyCalculator(currencyCalculator);

        try{
            ServerConnectionHandler.initialize();
        }
        catch(IOException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Error occurred while reading API data from files");
            errorAlert.setContentText(e.toString());
            return;
        }

        // ============= load the start scene =============
        CurrencyCalcScene scene;
        try{
            scene = LocalConnectionHandler.loadScene(Scenes.START);

        } catch(IOException e)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Error occurred while reading scene's .fxml data from files");
            errorAlert.setContentText(e.toString());
            return;
        }

        stage.setTitle("Currency calculator $£€");
        stage.setScene(scene);
        stage.show();

        // start loading assets
        StartScreenController ctrl = (StartScreenController) scene.getController();
        ctrl.processingAfterInitialization();   // starts loading assets
    }


    public static void main(String[] args) {
        launch();
    }
}
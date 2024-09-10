package com.example.currencycalculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage stage)  {

        // ================= initialize essential classes =================
        CurrencyCalculator currencyCalculator = new CurrencyCalculator();
        LocalConnectionHandler.setCurrencyCalculator(currencyCalculator);

        ServerConnectionHandler serverConnectionHandler;
        try{
            serverConnectionHandler = new ServerConnectionHandler();
        }catch(MalformedURLException e) {
            // @TODO prompt user about encountered error
            System.out.println("error has occured during constructing api link");
            return;
        } catch(IOException e) {
            // @TODO prompt user about encountered error
            System.out.println("Error has occured during accessing api configuration file");
            return;
        }

        // ============= load the start scene =============
        CurrencyCalcScene scene;
        try{
            scene = LocalConnectionHandler.loadScene(Scenes.START);

        } catch(IOException e)
        {
            // @TODO prompt user about encountered error
            System.out.println("error during loading .fxml file");
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
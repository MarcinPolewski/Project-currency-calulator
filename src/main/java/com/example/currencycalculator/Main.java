package com.example.currencycalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {

        LocalConnectionHandler localConnectionHandler = new LocalConnectionHandler();
        ServerConnectionHandler serverConnectionHandler;
        try{
            serverConnectionHandler = new ServerConnectionHandler(localConnectionHandler);
        }catch(MalformedURLException e) {
            // @TODO prompt user about encountered error
            System.out.println("error has occured during constructing api link");
            return;
        } catch(IOException e) {
            // @TODO prompt user about encountered error
            System.out.println("Error has occured during accessing api configuration file");
            return;
        }

        Scene scene;
        try{
            scene = localConnectionHandler.loadScene(Scenes.START);
        } catch(IOException e)
        {
            // @TODO prompt user about encountered error
            System.out.println("error during loading .fxml file");
            return;
        }

        stage.setTitle("Currency calculator $£€");
        stage.setScene(scene);
        stage.show();

        try
        {
            String s = serverConnectionHandler.loadCurrencyInfo();
            System.out.println(s);
        } catch (IOException e)
        {
            System.out.println("could not load currecy info");
        }

    }

    public static void main(String[] args) {
        launch();
    }
}
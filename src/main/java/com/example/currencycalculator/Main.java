package com.example.currencycalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {

        LocalConnectionHandler localConnectionHandler = new LocalConnectionHandler();
        Scene scene;
        try{
            scene = localConnectionHandler.loadScene(Scenes.START);
        } catch(IOException e)
        {
            return;
        }

        stage.setTitle("Currency calculator $£€");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
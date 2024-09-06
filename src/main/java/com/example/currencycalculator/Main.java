package com.example.currencycalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("start-screen.fxml"));
        Scene scene;
        try{
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e){
            System.out.println("Failed to read start screen");
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
package com.example.currencycalculator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Main extends Application {
    @Override
    public void start(Stage stage) {

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

        Scene scene;
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

        try {
            ArrayList<Currency> c = LocalConnectionHandler.getArchivalCurrenyObjects();
            System.out.println(c.get(0).getCode());
        } catch (IOException e) {
            System.out.println("dupa");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
package com.example.currencycalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartScreenController{
    @FXML
    Button loadArchiveDataButton;

    @FXML
    void loadArchiveDataButtonPressed(ActionEvent event)
    {
        // @TODO read archival data here
        Scene newScene;
        try{
            newScene = LocalConnectionHandler.loadScene(Scenes.MAIN);
        } catch (IOException e)
        {
            System.out.println("Error has occured during loading .fxml file of main screen");
            // @TODO prompt user about an error
            return;
        }
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }
}
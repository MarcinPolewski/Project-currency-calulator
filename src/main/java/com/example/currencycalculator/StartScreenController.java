package com.example.currencycalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartScreenController implements ControllerInterface{
    @FXML
    Button loadArchiveDataButton;

    LocalConnectionHandler localConnectionHandler;

    @FXML
    void loadArchiveDataButtonPressed(ActionEvent event)
    {
        // @TODO read archival data here
        Scene newScene;
        try{
            newScene = localConnectionHandler.loadScene(Scenes.MAIN);
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

    @Override
    public void setLocalConnectionHandler(LocalConnectionHandler localConnectionHandler) {
        this.localConnectionHandler = localConnectionHandler;
    }
}
package com.example.currencycalculator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

public class LocalConnectionHandler {
    public Scene loadScene(Scenes sceneId) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(sceneId.getFxmlFilePath()));
        return new Scene(fxmlLoader.load());
    }
}

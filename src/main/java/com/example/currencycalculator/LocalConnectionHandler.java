package com.example.currencycalculator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LocalConnectionHandler {
    public Scene loadScene(Scenes sceneId) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(sceneId.getFxmlFilePath()));
        Scene scene = new Scene(fxmlLoader.load());
        ControllerInterface controller = fxmlLoader.getController();
        controller.setLocalConnectionHandler(this);

        return scene;
    }

//    public String getApiKey() throws IOException
//    {
//        InputStream input =  LocalConnectionHandler.class.getResourceAsStream("config.properties");
//        if(input == null)
//            throw new IOException();
//
//        Properties properties = new Properties();
//        properties.load(input);
//        return properties.getProperty("api.key");
//    }

    public Properties getApiProperties() throws IOException
    {
        InputStream input =  LocalConnectionHandler.class.getResourceAsStream("config.properties");
        if(input == null)
            throw new IOException();

        Properties properties = new Properties();
        properties.load(input);
        return properties;
    }

    public void updateCurrencyInfo(String newFileContent)
    {
        // @TODO
    }
}

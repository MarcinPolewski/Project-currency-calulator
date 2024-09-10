package com.example.currencycalculator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

public class LocalConnectionHandler {
    private static CurrencyCalculator currencyCalculator;

    private static void writeToFile(String fileName, String content) throws IOException
    {
        // @TODO make a dynamic path ?
        String filePath = "src/main/resources/com/example/currencycalculator/" + fileName;
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(content);
        writer.close();
    }
    private static String readFromFile(String fileName) throws IOException
    {
        // @TODO make a dynamic path ?
        String filePath = "src/main/resources/com/example/currencycalculator/" + fileName;
        return Files.readString(Paths.get(filePath));
    }

    public static CurrencyCalcScene loadScene(Scenes sceneId) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(sceneId.getFxmlFilePath()));
        CurrencyCalcScene scene = new CurrencyCalcScene(fxmlLoader.load(), fxmlLoader.getController());
        ControllerInterface controller = fxmlLoader.getController();
        controller.setCurrencyCalculator(currencyCalculator);
        return scene;
    }

    public static String getArchvalExchangeRates() throws IOException
    {
        return readFromFile("exchange-rates.json");
    }

    public static String getArchivalCurrencyInfo() throws  IOException
    {
        return readFromFile("currencies-info.json");
    }

    public static ArrayList<Currency> getArchivalCurrenyObjects() throws IOException
    {
        return FileDataConverter.inputToArrayOfObjects(readFromFile("exchange-rates.json"), readFromFile("currencies-info.json"));
    }

    public static Properties getApiProperties() throws IOException
    {
        InputStream input =  LocalConnectionHandler.class.getResourceAsStream("config.properties");
        if(input == null)
            throw new IOException();

        Properties properties = new Properties();
        properties.load(input);
        return properties;
    }

    public static void updateCurrencyInfo(String newFileContent) throws IOException
    {
        writeToFile("currencies-info.json", newFileContent);
    }

    public static void updateExchangeRatesInformation(String newFileContent) throws IOException
    {
        writeToFile("exchange-rates.json", newFileContent);
    }

    public static void setCurrencyCalculator(CurrencyCalculator cc)
    {
        currencyCalculator = cc;
    }
}

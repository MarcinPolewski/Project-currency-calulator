package com.example.currencycalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Properties;

public class ServerConnectionHandler {
    private static Properties apiProperties;
    ServerConnectionHandler() throws IOException {
        this.apiProperties =  LocalConnectionHandler.getApiProperties();
    }

    public static String loadCurrencyInfo() throws IOException {
        String linkText = apiProperties.getProperty("api.baseLink") +
                apiProperties.getProperty("api.currencyInfoLinkExtension") +
                apiProperties.getProperty("api.keyLinkPrefix") +
                apiProperties.getProperty("api.key");
        URL url = new URL(linkText);
        URLConnection connection = url.openConnection();

        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while((line = input.readLine()) !=null)
        {
            response.append(line);
        }
        input.close();

        String responseString = response.toString();
        LocalConnectionHandler.updateCurrencyInfo(responseString);

        return responseString;
    }

    public static String loadExchangeRates() throws IOException
    {
        String linkText = apiProperties.getProperty("api.baseLink") +
                apiProperties.getProperty("api.exchangeRatesLinkExtension") +
                apiProperties.getProperty("api.keyLinkPrefix") +
                apiProperties.getProperty("api.key");
        URL url = new URL(linkText);
        URLConnection connection = url.openConnection();

        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while((line = input.readLine()) !=null)
        {
            response.append(line);
        }
        input.close();

        String responseString = response.toString();
        LocalConnectionHandler.updateExchangeRatesInformation(responseString);

        return responseString;
    }

    public static ArrayList<Currency> loadCurrencyObjects() throws IOException
    {
        String s1 = loadExchangeRates();
        String s2 = loadCurrencyInfo();

        return FileDataConverter.inputToArrayOfObjects(s1,s2);
    }


}

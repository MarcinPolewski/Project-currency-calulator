package com.example.currencycalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class ServerConnectionHandler {
    private Properties apiProperties;
    private LocalConnectionHandler localConnectionHandler;
    private URL apiURL;
    ServerConnectionHandler(LocalConnectionHandler localConnectionHandler) throws IOException {
        this.localConnectionHandler = localConnectionHandler;
        this.apiProperties =  localConnectionHandler.getApiProperties();
    }

    public String loadCurrencyInfo() throws IOException {
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
        localConnectionHandler.updateCurrencyInfo(responseString);

        return responseString;
    }


}

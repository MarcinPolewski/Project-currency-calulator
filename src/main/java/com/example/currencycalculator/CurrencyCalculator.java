package com.example.currencycalculator;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class CurrencyCalculator {
    // performs caluclations / conversion from base currency
    // keeps currency obejcts
    private ArrayList<Currency> currencies;
    private SimpleBooleanProperty assetsLoaded = new SimpleBooleanProperty(false);

    public void loadCurreneciesFromServer() throws IOException
    {
        currencies = ServerConnectionHandler.loadCurrencyObjects();
        assetsLoaded.set(true);
    }

    public void loadArchivalCurrencies() throws IOException
    {
        currencies = LocalConnectionHandler.getArchivalCurrenyObjects();
        assetsLoaded.set(true);
    }

    public SimpleBooleanProperty areAssetsLoadedProperty()
    {
        return assetsLoaded;
    }

//    public ArrayList<String> getConversionLabels()
//    {
//        // get contents of labels that are presented on the bottom part of the screen
//    }
//
//    public ArrayList<Currency> getCurrencies()
//    {
//       // used for drop down menus
//    }
//
//    public BigDecimal performConversion(BigDecimal value, Currency start, Currency end)
//    {
//    }
}

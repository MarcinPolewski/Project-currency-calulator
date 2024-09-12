package com.example.currencycalculator;

import javafx.beans.property.SimpleBooleanProperty;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class CurrencyCalculator {

    private ArrayList<Currency> currencies;
    private final SimpleBooleanProperty assetsLoaded = new SimpleBooleanProperty(false);

    public void loadCurrenciesFromServer() throws IOException
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

    public  ArrayList<Currency> getCurrencies()
    {
        return currencies;
    }

    public BigDecimal performConversion(BigDecimal value, Currency startCurrency, Currency endCurrency)
    {
        return startCurrency.convertTo(endCurrency, value);
    }
}

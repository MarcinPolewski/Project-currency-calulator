package com.example.currencycalculator;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class CurrencyCalculator {
    // performs caluclations / conversion from base currency
    // keeps currency obejcts
    private static Currency BaseCurrencyOfRates; // all exchange rates are relating to this currency

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

    public  ArrayList<Currency> getCurrencies()
    {
        return currencies;
    }

    public BigDecimal perfomConversion(BigDecimal value, Currency startCurrency, Currency endCurrency)
    {
        return startCurrency.convertTo(endCurrency, value);
    }
}

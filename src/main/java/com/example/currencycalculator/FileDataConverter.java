package com.example.currencycalculator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;

public class FileDataConverter {
    public static ArrayList<Currency> inputToArrayOfObjects(String exchangeRatesString, String currencyInfoString) throws IOException {
//        // fetch
//        String exchangeRatesString = LocalConnectionHandler.getArchvalExchangeRates();
//        String currencyInfoString = LocalConnectionHandler.getArchivalCurrencyInfo();

        ObjectMapper exchangeRatesObjectMapper= new ObjectMapper();
        JsonNode exchangeRatesNode = exchangeRatesObjectMapper.readTree(exchangeRatesString).get("rates"); // this is the exchangeRatesNode with "list" of SYMBOL: exchange rate

        ObjectMapper currencyInfoObjectMapper = new ObjectMapper();
        JsonNode currencyInfoNode = currencyInfoObjectMapper.readTree(currencyInfoString).get("symbols");

        Iterator<Map.Entry<String, JsonNode>> exchangeRatesIterator = exchangeRatesNode.fields();
        Iterator<Map.Entry<String, JsonNode>> currencyInfoIterator = currencyInfoNode.fields();

        ArrayList<Currency> currencies = new ArrayList<Currency>();
        while(exchangeRatesIterator.hasNext() && currencyInfoIterator.hasNext())
        {
            Map.Entry<String, JsonNode> exchangeRateField = exchangeRatesIterator.next();
            String currencyCode1 = exchangeRateField.getKey();
            JsonNode exchangeRate = exchangeRateField.getValue();

            Map.Entry<String, JsonNode> currencyInfoField = currencyInfoIterator.next();
            String currencyCode2 = currencyInfoField.getKey();
            JsonNode fullCurrencyName = currencyInfoField.getValue();

            if(!currencyCode2.equals(currencyCode1))
            {
                //throw new IOException("Invalid currency info order");
                System.out.println("kolejnosc nie pyka " + currencyCode2 + " " + currencyCode1);
                continue;

            }
            currencies.add(new Currency(currencyCode1, fullCurrencyName.asText(), new BigDecimal(exchangeRate.asText())));
        }
        return currencies;
    }



}

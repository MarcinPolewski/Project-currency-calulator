package com.example.currencycalculator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;

public class FileDataConverter {
    public static ArrayList<Currency> inputToArrayOfObjects(String exchangeRatesString, String currencyInfoString) throws IOException {
        // from two different json files from API creates array of objects
        ObjectMapper exchangeRatesObjectMapper= new ObjectMapper();
        JsonNode exchangeRatesNode = exchangeRatesObjectMapper.readTree(exchangeRatesString).get("rates"); // this is the exchangeRatesNode with "list" of SYMBOL: exchange rate

        // ========== get base currency code from input ==========
        JsonNode baseCurrency = exchangeRatesObjectMapper.readTree(exchangeRatesString).get("base");
        String baseCurrencyCode = baseCurrency.asText();

        ObjectMapper currencyInfoObjectMapper = new ObjectMapper();
        JsonNode currencyInfoNode = currencyInfoObjectMapper.readTree(currencyInfoString).get("symbols");

        Iterator<Map.Entry<String, JsonNode>> exchangeRatesIterator = exchangeRatesNode.fields();
        Iterator<Map.Entry<String, JsonNode>> currencyInfoIterator = currencyInfoNode.fields();

        ArrayList<Currency> currencies = new ArrayList<>();
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
                System.out.println("Wrong API data !!" + currencyCode2 + " " + currencyCode1);
                continue;

            }
            Currency newCurrency = new Currency(currencyCode1, fullCurrencyName.asText(), new BigDecimal(exchangeRate.asText()));
            currencies.add(newCurrency);

            //check if this currency is base currency
            if(baseCurrencyCode.equals(currencyCode1))
            {
                Currency.setBaseExchangeRateCurrency(newCurrency);
            }
        }
        if(currencies.size()<2)
        {
            throw new IOException("Input did not contain at least two currencies");
        }

        return currencies;
    }



}

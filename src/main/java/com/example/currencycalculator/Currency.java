package com.example.currencycalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Currency {
    private static Currency baseExchangeRateCurrency;

    private final String code;
    private final String fullName;
    private final BigDecimal exchangeRate;



    Currency(String code, String fullName, BigDecimal exchangeRate)
    {
        this.code = code;
        this.fullName = fullName;
        this.exchangeRate = exchangeRate;
    }
    public String getCode() {
        return code;
    }

    public String getFullName() {
        return fullName;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public BigDecimal convertTo(Currency targetCurrency, BigDecimal value) {
        BigDecimal result = value;
        result = result.multiply(targetCurrency.getExchangeRate());
        result = result.divide(this.getExchangeRate(), RoundingMode.HALF_EVEN);

        return result;
    }


    public String toString()
    {
        return "(" + this.code + ") " + this.fullName;
    }

    public static void setBaseExchangeRateCurrency(Currency baseExchangeRateCurrency) {
        Currency.baseExchangeRateCurrency = baseExchangeRateCurrency;
    }

    public static Currency getBaseExchangeRateCurrency(){
        return baseExchangeRateCurrency;
    }
}

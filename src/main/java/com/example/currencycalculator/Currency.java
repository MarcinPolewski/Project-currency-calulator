package com.example.currencycalculator;

import java.math.BigDecimal;
public class Currency {
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

}

package com.example.currencycalculator;

import javafx.scene.control.Label;
import java.math.BigDecimal;
public class ExchangeRateLabel extends Label {
    private Currency baseCurrency;
    private final Currency targetCurrency;
    BigDecimal resultValue;

    private void calculateResultValue()
    {
        resultValue = baseCurrency.convertTo(targetCurrency, BigDecimal.ONE);
    }

    public ExchangeRateLabel(Currency baseCurrency, Currency targetCurrency)
    {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;

        calculateResultValue();
        this.setText(toString());
    }

    public void setBaseCurrency(Currency baseCurrency)
    {
        this.baseCurrency = baseCurrency;
        calculateResultValue();
        this.setText(toString());
    }

    public String toString()
    {
        return "1 " + baseCurrency.getCode() + " = " + resultValue + " " + targetCurrency.getCode();
    }

}

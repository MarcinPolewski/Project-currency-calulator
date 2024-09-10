package com.example.currencycalculator;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class CurrencyCalcScene extends Scene {
    private final Object controller;

    public CurrencyCalcScene(Parent parent, Object controller) {
        super(parent);
        this.controller = controller;
    }
    public Object getController() {
        return controller;
    }

}
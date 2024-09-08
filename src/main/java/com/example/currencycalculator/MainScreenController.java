package com.example.currencycalculator;

public class MainScreenController implements ControllerInterface{
    private LocalConnectionHandler localConnectionHandler;
    @Override
    public void setLocalConnectionHandler(LocalConnectionHandler localConnectionHandler) {
        this.localConnectionHandler = localConnectionHandler;
    }
}

package com.example.currencycalculator;

public enum Scenes {
//    START(0,"/com/example/currencycalculator/start-screen.fxml"),
//    MAIN(1,"/com/example/currencycalculator/main-screen.fxml");
    START(0,"start-screen.fxml"),
    MAIN(1,"main-screen.fxml");


    private final String fxmlFilePath;
    private final int id;
    // constructor
    Scenes(int id, String fxmlFilePath) {
        this.id = id;
        this.fxmlFilePath = fxmlFilePath;
    }
    public String getFxmlFilePath()
    {
        return fxmlFilePath;
    }
    public int getId()
    {
        return id;
    }

}


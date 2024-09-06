module com.example.currencycalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.currencycalculator to javafx.fxml;
    exports com.example.currencycalculator;
}
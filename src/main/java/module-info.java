module com.example.currencycalculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens com.example.currencycalculator to javafx.fxml;
    exports com.example.currencycalculator;
}
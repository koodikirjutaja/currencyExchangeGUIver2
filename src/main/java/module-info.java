module com.example.currencyexchangeguiver2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.currencyexchangeguiver2 to javafx.fxml;
    exports com.example.currencyexchangeguiver2;
}
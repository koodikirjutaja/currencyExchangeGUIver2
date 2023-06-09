    package com.example.currencyexchangeguiver2;

    import javafx.fxml.FXML;
    import javafx.geometry.Insets;
    import javafx.geometry.Pos;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.scene.control.Label;
    import javafx.scene.control.TextField;
    import javafx.scene.layout.GridPane;
    import javafx.scene.layout.HBox;
    import javafx.scene.layout.VBox;
    import javafx.scene.paint.Color;
    import javafx.scene.text.Font;
    import javafx.scene.text.FontWeight;
    import javafx.stage.Stage;
    import java.util.Objects;

    public class RahaTulemus extends RahaNetist {

        @FXML
        private TextField initialCurrencyField;
        @FXML
        private TextField amountField;
        @FXML
        private TextField convertedCurrencyField;
        @FXML
        private Label resultLabel;


        @Override
        public void start(Stage primaryStage) throws Exception {
            primaryStage.setTitle("Valuutakalkulaator");

            // Akna suuruse muutmise luba
            primaryStage.setResizable(true);

            // Määrame akna miinimum- ja maksimumsuurused
            primaryStage.setMinWidth(350);
            primaryStage.setMinHeight(400);
            primaryStage.setMaxWidth(900);
            primaryStage.setMaxHeight(500);



            // Valuutakoodi väljad
            Label initialCurrencyLabel = new Label("Algne valuuta kood:");
            initialCurrencyField = new TextField();
            initialCurrencyField.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-alignment: center; -fx-text-fill: #000000; -fx-display: flex; -fx-align-items: center;");
            initialCurrencyField.setPromptText("Näiteks: EUR");

            Label convertedCurrencyLabel = new Label("Teisendatav valuuta kood:");
            convertedCurrencyField = new TextField();
            convertedCurrencyField.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-alignment: center; -fx-text-fill: #000000; -fx-display: flex; -fx-align-items: center;");
            convertedCurrencyField.setPromptText("Näiteks: USD");

            // Rahasumma väli
            Label amountLabel = new Label("Sisesta rahasumma:");
            amountField = new TextField();
            amountField.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-alignment: center; -fx-text-fill: #000000; -fx-display: flex; -fx-align-items: center;");
            amountField.setPromptText("Näiteks: 100");

            // Tulemus
            resultLabel = new Label();
            resultLabel.setAlignment(Pos.CENTER);
            resultLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 14));


            // Nupud
            Button convertButton = new Button("Teisenda");
            Button clearButton = new Button("Tühjenda väljad");

            // VBox ülemistele elementidele
            VBox topBox = new VBox(10);
            topBox.setAlignment(Pos.CENTER);
            topBox.getChildren().addAll(initialCurrencyLabel, initialCurrencyField, convertedCurrencyLabel, convertedCurrencyField,
                    amountLabel, amountField, convertButton);

            // HBox nuppudele
            HBox buttonBox = new HBox(10);
            buttonBox.setAlignment(Pos.CENTER);
            buttonBox.getChildren().addAll(convertButton, clearButton);

            // GridPane tulemusele ja nuppudele
            GridPane bottomGrid = new GridPane();
            bottomGrid.setAlignment(Pos.CENTER);
            bottomGrid.setHgap(10);
            bottomGrid.setVgap(10);
            bottomGrid.add(resultLabel, 0, 0, 2, 1);
            bottomGrid.add(buttonBox, 1, 1);

            // Ühendame VBox ja GridPane
            VBox root = new VBox(20);
            root.setPadding(new Insets(20, 20, 20, 20));
            root.getChildren().addAll(topBox, bottomGrid);

            // Nuppude funktsionaalsus
            convertButton.setOnAction(e -> {
                try {
                    String initialCurrency = initialCurrencyField.getText().toUpperCase();
                    double amount = Double.parseDouble(amountField.getText());
                    String convertedCurrency = convertedCurrencyField.getText().toUpperCase();

                    double result = rahaNetist(initialCurrency, amount, convertedCurrency);

                    resultLabel.setText(String.format(amount + " " + initialCurrency + " = " + result + " " + convertedCurrency + "\n\nEelnev teisendus: " + LogiFail.getLastConversion()));
                    String logiTeade = String.format("%.2f %s -> %.2f %s", amount, initialCurrency, result, convertedCurrency);
                    LogiFail.logi(logiTeade);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Viga: palun sisesta summa numbrites.");
                } catch (Exception ex) {
                    resultLabel.setText("Viga: midagi läks valesti. Kontrolli sisestatud andmeid.");
                }
            });

            clearButton.setOnAction(e -> {
                initialCurrencyField.clear();
                convertedCurrencyField.clear();
                amountField.clear();
                resultLabel.setText("");
            });

            Scene scene = new Scene(root, 500, 470);
            String css = Objects.requireNonNull(getClass().getResource("/stiil.css")).toExternalForm();
            scene.getStylesheets().add(css);
            scene.setFill(Color.LIGHTBLUE);
            primaryStage.setScene(scene);
            primaryStage.show();
            LogiFail.kuvaHoiatus();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
package com.example.currencyexchangeguiver2;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Application;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public abstract class RahaNetist extends Application {
    private static final String API_KEY = "b32a153551f11c9aab47484d";

    public double rahaNetist(String algneValuuta, double rahasumma, String uusValuuta) throws Exception {
        // https://v6.exchangerate-api.com/v6/b32a153551f11c9aab47484d/latest/EUR
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", API_KEY, algneValuuta);
        String vastus = saaVastus(url);

        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(vastus).getAsJsonObject();

        if (jsonObject.get("result").getAsString().equals("error")) {
            throw new Exception(jsonObject.get("error-type").getAsString());
        }

        double kurss = jsonObject.getAsJsonObject("conversion_rates").get(uusValuuta).getAsDouble();

        return rahasumma * kurss;
    }

    public double kurss(String algneValuuta, String uusValuuta) throws IOException {
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", API_KEY, algneValuuta);
        String vastus = saaVastus(url);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(vastus).getAsJsonObject();
        return jsonObject.getAsJsonObject("conversion_rates").get(uusValuuta).getAsDouble();
    }

    /**

     Meetod saadab GET-päringu antud URL-ile ja tagastab vastuse sisu.
     @param url URL, millele päring saadetakse.
     @return Päringu vastuse sisu.
     @throws IOException Kui päringu saatmisel või vastuse lugemisel ilmneb viga.
     */
    public String saaVastus(String url) throws IOException {

        // Loome ühenduse antud URL-iga ja saadame GET-päringu
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        // Kontrollime vastuskoodi, kui see on OK, loeme vastuse sisu sisse ja tagastame selle
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStreamReader in = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
            StringBuilder response = new StringBuilder();
            int c;
            while ((c = in.read()) != -1) {
                response.append((char) c);
            }
            in.close();
            return response.toString();
        }

        // Kui vastuskood ei ole OK, viskame erandi koos vastuskoodiga
        else {
            throw new IOException("Viga: " + responseCode);
        }
    }
}

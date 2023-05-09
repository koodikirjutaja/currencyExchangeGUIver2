package com.example.currencyexchangeguiver2;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogiFail {

    private static final String FILENAME = "valuutakalkulaatori_logi.txt";

    public static void logi(String teade) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(FILENAME, true);
            bw = new BufferedWriter(fw);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            Date currentDate = new Date();
            String formattedDate = sdf.format(currentDate);
            bw.write(formattedDate + ": " + teade);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    public static String getLastConversion() {
        try (BufferedReader reader = new BufferedReader(new FileReader("valuutakalkulaatori_logi.txt"))) {
            String line;
            String lastLine = null;
            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }
            System.out.println(lastLine);
            assert lastLine != null;
            String[] x = lastLine.split(" ");
            return x[0] + " " + x[1] + "\n" + x[2] + " " + x[3] + " " + x[4] + " " + x[5] + " " + x[6];
        } catch (IOException ex) {
            return "Varasemaid teisendusi pole";
        }
    }
}

package edu.escuelaing.arsw.concurrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Hilo extends Thread {
    private int id;
    private String empresa;
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String BASE_URL = "http://localhost:8080/api/get/";

    public Hilo(int id, String empresa) {
        this.id = id;
        this.empresa = empresa;
    }

    @Override
    public void run() {
        System.out.println("Thread " + id + " started for company: " + empresa);
        try {
            String response = executeGetRequest(empresa);
            // Aquí puedes manejar o procesar la respuesta según sea necesario
            System.out.println("Response received for " + empresa + ": " + response);
        } catch (IOException e) {
            System.err.println("Error executing GET request for " + empresa + ": " + e.getMessage());
        } finally {
            System.out.println("Thread " + id + " ended for company: " + empresa);
        }
    }

    private String executeGetRequest(String empresa) throws IOException {
        URL url = new URL(BASE_URL + empresa);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code for " + empresa + " :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            System.out.println("GET request failed for " + empresa);
            return null;
        }
    }
}



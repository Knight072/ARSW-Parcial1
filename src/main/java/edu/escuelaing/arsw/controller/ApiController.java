package edu.escuelaing.arsw.controller;

import org.springframework.web.bind.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/api")
public class ApiController {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String BASE_URL = "https://www.alphavantage.co/query";
    private static final String API_KEY = "demo";

    @GetMapping("/get/{name}")
    public String getMethod(@PathVariable("name") String name,
                            @RequestParam(value = "function", defaultValue = "TIME_SERIES_DAILY") String function,
                            @RequestParam(value = "outputsize", defaultValue = "full") String outputsize) {
        String getUrl = buildUrl(name, function, outputsize);

        try {
            URL url = new URL(getUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println("Response: " + response.toString());
                return response.toString();
            } else {
                System.out.println("GET request failed");
            }
        } catch (IOException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }

        return "GET request failed";
    }

    private String buildUrl(String symbol, String function, String outputsize) {
        return String.format("%s?function=%s&symbol=%s&outputsize=%s&apikey=%s",
                BASE_URL, function, symbol, outputsize, API_KEY);
    }
}


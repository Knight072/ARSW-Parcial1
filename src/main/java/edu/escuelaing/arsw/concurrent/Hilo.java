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
    private static final String GET_URL = "http://localhost:8080/api/get/";

    public Hilo(int id, String empresa) {
        this.empresa = empresa;
        this.id = id;
    }

    public void getMethod() throws IOException{
        try {
            URL obj = new URL(GET_URL+empresa);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);

            //The following invocation perform the connection implicitly before getting the code
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                //System.out.println( response.toString());
            } else {
                System.out.println("GET request not worked");
            }
            System.out.println("GET DONE: "+empresa);

        } catch (
                IOException e) {
        }

        System.out.println("Cerro el thread: " + id);

        System.out.println( "GET request not worked: "+ empresa);
    }


    @Override
    public void run() {
        System.out.println("Corrio el thread " + id);
        try {
            getMethod();
        } catch (IOException e) {
            System.err.println("Could not get petition");
            System.exit(1);
        }
    }

}


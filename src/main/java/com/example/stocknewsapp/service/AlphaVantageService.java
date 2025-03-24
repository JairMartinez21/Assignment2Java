package com.example.stocknewsapp.service;

import com.example.stocknewsapp.model.Stock;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AlphaVantageService {

    private static final String ALPHA_VANTAGE_API_KEY = "1LE9S0VQPGZ5VHPE"; // Replace with your API key

    public Stock getStockData(String symbol) throws IOException {
        String url = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + ALPHA_VANTAGE_API_KEY;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
            JsonObject quote = jsonObject.getAsJsonObject("Global Quote");
            if (quote == null || quote.isJsonNull() || !quote.has("01. symbol")) {
                return null;
            }

            Stock stock = new Stock();
            stock.setSymbol(quote.get("01. symbol").getAsString());
            stock.setPrice(quote.get("05. price").getAsString());
            stock.setVolume(quote.get("06. volume").getAsString());


            return stock;

        }
    }
}
package com.example.stocknewsapp.service;

import com.example.stocknewsapp.model.NewsArticle;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsAPIService {

    private static final String NEWS_API_KEY = "034c503ffa0449759b0f78e624f19156"; // Replace with your API key

    public List<NewsArticle> getNewsArticles(String companyName) throws IOException {
        String url = "https://newsapi.org/v2/everything?q=" + companyName + "&apiKey=" + NEWS_API_KEY;
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
            JsonArray articlesArray = jsonObject.getAsJsonArray("articles");

            List<NewsArticle> articles = new ArrayList<>();
            for (JsonElement element : articlesArray) {
                JsonObject articleJson = element.getAsJsonObject();
                NewsArticle article = new NewsArticle();
                article.setTitle(articleJson.get("title").getAsString());
                article.setUrl(articleJson.get("url").getAsString());
                article.setDescription(articleJson.has("description") && !articleJson.get("description").isJsonNull()
                        ? articleJson.get("description").getAsString()
                        : "No description available.");
                articles.add(article);
            }
            return articles;
        }
    }
}
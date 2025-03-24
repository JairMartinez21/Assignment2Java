package com.example.stocknewsapp.model;

public class NewsArticle {
    private String title;
    private String url;
    private String source;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "NewsArticle{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", source='" + source + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
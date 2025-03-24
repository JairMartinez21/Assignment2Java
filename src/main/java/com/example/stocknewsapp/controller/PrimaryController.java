package com.example.stocknewsapp.controller;

import com.example.stocknewsapp.model.NewsArticle;
import com.example.stocknewsapp.model.Stock;
import com.example.stocknewsapp.service.AlphaVantageService;
import com.example.stocknewsapp.service.NewsAPIService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {

    @FXML
    private TextField searchTextField;
    @FXML
    private Label symbolLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label volumeLabel;
    @FXML
    private ListView<NewsArticle> newsListView;

    private final AlphaVantageService alphaVantageService = new AlphaVantageService();
    private final NewsAPIService newsAPIService = new NewsAPIService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newsListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(NewsArticle item, boolean empty) {
                super.updateItem(item, empty);
                setText((empty || item == null || item.getTitle() == null) ? null : item.getTitle());
            }
        });

        newsListView.setOnMouseClicked(this::handleNewsClick);
    }

    @FXML
    private void searchStock() {
        fetchStockAndNews(searchTextField.getText());
    }

    @FXML
    private void searchAmazon() {
        fetchStockAndNews("AMZN");
    }

    @FXML
    private void searchGoogle() {
        fetchStockAndNews("GOOGL");
    }

    @FXML
    private void searchNvidia() {
        fetchStockAndNews("NVDA");
    }

    @FXML
    private void searchMeta() {
        fetchStockAndNews("META");
    }

    @FXML
    private void searchMicrosoft() {
        fetchStockAndNews("MSFT");
    }

    private void fetchStockAndNews(String symbol) {
        try {
            Stock stock = alphaVantageService.getStockData(symbol);
            if (stock != null) {
                Platform.runLater(() -> {
                    symbolLabel.setText(stock.getSymbol());
                    priceLabel.setText(stock.getPrice());
                    volumeLabel.setText(stock.getVolume());
                });

                List<NewsArticle> news = newsAPIService.getNewsArticles(symbol);

                // ✅ Limit news to 10 articles
                List<NewsArticle> limitedNews = news.stream().limit(10).toList();
                ObservableList<NewsArticle> newsList = FXCollections.observableArrayList(limitedNews);

                Platform.runLater(() -> newsListView.setItems(newsList));

            } else {
                Platform.runLater(() -> {
                    showAlert(Alert.AlertType.ERROR, "Error", "Stock data not found for symbol: " + symbol);
                });
            }
        } catch (IOException e) {
            Platform.runLater(() -> {
                showAlert(Alert.AlertType.ERROR, "Error", "Error fetching data: " + e.getMessage());
            });
            e.printStackTrace();
        }
    }


    private void handleNewsClick(MouseEvent event) {
        NewsArticle selectedArticle = newsListView.getSelectionModel().getSelectedItem();
        if (selectedArticle != null && selectedArticle.getUrl() != null) {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(URI.create(selectedArticle.getUrl()));
                } catch (IOException e) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Cannot open link: " + e.getMessage());
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Desktop browsing not supported on this system.");
            }
        }
    }

    // ✅ Moved inside the class
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}




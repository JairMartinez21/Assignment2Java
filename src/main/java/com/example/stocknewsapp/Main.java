package com.example.stocknewsapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/stocknewsapp/PrimaryView.fxml")));
        primaryStage.setTitle("Stock and News App");

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/stocknewsapp/styles.css")).toExternalForm());

        primaryStage.setScene(scene);

        // Optional: Application icon
        // primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/StockNewsApp/stock_icon.png"))));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
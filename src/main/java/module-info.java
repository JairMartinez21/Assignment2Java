module com.example.stocknewsapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires com.google.gson;
    requires java.desktop;

    opens com.example.stocknewsapp to javafx.fxml;
    opens com.example.stocknewsapp.controller to javafx.fxml;
    opens com.example.stocknewsapp.model to com.google.gson;

    exports com.example.stocknewsapp;
    exports com.example.stocknewsapp.controller;
    exports com.example.stocknewsapp.model;
    exports com.example.stocknewsapp.service;
}
package com.javafx.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MyApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 400, 400);

        } catch (Exception e) {

        }
    }
}
package com.javafx.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

/**
 * @author fanyuwen
 * @date 2019/6/17 1:01
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(
                new File(new File(Main.class.getClassLoader().getResource("").getFile())
                        .getParentFile()
                        .getParentFile()
                        .getParentFile().getPath() + "/resources/main/fxml/myScene.fxml").toURI().toURL()
        );

        primaryStage.setTitle("My Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
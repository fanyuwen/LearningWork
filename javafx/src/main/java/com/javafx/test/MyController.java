package com.javafx.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @author fanyuwen
 * @date 2019/6/17 0:57
 */
public class MyController implements Initializable {

    @FXML
    private Button myButton;
    @FXML
    private TextField myTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void showDateTime(ActionEvent event) {
        System.out.println("Button Clicked!");

        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        myTextField.setText(df.format(date));
    }
}

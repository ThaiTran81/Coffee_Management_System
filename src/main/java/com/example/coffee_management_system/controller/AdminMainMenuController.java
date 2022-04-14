package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminMainMenuController implements Initializable {
    @FXML
    private Circle myCircle;
    @FXML
    private Button btnClose;
    @FXML
    private Label lbName;
    @FXML
    private Label lbRole;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myCircle.setStroke(Color.GRAY);
        Image im = null;
        try {
            im = new Image(Main.class.getResource("asset/man.png").toURI().toString(), false);
            myCircle.setFill(new ImagePattern(im));
//            myCircle.setEfafect(new DropShadow(+25d, 0d, +2d, Color.DARKGRAY));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}

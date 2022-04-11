package com.example.coffee_management_system;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private Circle myCircle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myCircle.setStroke(Color.GRAY);
        Image im = null;
        try {
            im = new Image(MainMenuController.class.getResource("asset/man.png").toURI().toString(), false);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        myCircle.setFill(new ImagePattern(im));
        myCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKGRAY));
    }
}

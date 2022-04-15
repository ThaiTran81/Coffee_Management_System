package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DTO.CategoryDTO;
import com.example.coffee_management_system.Main;
import com.example.coffee_management_system.ultil.ComponentMenuListener;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URISyntaxException;
import java.net.URL;

public class MenuItemController {

    @FXML
    private JFXButton button;
    @FXML
    private AnchorPane mainContainer;
    @FXML
    private Label lbName;
    @FXML
    private ImageView ivIcon;

    ComponentMenuListener componentMenuListener = null;
    URL url = null;
    Object obj = null;


    void setData(String title, String img, Object obj, URL url) {
        lbName.setText(title);
        try {
            ivIcon.setImage(new Image(Main.class.getResource("asset/" + img).toURI().toString()));
        } catch (URISyntaxException | NullPointerException e) {
        }

        this.url = url;
        this.obj = obj;
    }

    void setClickListener(ComponentMenuListener componentMenuListener) {
        this.componentMenuListener = componentMenuListener;
    }


    @FXML
    void onClick(ActionEvent event) {
        if (componentMenuListener != null)
            componentMenuListener.onClickListener(url, obj);
    }

}

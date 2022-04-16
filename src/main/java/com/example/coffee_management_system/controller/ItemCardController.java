package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DTO.ItemDTO;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemCardController implements Initializable {

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private ImageView imgAvatar;

    @FXML
    private Label lbDescription;

    @FXML
    private Label lbPrice;

    @FXML
    private Label lbTitle;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    void setData(ItemDTO itemDTO){
        lbTitle.setText(itemDTO.getName());
        lbDescription.setText(itemDTO.getDescription());
        lbPrice.setText(String.valueOf(itemDTO.getPrice()));
    }
}

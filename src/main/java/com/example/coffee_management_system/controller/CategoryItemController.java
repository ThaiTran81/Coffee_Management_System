package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DTO.CategoryDTO;
import com.example.coffee_management_system.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URISyntaxException;

public class CategoryItemController {

    @FXML
    private AnchorPane mainContainer;
    @FXML
    private Label lbName;
    @FXML
    private ImageView ivIcon;


    void setData(CategoryDTO category){
        lbName.setText(category.getName());
        try {
            ivIcon.setImage(new Image(Main.class.getResource("asset/"+category.getIcUrl()).toURI().toString()));
        } catch (URISyntaxException e) {
        }
    }



}

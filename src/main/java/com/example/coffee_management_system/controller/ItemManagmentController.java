package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DTO.CategoryDTO;
import com.example.coffee_management_system.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ItemManagmentController implements Initializable {
    @FXML
    private VBox vBoxCategory;

    private List<CategoryDTO> categories = new ArrayList<CategoryDTO>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CategoryDTO categoryDTO = new CategoryDTO("Trái cây", 1, "apple_red.png");

        categories.add(categoryDTO);

        categoryDTO = new CategoryDTO("Trái cây 1", 1, "apple_red.png");
        categories.add(categoryDTO);

        for (int i = 0; i < categories.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("category_item.fxml"));
            AnchorPane anchorPane = null;
            try {
                anchorPane = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            CategoryItemController itemController = fxmlLoader.getController();
            itemController.setData(categories.get(i));

            vBoxCategory.getChildren().add(anchorPane); //(child,column,row)
//            //set grid width
//            vBoxCategory.setMinWidth(Region.USE_COMPUTED_SIZE);
//            vBoxCategory.setPrefWidth(Region.USE_COMPUTED_SIZE);
//            vBoxCategory.setMaxWidth(Region.USE_PREF_SIZE);
//
//            //set grid height
//            vBoxCategory.setMinHeight(Region.USE_COMPUTED_SIZE);
//            vBoxCategory.setPrefHeight(Region.USE_COMPUTED_SIZE);
//            vBoxCategory.setMaxHeight(Region.USE_PREF_SIZE);

        }
    }
}

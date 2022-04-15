package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DTO.CategoryDTO;
import com.example.coffee_management_system.Main;
import com.example.coffee_management_system.ultil.ComponentMenuListener;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ManagmentMenuController {
    @FXML
    private VBox menuLayout;

    private List<CategoryDTO> categories = new ArrayList<CategoryDTO>();

    @FXML
    private ImageView imgAddButton;

    @FXML
    private JFXButton btnAdd;

    ComponentMenuListener addListener;

    public void addItemMenu(String title, String img, Object obj, URL url, ComponentMenuListener componentMenuListener) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("management_menu_item.fxml"));
        AnchorPane anchorPane = null;
        try {
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MenuItemController itemController = fxmlLoader.getController();

        itemController.setData(title, img, obj, url);
        itemController.setClickListener(componentMenuListener);
        menuLayout.getChildren().add(anchorPane);
        
    }

    public void setAddButton(String title, String img, Object obj, URL url, ComponentMenuListener componentMenuListener) {
        btnAdd.setText(title);
        addListener = componentMenuListener;
        if (componentMenuListener != null)
            btnAdd.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    componentMenuListener.onClickListener(url, obj);
                }
            });

        if (img != null) {
            try {
                imgAddButton.setImage(new Image(Main.class.getResource("asset/" + img).toURI().toString()));
            } catch (URISyntaxException | NullPointerException ignored) {

            }
        }
    }

}

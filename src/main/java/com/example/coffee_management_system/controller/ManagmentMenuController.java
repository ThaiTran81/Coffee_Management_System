package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DTO.CategoryDTO;
import com.example.coffee_management_system.Main;
import com.example.coffee_management_system.ultil.ComponentMenuListener;
import com.example.coffee_management_system.ultil.StageUtils;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagmentMenuController {
    @FXML
    private VBox sideContentArea;

    private List<CategoryDTO> categories = new ArrayList<CategoryDTO>();

    @FXML
    private ImageView imgAddButton;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private StackPane contentArea;

    @FXML
    private Button btnBackToMainMenu;

    private URL backto_url;

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
        sideContentArea.getChildren().add(anchorPane);

    }

    FXMLLoader addItem(URL url){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        AnchorPane anchorPane = null;
        try {
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sideContentArea.getChildren().add(anchorPane);
        return fxmlLoader;
    }

    public void setAddButton(String title, String img, Object obj, URL url, ComponentMenuListener componentMenuListener) {
        btnAdd.setText(title);
        addListener = componentMenuListener;
        if (componentMenuListener != null)
            btnAdd.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    onAddButtonClick(componentMenuListener, url, obj);
                }
            });

        if (img != null) {
            try {
                imgAddButton.setImage(new Image(Main.class.getResource("asset/" + img).toURI().toString()));
            } catch (URISyntaxException | NullPointerException ignored) {

            }
        }
    }

    void onAddButtonClick(ComponentMenuListener componentMenuListener, URL url, Object obj) {
        try {
            componentMenuListener.onClickListener(url, obj);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void setContentArea(URL url) {
        try {
            Parent parent = FXMLLoader.load(url);
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void hideAddButton() {
        btnAdd.setVisible(false);
    }

    void showAddButton() {
        btnAdd.setVisible(true);
    }

    void setBackSatge(URL url){
        this.backto_url = url;
    }

    @FXML
    void onBackToMainMenuButtonClick(ActionEvent event) {
        try {
            StageUtils.switchTo(backto_url, event, StageStyle.UNDECORATED);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

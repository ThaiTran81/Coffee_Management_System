package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DTO.CategoryDTO;
import com.example.coffee_management_system.ultil.ComponentMenuListener;
import com.example.coffee_management_system.ultil.StageUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ManagmentMenuController {
    @FXML
    private VBox sideContentArea;

    private List<CategoryDTO> categories = new ArrayList<CategoryDTO>();

    @FXML
    private StackPane contentArea;

    @FXML
    private Button btnBackToMainMenu;

    private URL backto_url;

    ComponentMenuListener addListener;


    void setContentArea(URL url) {
        try {
            Parent parent = FXMLLoader.load(url);
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onBackToMainMenuButtonClick(ActionEvent event) {
        try {
            StageUtils.switchTo(backto_url, event, StageStyle.UNDECORATED);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setBackSatge(URL url){
        this.backto_url = url;
    }
}

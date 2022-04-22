package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DTO.CategoryDTO;
import com.example.coffee_management_system.DTO.TableDTO;
import com.example.coffee_management_system.ultil.ComponentMenuListener;
import com.example.coffee_management_system.ultil.StageUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import org.controlsfx.control.PropertySheet;

import java.io.IOException;
import java.net.URL;
import java.util.*;

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
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent parent = fxmlLoader.load();
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setContentArea(URL url, TableDTO tableDTO) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent parent = fxmlLoader.load();
            ItemManagementController itemManagementController = fxmlLoader.getController();
            itemManagementController.setTableDTO(tableDTO);
            itemManagementController.setScreenData();
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

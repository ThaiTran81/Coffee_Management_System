package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.ultil.StageUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private ImageView ivBrand;
    @FXML
    private Button btnCancel;
    @FXML
    private BorderPane parent;
    @FXML
    private Button btnLogin;

    private Double xOffset;
    private Double yOffset;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StageUtils.makeStageDrageable(parent);
    }


    @FXML
    public void onCancelButtonClick(ActionEvent event){
        Stage stage = (Stage)btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onLoginButtonClick(ActionEvent event){

    }

    boolean validate(){

        return true;
    }


//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
}
package com.example.coffee_management_system.controller;

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

    private Double xOffset;
    private Double yOffset;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        File brandFile = new File("asset/logo.png");
//        Image brandImage = new Image(brandFile.toURI().toString());
//        ivBrand.setImage(brandImage);
//        ivBrand.setSmooth(true);
    }

    private void makeStageDrageable(){
        parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
//                lbNotification.setText("x:"+xOffset+"-"+"y:"+yOffset);
            }
        });

        parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setX(event.getScreenX()-xOffset);
                stage.setY(event.getScreenY()-yOffset);

            }
        });
    }

    public void onCancelButtonClick(ActionEvent event){
        Stage stage = (Stage)btnCancel.getScene().getWindow();
        stage.close();
    }


//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
}
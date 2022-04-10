package com.example.coffee_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private ImageView ivBrand;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnRegister;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtRePassword;
    @FXML
    private Label txtNotification;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandFile = new File("asset/logo.png");
        Image brandImage = new Image(brandFile.toURI().toString());
        ivBrand.setImage(brandImage);
        ivBrand.setSmooth(true);
    }

    public void onCancelButtonClick(ActionEvent event){
        Stage stage = (Stage)btnCancel.getScene().getWindow();
        stage.close();
    }

    public void onRegisterButtonClick(ActionEvent event){
        validate();
    }

    boolean validate(){
        if(txtPassword.getText().isBlank() || txtRePassword.getText().isBlank()){
            txtNotification.setText("Vui lòng điền đầy đủ các trường!");
        };
        return true;
    }

}

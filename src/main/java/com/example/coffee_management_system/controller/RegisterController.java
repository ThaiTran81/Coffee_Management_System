package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DAO.AccountDAO;
import com.example.coffee_management_system.DAO.UserDAO;
import com.example.coffee_management_system.DTO.UserDTO;
import com.example.coffee_management_system.ultil.StageUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private BorderPane parent;
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
    private Label lbNotification;

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
    public void onRegisterButtonClick(ActionEvent event){
        if(validate()){
            String password = BCrypt.hashpw(txtPassword.getText(), BCrypt.gensalt());
            String username = txtUsername.getText();
            UserDTO userDTO = new UserDTO(username);

            try {
                UserDAO.insert(userDTO);
                AccountDAO.insert(username, password, 0);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        };
    }

    boolean validate(){
        String password = txtPassword.getText();
        String confirmPassword = txtRePassword.getText();

        if(password.isBlank() || confirmPassword.isBlank() || txtUsername.getText().isBlank()){
            lbNotification.setText("Vui lòng điền đầy đủ các trường!");
            return false;
        };
        if (!password.equals(confirmPassword)){
            lbNotification.setText("Mật khẩu xác nhận không khớp!");
            return false;
        }
        return true;
    }

}

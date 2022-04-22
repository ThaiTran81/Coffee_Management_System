package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DAO.AccountDAO;
import com.example.coffee_management_system.DAO.UserDAO;
import com.example.coffee_management_system.Main;
import com.example.coffee_management_system.ultil.StageUtils;
import com.example.coffee_management_system.values.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private Label lbNotification;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StageUtils.makeStageDrageable(parent);
    }


    @FXML
    public void onCancelButtonClick(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onLoginButtonClick(ActionEvent event) {
        if (validate() == false) return;

        String username = txtUsername.getText();
        String password = txtPassword.getText();
        boolean check = false;

        try {
            check = AccountDAO.authenticate(username, password);
            if (!check) {
                notify("Mật khẩu hoặc tài khoản không hợp lệ");
                return;
            }

            User.userInfo = UserDAO.findUserByUsername(username);
            if(User.userInfo==null){
                notify("Dữ liệu tài khoản không tồn tại");
                return;
            }

            Parent root=null;
            Stage stage;
            Scene scene ;
            User.userInfo.setType(User.role);
            if(User.userInfo.getType() == 0){
                root = FXMLLoader.load(Main.class.getResource("AdminMainMenu.fxml"));
            }else{
                root = FXMLLoader.load(Main.class.getResource("UserMainMenu.fxml"));
            }
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            assert root != null;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (SQLException | ClassNotFoundException e) {
            notify(e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @FXML
    void onInputChange(InputMethodEvent event) {
        lbNotification.setText("");
    }

    void notify(String msg) {
        lbNotification.setText(msg);
    }

    boolean validate() {
        if (txtUsername.getText().isBlank() || txtPassword.getText().isBlank()) {
            lbNotification.setText("Vui lòng điền đầy đủ các trường");
            return false;
        }
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
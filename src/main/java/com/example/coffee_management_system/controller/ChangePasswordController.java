package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DAO.AccountDAO;
import com.example.coffee_management_system.ultil.Toast;
import com.example.coffee_management_system.values.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ChangePasswordController {

    @FXML
    private TextField tfAuthNewPassword;

    @FXML
    private TextField tfNewPassword;

    @FXML
    private TextField tfOldPassword;

    @FXML
    void onChangePasswordButtonClick(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(!AccountDAO.authenticate(User.userInfo.getEmail(),tfOldPassword.getText())){
            Toast.showToast(Toast.TOAST_WARN, tfOldPassword,"Mật khẩu cũ không chính xác");
        }else if(!tfNewPassword.getText().equalsIgnoreCase(tfAuthNewPassword.getText())){
            Toast.showToast(Toast.TOAST_WARN, tfOldPassword,"Xác nhận mật khẩu mới không trùng khớp");
        }else{
            AccountDAO.updatePassword(User.userInfo.getEmail(), tfNewPassword.getText(), User.role);
            Toast.showToast(Toast.TOAST_SUCCESS, tfOldPassword,"Đổi mật khẩu thành công");
            Stage stage = (Stage) tfNewPassword.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void onCloseButtonClick(MouseEvent event) {
        Stage stage = (Stage) tfNewPassword.getScene().getWindow();
        stage.close();
    }
}

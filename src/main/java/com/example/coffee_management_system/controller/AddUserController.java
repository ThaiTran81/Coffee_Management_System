package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DAO.AccountDAO;
import com.example.coffee_management_system.DAO.CategoryDAO;
import com.example.coffee_management_system.DAO.UserDAO;
import com.example.coffee_management_system.DTO.CategoryDTO;
import com.example.coffee_management_system.DTO.UserDTO;
import com.example.coffee_management_system.ultil.Toast;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddUserController implements Initializable {
    @FXML
    private TextField txtStaffName;

    @FXML
    private DatePicker txtStaffDob;

    @FXML
    private TextField txtStaffAddress;

    @FXML
    private TextField txtStaffEmail;

    @FXML
    private TextField txtStaffPhoneNumber;

    @FXML
    private ComboBox txtStaffType;

    @FXML
    private Button btnAddNew;

    @FXML
    private Button btnCancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] type = {"Nhân viên phục vụ", "Nhân viên bếp"};

        txtStaffType.getItems().addAll(type);
    }

    @FXML
    void onAddButtonClick() {
        if (validate()) {
            UserDTO userDTO = new UserDTO();
            userDTO.setFullname(txtStaffName.getText());
            userDTO.setDob(txtStaffDob.getValue());
            userDTO.setAddress(txtStaffAddress.getText());
            userDTO.setEmail(txtStaffEmail.getText());
            userDTO.setPhone(txtStaffPhoneNumber.getText());

            try {
                if (UserDAO.findUserByUsername(userDTO.getEmail()) != null) {
                    return;
                }
                UserDAO.insert(userDTO);

                String staffType = (String) txtStaffType.getValue();
                int type = 1;

                if (Objects.equals(staffType, "Nhân viên bếp")) {
                    type = 2;
                }
                System.out.println(userDTO.createPassword());
                String password = BCrypt.hashpw(userDTO.createPassword(), BCrypt.gensalt());
                AccountDAO.insert(userDTO.getEmail(), password, type);

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            Stage stage = (Stage) btnAddNew.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void onCancelButtonClick() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    boolean validate() {

        return true;
    }
}

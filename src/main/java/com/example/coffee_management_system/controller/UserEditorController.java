package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DTO.UserDTO;
import com.example.coffee_management_system.ultil.Toast;
import com.example.coffee_management_system.ultil.UDHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserEditorController implements Initializable {
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

    private UserDTO m_user;
    UDHandler callback;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] type = {"Nhân viên phục vụ", "Nhân viên bếp"};

        txtStaffType.getItems().addAll(type);
    }

    @FXML
    void onEditButtonClick(ActionEvent event) {
        if (!validate()) {
            Toast.showToast(Toast.TOAST_SUCCESS, txtStaffAddress, "Vui lòng điền đầy đủ các trường!");
            return;
        }
        UserDTO userDTO = new UserDTO();
        if(m_user!=null)
            userDTO.setUserID(m_user.getUserID());
        userDTO.setFullname(txtStaffName.getText());
        userDTO.setDob(txtStaffDob.getValue());
        userDTO.setAddress(txtStaffAddress.getText());
        userDTO.setEmail(txtStaffEmail.getText());
        userDTO.setPhone(txtStaffPhoneNumber.getText());
        String staffType = (String) txtStaffType.getValue();
        int type = 1;
        if (Objects.equals(staffType, "Nhân viên bếp")) {
            type = 2;
        }
        userDTO.setType(type);


        callback.update(userDTO, event);
    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        callback.delete(null, event);
    }

    public void setData(UserDTO userDTO, UDHandler callback) {
        if (userDTO != null) {
            m_user = userDTO;

            txtStaffName.setText(m_user.getFullname());
            txtStaffDob.setValue(m_user.getDobAsLocalDate());
            txtStaffAddress.setText(m_user.getAddress());
            txtStaffEmail.setText(m_user.getEmail());
            txtStaffPhoneNumber.setText(m_user.getPhone());
            txtStaffType.getSelectionModel().select(m_user.getType()-1);

        }
        this.callback = callback;
    }

    void setUpdateButtonLabel(String label){
        btnAddNew.setText(label);
    }

    boolean validate() {
        if (txtStaffDob.getValue() == null) {
            return false;
        }
        return !txtStaffName.getText().isBlank()
                && !txtStaffEmail.getText().isBlank()
                && !txtStaffAddress.getText().isBlank()
                && !txtStaffPhoneNumber.getText().isBlank()
                && !txtStaffType.getSelectionModel().isEmpty();
    }
}

package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DTO.CategoryDTO;
import com.example.coffee_management_system.DTO.ItemDTO;
import com.example.coffee_management_system.DTO.UserDTO;
import com.example.coffee_management_system.ultil.UDCategoryHandler;
import com.example.coffee_management_system.ultil.UDHandler;
import com.example.coffee_management_system.ultil.UDUserHandler;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserCardController {
    @FXML
    private Button btnDelete;

    @FXML
    private Button btnDetail;

    @FXML
    private Label lbName;

    @FXML
    private Label lbPosition;

    private UserDTO userDTO;

    private UDHandler callback;


    void setData(UserDTO user, UDHandler callback){
        this.userDTO = user;

        lbName.setText(userDTO.getFullname());

        String pos = "Chức vụ: ";
        if (userDTO.getType() == 1) {
            pos += "Nhân viên phục vụ";
        }
        else {
            pos += "Nhân viên bếp";
        }
        lbPosition.setText(pos);
        this.callback = callback;
        this.userDTO = user;
    }

    @FXML
    void onDeleteButtonClick(ActionEvent event) {
        callback.delete(userDTO, event);
    }

    @FXML
    void onDetailButtonClick(ActionEvent event) {
        callback.update(userDTO, event);
    }
}

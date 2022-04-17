package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DTO.TableDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserTableCardController implements Initializable {

    public Label tfNameTable;
    public AnchorPane layoutTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setData(TableDTO tableDTO){
        tfNameTable.setText(tableDTO.getName());
    }
}

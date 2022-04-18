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
    @FXML
    private Label tfNameTable;
    @FXML
    private AnchorPane layoutTable;

    private TableDTO tableDTO;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setData(TableDTO tableDTO){
        this.tableDTO = tableDTO;
        tfNameTable.setText(tableDTO.getName());
        if(tableDTO.getStatus()==0){
            layoutTable.setStyle("-fx-background-color: #70e000");
        }else{
            layoutTable.setStyle("-fx-background-color: #d90429");
        }
    }


}

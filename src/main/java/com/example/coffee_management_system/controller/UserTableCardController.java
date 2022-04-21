package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DTO.TableDTO;
import com.example.coffee_management_system.ultil.SimpleHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserTableCardController implements Initializable {
    @FXML
    private Label tfNameTable;
    @FXML
    private AnchorPane layoutTable;

    private TableDTO tableDTO;
    private SimpleHandler callback;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        layoutTable.setOnMouseClicked(mouseEvent -> {
            this.callback.handle(this.tableDTO, mouseEvent);
        });
    }

//    public void setData(TableDTO tableDTO){
//        this.tableDTO = tableDTO;
//        tfNameTable.setText(tableDTO.getName());
//        if(tableDTO.getStatus()==0){
//            layoutTable.setStyle("-fx-background-color: #70e000");
//        }else{
//            layoutTable.setStyle("-fx-background-color: #d90429");
//        }
//    }

    public void setData(TableDTO tableDTO, SimpleHandler callback){
        this.tableDTO = tableDTO;
        this.callback = callback;
        tfNameTable.setText(tableDTO.getName());
        if(tableDTO.getStatus()==0){
            layoutTable.setStyle("-fx-background-color: #70e000");
        }else{
            layoutTable.setStyle("-fx-background-color: #d90429");
        }
    }


}

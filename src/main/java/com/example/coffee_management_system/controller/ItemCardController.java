package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DTO.ItemDTO;
import com.example.coffee_management_system.ultil.UDHandler;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemCardController implements Initializable {

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private ImageView imgAvatar;

    @FXML
    private Label lbDescription;

    @FXML
    private Label lbPrice;

    @FXML
    private Label lbTitle;

    UDHandler callback;

    ItemDTO m_itemDTO;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    void setData(ItemDTO itemDTO, UDHandler callback){
        lbTitle.setText(itemDTO.getName());
        lbDescription.setText(itemDTO.getDescription());
        lbPrice.setText(String.valueOf(itemDTO.getPrice())+"VNĐ");
        this.callback = callback;
        this.m_itemDTO = itemDTO;

    }

    @FXML
    void onDeleteButtonClick(ActionEvent event) {
        callback.delete(m_itemDTO, event);
    }

    @FXML
    void onUpdateButtonClick(ActionEvent event) {
        callback.update(m_itemDTO, event);
    }
}

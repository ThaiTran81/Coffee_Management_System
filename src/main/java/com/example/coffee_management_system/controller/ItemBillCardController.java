package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DTO.ItemDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemBillCardController implements Initializable {
    private ItemDTO itemDTO;
    @FXML
    private Label lbPriceItem;
    @FXML
    private TextField tfQuantityItem;
    @FXML
    private Label lbNameItem;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setData(ItemDTO item) {
        this.itemDTO = item;
        lbNameItem.setText(item.getName());
        tfQuantityItem.setText("1");
        lbPriceItem.setText(String.valueOf(item.getPrice()) + " VND");
    }

    public void onDeleteButtonClick(MouseEvent mouseEvent) {
    }
}

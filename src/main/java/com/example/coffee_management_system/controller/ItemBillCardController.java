package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DTO.ItemDTO;
import com.example.coffee_management_system.ultil.UDHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemBillCardController implements Initializable {
    private ItemDTO itemDTO;
    @FXML
    private Label lbPriceItem;
    @FXML
    public Spinner spQuantity;
    @FXML
    private Label lbNameItem;

    private UDHandler callback;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,20);
        spQuantity.setValueFactory(spinnerValueFactory);
        spQuantity.setEditable(true);

        spQuantity.getEditor().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = spQuantity.getEditor().getText();
                System.out.println(text);
            }
        });
    }

    public void setData(ItemDTO item, int quantity, UDHandler callback) {
        this.itemDTO = item;
        this.callback = callback;
        lbNameItem.setText(item.getName());
        spQuantity.getValueFactory().setValue(quantity);
        lbPriceItem.setText(String.valueOf(item.getPrice()) + " VND");
    }

    public void onDeleteButtonClick(ActionEvent event) {
        this.callback.delete(this.itemDTO, event);
    }
}

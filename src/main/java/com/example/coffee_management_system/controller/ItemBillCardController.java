package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DAO.BillDetailDAO;
import com.example.coffee_management_system.DTO.BillDetailDTO;
import com.example.coffee_management_system.DTO.ItemDTO;
import com.example.coffee_management_system.ultil.UDBillHandler;
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
    private BillDetailDTO billDetailDTO;
    @FXML
    private Label lbPriceItem;
    @FXML
    public Spinner spQuantity;
    @FXML
    private Label lbNameItem;

    private UDBillHandler callback;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,20);
        spQuantity.setValueFactory(spinnerValueFactory);
        spQuantity.setEditable(true);

        spinnerValueFactory.valueProperty().addListener(((observableValue, oldValue, newValue) -> {
            callback.update(this.itemDTO, this.billDetailDTO, oldValue, newValue);
            billDetailDTO.setQuantity(newValue);
            billDetailDTO.setPrice((float) (billDetailDTO.getPrice() + (newValue-oldValue) * itemDTO.getPrice()));
            BillDetailDAO.update(billDetailDTO);
        }));
    }

    public void setData(ItemDTO item, BillDetailDTO billDetailDTO, UDBillHandler callback) {
        this.itemDTO = item;
        this.callback = callback;
        this.billDetailDTO = billDetailDTO;
        lbNameItem.setText(item.getName());
        spQuantity.getValueFactory().setValue(billDetailDTO.getQuantity());
        lbPriceItem.setText(String.valueOf(item.getPrice()) + " VND");
    }

    public void onDeleteButtonClick(ActionEvent event) {
        this.callback.delete(this.itemDTO, this.billDetailDTO, event, (Integer) spQuantity.getValueFactory().getValue());
    }
}

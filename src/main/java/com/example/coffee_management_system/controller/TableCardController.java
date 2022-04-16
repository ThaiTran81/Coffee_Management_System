package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DTO.TableDTO;
import com.example.coffee_management_system.ultil.UDTableHandler;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TableCardController implements Initializable {
    public Label lbArea;
    @FXML
    private TextField txtTable;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnDelete;

    private TableDTO tableDTO;
    private UDTableHandler callback;

    void setData(TableDTO table, UDTableHandler callback){
        this.tableDTO = table;
        this.callback = callback;
        txtTable.setText(table.getName());
    }

    public void onUpdateButtonClick(ActionEvent event) {
        if (txtTable.getText().equalsIgnoreCase(tableDTO.getName()))
        {
            // add msg to notify the name existed
            return;
        }

        tableDTO.setName(txtTable.getText());
        callback.update(tableDTO);
    }

    public void onDeleteButtonClick(ActionEvent event) {
        callback.delete(tableDTO);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbArea.setVisible(false);
    }
}

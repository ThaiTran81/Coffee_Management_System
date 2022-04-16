package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DAO.CategoryDAO;
import com.example.coffee_management_system.DTO.CategoryDTO;
import com.example.coffee_management_system.ultil.UDCategoryHandler;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class CategoryCardController {
    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private Label lbNum;

    @FXML
    private TextField txtCategory;

    private CategoryDTO categoryDTO;

    private UDCategoryHandler callback;

    void setData(CategoryDTO category, UDCategoryHandler callback){
        this.categoryDTO = category;
        this.callback = callback;
        txtCategory.setText(categoryDTO.getName());
    }

    @FXML
    void onUpdateButtonClick(ActionEvent event){
        if (txtCategory.getText().equalsIgnoreCase(categoryDTO.getName()))
        {
            // add msg to notify the name existed
            return;
        }

        categoryDTO.setName(txtCategory.getText());
        callback.update(categoryDTO);
    }

    @FXML
    void onDeleteButtonClick(ActionEvent event) throws SQLException, ClassNotFoundException {
        callback.delete(categoryDTO);
    }
}

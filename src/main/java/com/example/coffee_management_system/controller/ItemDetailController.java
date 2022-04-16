package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DAO.CategoryDAO;
import com.example.coffee_management_system.DTO.CategoryDTO;
import com.example.coffee_management_system.DTO.ItemDTO;
import com.example.coffee_management_system.ultil.SimpleHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ItemDetailController implements Initializable {
    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXComboBox<CategoryDTO> cbCategory;

    @FXML
    private ImageView ivItem;

    @FXML
    private JFXTextArea taDescription;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    SimpleHandler callback;

    private ItemDTO m_item;
    ObservableList<CategoryDTO> options;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        options = FXCollections.observableArrayList();
        try {
            List<CategoryDTO> categories;
            categories = CategoryDAO.getAll();
            options.addAll(categories);
            cbCategory.setItems(options);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void onCancelButton(ActionEvent event) {
        callback.handle();
    }

    @FXML
    void onUpdateButton(ActionEvent event) {

    }

    public void setData(ItemDTO itemDTO, SimpleHandler callback){
        m_item = itemDTO;
        this.callback =callback;
        txtName.setText(m_item.getName());
        taDescription.setText(m_item.getDescription());
        txtPrice.setText(String.valueOf(m_item.getPrice()));

        for(CategoryDTO categoryDTO: options){
            if(categoryDTO.getId()==m_item.getCategory())
                cbCategory.getSelectionModel().select(categoryDTO);
        }
    }


}

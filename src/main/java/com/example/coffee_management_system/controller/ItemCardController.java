package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DTO.ItemDTO;
import com.example.coffee_management_system.ultil.UDHandler;
import com.example.coffee_management_system.values.User;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemCardController implements Initializable {

    @FXML
    public JFXButton btnAddItemBill;

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
        if(User.userInfo.getType() == 0){
            btnDelete.setVisible(true);
            btnUpdate.setVisible(true);
            btnAddItemBill.setVisible(false);
        }else{
            btnDelete.setVisible(false);
            btnDelete.managedProperty().bind(btnDelete.visibleProperty());
            btnUpdate.setVisible(false);
            btnUpdate.managedProperty().bind(btnUpdate.visibleProperty());
            btnAddItemBill.setVisible(true);
        }
    }

    void setData(ItemDTO itemDTO, UDHandler callback) {
        lbTitle.setText(itemDTO.getName());
        lbDescription.setText(itemDTO.getDescription());
        lbPrice.setText(String.valueOf(itemDTO.getPrice()) + "VNĐ");
        this.callback = callback;
        this.m_itemDTO = itemDTO;
         if (m_itemDTO.getImg()!=null){
            try {
                InputStream inputStream = m_itemDTO.getImg().getBinaryStream();
                imgAvatar.setImage(new Image(inputStream));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (m_itemDTO.getImg()!=null){
            try {
                InputStream inputStream = m_itemDTO.getImg().getBinaryStream();
                imgAvatar.setImage(new Image(inputStream));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void onDeleteButtonClick(ActionEvent event) {
        callback.delete(m_itemDTO, event);
    }

    @FXML
    void onUpdateButtonClick(ActionEvent event) {
        callback.update(m_itemDTO, event);
    }

    @FXML
    public void onAddItemToBillButtonClick(ActionEvent event) {
        callback.addToBill(m_itemDTO, event);
    }
}

package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DAO.AreaDAO;
import com.example.coffee_management_system.DTO.AreaDTO;
import com.example.coffee_management_system.Main;
import com.jfoenix.controls.JFXButton;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TableManagementController implements Initializable {
    public ComboBox areaCb;
    public JFXButton addBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setAreaCb();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setAreaCb() throws SQLException, ClassNotFoundException {
        ArrayList<String> listArea = AreaDAO.findAllAreaName();
        ObservableList<String> valueCb = FXCollections.observableArrayList(listArea);
        areaCb.setItems(valueCb);
        areaCb.getSelectionModel().selectFirst();
    }

    public void addTable(MouseEvent mouseEvent) {
        try {

            Stage addTable = new Stage();
            Parent root = FXMLLoader.load(Main.class.getResource("addTable.fxml"));
            Scene scene = new Scene(root);
            addTable.setScene(scene);
            addTable.initModality(Modality.APPLICATION_MODAL);
            addTable.showAndWait();
            addTable.setResizable(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTable(MouseEvent mouseEvent) {

    }
}

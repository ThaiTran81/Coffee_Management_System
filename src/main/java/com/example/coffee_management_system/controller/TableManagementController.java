package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DAO.TableDAO;
import com.example.coffee_management_system.DTO.TableDTO;
import com.example.coffee_management_system.Main;
import com.example.coffee_management_system.ultil.Toast;
import com.example.coffee_management_system.ultil.UDTableHandler;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TableManagementController implements Initializable {
    public JFXButton btnAddNew;
    public VBox layout;
    public TextField txtNewTable;

    private List<TableDTO> tables;
    private UDTableHandler udTableHandler;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        udTableHandler = new UDTableHandler() {
            @Override
            public void update(TableDTO tableDTO) {
                try {
                    TableDAO.update(tableDTO);
                    reload();
                    Toast.showToast(Toast.TOAST_SUCCESS, txtNewTable, "Đã cập nhật thành công");
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void delete(TableDTO tableDTO) {
                try {
                    TableDAO.delete(tableDTO);
                    reload();
                    Toast.showToast(Toast.TOAST_SUCCESS, txtNewTable, "Đã xoá bàn " + tableDTO.getName() + " thành công");
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
        pullData();
        setData();
    }

    public void onAddButtonClick(ActionEvent event) {
        if (validate()) {
            TableDTO tableDTO = new TableDTO();

            tableDTO.setName(txtNewTable.getText());
            tableDTO.setArea_id(1);
            tableDTO.setBill_id(1);
            tableDTO.setStatus(0);
            try {

                if (TableDAO.findByName(tableDTO.getName()) != null) {
                    Toast.showToast(Toast.TOAST_SUCCESS, txtNewTable, "Bàn đã tồn tại");
                    return;
                }

                TableDAO.insert(tableDTO);
                reload();
                txtNewTable.setText("");
                Toast.showToast(Toast.TOAST_SUCCESS, txtNewTable, "Đã thêm bàn "+ tableDTO.getName()+ " thành công");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    void setData(){
        for (TableDTO item : tables) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("table_card.fxml"));
            AnchorPane anchorPane = null;
            try {
                anchorPane = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            TableCardController itemController = fxmlLoader.getController();

            itemController.setData(item, udTableHandler);
            layout.getChildren().add(anchorPane);
        }

    }

    void pullData(){
        try {
            tables = TableDAO.findAll();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void reload() {
        layout.getChildren().clear();
        pullData();
        setData();

    }

    boolean validate() {
        if (txtNewTable.getText().isBlank()) {
            return false;
        }
        return true;
    }

    public void deleteTable(MouseEvent mouseEvent) {
    }
}

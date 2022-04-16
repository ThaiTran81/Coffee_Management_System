package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DAO.CategoryDAO;
import com.example.coffee_management_system.DTO.CategoryDTO;
import com.example.coffee_management_system.Main;
import com.example.coffee_management_system.ultil.Toast;
import com.example.coffee_management_system.ultil.UDCategoryHandler;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryManagementController implements Initializable {

    @FXML
    private JFXButton btnAddNew;

    @FXML
    private VBox layout;

    @FXML
    private TextField txtNewCategory;

    private List<CategoryDTO> categories;
    private UDCategoryHandler udCategoryHandler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        udCategoryHandler = new UDCategoryHandler() {
            @Override
            public void update(CategoryDTO categoryDTO) {
                try {
                    CategoryDAO.update(categoryDTO);
                    reload();
                    Toast.showToast(Toast.TOAST_SUCCESS, txtNewCategory, "Đã cập nhật thành công");
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void delete(CategoryDTO categoryDTO) {
                try {
                    CategoryDAO.delete(categoryDTO);
                    reload();
                    Toast.showToast(Toast.TOAST_SUCCESS, txtNewCategory, "Đã xoá danh mục " + categoryDTO.getName() + " thành công");
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };

        pullData();
        setData();
    }

    void setData() {
        for (CategoryDTO item : categories) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("category_card.fxml"));
            AnchorPane anchorPane = null;
            try {
                anchorPane = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            CategoryCardController itemController = fxmlLoader.getController();

            itemController.setData(item, udCategoryHandler);
            layout.getChildren().add(anchorPane);
        }

    }

    void pullData() {
        try {
            categories = CategoryDAO.getAll();

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

    @FXML
    void onAddButtonClick() {
        if (validate()) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setName(txtNewCategory.getText());
            categoryDTO.setIcUrl("");


            try {

                if (CategoryDAO.findByName(categoryDTO.getName()) != null) {
                    return;
                }

                CategoryDAO.insert(categoryDTO);
                reload();
                txtNewCategory.setText("");
                Toast.showToast(Toast.TOAST_SUCCESS, txtNewCategory, "Đã thêm danh mục "+ categoryDTO.getName()+ " thành công");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    boolean validate() {
        if (txtNewCategory.getText().isBlank()) {
            return false;
        }
        return true;
    }
}

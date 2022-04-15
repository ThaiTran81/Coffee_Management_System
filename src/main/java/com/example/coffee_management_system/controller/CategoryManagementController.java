package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DAO.CategoryDAO;
import com.example.coffee_management_system.DTO.CategoryDTO;
import com.example.coffee_management_system.Main;
import com.example.coffee_management_system.ultil.UDCategoryHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryManagementController implements Initializable {

    @FXML
    VBox layout;

    private List<CategoryDTO> categories;
    private UDCategoryHandler udCategoryHandler;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        udCategoryHandler = new UDCategoryHandler() {
            @Override
            public void update(CategoryDTO categoryDTO) {
            }

            @Override
            public void delete(CategoryDTO categoryDTO) {
                try {
                    CategoryDAO.delete(categoryDTO);
                    reload();
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };

        pullData();
        setData();
    }

    void setData(){
        for(CategoryDTO item: categories){
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

    void pullData(){
        try {
            categories = CategoryDAO.getAll();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void reload(){
        layout.getChildren().clear();
        pullData();
        setData();

    }
}

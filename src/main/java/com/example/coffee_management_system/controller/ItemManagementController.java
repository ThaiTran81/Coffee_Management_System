package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DAO.CategoryDAO;
import com.example.coffee_management_system.DAO.ItemDAO;
import com.example.coffee_management_system.DTO.CategoryDTO;
import com.example.coffee_management_system.DTO.ItemDTO;
import com.example.coffee_management_system.Main;
import com.example.coffee_management_system.ultil.SimpleHandler;
import com.example.coffee_management_system.ultil.Toast;
import com.example.coffee_management_system.ultil.UDHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ItemManagementController implements Initializable {

    @FXML
    private FlowPane flow;

    @FXML
    private ScrollPane scroll;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXComboBox<CategoryDTO> cbCategory;

    @FXML
    VBox rightLayout;


    @FXML
    private TextField txtSearch;

    SimpleHandler simpleHandler;

    List<ItemDTO> itemList;
    ObservableList<CategoryDTO> options = FXCollections.observableArrayList();
    UDHandler itemHandler;

    void getData() {
        List<CategoryDTO> categories;

        CategoryDTO category = new CategoryDTO("Tất cả", 0, null);
        options.add(category);

        try {
            itemList = ItemDAO.getAll();
            categories = CategoryDAO.getAll();

            for (int i = 0; i < categories.size(); i++) {
                options.add(categories.get(i));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getData();
        cbCategory.setItems(options);
        cbCategory.getSelectionModel().selectFirst();

        flow.setPadding(new Insets(5, 5, 5, 5));
        flow.setVgap(10);
        flow.setHgap(10);
        flow.setAlignment(Pos.TOP_LEFT);


        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Horizontal scroll bar
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Vertical scroll bar
        scroll.setContent(flow);

        simpleHandler = new SimpleHandler() {
            @Override
            public void handle() {
                rightLayout.getChildren().clear();
                rightLayout.setPrefWidth(Region.USE_COMPUTED_SIZE);
            }
        };

        scroll.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
                flow.setPrefWidth(bounds.getWidth());
                flow.setPrefHeight(bounds.getHeight());
            }
        });

        itemHandler = new UDHandler() {
            @Override
            public void update(Object obj) {
                ItemDTO itemDTO = (ItemDTO) obj;
                FXMLLoader fxmlLoader = addItem(Main.class.getResource("item_detail.fxml"));
                ItemDetailController itemDetailController = fxmlLoader.getController();
                itemDetailController.setData(itemDTO, simpleHandler);
            }

            @Override
            public void delete(Object obj) {
                ItemDTO itemDTO = (ItemDTO) obj;
                try {
                    ItemDAO.delete(itemDTO);
                    Toast.showToast(Toast.TOAST_SUCCESS, btnSearch, "Đã xoá " + itemDTO.getName() + " khỏi thực đơn");
                    itemList.remove(itemDTO);
                    setData2Grid(itemList);
                } catch (SQLException | ClassNotFoundException e) {
                    Toast.showToast(Toast.TOAST_ERROR, btnSearch, "Không thể xoá " + itemDTO.getName() + " khỏi thực đơn vào lúc này");
                    e.printStackTrace();
                }
            }
        };

        setData2Grid(itemList);
    }

    @FXML
    private void onCategoryCBClick(ActionEvent event) {
        CategoryDTO categoryDTO = cbCategory.getSelectionModel().getSelectedItem();

        try {
            if (categoryDTO.getId() == 0) itemList = ItemDAO.getAll();
            else itemList = ItemDAO.getByCategory(categoryDTO.getId());

            if (!txtSearch.getText().isBlank()) {
                onSearchButtonClick(event);
            } else setData2Grid(itemList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void setData2Grid(List<ItemDTO> list) {
        try {
            flow.getChildren().clear();
            for (ItemDTO itemDTO : list) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("item_card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemCardController itemController = fxmlLoader.getController();
                itemController.setData(itemDTO, itemHandler);

                flow.getChildren().add(anchorPane);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSearchButtonClick(ActionEvent event) {
        List<ItemDTO> copy = new ArrayList<>(itemList);
        String key = txtSearch.getText();
        copy.removeIf(item -> !key.isBlank() && !item.getName().toLowerCase().contains(key.toLowerCase()));

        setData2Grid(copy);
    }

    FXMLLoader addItem(URL url) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        AnchorPane anchorPane = null;
        try {
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        rightLayout.getChildren().clear();
//        rightLayout.setPrefWidth(anchorPane.getWidth());
        rightLayout.getChildren().add(anchorPane);
        return fxmlLoader;
    }
}

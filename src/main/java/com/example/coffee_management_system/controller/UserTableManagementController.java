package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DAO.AreaDAO;
import com.example.coffee_management_system.DAO.TableDAO;
import com.example.coffee_management_system.DTO.AreaDTO;
import com.example.coffee_management_system.DTO.TableDTO;
import com.example.coffee_management_system.Main;
import com.example.coffee_management_system.ultil.SimpleHandler;
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
import java.util.List;
import java.util.ResourceBundle;

public class UserTableManagementController implements Initializable {

    @FXML
    private FlowPane flow;

    @FXML
    private ScrollPane scroll;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXComboBox<AreaDTO> cbArea;

    @FXML
    VBox rightLayout;


    @FXML
    private TextField txtSearch;


    List<TableDTO> itemList;
    ObservableList<AreaDTO> options = FXCollections.observableArrayList();
    UDHandler itemHandler;

    void getData() {
        List<AreaDTO> areas;

        AreaDTO area = new AreaDTO(0, "Tất cả", 1);
        options.add(area);

        try {
            itemList = TableDAO.findAll();
            areas = AreaDAO.findAll();

            for (int i = 0; i < areas.size(); i++) {
                options.add(areas.get(i));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getData();
        cbArea.setItems(options);
        cbArea.getSelectionModel().selectFirst();

        flow.setPadding(new Insets(5, 5, 5, 5));
        flow.setVgap(10);
        flow.setHgap(10);
        flow.setAlignment(Pos.TOP_LEFT);

        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Horizontal scroll bar
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Vertical scroll bar
        scroll.setContent(flow);

        simpleHandler = new SimpleHandler() {
            @Override
            public void handle(Object[] obj) {
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

        setData2Grid(itemList);
    }

    private void setData2Grid(List<TableDTO> list) {
        try {
            flow.getChildren().clear();
            for (TableDTO itemDTO : list) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("user_table_card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                UserTableCardController  itemController = fxmlLoader.getController();
//                itemController.setData(TableDTO, itemHandler);
                itemController.setData(itemDTO);
                flow.getChildren().add(anchorPane);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onSearchButtonClick(ActionEvent event) {
        List<TableDTO> copy = new ArrayList<>(itemList);
        String key = txtSearch.getText();
        copy.removeIf(item -> !key.isBlank() && !item.getName().toLowerCase().contains(key.toLowerCase()));

        setData2Grid(copy);
    }

    public void onCategoryCBClick(ActionEvent event) {
        AreaDTO areaDTO = cbArea.getSelectionModel().getSelectedItem();

        try {
            if (areaDTO.getId() == 0) itemList = TableDAO.findAll();
            else itemList = TableDAO.findByAreaId(areaDTO.getId());

            if (!txtSearch.getText().isBlank()) {
                onSearchButtonClick(event);
            } else setData2Grid(itemList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DAO.ItemDAO;
import com.example.coffee_management_system.DTO.ItemDTO;
import com.example.coffee_management_system.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ItemManagementController implements Initializable {

    @FXML
    private FlowPane flow;

    @FXML
    private ScrollPane scroll;

    List<ItemDTO> itemList;

    void getData(){
        try {
            itemList = ItemDAO.getAll();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getData();
        int column = 0;
        int row = 1;

        flow.setPadding(new Insets(5, 5, 5, 5));
        flow.setVgap(10);
        flow.setHgap(10);
        flow.setAlignment(Pos.TOP_LEFT);

        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Horizontal scroll bar
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Vertical scroll bar
        scroll.setContent(flow);
        scroll.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
                flow.setPrefWidth(bounds.getWidth());
                flow.setPrefHeight(bounds.getHeight());
            }
        });
        try {
            for (int i = 0; i < itemList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("item_card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemCardController itemController = fxmlLoader.getController();
                itemController.setData(itemList.get(i));

                if (column == 4) {
                    column = 0;
                    row++;
                }

//                grid.add(anchorPane, column++, row); //(child,column,row)
                flow.getChildren().add(anchorPane);
                //set grid width
//                flow.setMinWidth(Region.USE_COMPUTED_SIZE);
//                flow.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                flow.setMaxWidth(Region.USE_PREF_SIZE);
//
//                //set grid height
//                flow.setMinHeight(Region.USE_COMPUTED_SIZE);
//                flow.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                flow.setMaxHeight(Region.USE_PREF_SIZE);

//                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

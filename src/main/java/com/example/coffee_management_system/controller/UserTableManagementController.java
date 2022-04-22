package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DAO.AreaDAO;
import com.example.coffee_management_system.DAO.BillDAO;
import com.example.coffee_management_system.DAO.TableDAO;
import com.example.coffee_management_system.DTO.AreaDTO;
import com.example.coffee_management_system.DTO.BillDTO;
import com.example.coffee_management_system.DTO.TableDTO;
import com.example.coffee_management_system.Main;
import com.example.coffee_management_system.ultil.SimpleHandler;
import com.example.coffee_management_system.ultil.StageUtils;
import com.example.coffee_management_system.values.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
    SimpleHandler simpleHandler;


    List<TableDTO> tableList;
    ObservableList<AreaDTO> options = FXCollections.observableArrayList();
//    SimpleHandler simpleHandler;

    void getData() {
        List<AreaDTO> areas;

        AreaDTO area = new AreaDTO(0, "Tất cả", 1);
        options.add(area);

        try {
            tableList = TableDAO.findAllAvailableTable();
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
            public void handle(Object obj, MouseEvent event) {
                TableDTO tableDTO = (TableDTO) obj;
                FXMLLoader fxmlLoader;
                try {
                    fxmlLoader = switchTo(Main.class.getResource("management_menu.fxml"), event);
                    ManagmentMenuController managmentMenuController = fxmlLoader.getController();
                    if(tableDTO.getBill_id() == 0){
                        int newBillId = BillDAO.countBill() + 1;
                        BillDTO billDTO = new BillDTO();
                        billDTO.setBill_id(newBillId);
                        billDTO.setUser_id(User.userInfo.getUserID());
                        BillDAO.insert(billDTO);
                        tableDTO.setBill_id(newBillId);
                        TableDAO.setBillId(tableDTO.getTable_id(), newBillId);
                    }
                    managmentMenuController.setContentArea(Main.class.getResource("item_management.fxml"), tableDTO);
                    managmentMenuController.setBackSatge(Main.class.getResource("UserMainMenu.fxml"));


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };

        scroll.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
                flow.setPrefWidth(bounds.getWidth());
                flow.setPrefHeight(bounds.getHeight());
            }
        });

        setData2Grid(tableList);
    }

    FXMLLoader switchTo(URL url, MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Stage prevStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        prevStage.hide();

        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());

        stage.hide();
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();

        return fxmlLoader;
    }

    private void setData2Grid(List<TableDTO> list) {
        try {
            flow.getChildren().clear();
            for (TableDTO tableDTO : list) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("user_table_card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                UserTableCardController  itemController = fxmlLoader.getController();
                itemController.setData(tableDTO, simpleHandler);
//                itemController.setData(itemDTO);
                flow.getChildren().add(anchorPane);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onSearchButtonClick(ActionEvent event) {
        List<TableDTO> copy = new ArrayList<>(tableList);
        String key = txtSearch.getText();
        copy.removeIf(item -> !key.isBlank() && !item.getName().toLowerCase().contains(key.toLowerCase()));

        setData2Grid(copy);
    }

    public void onCategoryCBClick(ActionEvent event) {
        AreaDTO areaDTO = cbArea.getSelectionModel().getSelectedItem();

        try {
            if (areaDTO.getId() == 0) tableList = TableDAO.findAllAvailableTable();
            else tableList = TableDAO.findAvailableTableByAreaId(areaDTO.getId());

            if (!txtSearch.getText().isBlank()) {
                onSearchButtonClick(event);
            } else setData2Grid(tableList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

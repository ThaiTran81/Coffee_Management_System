package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DAO.ItemDAO;
import com.example.coffee_management_system.DAO.TableDAO;
import com.example.coffee_management_system.DTO.ItemDTO;
import com.example.coffee_management_system.DTO.TableDTO;
import com.example.coffee_management_system.Main;
import com.example.coffee_management_system.ultil.StageUtils;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class BillManagementController implements Initializable {


    @FXML
    public Label lbDateTime;
    @FXML
    public Label lbEmployee;
    @FXML
    public TextField tfCustomer;
    @FXML
    public TextField tfPhoneCustomer;
    @FXML
    public VBox itemBillArea;
    @FXML
    public Label tfTotalPrice;
    @FXML
    public JFXComboBox cbDiscount;
    @FXML
    public Label tfFinalTotalPrice;
    public ScrollPane scroll;

    List<ItemDTO> items;

    @FXML
    void onCancelButtonClick(MouseEvent event) {

    }

    @FXML
    void onPayButtonClick(MouseEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        itemBillArea.setPadding(new Insets(5, 5, 5, 5));
        itemBillArea.setAlignment(Pos.CENTER);

        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Horizontal scroll bar
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Vertical scroll bar
        scroll.setContent(itemBillArea);

        pullData();
        setData();
    }

    private void pullData(){
        try {
            items = ItemDAO.getByCategory(7);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setData(){
        for (ItemDTO item : items) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("item_bill_card.fxml"));
            AnchorPane anchorPane = null;
            try {
                anchorPane = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ItemBillCardController itemController = fxmlLoader.getController();

//            itemController.setData(item, udTableHandler);
            itemController.setData(item);
            itemBillArea.getChildren().add(anchorPane);
        }
    }
}

package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DAO.BillDAO;
import com.example.coffee_management_system.DAO.BillDetailDAO;
import com.example.coffee_management_system.DAO.ItemDAO;
import com.example.coffee_management_system.DAO.TableDAO;
import com.example.coffee_management_system.DTO.BillDTO;
import com.example.coffee_management_system.DTO.BillDetailDTO;
import com.example.coffee_management_system.DTO.ItemDTO;
import com.example.coffee_management_system.DTO.TableDTO;
import com.example.coffee_management_system.Main;
import com.example.coffee_management_system.ultil.StageUtils;
import com.example.coffee_management_system.values.User;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BillManagementController implements Initializable {

    @FXML
    public Label lbIdBill;
    @FXML
    public Label lbArea;
    @FXML
    public Label lbNameTable;
    @FXML
    private Label lbDateTime;
    @FXML
    private Label lbEmployee;
    @FXML
    private TextField tfCustomer;
    @FXML
    private TextField tfPhoneCustomer;
    @FXML
    private VBox itemBillArea;
    @FXML
    private Label tfTotalPrice;
    @FXML
    private JFXComboBox cbDiscount;
    @FXML
    private Label tfFinalTotalPrice;
    @FXML
    private ScrollPane scroll;

    BillDTO billDTO;
    List<ItemDTO> items = new ArrayList<>();
    private TableDTO tableDTO;

    @FXML
    void onCancelButtonClick(MouseEvent event) {

    }

    @FXML
    void onPayButtonClick(MouseEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        itemBillArea.setPadding(new Insets(5, 5, 5, 5));
        itemBillArea.setAlignment(Pos.TOP_CENTER);

        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Horizontal scroll bar
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Vertical scroll bar
        scroll.setContent(itemBillArea);

    }

    public void pullData(TableDTO tableDTO){
        try {
            this.tableDTO = tableDTO;
            this.billDTO = BillDAO.findById(tableDTO.getBill_id());
            List<BillDetailDTO> listBillDetail = BillDetailDAO.findByBillID(tableDTO.getBill_id());
            System.out.println(listBillDetail.size());
            for(BillDetailDTO billDetailDTO : listBillDetail){
                System.out.println(billDetailDTO.getItem_id());
                items.add(ItemDAO.findById(billDetailDTO.getItem_id()));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setData(){
        lbArea.setText(String.valueOf(this.tableDTO.getArea_id()));
        lbNameTable.setText(tableDTO.getName());
        lbIdBill.setText(String.valueOf(billDTO.getBill_id()));
        lbDateTime.setText(String.valueOf(billDTO.getCreate_time()));
        lbEmployee.setText(User.userInfo.getFullname());
        tfTotalPrice.setText(String.valueOf(billDTO.getTotal()));

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

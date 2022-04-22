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
import com.example.coffee_management_system.ultil.UDHandler;
import com.example.coffee_management_system.values.User;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
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
import org.controlsfx.control.PropertySheet;

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
    public VBox layoutParent;
    @FXML
    public Label lbTotalPricePay;
    @FXML
    public Label lbDiscount;
    @FXML
    public Label lbTotalPrice;
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
    private JFXComboBox cbDiscount;
    @FXML
    private ScrollPane scroll;
    @FXML
    private ScrollPane parentScroll;
    BillDTO billDTO;
    List<ItemDTO> items = new ArrayList<>();
    private TableDTO tableDTO;
    UDHandler itemBillHandler;

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
        layoutParent.setPadding(new Insets(5,5,5,5));
        layoutParent.setAlignment(Pos.TOP_CENTER);

        parentScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Horizontal scroll bar
        parentScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Vertical scroll bar
        parentScroll.setContent(layoutParent);

        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Horizontal scroll bar
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Vertical scroll bar
        scroll.setContent(itemBillArea);

        itemBillHandler = new UDHandler() {
            @Override
            public void update(Object obj, ActionEvent event) {
            }
            @Override
            public void delete(Object obj, ActionEvent event) {
                ItemDTO itemDTO = (ItemDTO) obj;
                for(int i = 0; i < items.size();++i){
                    if(itemDTO.equals(items.get(i))){
                        itemBillArea.getChildren().remove(i);
                        items.remove(i);

                    }
                }
            }
            @Override
            public void addToBill(Object obj, ActionEvent event) {}
        };

    }

    public void pullData(TableDTO tableDTO){
            this.tableDTO = tableDTO;
    }

    public void setData() {
        ArrayList<Integer> quantity = new ArrayList<>();
        try {
            this.billDTO = BillDAO.findById(tableDTO.getBill_id());
            List<BillDetailDTO> listBillDetail = BillDetailDAO.findByBillID(tableDTO.getBill_id());
            for(BillDetailDTO billDetailDTO : listBillDetail){
                items.add(ItemDAO.findById(billDetailDTO.getItem_id()));
                quantity.add(billDetailDTO.getQuantity());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        lbArea.setText(String.valueOf(this.tableDTO.getArea_id()));
        lbNameTable.setText(tableDTO.getName());
        lbIdBill.setText(String.valueOf(billDTO.getBill_id()));
        lbDateTime.setText(String.valueOf(billDTO.getCreate_time()));
        lbEmployee.setText(User.userInfo.getFullname());
        lbTotalPrice.setText(String.valueOf(billDTO.getTotal()));
        lbTotalPricePay.setText(String.valueOf(billDTO.getTotal()));


        int i = 0;
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

            itemController.setData(item,quantity.get(i), itemBillHandler);
            i++;
            itemBillArea.getChildren().add(anchorPane);
        }
    }

    public void addItemToBill(Object obj) {
        ItemDTO itemDTO = (ItemDTO) obj;
        items.add(itemDTO);
        billDTO.setTotal((float) (billDTO.getTotal() + itemDTO.getPrice()));
        lbTotalPricePay.setText(String.valueOf(billDTO.getTotal()));
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
        itemController.setData(itemDTO, 1, itemBillHandler);
        itemBillArea.getChildren().add(anchorPane);
    }
}

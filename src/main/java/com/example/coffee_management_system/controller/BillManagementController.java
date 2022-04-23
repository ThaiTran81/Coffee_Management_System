package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DAO.*;
import com.example.coffee_management_system.DTO.*;
import com.example.coffee_management_system.Main;
import com.example.coffee_management_system.ultil.StageUtils;
import com.example.coffee_management_system.ultil.Toast;
import com.example.coffee_management_system.ultil.UDBillHandler;
import com.example.coffee_management_system.ultil.UDHandler;
import com.example.coffee_management_system.values.User;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.stage.StageStyle;
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
    private JFXComboBox<PromotionDTO> cbPromotion;
    @FXML
    private ScrollPane scroll;
    @FXML
    private ScrollPane parentScroll;

    private CustomerDTO customerDTO;


    BillDTO billDTO;
    List<ItemDTO> items = new ArrayList<>();
    private TableDTO tableDTO;
    UDBillHandler itemBillHandler;
    ObservableList<PromotionDTO> options = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<PromotionDTO> promotions;
        PromotionDTO promotionDTO = new PromotionDTO(0,"Không có","",0,null,null,1);
        options.add(promotionDTO);


        try {
            promotions = PromotionDAO.getAll();
            for(int i = 0; i < promotions.size();++i){
                if(promotions.get(i).getStatus() == 1) options.add(promotions.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cbPromotion.setItems(options);
        cbPromotion.getSelectionModel().selectFirst();

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

        itemBillHandler = new UDBillHandler() {
            @Override
            public void delete(Object obj, Object obj1,Event event) {
                ItemDTO itemDTO = (ItemDTO) obj;
                BillDetailDTO billDetailDTO = (BillDetailDTO) obj1;
                for(int i = 0; i < items.size();++i){
                    if(itemDTO.equals(items.get(i))){
                        itemBillArea.getChildren().remove(i);
                        items.remove(i);
                        calAndSetTotalPriceBill(billDetailDTO.getPrice() * -1.0f);
                        calAndSetTotalPricePayBill();
                    }
                }
                BillDetailDAO.delete(billDetailDTO);
            }

            @Override
            public void update(Object obj, Object obj1, int oldQuantityItem, int newQuantityItem) {
                ItemDTO itemDTO = (ItemDTO) obj;
                BillDetailDTO billDetailDTO = (BillDetailDTO) obj1;
                int size = newQuantityItem - oldQuantityItem;
                calAndSetTotalPriceBill((float) (itemDTO.getPrice()*size));
                calAndSetTotalPricePayBill();
            }
        };

    }

    public void pullData(TableDTO tableDTO){
            this.tableDTO = tableDTO;
    }

    public void setData() {
//        ArrayList<Integer> quantity = new ArrayList<>();
        try {
            this.billDTO = BillDAO.findById(tableDTO.getBill_id());
            PromotionDTO promotionDTO = PromotionDAO.findByPromotionId(billDTO.getPromotion());

            options.forEach( promotion -> {
                if(promotionDTO != null && promotion.getPromotionID() == promotionDTO.getPromotionID()){
                    cbPromotion.getSelectionModel().select(promotion);
                    System.out.println("haha");
                    }
                }
            );
            List<BillDetailDTO> listBillDetail = BillDetailDAO.findByBillID(tableDTO.getBill_id());
            for(BillDetailDTO billDetailDTO : listBillDetail){
                items.add(ItemDAO.findById(billDetailDTO.getItem_id()));
//                quantity.add(billDetailDTO.getQuantity());
            }
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

                itemController.setData(item,listBillDetail.get(i), itemBillHandler);
                i++;
                itemBillArea.getChildren().add(anchorPane);
            }

            customerDTO = CustomerDAO.findById(billDTO.getCustomer_id());
            if(customerDTO != null){
                tfCustomer.setText(customerDTO.getFullname());
                tfCustomer.setText(customerDTO.getPhone());
            }

            lbArea.setText(String.valueOf(this.tableDTO.getArea_id()));
            lbNameTable.setText(tableDTO.getName());
            lbIdBill.setText(String.valueOf(billDTO.getBill_id()));
            lbDateTime.setText(String.valueOf(billDTO.getCreate_time()));
            lbEmployee.setText(User.userInfo.getFullname());
            lbTotalPrice.setText(String.valueOf(billDTO.getTotal()));
            lbDiscount.setText(String.valueOf(billDTO.getDiscount()));

            calAndSetTotalPricePayBill();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addItemToBill(Object obj) throws SQLException, ClassNotFoundException {
        ItemDTO itemDTO = (ItemDTO) obj;
        items.add(itemDTO);
       calAndSetTotalPriceBill((float) itemDTO.getPrice());
       calAndSetTotalPricePayBill();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("item_bill_card.fxml"));
        AnchorPane anchorPane = null;

        try {
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ItemBillCardController itemController = fxmlLoader.getController();
        BillDetailDTO billDetailDTO = new BillDetailDTO(
                0,
                billDTO.getBill_id(),
                itemDTO.getItem_id(),
                1,
                null,
                0,
                (float) itemDTO.getPrice()
        );
        billDetailDTO.setId(BillDetailDAO.insert(billDetailDTO));
//            itemController.setData(item, udTableHandler);
        itemController.setData(itemDTO, billDetailDTO, itemBillHandler);
        itemBillArea.getChildren().add(anchorPane);

    }

    public float calAndSetTotalPriceBill(float amount){
        billDTO.setTotal((billDTO.getTotal() + amount));
        lbTotalPrice.setText(String.valueOf(billDTO.getTotal()));
        try {
            BillDAO.updateTotalPrice(billDTO.getBill_id(), billDTO.getTotal());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return billDTO.getTotal();
    }

    public float calAndSetTotalPricePayBill(){

        float discount = billDTO.getDiscount();
        lbTotalPricePay.setText(String.valueOf(billDTO.getTotal()*(100-discount)/100));

        return billDTO.getTotal();
    }

    public void onLookUpButtonClick(ActionEvent event) throws SQLException, ClassNotFoundException {
        String name = tfCustomer.getText();
        String phone = tfPhoneCustomer.getText();
        if(phone.trim().length() != 0){
            customerDTO = CustomerDAO.findByPhone(phone);
            if(customerDTO != null){
                tfCustomer.setText(customerDTO.getFullname());
            }else{
                Toast.showToast(Toast.TOAST_WARN, lbIdBill, "Customer is not available in system");
            }
        }
    }

    public void onPayButtonClick(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        BillDAO.updateStateBill(billDTO.getBill_id(), 1);
        TableDAO.setBillId(tableDTO.getTable_id(), 0);
        StageUtils.switchTo(Main.class.getResource("UserMainMenu.fxml"), event, StageStyle.UNDECORATED);

    }

    public void onCancelButtonClick(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        TableDAO.setBillId(tableDTO.getTable_id(),0);
        BillDetailDAO.deleteByBillId(billDTO.getBill_id());
        BillDAO.delete(billDTO.getBill_id());
        StageUtils.switchTo(Main.class.getResource("UserMainMenu.fxml"), event, StageStyle.UNDECORATED);
    }

    public void onCbPromotionClick(ActionEvent event) {
        PromotionDTO promotionDTO = cbPromotion.getSelectionModel().getSelectedItem();
        lbDiscount.setText(String.valueOf(promotionDTO.getDiscount()));
        billDTO.setDiscount(promotionDTO.getDiscount());
        billDTO.setPromotion(promotionDTO.getPromotionID());
        BillDAO.updatePromotionDiscount(billDTO.getBill_id(), billDTO.getDiscount(), promotionDTO.getPromotionID());
        calAndSetTotalPricePayBill();
    }
}

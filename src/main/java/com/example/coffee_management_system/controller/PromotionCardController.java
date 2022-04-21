package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DTO.PromotionDTO;
import com.example.coffee_management_system.DTO.UserDTO;
import com.example.coffee_management_system.ultil.UDHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PromotionCardController {
    @FXML
    private Button btnDelete;

    @FXML
    private Button btnDetail;

    @FXML
    private Label lbName;

    @FXML
    private Label lbDiscount;

    private PromotionDTO promotionDTO;

    private UDHandler callback;


    void setData(PromotionDTO promotion, UDHandler callback){
        this.promotionDTO = promotion;

        lbName.setText(promotionDTO.getName());
        lbDiscount.setText("Giảm giá " + String.valueOf(promotionDTO.getDiscount()) + "% trên tổng hóa đơn");
        this.callback = callback;
        this.promotionDTO = promotion;
    }

    @FXML
    void onDeleteButtonClick(ActionEvent event) {
        callback.delete(promotionDTO, event);
    }

    @FXML
    void onDetailButtonClick(ActionEvent event) {
        callback.update(promotionDTO, event);
    }
}

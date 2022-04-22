package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DTO.PromotionDTO;
import com.example.coffee_management_system.DTO.UserDTO;
import com.example.coffee_management_system.ultil.Toast;
import com.example.coffee_management_system.ultil.UDHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PromotionEditorController implements Initializable {
    @FXML
    private TextField txtPromotionName;

    @FXML
    private DatePicker txtPromotionStartDate;

    @FXML
    private DatePicker txtPromotionEndDate;

    @FXML
    private TextArea txtPromotionDescription;

    @FXML
    private TextField txtPromotionDiscount;

    @FXML
    private Button btnAddNew;

    @FXML
    private Button btnCancel;

    private PromotionDTO m_promotion;
    UDHandler callback;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void onEditButtonClick(ActionEvent event) {
        if (!validate()) {
            Toast.showToast(Toast.TOAST_SUCCESS, txtPromotionEndDate, "Vui lòng điền đầy đủ các trường!");
            return;
        }
        PromotionDTO promotionDTO = new PromotionDTO();
        if(m_promotion!=null) {
            promotionDTO.setPromotionID(m_promotion.getPromotionID());
            promotionDTO.setStatus(m_promotion.getStatus());
        }
        promotionDTO.setName(txtPromotionName.getText());
        promotionDTO.setStartDate(txtPromotionStartDate.getValue());
        promotionDTO.setEndDate(txtPromotionEndDate.getValue());
        promotionDTO.setDescription(txtPromotionDescription.getText());
        promotionDTO.setDiscount(Float.parseFloat(txtPromotionDiscount.getText()));

        callback.update(promotionDTO, event);
    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        callback.delete(null, event);
    }

    public void setData(PromotionDTO promotionDTO, UDHandler callback) {
        if (promotionDTO != null) {
            m_promotion = promotionDTO;

            txtPromotionName.setText(m_promotion.getName());
            txtPromotionDescription.setText(m_promotion.getDescription());
            txtPromotionDiscount.setText(String.valueOf(m_promotion.getDiscount()));
            txtPromotionStartDate.setValue(m_promotion.getstartDateAsLocalDate());
            txtPromotionEndDate.setValue(m_promotion.getendDateAsLocalDate());

        }
        this.callback = callback;
    }

    void setUpdateButtonLabel(String label){
        btnAddNew.setText(label);
    }

    boolean validate() {
        if (txtPromotionEndDate.getValue() == null || txtPromotionStartDate.getValue() == null) {
            return false;
        }
        return !txtPromotionName.getText().isBlank()
                && !txtPromotionDescription.getText().isBlank()
                && !txtPromotionDiscount.getText().isBlank();
    }
}

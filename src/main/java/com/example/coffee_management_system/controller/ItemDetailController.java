package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DAO.CategoryDAO;
import com.example.coffee_management_system.DTO.CategoryDTO;
import com.example.coffee_management_system.DTO.ItemDTO;
import com.example.coffee_management_system.ultil.UDHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ItemDetailController implements Initializable {
    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXComboBox<CategoryDTO> cbCategory;

    @FXML
    private ImageView ivItem;

    @FXML
    private JFXTextArea taDescription;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private JFXButton btnChangeImg;

    UDHandler callback;

    String img_url = null;

    private ItemDTO m_item = null;
    ObservableList<CategoryDTO> options;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        options = FXCollections.observableArrayList();
        try {
            List<CategoryDTO> categories;
            categories = CategoryDAO.getAll();
            options.addAll(categories);
            cbCategory.setItems(options);
            m_item = new ItemDTO();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void onCancelButton(ActionEvent event) {
        callback.delete(null, event);
    }

    @FXML
    void onUpdateButton(ActionEvent event) {

        if (!validate()) {
            return;
        }
        CategoryDTO categoryDTO = cbCategory.getSelectionModel().getSelectedItem();
        ItemDTO item = new ItemDTO();
        if (m_item != null)
            item.setItem_id(m_item.getItem_id());
        item.setName(txtName.getText());
        item.setPrice(Double.parseDouble(txtPrice.getText()));
        item.setCategory(categoryDTO.getId());
        item.setDescription(taDescription.getText());
        item.setImg_url(m_item.getImg_url());

        callback.update(item, event);

    }

    @FXML
    void onChangeImgButtonClick(ActionEvent event){
        FileChooser fileChooser  = new FileChooser();
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if(selectedFile !=null){
            m_item.setImg_url(selectedFile.getPath());
            ivItem.setImage(new Image(selectedFile.toURI().toString()));
        }
    }

    boolean validate() {
        return !txtName.getText().isBlank() && !txtPrice.getText().isBlank();
    }

    public void setData(ItemDTO itemDTO, UDHandler callback) {
        if (itemDTO != null) {
            m_item = itemDTO;

            txtName.setText(m_item.getName());
            taDescription.setText(m_item.getDescription());
            txtPrice.setText(String.valueOf(m_item.getPrice()));
            for (CategoryDTO categoryDTO : options) {
                if (categoryDTO.getId() == m_item.getCategory())
                    cbCategory.getSelectionModel().select(categoryDTO);
            }
            if (m_item.getImg()!=null){
                try {
                    InputStream inputStream = m_item.getImg().getBinaryStream();
                    ivItem.setImage(new Image(inputStream));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        this.callback = callback;
    }

    void setUpdateButtonLabel(String label) {
        btnUpdate.setText(label);
    }

}

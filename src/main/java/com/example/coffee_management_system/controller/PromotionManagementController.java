package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DAO.PromotionDAO;
import com.example.coffee_management_system.DTO.PromotionDTO;
import com.example.coffee_management_system.Main;
import com.example.coffee_management_system.ultil.Toast;
import com.example.coffee_management_system.ultil.UDHandler;
import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PromotionManagementController implements Initializable {

    @FXML
    private VBox layout;

    @FXML
    private FlowPane flow;

    @FXML
    private ScrollPane scroll;

    @FXML
    private VBox editor_layout;

    @FXML
    private TextField txtSearch;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnAddNew;

    private List<PromotionDTO> promotionDTOList;
    UDHandler promotionHandle;
    UDHandler promotionEditorHandler;
    UDHandler newPromotionHandler;

    String email;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

        newPromotionHandler = new UDHandler() {
            @Override
            public void update(Object obj, ActionEvent event) {
                PromotionDTO promotionDTO  = (PromotionDTO) obj;
                try {
                    PromotionDAO.insert(promotionDTO);

                    reload();
                    Toast.showToast(Toast.TOAST_SUCCESS, btnSearch,"???? th??m ch????ng tr??nh " + promotionDTO.getName() + " th??nh c??ng");
                    editor_layout.getChildren().clear();
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                    Toast.showToast(Toast.TOAST_ERROR, btnSearch, "Kh??ng th??? th??m ch????ng tr??nh" + promotionDTO.getName() + " v??o l??c n??y!");
                }
            }

            @Override
            public void delete(Object obj, ActionEvent event) {
                editor_layout.getChildren().clear();
            }

            @Override
            public void addToBill(Object obj, ActionEvent event) {

            }
        };

        promotionEditorHandler = new UDHandler() {
            @Override
            public void update(Object obj, ActionEvent event) {
                PromotionDTO promotionDTO = (PromotionDTO) obj;
                try {
                    PromotionDAO.update(promotionDTO);
                    Toast.showToast(Toast.TOAST_SUCCESS, btnSearch, "C???p nh???t th??nh c??ng");
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                    Toast.showToast(Toast.TOAST_ERROR, btnSearch, "Kh??ng th??? c???p nh???t v??o l??c n??y");
                }
            }

            @Override
            public void delete(Object obj, ActionEvent event) {
                editor_layout.getChildren().clear();
            }

            @Override
            public void addToBill(Object obj, ActionEvent event) {

            }
        };

        promotionHandle = new UDHandler() {
            @Override
            public void update(Object obj, ActionEvent event) {
                PromotionDTO promotionDTO = (PromotionDTO) obj;

                FXMLLoader fxmlLoader = addItem(Main.class.getResource("promotion_editor.fxml"));
                PromotionEditorController promotionEditorController = fxmlLoader.getController();
                promotionEditorController.setData(promotionDTO, promotionEditorHandler);
            }

            @Override
            public void delete(Object obj, ActionEvent event) {
                PromotionDTO promotionDTO = (PromotionDTO) obj;

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("X??c nh???n");
                alert.setHeaderText("X??c nh???n x??a " + promotionDTO.getName() + "?");

                if (alert.showAndWait().get() == ButtonType.OK) {
                    try {
                        PromotionDAO.delete(promotionDTO);

                        reload();
                        Toast.showToast(Toast.TOAST_SUCCESS, btnSearch, "???? xo?? " + promotionDTO.getName() + " kh???i danh s??ch nh??n vi??n");
                    } catch (SQLException | ClassNotFoundException e) {
                        Toast.showToast(Toast.TOAST_ERROR, btnSearch, "Kh??ng th??? xo?? " + promotionDTO.getName() + " kh???i danh s??ch nh??n vi??n v??o l??c n??y");
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void addToBill(Object obj, ActionEvent event) {

            }
        };

        reload();
    }

    private void setData2Grid(List<PromotionDTO> list) {
        try {
            flow.getChildren().clear();
            for (PromotionDTO promotionDTO : list) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("promotion_card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                PromotionCardController promotionCardController = fxmlLoader.getController();
                promotionCardController.setData(promotionDTO, promotionHandle);

                flow.getChildren().add(anchorPane);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void pullData() {
        try {
            promotionDTOList = PromotionDAO.getAll();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void reload() {
        pullData();
        setData2Grid(promotionDTOList);
    }

    FXMLLoader addItem(URL url) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        AnchorPane anchorPane = null;
        try {
            anchorPane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor_layout.getChildren().clear();
        editor_layout.getChildren().add(anchorPane);
        return fxmlLoader;
    }

    @FXML
    void onAddButtonClick()  {
        FXMLLoader fxmlLoader = addItem(Main.class.getResource("promotion_editor.fxml"));
        PromotionEditorController promotionEditorController = fxmlLoader.getController();
        promotionEditorController.setData(null, newPromotionHandler);
        promotionEditorController.setUpdateButtonLabel("Th??m");
    }

    @FXML
    void onSearchTextFieldKeyPress(KeyEvent event) {
        if( event.getCode() == KeyCode.ENTER ) {
            onSearchButtonClick();
        }
    }

    @FXML
    void onSearchButtonClick() {
        List<PromotionDTO> copy = new ArrayList<>(promotionDTOList);
        String key = txtSearch.getText();
        copy.removeIf(item -> !key.isBlank() && !item.getName().toLowerCase().contains(key.toLowerCase()));

        setData2Grid(copy);
    }
}

package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DAO.AccountDAO;
import com.example.coffee_management_system.DAO.CategoryDAO;
import com.example.coffee_management_system.DAO.ItemDAO;
import com.example.coffee_management_system.DAO.UserDAO;
import com.example.coffee_management_system.DTO.CategoryDTO;
import com.example.coffee_management_system.DTO.ItemDTO;
import com.example.coffee_management_system.DTO.UserDTO;
import com.example.coffee_management_system.Main;
import com.example.coffee_management_system.ultil.Toast;
import com.example.coffee_management_system.ultil.UDCategoryHandler;
import com.example.coffee_management_system.ultil.UDHandler;
import com.example.coffee_management_system.ultil.UDUserHandler;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserManagementController implements Initializable {

    @FXML
    private VBox layout;

    @FXML
    private FlowPane flow;

    @FXML
    private ScrollPane scroll;

    @FXML
    private TextField txtSearch;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnAddNew;

    private List<UserDTO> users;
    private UDHandler userHandle;

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
        
        userHandle = new UDHandler() {
            @Override
            public void update(Object obj, ActionEvent event) {
                
            }

            @Override
            public void delete(Object obj, ActionEvent event) {
                UserDTO userDTO = (UserDTO) obj;

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Xác nhận");
                alert.setHeaderText("Xác nhận xóa " + userDTO.getFullname() + "?");

                if (alert.showAndWait().get() == ButtonType.OK) {
                    try {
                        AccountDAO.delete(userDTO);
                        users.remove(userDTO);
                        setData2Grid(users);
                        Toast.showToast(Toast.TOAST_SUCCESS, btnSearch, "Đã xoá " + userDTO.getFullname() + " khỏi danh sách nhân viên");
                    } catch (SQLException | ClassNotFoundException e) {
                        Toast.showToast(Toast.TOAST_ERROR, btnSearch, "Không thể xoá " + userDTO.getFullname() + " khỏi danh sách nhân viên vào lúc này");
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void addToBill(Object obj, ActionEvent event) {

            }
        };

        pullData();
        setData2Grid(users);
    }

    private void setData2Grid(List<UserDTO> list) {
        try {
            flow.getChildren().clear();
            for (UserDTO userDTO : list) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Main.class.getResource("user_card.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                UserCardController userCardController = fxmlLoader.getController();
                userCardController.setData(userDTO, userHandle);

                flow.getChildren().add(anchorPane);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void pullData() {
        try {
            users = UserDAO.getAll();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    void reload() {
//        lst_layout.getChildren().clear();
//        pullData();
//        setData();
//    }

    @FXML
    void onAddButtonClick()  {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("add_user.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onSearchButtonClick() {

    }
}

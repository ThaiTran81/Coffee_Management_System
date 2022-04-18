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
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
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
    private VBox editor_layout;

    @FXML
    private TextField txtSearch;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnAddNew;

    private List<UserDTO> users;
    UDHandler userHandle;
    UDHandler userEditorHandler;
    UDHandler newUserHandler;

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

        newUserHandler = new UDHandler() {
            @Override
            public void update(Object obj, ActionEvent event) {
                UserDTO userDTO  = (UserDTO) obj;
                try {
                    if (UserDAO.findUserByUsername(userDTO.getEmail()) != null) {
                        Toast.showToast(Toast.TOAST_ERROR, btnSearch, "Nhân viên " + userDTO.getFullname() + " đã tồn tại!");
                        return;
                    }
                    UserDAO.insert(userDTO);

                    String password = BCrypt.hashpw(userDTO.createPassword(), BCrypt.gensalt());
                    AccountDAO.insert(userDTO.getEmail(), password, userDTO.getType());
                    reload();
                    Toast.showToast(Toast.TOAST_SUCCESS, btnSearch,"Đã thêm "+userDTO.getFullname()+" thành công");
                    editor_layout.getChildren().clear();
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                    Toast.showToast(Toast.TOAST_ERROR, btnSearch, "Không thể thêm nhân viên" + userDTO.getFullname() + " vào lúc này!");
                }
            }

            @Override
            public void delete(Object obj, ActionEvent event) {
                editor_layout.getChildren().clear();
            }
        };

        userEditorHandler = new UDHandler() {
            @Override
            public void update(Object obj, ActionEvent event) {
                UserDTO userDTO = (UserDTO) obj;
                try {
                    UserDAO.update(userDTO);
                    AccountDAO.updateByEmail(email, userDTO.getEmail());
                    AccountDAO.updateType(userDTO.getEmail(), userDTO.getType());
                    Toast.showToast(Toast.TOAST_SUCCESS, btnSearch, "Cập nhật thành công");
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                    Toast.showToast(Toast.TOAST_ERROR, btnSearch, "Không thể cập nhật vào lúc này");
                }
            }

            @Override
            public void delete(Object obj, ActionEvent event) {
                editor_layout.getChildren().clear();
            }
        };
        
        userHandle = new UDHandler() {
            @Override
            public void update(Object obj, ActionEvent event) {
                UserDTO userDTO = (UserDTO) obj;
                email = userDTO.getEmail();
                FXMLLoader fxmlLoader = addItem(Main.class.getResource("user_editor.fxml"));
                UserEditorController userEditorController = fxmlLoader.getController();
                userEditorController.setData(userDTO, userEditorHandler);
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

                        reload();
                        Toast.showToast(Toast.TOAST_SUCCESS, btnSearch, "Đã xoá " + userDTO.getFullname() + " khỏi danh sách nhân viên");
                    } catch (SQLException | ClassNotFoundException e) {
                        Toast.showToast(Toast.TOAST_ERROR, btnSearch, "Không thể xoá " + userDTO.getFullname() + " khỏi danh sách nhân viên vào lúc này");
                        e.printStackTrace();
                    }
                }
            }
        };

        reload();
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

    void reload() {
        pullData();
        setData2Grid(users);
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
        FXMLLoader fxmlLoader = addItem(Main.class.getResource("user_editor.fxml"));
        UserEditorController userEditorController = fxmlLoader.getController();
        userEditorController.setData(null, newUserHandler);
        userEditorController.setUpdateButtonLabel("Thêm");
    }

    @FXML
    void onSearchButtonClick() {

    }
}

package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.Main;
import com.example.coffee_management_system.ultil.StageUtils;
import com.example.coffee_management_system.values.Role;
import com.example.coffee_management_system.values.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserMainMenuController implements Initializable {
    @FXML
    private Circle myCircle;
    @FXML
    private Button btnClose;
    @FXML
    private Label lbName;
    @FXML
    private Label lbRole;

    @FXML
    private StackPane parent;

    @FXML
    private Button btnCategoryManagement;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StageUtils.makeStageDrageable(parent);
        myCircle.setStroke(Color.GRAY);
        Image im = null;
        try {
            im = new Image(Main.class.getResource("asset/man.png").toURI().toString(), false);
            myCircle.setFill(new ImagePattern(im));
//            myCircle.setEfafect(new DropShadow(+25d, 0d, +2d, Color.DARKGRAY));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        lbName.setText(User.userInfo.getFullname());
        lbRole.setText(Role.role[User.role]);

    }


    FXMLLoader switchTo(URL url, ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Stage prevStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        prevStage.hide();

        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());

        stage.hide();
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();

        return fxmlLoader;
    }

    @FXML
    public void onInfomationAdminClick(ActionEvent event) {
        FXMLLoader fxmlLoader;
        try {
            fxmlLoader = switchTo(Main.class.getResource("management_menu.fxml"), event);
            ManagmentMenuController managmentMenuController = fxmlLoader.getController();
            managmentMenuController.setContentArea(Main.class.getResource("user_information.fxml"));

            managmentMenuController.setBackSatge(Main.class.getResource("UserMainMenu.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onTableManagementButtonClick(ActionEvent event) {
        FXMLLoader fxmlLoader;
        try {
            fxmlLoader = switchTo(Main.class.getResource("management_menu.fxml"), event);
            ManagmentMenuController managmentMenuController = fxmlLoader.getController();
            managmentMenuController.setContentArea(Main.class.getResource("user_table_management.fxml"));
            managmentMenuController.setBackSatge(Main.class.getResource("UserMainMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onCloseButtonClick(ActionEvent event) {
        try {
            StageUtils.switchTo(Main.class.getResource("login.fxml"), event, StageStyle.UNDECORATED);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

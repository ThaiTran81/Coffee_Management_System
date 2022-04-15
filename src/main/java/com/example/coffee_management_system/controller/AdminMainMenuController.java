package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.Main;
import com.example.coffee_management_system.ultil.StageUtils;
import com.example.coffee_management_system.values.Role;
import com.example.coffee_management_system.values.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.controlsfx.tools.Utils;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminMainMenuController implements Initializable {
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

    @FXML
    public void onCloseButtonClick(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}

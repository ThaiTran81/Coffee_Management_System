package com.example.coffee_management_system.ultil;

import com.example.coffee_management_system.Main;
import com.example.coffee_management_system.controller.ManagmentMenuController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class StageUtils {
    public static void makeStageDrageable(Parent parent){
        final Double[] xOffset = {0.0};
        final Double[] yOffset = {0.0};
        parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset[0] = event.getSceneX();
                yOffset[0] = event.getSceneY();
//                lbNotification.setText("x:"+xOffset+"-"+"y:"+yOffset);
            }
        });

        parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setX(event.getScreenX()- xOffset[0]);
                stage.setY(event.getScreenY()- yOffset[0]);

            }
        });
    }

    public static FXMLLoader switchTo(URL url, ActionEvent event, StageStyle stageStyle) throws IOException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

            }
        });
        Stage prevStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        prevStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(url);
//        Stage stage = new Stage();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

//        prevStage.initStyle(stageStyle);
        prevStage.sizeToScene();
        prevStage.setScene(scene);
        prevStage.show();

        return fxmlLoader;
    }

    public static FXMLLoader switchToUserTableManagement(ActionEvent event, StageStyle stageStyle) throws IOException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {}
        });
        Stage prevStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        prevStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("management_menu.fxml"));

//        Stage stage = new Stage();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
            ManagmentMenuController managmentMenuController = fxmlLoader.getController();
            managmentMenuController.setContentArea(Main.class.getResource("user_table_management.fxml"));
            managmentMenuController.setBackSatge(Main.class.getResource("UserMainMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        prevStage.initStyle(stageStyle);
        prevStage.sizeToScene();
        prevStage.setScene(scene);
        prevStage.show();

        return fxmlLoader;
    }
}

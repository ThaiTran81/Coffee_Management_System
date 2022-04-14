package com.example.coffee_management_system.ultil;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
}

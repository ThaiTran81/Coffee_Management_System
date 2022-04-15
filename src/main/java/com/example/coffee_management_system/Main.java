package com.example.coffee_management_system;

import com.example.coffee_management_system.DAO.AccountDAO;
import com.example.coffee_management_system.controller.ManagmentMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        System.setProperty("prism.lcdtext", "false");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));


//      Check whether admin account was registered or not
        try {
            if(!AccountDAO.checkAdminExist()){
                fxmlLoader = new FXMLLoader(Main.class.getResource("register.fxml"));
            };
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(fxmlLoader.load());
//        ManagmentMenuController managmentController = fxmlLoader.getController();
//        managmentController.addItemMenu("test","apple_juice.png", null, null, null);
//
//        managmentController.setAddButton("Them gi ta","cross.png", null, null, null);
        stage.initStyle(StageStyle.UNDECORATED);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
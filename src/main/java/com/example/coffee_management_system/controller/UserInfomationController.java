package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.Main;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class UserInfomationController implements Initializable {
        public ImageView avaIv;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                Image img = new Image(String.valueOf(Main.class.getResource("asset/profile.png")));
                avaIv.setImage(img);
//                System.out.println(Main.class.getResource("asset/profile.png"));
        }
}

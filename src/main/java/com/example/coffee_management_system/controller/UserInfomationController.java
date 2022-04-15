package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DTO.UserDTO;
import com.example.coffee_management_system.Main;
import com.example.coffee_management_system.values.User;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class UserInfomationController implements Initializable {
        public ImageView avaIv;
        public TextField phoneTf;
        public TextField emailTf;
        public TextField addrTf;
        public TextField dataTf;
        public TextField nameTf;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                Image img = new Image(String.valueOf(Main.class.getResource("asset/profile.png")));
                avaIv.setImage(img);
                initUserInfo(User.userInfo);
//                System.out.println(Main.class.getResource("asset/profile.png"));
        }

        private void initUserInfo(UserDTO User){
                nameTf.setText(User.getFullname());
                dataTf.setText(String.valueOf(User.getDob()));
                addrTf.setText(User.getAddress());
                emailTf.setText(User.getEmail());
                phoneTf.setText(User.getPhone());
        }
}

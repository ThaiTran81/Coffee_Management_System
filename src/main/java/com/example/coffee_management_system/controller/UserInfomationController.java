package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DTO.UserDTO;
import com.example.coffee_management_system.Main;
import com.example.coffee_management_system.values.User;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class UserInfomationController implements Initializable {

        public ImageView avaIv;
        public TextField phoneTf;
        public TextField emailTf;
        public TextField addrTf;
        public TextField nameTf;
        public JFXButton changeInfoBtn;
        public DatePicker datePk;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                Image img = new Image(String.valueOf(Main.class.getResource("asset/profile.png")));
                avaIv.setImage(img);
                initUserInfo(User.userInfo);
//                System.out.println(Main.class.getResource("asset/profile.png"));
        }

        private void initUserInfo(UserDTO User){
                nameTf.setText(User.getFullname());

                System.out.println(User.getDob().getDate());
                addrTf.setText(User.getAddress());
                emailTf.setText(User.getEmail());
                phoneTf.setText(User.getPhone());
        }


        public void changeInfoClick(ActionEvent event) {

        }

        public void changePasswordClick(ActionEvent event) {
        }
}

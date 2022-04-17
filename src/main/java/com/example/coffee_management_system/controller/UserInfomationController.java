package com.example.coffee_management_system.controller;

import com.example.coffee_management_system.DAO.AccountDAO;
import com.example.coffee_management_system.DAO.UserDAO;
import com.example.coffee_management_system.DTO.UserDTO;
import com.example.coffee_management_system.Main;
import com.example.coffee_management_system.ultil.Toast;
import com.example.coffee_management_system.values.User;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import org.w3c.dom.CDATASection;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.SimpleFormatter;

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
        }

        private LocalDate LOCAL_DATE(String dataString){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(dataString, formatter);
                return localDate;
        }

        private Date DATE(LocalDate ld){
                Date date = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
                return date;
        }
        private void initUserInfo(UserDTO User){
                nameTf.setText(User.getFullname());
//                System.out.println(User.getDob().toString());
                datePk.setValue(LOCAL_DATE(User.getDob().toString()));
                addrTf.setText(User.getAddress());
                emailTf.setText(User.getEmail());
                phoneTf.setText(User.getPhone());
        }

        private boolean authenticate(){
                if(nameTf.getText().trim() == null
                        || datePk.getValue() == null
                        || addrTf.getText().trim() == null
                        || emailTf.getText().trim() == null
                        || phoneTf.getText().trim() == null){
                        return false;
                }
                return true;
        }

        public void changeInfoClick(ActionEvent event) throws SQLException, ClassNotFoundException {
                if(authenticate() == false)
                        return;
                java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(datePk.getValue());
                UserDTO userDTO = new UserDTO(nameTf.getText()
                        ,emailTf.getText()
                        , addrTf.getText()
                        , phoneTf.getText()
                        , gettedDatePickerDate);
                userDTO.setUserID(User.userInfo.getUserID());
                userDTO.setType(User.userInfo.getType());
                if(!User.userInfo.getEmail().equalsIgnoreCase(userDTO.getEmail())){
                    AccountDAO.updateByEmail(User.userInfo.getEmail(), userDTO.getEmail());
                }
                User.userInfo = userDTO;
                UserDAO.update(User.userInfo);
                Toast.showToast(Toast.TOAST_SUCCESS, phoneTf, "Đã cập nhật thông tin thành công");

        }

        public void changePasswordClick(ActionEvent event) throws IOException {

                Stage changePassword = new Stage();
                Parent root = FXMLLoader.load(Main.class.getResource("change_password.fxml"));
                Scene scene = new Scene(root);
                changePassword.initStyle(StageStyle.UNDECORATED);
                changePassword.setScene(scene);
                changePassword.showAndWait();
                changePassword.setResizable(false);

        }
}

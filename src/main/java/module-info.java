module com.example.coffee_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.jfoenix;
    requires java.sql;

    opens com.example.coffee_management_system to javafx.fxml;
    exports com.example.coffee_management_system;
    exports com.example.coffee_management_system.controller;
    opens com.example.coffee_management_system.controller to javafx.fxml;
}
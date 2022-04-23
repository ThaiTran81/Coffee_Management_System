package com.example.coffee_management_system.ultil;

import javafx.event.ActionEvent;
import javafx.event.Event;

public interface UDBillHandler {
    public void delete(Object obj, Object obj1, Event event);
    public void update(Object obj, Object obj1, int oldQuantityItem, int newQuantityItem);
}

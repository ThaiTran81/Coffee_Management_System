package com.example.coffee_management_system.ultil;

import java.net.URL;
import java.sql.SQLException;

public interface ComponentMenuListener {
    void onClickListener(URL url, Object obj) throws SQLException, ClassNotFoundException;
}

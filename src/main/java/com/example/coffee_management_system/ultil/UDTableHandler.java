package com.example.coffee_management_system.ultil;

import com.example.coffee_management_system.DTO.CategoryDTO;
import com.example.coffee_management_system.DTO.TableDTO;

public interface UDTableHandler {
    void update(TableDTO tableDTO);
    void delete(TableDTO tableDTO);
}

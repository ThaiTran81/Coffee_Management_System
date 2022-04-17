package com.example.coffee_management_system.ultil;

import com.example.coffee_management_system.DTO.UserDTO;

public interface UDUserHandler {
    void update(UserDTO userDTO);
    void delete(UserDTO userDTO);
}

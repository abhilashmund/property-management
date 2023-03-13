package com.mycompany.propertymanagement.service;

import com.mycompany.propertymanagement.dto.UserDTO;

public interface UserService {
    UserDTO register(UserDTO UserDTO);
    UserDTO login(String email, String password);
}

package com.mycompany.propertymanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.propertymanagement.converter.UserConverter;
import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.repository.UserRepository;
import com.mycompany.propertymanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO UserDTO) {
        UserEntity userEntity = userConverter.convertDTOtoEntity(UserDTO);
        userEntity = userRepository.save(userEntity);
        UserDTO = userConverter.convertEntitytoDTO(userEntity);
        return UserDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        return null;
    }
    
}
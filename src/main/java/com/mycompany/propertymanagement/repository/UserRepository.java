package com.mycompany.propertymanagement.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.propertymanagement.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long>{
    
    Optional<UserEntity> findByOwnerEmailAndPassword(String email, String password);
}

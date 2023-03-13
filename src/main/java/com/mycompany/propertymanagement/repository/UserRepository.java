package com.mycompany.propertymanagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.propertymanagement.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long>{
    
}

package com.mycompany.propertymanagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.propertymanagement.entity.PropertyEntity;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long>{
    
}

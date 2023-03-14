package com.mycompany.propertymanagement.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.propertymanagement.entity.PropertyEntity;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long>{
    List<PropertyEntity> findAllByUserEntityId(Long userId);
}

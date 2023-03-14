package com.mycompany.propertymanagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.propertymanagement.entity.AddressEntity;

public interface AddressRepositoty extends CrudRepository<AddressEntity, Long>{
    
}

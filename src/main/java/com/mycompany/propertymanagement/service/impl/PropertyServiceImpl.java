package com.mycompany.propertymanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.exception.BusinessException;
import com.mycompany.propertymanagement.exception.ErrorModel;
import com.mycompany.propertymanagement.repository.PropertyRepository;
import com.mycompany.propertymanagement.repository.UserRepository;
import com.mycompany.propertymanagement.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService{

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyConverter propertyConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {

        Optional<UserEntity> optionalUserEntity = userRepository.findById(propertyDTO.getUserId());
        if(optionalUserEntity.isPresent()){
            PropertyEntity propertyEntity = propertyConverter.convertDTOtoEntity(propertyDTO);
            propertyEntity.setUserEntity(optionalUserEntity.get());
            //This expects Repository type but we are converting DTO to Repository ( This is adaptor design pattern)
            propertyEntity = propertyRepository.save(propertyEntity);
            propertyDTO = propertyConverter.convertEntityToDTO(propertyEntity);
        }else{
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("USER_ID_DOES_NOT_EXIST");
            errorModel.setMessage("User doesn't exist.");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyEntity> listOfProps = (List<PropertyEntity>)propertyRepository.findAll();
        List<PropertyDTO> propList = new ArrayList<>();
        for (PropertyEntity propertyEntity : listOfProps) {
           PropertyDTO propertyDTO = propertyConverter.convertEntityToDTO(propertyEntity);
           propList.add(propertyDTO);
        }
        return propList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        PropertyDTO propDTO = null;
        Optional<PropertyEntity> optionalPropEntity = propertyRepository.findById(propertyId);
        if(optionalPropEntity.isPresent()){
            PropertyEntity pe = optionalPropEntity.get(); //Data from DB
            pe.setTitle(propertyDTO.getTitle());
            pe.setDescription(propertyDTO.getDescription());
            pe.setPrice(propertyDTO.getPrice());
            pe.setAddress(propertyDTO.getAddress());
            propDTO = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe); //Save updated data to DB
        }
        return propDTO;
    }

    @Override
    public PropertyDTO deleteProperty(Long propertyId) {
        Optional<PropertyEntity> optionalPropertyEntity = propertyRepository.findById(propertyId);
        PropertyDTO propertyDTO = null;
        if(optionalPropertyEntity.isPresent()){
            propertyRepository.deleteById(propertyId);
            PropertyEntity pe = optionalPropertyEntity.get();
            propertyDTO = propertyConverter.convertEntityToDTO(pe);
        }
        return propertyDTO;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        PropertyDTO propDTO = null;
        Optional<PropertyEntity> optionalPropEntity = propertyRepository.findById(propertyId);
        if(optionalPropEntity.isPresent()){
            PropertyEntity pe = optionalPropEntity.get(); //Data from DB
            pe.setDescription(propertyDTO.getDescription());
            propDTO = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe); //Save updated data to DB
        }
        return propDTO;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
        PropertyDTO propDTO = null;
        Optional<PropertyEntity> optionalPropEntity = propertyRepository.findById(propertyId);
        if(optionalPropEntity.isPresent()){
            PropertyEntity pe = optionalPropEntity.get(); //Data from DB
            pe.setPrice(propertyDTO.getPrice());
            propDTO = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe); //Save updated data to DB
        }
        return propDTO;
    }

    @Override
    public List<PropertyDTO> getAllPropertiesForUser(Long userId) {
        List<PropertyEntity> listOfProps = propertyRepository.findAllByUserEntityId(userId);
        List<PropertyDTO> propList = new ArrayList<>();
        for (PropertyEntity propertyEntity : listOfProps) {
           PropertyDTO propertyDTO = propertyConverter.convertEntityToDTO(propertyEntity);
           propList.add(propertyDTO);
        }
        return propList;
    }
    
}

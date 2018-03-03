package com.example.service;

import com.example.repository.ApplicationPropertyDao;
import com.example.domain.ApplicationProperty;
import com.example.service.dto.ApplicationPropertyDTO;
import com.example.service.mapper.ApplicationPropertyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amarendra on 24/3/17.
 */
@Service
public class ApplicationPropertyService {

    private ApplicationPropertyDao propertyDao;
    private ApplicationPropertyMapper propertyMapper;

    public ApplicationPropertyService(ApplicationPropertyDao propertyDao, ApplicationPropertyMapper propertyMapper) {
        this.propertyDao = propertyDao;
        this.propertyMapper = propertyMapper;
    }

    /**
     *
     * @param applicationPropertyDTO
     */
    public ApplicationPropertyDTO getByProfileAndApplicationName(ApplicationPropertyDTO applicationPropertyDTO){
        ApplicationProperty applicationProperty = propertyMapper.toEntity(applicationPropertyDTO);
        ApplicationProperty byProfileAndApplicationName = propertyDao.getByProfileAndApplicationName(applicationProperty);
        ApplicationPropertyDTO propertyDTO = propertyMapper.toDto(byProfileAndApplicationName);
        return propertyDTO;
    }

    /**
     *
     * @param applicationPropertyDTO
     */
    public ApplicationPropertyDTO save(ApplicationPropertyDTO applicationPropertyDTO){
        ApplicationProperty applicationProperty = propertyMapper.toEntity(applicationPropertyDTO);
        ApplicationProperty byProfileAndApplicationName = propertyDao.save(applicationProperty);
        ApplicationPropertyDTO propertyDTO = propertyMapper.toDto(byProfileAndApplicationName);
        return propertyDTO;
    }

    /***
     *
     * @param applicationName
     */
    public void drop(String applicationName) {
        propertyDao.delete(applicationName);
    }

    /**
     *
     * @param applicationPropertyDTO
     */
    public ApplicationPropertyDTO upsertPropertyByKey(ApplicationPropertyDTO applicationPropertyDTO){
        ApplicationProperty applicationProperty = propertyMapper.toEntity(applicationPropertyDTO);
        ApplicationProperty byProfileAndApplicationName = propertyDao.upsertPropertyByKey(applicationProperty);
        ApplicationPropertyDTO propertyDTO = propertyMapper.toDto(byProfileAndApplicationName);
        return propertyDTO;
    }

    /**
     *
     * @param applicationPropertyDTO
     */
    public ApplicationPropertyDTO addPropertyByKey(ApplicationPropertyDTO applicationPropertyDTO){
        ApplicationProperty applicationProperty = propertyMapper.toEntity(applicationPropertyDTO);
        ApplicationProperty byProfileAndApplicationName = propertyDao.addPropertyByKey(applicationProperty);
        ApplicationPropertyDTO propertyDTO = propertyMapper.toDto(byProfileAndApplicationName);
        return propertyDTO;
    }

    /**
     *
     * @param applicationPropertyDTO
     */
    public boolean deletePropertyByApplication(ApplicationPropertyDTO applicationPropertyDTO){
        ApplicationProperty applicationProperty = propertyMapper.toEntity(applicationPropertyDTO);
        propertyDao.removePropertyByKey(applicationProperty);
        return true;
    }


    public List<ApplicationPropertyDTO> getByProfile(ApplicationPropertyDTO applicationPropertyDTO) {
        ApplicationProperty applicationProperty = propertyMapper.toEntity(applicationPropertyDTO);
        List<ApplicationProperty> byProfile = propertyDao.getByProfile(applicationProperty);
        List<ApplicationPropertyDTO> applicationPropertyDTOS = propertyMapper.toDto(byProfile);
        return applicationPropertyDTOS;
    }

    public boolean deleteByProfileAndApplicationName(ApplicationPropertyDTO applicationPropertyDTO) {
        ApplicationProperty applicationProperty = propertyMapper.toEntity(applicationPropertyDTO);
        return propertyDao.deleteByProfileAndApplicationName(applicationProperty);
    }

    public List<String> getAll() {
        return propertyDao.getAll();
    }

}

package com.example;

import com.example.model.ApplicationProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by amarendra on 24/3/17.
 */
@Service
public class ApplicationPropertyService {

    /**
     *
     */
    @Autowired
    ApplicationPropertyDao propertyDao;


    /**
     *
     * @param applicationProperty
     */
    public ApplicationProperty getByProfileAndApplicationName(ApplicationProperty applicationProperty){
        return propertyDao.getByProfileAndApplicationName(applicationProperty);
    }

    /**
     *
     * @param applicationProperty
     */
    public void save(ApplicationProperty applicationProperty){
        propertyDao.save(applicationProperty);
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
     * @param applicationProperty
     */
    public void upsertPropertyByKey(ApplicationProperty applicationProperty){
        propertyDao.upsertPropertyByKey(applicationProperty);
    }

    /**
     *
     * @param applicationProperty
     */
    public void addPropertyByKey(ApplicationProperty applicationProperty){
        propertyDao.addPropertyByKey(applicationProperty);
    }

    /**
     *
     * @param applicationProperty
     */
    public void removePropertyByKey(ApplicationProperty applicationProperty){
        propertyDao.removePropertyByKey(applicationProperty);
    }


    public List<ApplicationProperty> getByProfile(ApplicationProperty applicationProperty) {
        return propertyDao.getByProfile(applicationProperty);
    }

    public void deleteByProfileAndApplicationName(ApplicationProperty applicationProperty) {
        propertyDao.deleteByProfileAndApplicationName(applicationProperty);
    }

    public List<String> getAll() {
        return propertyDao.getAll();
    }
}

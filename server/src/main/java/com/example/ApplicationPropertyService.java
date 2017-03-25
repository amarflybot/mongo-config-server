package com.example;

import com.example.model.ApplicationProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by amarendra on 24/3/17.
 */
@Service
public class ApplicationPropertyService {

    @Autowired ApplicationPropertyDao propertyDao;

    public ApplicationProperty getForAppNameAnd(){
        return null;
    }

    public void save(ApplicationProperty applicationProperty){
        propertyDao.save(applicationProperty);
    }

    public void drop(String applicationName) {
        propertyDao.delete(applicationName);
    }

    public void updatePropertyByKey(ApplicationProperty applicationProperty){
        propertyDao.updatePropertyByKey(applicationProperty);
    }
}

package com.example;

import com.example.model.ApplicationProperty;
import com.example.model.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.example.model.ApplicationProperty.LABEL;
import static com.example.model.ApplicationProperty.PROFILE;

/**
 * Created by amarendra on 24/3/17.
 */
@Repository
public class ApplicationPropertyDao {

    @Autowired
    MongoTemplate mongoTemplate;


    public void save(ApplicationProperty applicationProperty) {
        Map<String, Object> objectToSave = new Hashtable<>();
        objectToSave.put(LABEL, applicationProperty.getLabel());
        objectToSave.put(PROFILE, applicationProperty.getProfile());
        objectToSave.put(ApplicationProperty.SOURCE, applicationProperty.getSource());
        mongoTemplate.save(objectToSave, applicationProperty.getApplicationName());
    }

    public void delete(String collectionName){
        mongoTemplate.dropCollection(collectionName);
    }

    public void updatePropertyByKey(ApplicationProperty applicationProperty) {
        final Map.Entry<String, Object> objectEntry = applicationProperty.getSource().getAllProperties().entrySet().iterator().next();
        Query query = new Query();
        query.addCriteria(Criteria.where(PROFILE).in(Arrays.asList(applicationProperty.getProfile())));
        query.addCriteria(Criteria.where(LABEL).in(Arrays.asList(applicationProperty.getLabel())));
        List<MongoEnvironmentRepository.MongoPropertySource> propertySources =
                mongoTemplate.find(query, MongoEnvironmentRepository.MongoPropertySource.class, applicationProperty.getApplicationName());

        propertySources.forEach(mongoPropertySource -> {
            mongoPropertySource.getSource().forEach((key, val) -> {
                String keyBeautified = MongoEnvironmentRepository.beautifySource(key);
                if (objectEntry.getKey().equals(keyBeautified)){
                    mongoPropertySource.setSourceByKey(keyBeautified,val);
                }
            });
        });
        //applicationProperty.setSource();

    }

}

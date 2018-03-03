package com.example.repository;

import com.example.domain.ApplicationProperty;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.example.domain.ApplicationProperty.*;

/**
 * Created by amarendra on 24/3/17.
 */
@Repository
public class ApplicationPropertyDao {

    /**
     *
     */
    @Autowired
    MongoTemplate mongoTemplate;


    /**
     *
     * @param applicationProperty
     */
    public ApplicationProperty save(ApplicationProperty applicationProperty) {
        applicationProperty.setLastModified(new Date());
        Map<String, Object> objectToSave = getStringObjectMap(applicationProperty);
        mongoTemplate.save(objectToSave, applicationProperty.getApplicationName());
        return applicationProperty;
    }

    /**
     *
     * @param collectionName
     */
    public void delete(String collectionName){
        mongoTemplate.dropCollection(collectionName);
    }

    /**
     *
     * @param applicationProperty
     */
    public ApplicationProperty upsertPropertyByKey(ApplicationProperty applicationProperty) {
        Query query = new Query();
        query.addCriteria(Criteria.where(PROFILE).in(Arrays.asList(applicationProperty.getProfile())));
        query.addCriteria(Criteria.where(LABEL).in(Arrays.asList(applicationProperty.getLabel())));
        ApplicationProperty latestApplicationProperty = getLatestApplicationProperty(applicationProperty, query);
        applicationProperty.getSource().getAllProperties().forEach((key,val) -> {
            latestApplicationProperty.getSource().getAllProperties().put(key,val);
        });
        this.save(latestApplicationProperty);
        return getLatestApplicationProperty(applicationProperty, query);
    }

    /**
     *
     * @param applicationProperty
     */
    public ApplicationProperty addPropertyByKey(ApplicationProperty applicationProperty) {
        Query query = new Query();
        query.addCriteria(Criteria.where(PROFILE).in(Arrays.asList(applicationProperty.getProfile())));
        query.addCriteria(Criteria.where(LABEL).in(Arrays.asList(applicationProperty.getLabel())));
        ApplicationProperty latestApplicationProperty = getLatestApplicationProperty(applicationProperty, query);

        applicationProperty.getSource().getAllProperties().forEach((key, val) -> {
            latestApplicationProperty.getSource().addProperty(key, val);
        });

        ApplicationProperty property = this.save(latestApplicationProperty);
        return property;

    }

    /**
     *
     * @param applicationProperty
     */
    public void removePropertyByKey(ApplicationProperty applicationProperty) {
        Query query = new Query();
        query.addCriteria(Criteria.where(PROFILE).in(Arrays.asList(applicationProperty.getProfile())));
        query.addCriteria(Criteria.where(LABEL).in(Arrays.asList(applicationProperty.getLabel())));
        ApplicationProperty latestApplicationProperty = getLatestApplicationProperty(applicationProperty, query);

        applicationProperty.getSource().getAllProperties().forEach((key, val) -> {
            latestApplicationProperty.getSource().getAllProperties().remove(key);
        });

        this.save(latestApplicationProperty);
    }

    /**
     *
     * @param applicationProperty
     * @return
     */
    private Map<String, Object> getStringObjectMap(ApplicationProperty applicationProperty) {
        Map<String, Object> objectToSave = new Hashtable<>();
        objectToSave.put(LABEL, applicationProperty.getLabel());
        objectToSave.put(PROFILE, applicationProperty.getProfile());
        objectToSave.put(APPLICATION_NAME, applicationProperty.getApplicationName());
        objectToSave.put(SOURCE, applicationProperty.getSource());
        objectToSave.put(LAST_MODIFIED_DATE, applicationProperty.getLastModified());
        return objectToSave;
    }


    public ApplicationProperty getByProfileAndApplicationName(ApplicationProperty applicationProperty) {
        Query query = new Query();
        query.addCriteria(Criteria.where(PROFILE).in(Arrays.asList(applicationProperty.getProfile())));
        query.addCriteria(Criteria.where(LABEL).in(Arrays.asList(applicationProperty.getLabel())));
        ApplicationProperty latestApplicationProperty = getLatestApplicationProperty(applicationProperty, query);
        return latestApplicationProperty;
    }

    private ApplicationProperty getLatestApplicationProperty(ApplicationProperty applicationProperty, Query query) {
        List<ApplicationProperty> applicationProperties =
                mongoTemplate.find(query, ApplicationProperty.class, applicationProperty.getApplicationName());
        return applicationProperties.get(applicationProperties.size() - 1);
    }

    public List<ApplicationProperty> getByProfile(ApplicationProperty applicationProperty) {

        List<ApplicationProperty> applicationProperties = new ArrayList<>();
        Query query = new Query();
        query.limit(1);
        query.with(new Sort(Sort.Direction.DESC, LAST_MODIFIED_DATE));
        query.addCriteria(Criteria.where(PROFILE).in(Arrays.asList(applicationProperty.getProfile())));
        Set<String> collectionNames = mongoTemplate.getDb().getCollectionNames();
        collectionNames.forEach(collectionName -> {
            ApplicationProperty applicationProperties1 = mongoTemplate.findOne(query, ApplicationProperty.class, collectionName);
            if (applicationProperties1 != null) {
                applicationProperties.add(applicationProperties1);
            }
        });
        return applicationProperties;
    }

    public boolean deleteByProfileAndApplicationName(ApplicationProperty applicationProperty) {
        Query query = new Query();
        query.addCriteria(Criteria.where(PROFILE).in(Arrays.asList(applicationProperty.getProfile())));
        query.addCriteria(Criteria.where(LABEL).in(Arrays.asList(applicationProperty.getLabel())));
        WriteResult remove = mongoTemplate.remove(query, ApplicationProperty.class, applicationProperty.getApplicationName());
        return remove.wasAcknowledged();
    }

    public List<String> getAll() {
        Set<String> collectionNames = mongoTemplate.getDb().getCollectionNames();
        return new ArrayList<>(new TreeSet<>(collectionNames));
    }
}

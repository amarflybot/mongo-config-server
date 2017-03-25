package com.example;

import com.example.model.ApplicationProperty;
import com.example.model.Source;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;

/**
 * Created by amarendra on 24/3/17.
 */
@RunWith(SpringRunner.class)
public class ApplicationPropertyDaoTest {

    @MockBean
    MongoTemplate mongoTemplate;

    ApplicationPropertyDao dao;

    @Test
    public void testSave(){
        doNothing().when(mongoTemplate).save(any(), any());
        dao = new ApplicationPropertyDao();
        ApplicationProperty applicationProperty = new ApplicationProperty();
        applicationProperty.setProfile("dev");
        applicationProperty.setLabel("master");
        Source source = new Source();
        Map<String, Object> map = new HashMap<>();
        map.put("max-connections", 1);
        map.put("timeout-ms", 3600);
        map.put("name","ram");
        source.addProperty("user", map);
        applicationProperty.setSource(source);
        dao.save(applicationProperty);
    }
}

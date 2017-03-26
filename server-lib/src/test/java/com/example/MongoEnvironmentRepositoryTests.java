package com.example;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by amarendra on 24/3/17.
 */
public class MongoEnvironmentRepositoryTests {
    private ConfigurableApplicationContext context;

    @Before
    public void init() {

    }

    @After
    public void close() {
        if (this.context != null) {
            this.context.close();
        }
    }

    @Test
    public void defaultRepo() {
        // Prepare context
        Map<String, Object> props = new HashMap<>();
        props.put("spring.data.mongodb.database", "testdb");
        context = new SpringApplicationBuilder(TestConfiguration.class).web(false).properties(props).run();
        // Prepare test
        MongoTemplate mongoTemplate = this.context.getBean(MongoTemplate.class);
        mongoTemplate.dropCollection("testapp");
        MongoEnvironmentRepository.MongoPropertySource ps = new MongoEnvironmentRepository.MongoPropertySource();
        Map<String, Object> map = new HashMap<>();
        map.put("key1","val1");
        map.put("key2","val2");
        ps.getSource().put(MongoEnvironmentRepository.PROPERTY, map);
        mongoTemplate.save(ps, "testapp");
        // Test
        EnvironmentRepository repository = this.context.getBean(EnvironmentRepository.class);
        Environment environment = repository.findOne("testapp", "default", null);
        assertEquals("testapp-default", environment.getPropertySources().get(0).getName());
        assertEquals(1, environment.getPropertySources().size());
        assertEquals(true, environment.getPropertySources().get(0).getSource().containsKey("key1"));
        assertEquals("val1", environment.getPropertySources().get(0).getSource().get("key1"));
    }

    @Test
    public void nestedPropertySource() {
        // Prepare context
        Map<String, Object> props = new HashMap<>();
        props.put("spring.data.mongodb.database", "testdb");
        context = new SpringApplicationBuilder(TestConfiguration.class).web(false).properties(props).run();
        // Prepare test
        MongoTemplate mongoTemplate = this.context.getBean(MongoTemplate.class);
        mongoTemplate.dropCollection("testapp");
        MongoEnvironmentRepository.MongoPropertySource ps = new MongoEnvironmentRepository.MongoPropertySource();
        Map<String, String> inner = new HashMap<String, String>();
        inner.put("outer.inner", "value");
        ps.getSource().put(MongoEnvironmentRepository.PROPERTY, inner);
        mongoTemplate.save(ps, "testapp");
        // Test
        EnvironmentRepository repository = this.context.getBean(EnvironmentRepository.class);
        Environment environment = repository.findOne("testapp", "default", null);
        assertEquals("testapp-default", environment.getPropertySources().get(0).getName());
        assertEquals(1, environment.getPropertySources().size());
        assertEquals(true, environment.getPropertySources().get(0).getSource().containsKey("outer.inner"));
        assertEquals("value", environment.getPropertySources().get(0).getSource().get("outer.inner"));
    }

    @Test
    public void repoWithProfileAndLabelInSource() {
        // Prepare context
        Map<String, Object> props = new HashMap<>();
        props.put("spring.data.mongodb.database", "testdb");
        context = new SpringApplicationBuilder(TestConfiguration.class).web(false).properties(props).run();
        // Prepare test
        MongoTemplate mongoTemplate = this.context.getBean(MongoTemplate.class);
        mongoTemplate.dropCollection("testapp");
        MongoEnvironmentRepository.MongoPropertySource ps = new MongoEnvironmentRepository.MongoPropertySource();
        ps.setProfile("confprofile");
        ps.setLabel("conflabel");
        Map<String, String> inner = new HashMap<String, String>();
        inner.put("profile", "sourceprofile");
        inner.put("label", "sourcelabel");
        ps.getSource().put(MongoEnvironmentRepository.PROPERTY, inner);
        mongoTemplate.save(ps, "testapp");
        // Test
        EnvironmentRepository repository = this.context.getBean(EnvironmentRepository.class);
        Environment environment = repository.findOne("testapp", "confprofile", "conflabel");
        assertEquals(1, environment.getPropertySources().size());
        assertEquals("testapp-confprofile-conflabel", environment.getPropertySources().get(0).getName());
        assertEquals(true, environment.getPropertySources().get(0).getSource().containsKey("profile"));
        assertEquals("sourceprofile", environment.getPropertySources().get(0).getSource().get("profile"));
        assertEquals(true, environment.getPropertySources().get(0).getSource().containsKey("label"));
        assertEquals("sourcelabel", environment.getPropertySources().get(0).getSource().get("label"));
    }

    @Configuration
    @EnableMongoConfigServer
    @Import({
            PropertyPlaceholderAutoConfiguration.class,
            MongoAutoConfiguration.class,
            MongoDataAutoConfiguration.class
    })
    protected static class TestConfiguration {

    }

}

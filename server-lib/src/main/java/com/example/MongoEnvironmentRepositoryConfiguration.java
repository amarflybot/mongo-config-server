package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.cloud.config.server.environment.NativeEnvironmentRepository;
import org.springframework.cloud.config.server.environment.SearchPathLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * Created by amarendra on 24/3/17.
 */
@Configuration
public class MongoEnvironmentRepositoryConfiguration {

    public static final String MAP_KEY_DOT_REPLACEMENT = "\uff0e";
    @Autowired
    private ConfigurableEnvironment environment;

    @Bean
    public SearchPathLocator searchPathLocator() {
        return new NativeEnvironmentRepository(environment);
    }

    @Bean
    @Primary
    public EnvironmentRepository environmentRepository(final MongoTemplate mongoTemplate) {
        return new MongoEnvironmentRepository(mongoTemplate);
    }

    @Bean
    public MappingMongoConverter mongoConverter(final MongoDbFactory mongoDbFactory) throws Exception {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter mongoConverter = new MappingMongoConverter(dbRefResolver, mappingContext);
        mongoConverter.setMapKeyDotReplacement(MAP_KEY_DOT_REPLACEMENT);
        return mongoConverter;
    }

}

package com.example;

import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by amarendra on 24/3/17.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@Import(MongoEnvironmentRepositoryConfiguration.class)
@EnableConfigServer
public @interface EnableMongoConfigServer {

}

package com.example;

import com.example.model.ApplicationProperty;
import com.example.model.Source;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.*;

@SpringBootApplication
@EnableMongoConfigServer
public class MongoConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoConfigServerApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(final ApplicationPropertyService propertyService){
	    return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {
				propertyService.drop("TestCollection");
				ApplicationProperty applicationProperty = new ApplicationProperty();
				applicationProperty.setApplicationName("TestCollection");
				applicationProperty.setProfile("dev");
				applicationProperty.setLabel("master");
				Source source = new Source();
				source.addProperty("user.max-connections",1);
				source.addProperty("user.timeout-ms",3600);
				source.addProperty("user.name","ram");
				applicationProperty.setSource(source);
				propertyService.save(applicationProperty);
				ApplicationProperty applicationProperty1 = new ApplicationProperty();
				applicationProperty1.setApplicationName("TestCollection");
				applicationProperty1.setProfile("dev");
				applicationProperty1.setLabel("master");
				Source source1 = new Source();
				source1.addProperty("user.name","shyam");
				applicationProperty1.setSource(source1);
				propertyService.updatePropertyByKey(applicationProperty1);
			}
		};
    }

}

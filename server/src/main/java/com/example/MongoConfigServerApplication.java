package com.example;

import com.example.domain.ApplicationProperty;
import com.example.domain.Source;
import com.example.service.ApplicationPropertyService;
import com.example.service.dto.ApplicationPropertyDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
				ApplicationPropertyDTO applicationProperty = new ApplicationPropertyDTO();
				applicationProperty.setApplicationName("TestCollection");
				applicationProperty.setProfile("dev");
				applicationProperty.setLabel("master");
				Source source = new Source();
				source.addProperty("user.max-connections",1);
				source.addProperty("user.timeout-ms",3600);
				source.addProperty("user.name","ram");
				applicationProperty.setSource(source);
				propertyService.save(applicationProperty);
				ApplicationPropertyDTO applicationProperty1 = new ApplicationPropertyDTO();
				applicationProperty1.setApplicationName("TestCollection");
				applicationProperty1.setProfile("dev");
				applicationProperty1.setLabel("master");
				Source source1 = new Source();
				source1.addProperty("user.name","shyam");
				applicationProperty1.setSource(source1);
				propertyService.upsertPropertyByKey(applicationProperty1);
			}
		};
    }

}

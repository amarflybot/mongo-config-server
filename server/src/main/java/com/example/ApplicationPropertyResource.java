package com.example;

import com.example.model.ApplicationProperty;
import com.example.model.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by amarendra on 24/3/17.
 */
@RestController
@RequestMapping("/app")
public class ApplicationPropertyResource {

    /**
     *
     */
    @Autowired
    ApplicationPropertyService service;

    /**
     * @param profile
     * @param applicationName
     * @param properties
     * @return
     */
    @PutMapping(value = "/{profile}/{applicationName}")
    ResponseEntity<?> addNewApplication(@PathVariable String profile,
                                        @PathVariable String applicationName,
                                        @RequestBody Map properties) {
        Source source = new Source(properties);
        ApplicationProperty applicationProperty =
                new ApplicationProperty("master", profile, source, applicationName);
        service.save(applicationProperty);
        return ResponseEntity.ok(applicationProperty);
    }

    @DeleteMapping(value = "/property/{profile}/{applicationName}")
    ResponseEntity<?> deletePropertyByApplication(@PathVariable String profile,
                                                  @PathVariable String applicationName,
                                                  @RequestBody Map properties) {
        Source source = new Source(properties);
        ApplicationProperty applicationProperty =
                new ApplicationProperty("master", profile, source, applicationName);
        boolean deleteByProfileAndApplicationName = service.deletePropertyByApplication(applicationProperty);
        return ResponseEntity.ok(deleteByProfileAndApplicationName);
    }

    @DeleteMapping(value = "/{profile}/{applicationName}")
    ResponseEntity<?> deleteApplication(@PathVariable String profile,
                                        @PathVariable String applicationName) {
        ApplicationProperty applicationProperty =
                new ApplicationProperty("master", profile, null, applicationName);
        boolean deleteByProfileAndApplicationName = service.deleteByProfileAndApplicationName(applicationProperty);
        return ResponseEntity.ok(deleteByProfileAndApplicationName);
    }

    /**
     * @param profile
     * @param applicationName
     * @return
     */
    @GetMapping(value = "/{profile}/{applicationName}")
    ResponseEntity<?> getApplicationByProfileAndApplicationName(@PathVariable String profile,
                                                                @PathVariable String applicationName) {
        ApplicationProperty applicationProperty =
                new ApplicationProperty("master", profile, null, applicationName);
        ApplicationProperty applicationPropertyReturned = service.getByProfileAndApplicationName(applicationProperty);
        return ResponseEntity.ok(applicationPropertyReturned);
    }

    /**
     * @param profile
     * @return
     */
    @GetMapping(value = "/{profile}")
    ResponseEntity<?> getApplicationByProfile(@PathVariable String profile) {
        ApplicationProperty applicationProperty =
                new ApplicationProperty("master", profile, null, null);
        List<ApplicationProperty> applicationPropertyReturned = service.getByProfile(applicationProperty);
        return ResponseEntity.ok(applicationPropertyReturned);
    }

    /**
     * @param profile
     * @param applicationName
     * @param property
     * @return
     */
    @PostMapping(value = "/{profile}/{applicationName}")
    ResponseEntity<?> upsertPropertyByKey(@PathVariable String profile,
                                          @PathVariable String applicationName,
                                          @RequestBody Map property) {
        Source source = new Source(property);
        ApplicationProperty applicationProperty =
                new ApplicationProperty("master", profile, source, applicationName);
        ApplicationProperty applicationPropertyAfterOperation = service.upsertPropertyByKey(applicationProperty);
        return ResponseEntity.ok(applicationPropertyAfterOperation);
    }

    @GetMapping
    ResponseEntity<?> getAllApplications() {
        List<String> allApps = service.getAll();
        return ResponseEntity.ok(allApps);
    }
}

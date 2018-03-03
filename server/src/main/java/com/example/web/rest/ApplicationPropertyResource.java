package com.example.web.rest;

import com.example.service.ApplicationPropertyService;
import com.example.domain.ApplicationProperty;
import com.example.domain.Source;
import com.example.service.dto.ApplicationPropertyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by amarendra on 24/3/17.
 */
@RestController
@RequestMapping("/api")
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
        ApplicationPropertyDTO applicationProperty =
                new ApplicationPropertyDTO("master", profile, source, applicationName);
        service.save(applicationProperty);
        return ResponseEntity.ok(applicationProperty);
    }

    @DeleteMapping(value = "/property/{profile}/{applicationName}")
    ResponseEntity<?> deletePropertyByApplication(@PathVariable String profile,
                                                  @PathVariable String applicationName,
                                                  @RequestBody Map properties) {
        Source source = new Source(properties);
        ApplicationPropertyDTO applicationProperty =
                new ApplicationPropertyDTO("master", profile, source, applicationName);
        boolean deleteByProfileAndApplicationName = service.deletePropertyByApplication(applicationProperty);
        return ResponseEntity.ok(deleteByProfileAndApplicationName);
    }

    @DeleteMapping(value = "/{profile}/{applicationName}")
    ResponseEntity<?> deleteApplication(@PathVariable String profile,
                                        @PathVariable String applicationName) {
        ApplicationPropertyDTO applicationProperty =
                new ApplicationPropertyDTO("master", profile, null, applicationName);
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
        ApplicationPropertyDTO applicationProperty =
                new ApplicationPropertyDTO("master", profile, null, applicationName);
        ApplicationPropertyDTO applicationPropertyReturned = service.getByProfileAndApplicationName(applicationProperty);
        return ResponseEntity.ok(applicationPropertyReturned);
    }

    /**
     * @param profile
     * @return
     */
    @GetMapping(value = "/{profile}")
    ResponseEntity<?> getApplicationByProfile(@PathVariable String profile) {
        ApplicationPropertyDTO applicationProperty =
                new ApplicationPropertyDTO("master", profile, null, null);
        List<ApplicationPropertyDTO> applicationPropertyReturned = service.getByProfile(applicationProperty);
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
        ApplicationPropertyDTO applicationProperty =
                new ApplicationPropertyDTO("master", profile, source, applicationName);
        ApplicationPropertyDTO applicationPropertyAfterOperation = service.upsertPropertyByKey(applicationProperty);
        return ResponseEntity.ok(applicationPropertyAfterOperation);
    }

    @GetMapping
    ResponseEntity<?> getAllApplications() {
        List<String> allApps = service.getAll();
        return ResponseEntity.ok(allApps);
    }
}

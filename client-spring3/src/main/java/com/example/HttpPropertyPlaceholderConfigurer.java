package com.example;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

/**
 * Created by amarendra on 26/03/17.
 */
public class HttpPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {


    public HttpPropertyPlaceholderConfigurer() {
        super();
    }

  /**
   *
   * @param beanFactory
   * @throws BeansException
   */
  @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        try {
            Properties mergedProps = mergeProperties();

            HttpPropertyPlaceholderProperties placeholderProperties =
                    new HttpPropertyPlaceholderProperties(mergedProps);

            RestTemplate restTemplate = getRestTemplate(placeholderProperties);

            Map<String, Object> remoteEnvironment = getRemoteEnvironment(restTemplate, placeholderProperties.getUri(), placeholderProperties.getName()
                    , placeholderProperties.getProfile(), placeholderProperties.getLabel());

            // This is the place where we call Config server URL.
            Iterator it = remoteEnvironment.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                System.out.println(pair.getKey() + " = " + pair.getValue());
                mergedProps.put(pair.getKey(), pair.getValue());
                //it.remove(); // avoids a ConcurrentModificationException
            }

            // Convert the merged properties, if necessary.
            convertProperties(mergedProps);

            // Let the subclass process the properties.
            processProperties(beanFactory, mergedProps);
        }
        catch (IOException ex) {
            throw new BeanInitializationException("Could not load properties", ex);
        }
    }


  /**
   *
   * @param placeholderProperties
   * @return
   */
  private RestTemplate getRestTemplate(HttpPropertyPlaceholderProperties placeholderProperties) {
        RestTemplate template = new RestTemplate();
        String password = placeholderProperties.getPassword();
        if (password != null) {
        }
        return template;
    }

  /**
   *
   * @param originalValue
   * @return
   */
  @Override
    protected String convertPropertyValue(String originalValue) {
        // This is the place where we convert PW(***)
        return super.convertPropertyValue(originalValue);
    }


  /**
   *
   * @param key
   * @return
   */
  @Override
    protected String resolveSystemProperty(String key) {
        return super.resolveSystemProperty(key);
    }

  /**
   *
   * @param restTemplate
   * @param uri
   * @param name
   * @param profile
   * @param label
   * @return
   */
    private Map<String,Object> getRemoteEnvironment(RestTemplate restTemplate, String uri, String name, String profile, String label) {
        String path = "/{name}/{profile}";
        Object[] args = new String[] { name, profile };
        if (StringUtils.hasText(label)) {
            args = new String[] { name, profile, label };
            path = path + "/{label}";
        }
        ResponseEntity<Object> response = null;

        try {
            response = restTemplate.exchange(uri + path,
                    HttpMethod.GET, new HttpEntity<Void>((Void) null),
                    Object.class, args);
        } catch (HttpClientErrorException e) {
            if(e.getStatusCode() != HttpStatus.NOT_FOUND ) {
                throw e;
            }
        }

        if (response==null || response.getStatusCode()!=HttpStatus.OK) {
            return null;
        }
        Map<String, Object> result = (Map<String, Object>) response.getBody();
        return (Map<String, Object>) ((Map)((List)result.get("propertySources")).get(0)).get("source");
    }


}

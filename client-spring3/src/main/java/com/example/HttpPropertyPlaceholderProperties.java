package com.example;


import java.util.Properties;

/**
 * Created by amarendra on 26/03/17.
 */
public class HttpPropertyPlaceholderProperties {

    public static final String PREFIX = "spring.cloud.config";

    /**
     * Flag to say that remote configuration is enabled. Default true;
     */
    private boolean enabled = true;

    /**
     * The default profile to use when fetching remote configuration (comma-separated).
     * Default is "default".
     */
    private String profile = "default";

    /**
     * Name of application used to fetch remote properties.
     */
    private String name;

    /**
     * The label name to use to pull remote configuration properties. The default is set
     * on the server (generally "master" for a git based server).
     */
    private String label = "master";

    /**
     * The username to use (HTTP Basic) when contacting the remote server.
     */
    private String username;

    /**
     * The password to use (HTTP Basic) when contacting the remote server.
     */
    private String password;

    /**
     * The URI of the remote server (default http://localhost:8888).
     */
    private String uri = "http://localhost:8888";

    /**
     * Flag to indicate that failure to connect to the server is fatal (default false).
     */
    private boolean failFast = false;

    private HttpPropertyPlaceholderProperties() {
    }

  /**
   *
   * @param properties
   */
  public HttpPropertyPlaceholderProperties(Properties properties){
        this.uri = properties.getProperty("spring.cloud.config.uri");
        String labelProperty = properties.getProperty("spring.cloud.config.label");
        this.label= labelProperty == null ? "master" : labelProperty;
        this.profile = properties.getProperty("spring.cloud.config.profile");
        this.username = extractUsername(properties.getProperty("spring.cloud.config.username"));
        this.password = extractPassword(properties.getProperty("spring.cloud.config.password"));
        this.name = properties.getProperty("spring.application.name");
    }

    private String extractPassword(String password) {
        return password;
    }

    private String extractUsername(String username) {
        return username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getProfile() {
        return profile;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUri() {
        return uri;
    }

    public boolean isFailFast() {
        return failFast;
    }
}

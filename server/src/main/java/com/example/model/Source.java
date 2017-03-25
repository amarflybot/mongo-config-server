
package com.example.model;

import java.util.HashMap;
import java.util.Map;

public class Source {

    private Map<String, Object> property;

    public Source() {
        this.property = new HashMap<>();
    }

    public Source(Map<String, Object> property) {
        this.property = property;
    }

    public Map<String, Object> getAllProperties() {
        return property;
    }

    public Object getAllProperties(String key) {
        return property.get(key);
    }

    public void addProperty(String key, Object value) {
        this.property.put(key, value);
    }
}

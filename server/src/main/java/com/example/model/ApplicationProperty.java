
package com.example.model;

import java.io.Serializable;

/**
 *
 */
public class ApplicationProperty implements Serializable{

    public static final String LABEL="label";
    public static final String PROFILE="profile";
    public static final String SOURCE="source";
    public static final String APPLICATION_NAME="applicationName";

    private String label;
    private String profile;
    private Source source;
    private String applicationName;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ApplicationProperty() {
    }

    /**
     *
     * @param source
     * @param label
     * @param profile
     */
    public ApplicationProperty(String label, String profile, Source source, String applicationName) {
        super();
        this.label = label;
        this.profile = profile;
        this.source = source;
        this.applicationName = applicationName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ApplicationProperty withLabel(String label) {
        this.label = label;
        return this;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public ApplicationProperty withProfile(String profile) {
        this.profile = profile;
        return this;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicationProperty)) return false;

        ApplicationProperty that = (ApplicationProperty) o;

        if (getLabel() != null ? !getLabel().equals(that.getLabel()) : that.getLabel() != null) return false;
        if (getProfile() != null ? !getProfile().equals(that.getProfile()) : that.getProfile() != null) return false;
        if (getSource() != null ? !getSource().equals(that.getSource()) : that.getSource() != null) return false;
        return getApplicationName() != null ? getApplicationName().equals(that.getApplicationName()) : that.getApplicationName() == null;
    }

    public boolean equalByLabelAndProfile(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicationProperty)) return false;

        ApplicationProperty that = (ApplicationProperty) o;

        if (getLabel() != null ? !getLabel().equals(that.getLabel()) : that.getLabel() != null) return false;
        if (getProfile() != null ? !getProfile().equals(that.getProfile()) : that.getProfile() != null) return false;
        return getApplicationName() != null ? getApplicationName().equals(that.getApplicationName()) : that.getApplicationName() == null;
    }

    @Override
    public int hashCode() {
        int result = getLabel() != null ? getLabel().hashCode() : 0;
        result = 31 * result + (getProfile() != null ? getProfile().hashCode() : 0);
        result = 31 * result + (getSource() != null ? getSource().hashCode() : 0);
        result = 31 * result + (getApplicationName() != null ? getApplicationName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ApplicationProperty{" +
                "label='" + label + '\'' +
                ", profile='" + profile + '\'' +
                ", source=" + source +
                '}';
    }
}

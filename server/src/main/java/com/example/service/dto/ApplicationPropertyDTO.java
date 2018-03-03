
package com.example.service.dto;

import com.example.domain.Source;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 */
public class ApplicationPropertyDTO implements Serializable{

    private String label;
    private String profile;
    private Source source;
    private String applicationName;
    private Date lastModified;


    public ApplicationPropertyDTO(String label, String profile, Source source, String applicationName) {
        this.label = label;
        this.profile = profile;
        this.source = source;
        this.applicationName = applicationName;
    }

    public ApplicationPropertyDTO() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationPropertyDTO that = (ApplicationPropertyDTO) o;
        return Objects.equals(label, that.label) &&
                Objects.equals(profile, that.profile) &&
                Objects.equals(source, that.source) &&
                Objects.equals(applicationName, that.applicationName) &&
                Objects.equals(lastModified, that.lastModified);
    }

    @Override
    public int hashCode() {

        return Objects.hash(label, profile, source, applicationName, lastModified);
    }

    @Override
    public String toString() {
        return "ApplicationPropertyDto{" +
                "label='" + label + '\'' +
                ", profile='" + profile + '\'' +
                ", source=" + source +
                ", applicationName='" + applicationName + '\'' +
                ", lastModified=" + lastModified +
                '}';
    }
}

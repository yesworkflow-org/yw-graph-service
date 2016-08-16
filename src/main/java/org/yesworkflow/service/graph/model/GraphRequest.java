package org.yesworkflow.service.graph.model;

public class GraphRequest {

    private String language;
    private String code;
    private String properties;

    public GraphRequest() {}

    public GraphRequest(String language, String code, String properties) {
        this.language = language;
        this.code = code;
        this.properties = properties;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getProperties() {
        return properties;
    }
}
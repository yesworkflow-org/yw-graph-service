package org.yesworkflow.service.graph.model;

public class GraphRequest {

    private String language;
    private String code;

    public GraphRequest() {}

    public GraphRequest(String language, String code) {
        this.language = language;
        this.code = code;
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
}
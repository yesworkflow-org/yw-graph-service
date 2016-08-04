package org.yesworkflow.service.graph.model;

public class Script {

    private String language;
    private String code;

    public Script() {}

    public Script(String language, String code) {
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
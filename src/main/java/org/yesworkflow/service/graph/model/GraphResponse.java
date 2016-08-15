package org.yesworkflow.service.graph.model;

public class GraphResponse {
    
    private Long id;
    private String skeleton;
    private String dot;
    private String svg;
    private String error;

	public GraphResponse() {}

    public GraphResponse(Long id, String skeleton, String dot, String svg, String error) {
        this();
        this.id = id;
        this.skeleton = skeleton;
        this.dot = dot;
        this.svg = svg;
        this.error = error;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setSkeleton(String skeleton) {
        this.skeleton = skeleton;
    }

    public String getSkeleton() {
        return this.skeleton;
    }

    public void setDot(String dot) {
        this.dot = dot;
    }

    public String getDot() {
        return this.dot;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }

    public String getSvg() {
        return this.svg;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return this.error;
    }
}

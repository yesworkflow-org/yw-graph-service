package org.yesworkflow.service.graph.model;

public class Graph {
    
    private Long id;
    private String skeleton;
    private String dotSource;

	public Graph() {}

    public Graph(Long id, String skeleton, String dotSource) {
        this();
        this.id = id;
        this.skeleton = skeleton;
        this.dotSource = dotSource;
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

    public void setDotSource(String dotSource) {
        this.dotSource = dotSource;
    }

    public String getDotSource() {
        return dotSource;
    }
}

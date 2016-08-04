package org.yesworkflow.service.graph.model;

public class Graph {
    
    private String dot;

	public Graph() {}

    public Graph(String dot) {
        this();
        this.dot = dot;
    }

    public void setDot(String dot) {
        this.dot = dot;
    }

    public String getDot() {
        return dot;
    }
}

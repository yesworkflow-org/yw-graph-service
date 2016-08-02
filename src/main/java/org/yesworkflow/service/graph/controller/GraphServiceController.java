package org.yesworkflow.service.graph.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.yesworkflow.service.graph.model.Graph;
import org.yesworkflow.service.graph.model.Script;

@RestController
@RequestMapping("/api/v1/")
@EnableAutoConfiguration
@CrossOrigin
public class GraphServiceController {

	@RequestMapping(value="graph", method=RequestMethod.POST)
    @ResponseBody
	public Graph getGraph(@RequestBody Script script) {
		return new Graph(script.getLanguage());
	}

	@RequestMapping(value="graph/cache/{id}", method=RequestMethod.GET)
    // @ResponseBody
	public String getCachedGraph(@PathVariable Long id) {
		return "{ \"cached_graphviz_file\": " + id + "}";
	}
}

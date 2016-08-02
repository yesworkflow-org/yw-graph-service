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

@RestController
@RequestMapping("/api/v1/")
@EnableAutoConfiguration
@CrossOrigin
public class GraphServiceController {

	@RequestMapping(value="graph/cache/{id}", method=RequestMethod.GET)
    // @ResponseBody
	public String getCachedGraph(@PathVariable Long id) { //@RequestBody String script) {
		return "{ \"cached_graphviz_file\": " + id + "}";
	}
}

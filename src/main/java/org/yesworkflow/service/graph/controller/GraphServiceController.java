package org.yesworkflow.service.graph.controller;

import java.io.BufferedReader;
import java.io.StringReader;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.yesworkflow.Language;
import org.yesworkflow.db.YesWorkflowDB;
import org.yesworkflow.extract.Extractor;
import org.yesworkflow.extract.DefaultExtractor;

import org.yesworkflow.service.graph.model.Graph;
import org.yesworkflow.service.graph.model.Script;


@RestController
@RequestMapping("/api/v1/")
@EnableAutoConfiguration
@CrossOrigin
public class GraphServiceController {

	public GraphServiceController() throws Exception {
	}
    
	@RequestMapping(value="graph", method=RequestMethod.POST)
    @ResponseBody
	public Graph getGraph(@RequestBody Script script) throws Exception {

		String code = script.getCode();

		BufferedReader reader = new BufferedReader(new StringReader(code));

        YesWorkflowDB ywdb = YesWorkflowDB.createInstance();
        Extractor extractor = new DefaultExtractor(ywdb)
        	.configure("language", Language.PYTHON)
			.reader(reader)
			.extract();

		String skeleton = extractor.getSkeleton();
		return new Graph(skeleton);
	}

	@RequestMapping(value="graph/cache/{id}", method=RequestMethod.GET)
    // @ResponseBody
	public String getCachedGraph(@PathVariable Long id) {
		return "{ \"cached_graphviz_file\": " + id + "}";
	}
}

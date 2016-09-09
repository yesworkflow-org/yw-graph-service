package org.yesworkflow.service.graph.controller;

import java.io.BufferedReader;
import java.io.StringReader;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.yesworkflow.Language;

import org.yesworkflow.config.YWConfiguration;

import org.yesworkflow.db.YesWorkflowDB;

import org.yesworkflow.extract.Extractor;
import org.yesworkflow.extract.DefaultExtractor;

import org.yesworkflow.model.DefaultModeler;
import org.yesworkflow.model.Modeler;
import org.yesworkflow.model.Workflow;
import org.yesworkflow.graph.Grapher;
import org.yesworkflow.graph.DotGrapher;

import org.yesworkflow.service.graph.model.GraphRequest;
import org.yesworkflow.service.graph.model.GraphResponse;
import org.yesworkflow.util.ProcessRunner;
import org.yesworkflow.util.StreamSink;

@RestController
@RequestMapping("/${graph-service.prefix}/api/${graph-service.version}/")
@EnableAutoConfiguration
@CrossOrigin
public class YWGraphServiceController {

	private Long nextGraphId = 1L;

	@Value("${graph-service.dot-command}")
	public String dotCommand;
    
	@RequestMapping(value="graph", method=RequestMethod.POST)
    @ResponseBody
	public GraphResponse getGraph(@RequestBody GraphRequest request) throws Exception {

		String skeleton = null;
		String dot = null;
		String svg = null;
		String error = null;
		String code = request.getCode();

		BufferedReader reader = new BufferedReader(new StringReader(code));
		YesWorkflowDB ywdb = YesWorkflowDB.createInstance();

		try {

			YWConfiguration config = new YWConfiguration();
			String properties = request.getProperties();
			if (properties != null) {
				config.applyProperties(new StringReader(properties));
			}

			String requestLanguage = request.getLanguage();
			Language ywLanguage = Language.toLanguage(requestLanguage);

			Extractor extractor = new DefaultExtractor(ywdb)
				.configure(config.getSection("extract"))
				.configure("language", ywLanguage)
				.reader(reader)
				.extract();

			skeleton = extractor.getSkeleton();

			Modeler modeler = new DefaultModeler(ywdb)
				.configure(config.getSection("model"))
				.annotations(extractor.getAnnotations())
				.model();

        	Workflow workflow = modeler.getModel().workflow;

			Grapher grapher = new DotGrapher(System.out, System.err)
				.configure(config.getSection("graph"))
				.workflow(workflow);

			grapher.graph();
			dot = grapher.toString();
			
			StreamSink streams[] = runGraphviz(dot);
			svg = streams[0].toString();

			System.out.println(svg);

		} catch(Exception e) {
			error = e.getMessage();
		}

		return new GraphResponse(nextGraphId++, skeleton, dot, svg, error);
	}

	// @RequestMapping(value="graph/cache/{id}", method=RequestMethod.GET)
	// public String getCachedGraph(@PathVariable Long id) {
	// 	return "{ \"cached_graphviz_file\": " + id + "}";
	// }
	
	
	private StreamSink[] runGraphviz(String dotSource) throws Exception {
		return ProcessRunner.run(dotCommand + " -Tsvg", dotSource, new String[0], null);
	}
}

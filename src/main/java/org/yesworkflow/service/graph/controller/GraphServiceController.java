package org.yesworkflow.service.graph.controller;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

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

import org.yesworkflow.annotations.Annotation;
import org.yesworkflow.model.DefaultModeler;
import org.yesworkflow.model.Modeler;
import org.yesworkflow.model.Program;
import org.yesworkflow.model.Workflow;

import org.yesworkflow.graph.Grapher;
import org.yesworkflow.graph.CommentVisibility;
import org.yesworkflow.graph.DotGrapher;
import org.yesworkflow.graph.GraphView;
import org.yesworkflow.graph.ParamVisibility;
import org.yesworkflow.graph.PortLayout;
import org.yesworkflow.graph.TitlePosition;

import org.yesworkflow.service.graph.model.Graph;
import org.yesworkflow.service.graph.model.Script;


@RestController
@RequestMapping("/api/v1/")
@EnableAutoConfiguration
@CrossOrigin
public class GraphServiceController {

	private Long nextGraphId = 1L;

	public GraphServiceController() {
	}
    
	@RequestMapping(value="graph", method=RequestMethod.POST)
    @ResponseBody
	public Graph getGraph(@RequestBody Script script) throws Exception {

		String skeleton = null;
		String graph = null;
		String error = null;
		String code = script.getCode();

		BufferedReader reader = new BufferedReader(new StringReader(code));
		YesWorkflowDB ywdb = YesWorkflowDB.createInstance();

		try {

			Extractor extractor = new DefaultExtractor(ywdb)
				.configure("language", Language.PYTHON)
				.reader(reader)
				.extract();

			skeleton = extractor.getSkeleton();

			Modeler modeler = new DefaultModeler(ywdb)
				.annotations(extractor.getAnnotations())
				.model();

        	Program program = modeler.getModel().program;

			Grapher grapher = new DotGrapher(System.out, System.err)
			  	.configure("view", GraphView.COMBINED_VIEW)
               	.configure("dotcomments", CommentVisibility.ON)
               	.configure("params", ParamVisibility.SHOW)
               	.configure("titleposition", TitlePosition.HIDE)
               	.configure("portlayout", PortLayout.RELAX)
				.workflow(program);

			grapher.graph();
			graph = grapher.toString();

		} catch(Exception e) {
			error = e.getMessage();
		}

		System.out.println(graph);

		return new Graph(nextGraphId++, skeleton, graph);
	}

	@RequestMapping(value="graph/cache/{id}", method=RequestMethod.GET)
	public String getCachedGraph(@PathVariable Long id) {
		return "{ \"cached_graphviz_file\": " + id + "}";
	}
}

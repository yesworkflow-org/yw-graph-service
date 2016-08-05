package org.yesworkflow.service.graph.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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

import org.yesworkflow.model.DefaultModeler;
import org.yesworkflow.model.Modeler;
import org.yesworkflow.model.Program;

import org.yesworkflow.graph.Grapher;
import org.yesworkflow.graph.CommentVisibility;
import org.yesworkflow.graph.DotGrapher;
import org.yesworkflow.graph.GraphView;
import org.yesworkflow.graph.ParamVisibility;
import org.yesworkflow.graph.PortLayout;
import org.yesworkflow.graph.TitlePosition;

import org.yesworkflow.service.graph.model.Graph;
import org.yesworkflow.service.graph.model.Script;
import org.yesworkflow.util.ProcessRunner;
import org.yesworkflow.util.StreamSink;


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
		String dot = null;
		String svg = null;
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
			dot = grapher.toString();
			
			StreamSink streams[] = runGraphviz(dot);
			String svgDocument = streams[0].toString();

			int svgElementStart = svgDocument.indexOf("<svg");
			svg = svgDocument.substring(svgElementStart);

			
			

		} catch(Exception e) {
			error = e.getMessage();
		}

		return new Graph(nextGraphId++, skeleton, dot, svg, error);
	}

	@RequestMapping(value="graph/cache/{id}", method=RequestMethod.GET)
	public String getCachedGraph(@PathVariable Long id) {
		return "{ \"cached_graphviz_file\": " + id + "}";
	}
	
	
	private StreamSink[] runGraphviz(String dotSource) throws Exception {
		
		 StreamSink[] streams = ProcessRunner.run("C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe -Tsvg", dotSource, new String[0], null);
		
		return streams;
	}
}

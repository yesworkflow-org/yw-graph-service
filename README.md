YesWorkflow Graph Service
=========================

This repository contains the source code for a microservice currently under development for producing [YesWorkflow](https://github.com/yesworkflow-org/yw-prototypes/blob/master/README.md) (YW) visualizations of scripts.  When provided with the text of a script containing YW markup, the service will return an [SVG](https://www.w3.org/Graphics/SVG/) representation of the YW visualization of the script.  The [GraphViz](http://graphviz.org/) [dot language](http://graphviz.org/content/dot-language) source the YesWorkflow uses to produce the SVG is included in the response. Visualization properties sent as part of the request allow customization of the visualization.

Running the YW Graph Service
----------------------------
The microservice is implemented using [Spring Boot](http://projects.spring.io/spring-boot/), and employs an embedded Tomcat application server. Running the microservice does not require installing Tomcat or a web server of any kind. Only a Java Runtime Environment (JRE) version 1.8 or higher and an installation of GraphViz are needed to run the microservice.

### Install a Java Runtime Environment (JRE)

YesWorkflow Graph Service requires Java version 1.8 or higher. To determine the version of java installed on your computer use the -version option to the java command. For example,


    $ java -version
    java version "1.8.0_101"
    Java(TM) SE Runtime Environment (build 1.8.0_101-b13)
    Java HotSpot(TM) 64-Bit Server VM (build 25.101-b13, mixed mode)
    $

 A JRE may be downloaded from Oracle's [Java SE Downloads](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) page or installed via a package manager. Install a JDK rather than the JRE if you plan to build the microservice from source.

### Install GraphViz

Graphviz may be obtained from  [http://graphviz.org/Download.php](http://graphviz.org/Download.php) or installed via a package manager. Make sure that the `dot` command is on the PATH following installation.

### Download the Jar file for the latest release

A pre-built jar file is included in each [release](https://github.com/yesworkflow-org/yw-graph-service/releases) of the YW Graph Service package.  Download the jar file to your system.  It will be named something like `yw-graph-service-0.2.1.1.jar`.

### Run the jar file

The microservice now can be run using the `java -jar` command. For example:

    $ java -jar yw-graph-service-0.2.1.1.jar 

This will start the service running on the default port of 8000. To run the service on a different port specify using the --server-port option.  For example:

    $ java -jar yw-graph-service-0.2.1.1.jar --server.port=8001





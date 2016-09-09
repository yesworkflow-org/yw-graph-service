YesWorkflow Graph Service
=========================

This repository contains the source code for a microservice that produces [YesWorkflow](https://github.com/yesworkflow-org/yw-prototype) (YW) visualizations of scripts.  When provided with the text of a script containing YW markup, the service will return an [SVG](https://www.w3.org/Graphics/SVG/) representation of the YW visualization of the script.  The [GraphViz](http://graphviz.org/) [dot language](http://graphviz.org/content/dot-language) source used to produce the SVG is included in the response.  Visualization properties sent as part of the request allow customization of the visualization.

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

 A JRE may be downloaded from Oracle's [Java SE Downloads](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) or installed via a package manager. Install a JDK rather than the JRE if you plan to build the microservice from source.

### Install GraphViz

Graphviz may be obtained from  [http://graphviz.org/Download.php](http://graphviz.org/Download.php) or installed via a package manager. Make sure that the `dot` command is on the PATH following installation.






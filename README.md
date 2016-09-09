YesWorkflow Graph Service
=========================

This repository contains the source code for a microservice currently under development for producing [YesWorkflow](https://github.com/yesworkflow-org/yw-prototypes/blob/master/README.md) (YW) visualizations of scripts.  When provided with the text of a script containing YW markup, the service will return an [SVG](https://www.w3.org/Graphics/SVG/) representation of the YW visualization of the script.  The [GraphViz](http://graphviz.org/) [dot-language](http://graphviz.org/content/dot-language) source the YesWorkflow uses to produce the SVG is included in the response. Visualization properties sent as part of the request allow customization of the visualization.

The graph service is used by the web-based [YesWorkflow Editor](https://github.com/yesworkflow-org/yw-editor-webapp) application. A demonstration of the editor, graph service, and YesWorkflow can be found at [try.yesworkflow.org](http://try.yesworkflow.org).

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

This will start the service running on the default port of 8000. To run the service on a different port specify using the `--server-port` option.  For example:

    $ java -jar yw-graph-service-0.2.1.1.jar --server.port=8001


Building from source code
-------------------------

The YW Graph Service is implemented in Java and built using Maven.

#### JDK and Maven configuration

Before building the Graph Service confirm that the `mvn` command is in your path, that your version of Maven is at least 3.3.9, and that a JDK version 1.8 (or higher) is found by Maven:
    
    $ mvn --version
    Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-10T08:41:47-08:00)
    Maven home: C:\Users\tmcphill\Dropbox\Library\Java\apache-maven-3.3.9
    Java version: 1.8.0_92, vendor: Oracle Corporation
    Java home: C:\Program Files\Java\jdk1.8.0_92\jre
    Default locale: en_US, platform encoding: Cp1252
    OS name: "windows 10", version: "10.0", arch: "amd64", family: "dos"
    $


JDK 8 and Maven 3 downloads and detailed installation instructions can be found at the following links:

- [Instructions for installing and configuring JDK 1.8](http://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html) (Oracle Java Documentation)
- [Instructions for installing and configuring Maven 3](http://maven.apache.org/download.cgi) (Apache Maven Project)

#### Building and running with Maven

The YW Graph Service can be built and tested from the command line using the following commands:

Command       | Description
--------------|------------
mvn clean     | Delete the target directory including all compiled classes.
mvn compile   | Download required dependencies and compile source code in src/main/java.  Only those source files changes since the last compilation or clean are built.
mvn package   | Package the compiled classes in target/classes and files found in src/main/resources into a jar file.
mvn spring-boot:run | Run the Graph Service without packaging it as a jar.  Compiles any changed source files as necessary.

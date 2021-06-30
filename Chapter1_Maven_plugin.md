# Spring boot Maven Plugin 

## References 
*  Simple Introduction : https://www.javalopment.com/2019/04/configuring-spring-boot-application.html
*  Complete Reference : https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/#?.?


## Bare Minimum 
1. Add Spring boot started Parent \
\
In the heart of Spring Boot is inheriting the defaults from predefined configurations and that is the usage of starter pom files. A starter pom file includes convenient dependencies and versions in sync for your application; and when you import that starter you would automatically have the dependencies defined in that starter pom file.

    ````
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.3.RELEASE</version>
        <relativePath/>
    </parent>

    ````
    By setting the `relativePath` as empty, we are telling maven to lookup the parent directly from _.m2_ repository, meaning that we command to bypass searching the parent in our local workspace and get it directly from repository.

2. Add application starters  \
\
After wiring the dependency space and configuring the default plugins with the parent starter, we can now add some other starters up to the needs of our application

    A starter provides two important shortcuts for us:
    - It adds all the dependencies needed for a given type of the application.
    - It automatically makes the configuration for defaults with respect to the general usage.

    ````
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>    

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency> 
    ````
3. Add the Springboot Maven Plugin 
\
To package a Spring Boot application as jar/war and to run it, we can use the Spring Boot Maven Plugin by adding it to the pom file.
    ````
    <build>
        <plugins> 
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
    ````

## Advanced ##

### Goals ###

Following Goals are available ; we can run them using `mvn spring-boot:<goal name>`

| Goal | Description
| :--- | :--- |
|spring-boot:help| Display help information on spring-boot-maven-plugin |
|spring-boot:start | Start a spring application. Contrary to the run goal, this does not block and allows other goals to operate on the application. This goal is typically used in integration test scenario where the application is started before a test suite and stopped after. |
| spring-boot:stop |Stop an application that has been started by the "start" goal. Typically invoked once a test suite has completed. |
|spring-boot:run|Run an application in place.|
|spring-boot:repackage| TBD |
|spring-boot:build-image|TBD|
|spring-boot:build-info|TBD|



To find further details about a Sprig boot goal you can use the following command
````
mvn spring-boot:help -Ddetail=true -Dgoal=run
mvn spring-boot:help -Ddetail=true -Dgoal=start
mvn spring-boot:help -Ddetail=true -Dgoal=stop
````

- Difference bwteen spring-boot:run and spring-boot:start

| spring-boot:start | spring-boot:run | 
| :--- | :--- |
|  just starts the application directly ; does not block ; allows other goals to execute  | Run an application in place. Block other goals from running|
|Useful for integration tests ||
|need to explicitly stop the run by goal: stop||



- how to find the possible parameters for each of these spring boot Goals \
    * you can use maven commad help
    * You can read teh documentation   

## jar or war
- if you do not specify the packaging type by default it is jar. Here `maven-jar-plugin` kicks in and produces the jar file. 
- if you add following section it will produce war. Here `maven-war-plugin` kicks in and produces the jar file. 
````
	<packaging>war</packaging>
````

## Repackaging 

- The maven `package` goal takes the compiled code and package it in its _distributable_ format, such as a JAR,WAR,EAR
    -   you can package a maven project using 
    ````
    mvn package
    ````
    - The output of the command is JAR/WAR. This file only contains the compiled distributibles. It does not contain the dependencies . So this file is not directly executible  

- The spring-boot `repackage` goal that repackages the JAR produced by maven by specifying the main class and make it _executable_ using an embedded container.
    - you can explicitly  re-package a maven project using (  if you hace added the spring-boot-maven-plugin then it will also automatically trigger the repackaging)
    ````
    mvn spring-boot:repackage
    ````
    - A repackaged file also contains the dependencies, so it is dircetly execution ready.
    - after repackage there will be following 2 files in target 
        - one original jar with name `xxx.jar.original`; this is the result of the `mavn package`, thsi file is not directly executable 
        - the executable jar `xxx.jar`; this file is excutable file and containes all dependent jars . The structure would look like this 

           ![image](https://user-images.githubusercontent.com/8110582/124010941-1b207f80-d9fd-11eb-8fb9-6eb0c3cc07b7.png)  inside BOOT-INF![image](https://user-images.githubusercontent.com/8110582/124011950-32ac3800-d9fe-11eb-9fdf-4287e5b07cbe.png)

	   \
       BOOT-INF contains all the dependent jars ( coming from maven dependency)
       \
       META-INF and org are same from the original jar
	   
        - Sometimes it is also called fat jar

 
Now we learnt `repackaging` produces Fat Jar/War which is sometimes called _layered_ JAR/WAR

A repackaged jar contains the application’s classes and dependencies in BOOT-INF/classes and BOOT-INF/lib respectively. Similarly, an executable war contains the application’s classes in WEB-INF/classes and dependencies in WEB-INF/lib and WEB-INF/lib-provided. For cases where a docker image needs to be built from the contents of a jar or war, it’s useful to be able to separate these directories further so that they can be written into distinct layers.

Layered archives use the same layout as a regular repackaged jar or war, but include an additional meta-data file that describes each layer.


read more here --> 
- https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/#packaging 

- [ MUST READ ] https://www.baeldung.com/spring-boot-run-maven-vs-executable-jar 

## OCI and Docker Image 
- https://www.baeldung.com/docker-layers-spring-boot 

## Actuators 

## Integration test 
- https://www.baeldung.com/maven-integration-test
- https://vorba.ch/2016/integration-testing-spring-boot-travis-saucelabs.html
- https://github.com/pvorb/spring-boot-integration-test-example
- https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/#integration-tests

# Some learnings from Maven world
- If you are using `spring-boot-starter-parent` as your parent PoM you get all the plugins in your project by default ; but they do not execute by default unless you add them in plugin section 
````
<build>
		<plugin>
				<groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin<artifactId>
                </plugin>
		</plugins>
	</build>

````
So it means you need to explicitly add the plugins in the project context even though they are defined in the parent pom

- In `spring-boot-starter-parent` , the `spring-boot-maven-plugin` is configured to execute for goal `repackage`, you can see this in the console. This goal is not not need to mention explicitly in the child pom. If we want to pass additional param in the goal we can add the goal explicitly on child pom.


- However for plugins like `maven-jar-plugin` or `maven-war-plugin` it does not work like that. In this pom it is added onlt in parent POM and not declared in child pom's plug in , still it is getting invoked ( you can check from the log )



Markdown Ref: https://github.com/tchapi/markdown-cheatsheet/blob/master/README.md

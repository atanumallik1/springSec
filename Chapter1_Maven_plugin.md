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
| Goal | Description
| :--- | :--- |
|spring-boot:help| Display help information on spring-boot-maven-plugin |
|spring-boot:start | Start a spring application. Contrary to the run goal, this does not block and allows other goals to operate on the application. This goal is typically used in integration test scenario where the application is started before a test suite and stopped after. |
| spring-boot:stop |Stop an application that has been started by the "start" goal. Typically invoked once a test suite has completed. |
|spring-boot:run|Run an application in place.|
|spring-boot:repackage| TBD |
|spring-boot:build-image|TBD|
|spring-boot:build-info|TBD|



- Difference bwteen spring-boot:run and spring-boot:start
- how to find the possible parameters for each of these spring boot Goals




Markdown Ref: https://github.com/tchapi/markdown-cheatsheet/blob/master/README.md
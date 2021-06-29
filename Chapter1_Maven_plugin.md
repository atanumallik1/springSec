# Spring boot Maven Plugin 

## References 
*  Simple Introduction : https://www.javalopment.com/2019/04/configuring-spring-boot-application.html
*  Complete Reference : https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/#?.?


## Bare Minimum 
1. Add Spring boot started Parent 
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

2. Add application starters 
After wiring the dependency space and configuring the default plugins with the parent starter, we can now add some other starters up to the needs of our application

a starter provides two important shortcuts for us:
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
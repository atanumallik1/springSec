# Spring boot Maven Plugin 

## References 
*  Simple Introduction : https://www.javalopment.com/2019/04/configuring-spring-boot-application.html
*  Complete Reference : https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/#?.?


## Bare Minimum 
1. Add Spring boot started Parent 
````
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.0.3.RELEASE</version>
    <relativePath/>
</parent>

````
2. Add application starters 
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
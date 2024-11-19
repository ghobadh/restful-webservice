

## Spring Boot Actuator
* Spring Boot Actuator module provides production-ready feature such as monitoring , metrics and health checks
* The Spring Boot Actuator enables you to monitor the application using HTTP endpoints and JMX.
* Spring Boot provides a spring-boot-starter-actuator library to aut-configure Actuator
* the property `management.endpoints.web.exposure.include=*` is added to application.properties file under resources


### /info Endpoint
* If you added any information about the application in application.properties then we can view it using /info endpoint
* For info we need to add `management.info.env.enabled=true` to the application.property file also
* I need to add some info in application.properties file . after 'info' you can add anything you like
```
info.app.name= Spring Boot Restful Web Service
info.app.description = Spring Boot Restful Web Services Demo
info.app.version=1.0.0
```

### /health Endpoint
* The /health endpoing shows the health o the application, including the disk space , database and more.
* In order to see more information other than the status of the application, I need to add
  `management.endpoint.health.show-details=always` to application.property file, and the result would be like this
```
{
  "status": "UP",
  "components": {
    "db": {
      "status": "UP",
      "details": {
        "database": "MySQL",
        "validationQuery": "isValid()"
      }
    },
    "diskSpace": {
      "status": "UP",
      "details": {
        "total": 1000284,
        "free": 5900544,
        "threshold": 1760,
        "path": "/gitApplication/restful-webservice/.",
        "exists": true
      }
    },
    "ping": {
      "status": "UP"
    }
  }
}
```

### /beans Endpoint
* The /beans endpoint shows all the beans registered in your application, including the beans you explicitly
  configured and those auto configured by Spring Boot

### /conditions Endpoint
* The /conditions endpoint shows the auto configuration report, categoriesd into positiveMatches and negativeMatches

### /mappings Endpoint
* The /mappings endpoint shows all the @RequestMapping paths declared in the application
* This is very helpful for checking which request path willl be handled by which controller method

### /configprops Endpoint
* The /configprops endpoint offers all the configuration properties defined by @ConfigurationProperties bean,
  including your configuration properties defined in the application.properties or YAML file.
* make sure you add
```
management.endpoint.env.show-values=always
management.endpoint.configprops.show-values=always
```
into application.properties file, in order to see all value; otherwise, you see many stars instead of actual values.
Note that, by enabling them you expose much sensitive information, so you need to keep them usually disable or change it
to 'when_authorized' option

### /metrics Endpoint
* The /metrics endpoint shows various metrics about the current application such as how much memory it is using,
  how much memory is free, the size of the heap is used, and the number of threads used, and so on.
* when you use this endpoint it will show all name of option you can use with metrics endpoint. you just need
  to add the name of the metrics after /acturator/metrics. For example,
  `http://localhost:8080/actuator/metrics/jvm.info` will show the java version the application is running

### /env Endpoint
* The /env endpoint exposes all the properties from the Spring's __ConfigurableEnvironment__ interface, such as
  a list of active profiles, application properties, system environment variables and so on.

### /threaddump Endpoint
* Using this endpoint, you can view your application's thread dump with running threads details and JVS stack trace.

### /loggers Endpoint
* The /loggers endpoint allows you to view and configure the log level of your application at runtime.
* You can view the logging level of the specific logger `http://localhost:8080/actuator/loggers/{name}`.
  For example `http://localhost:8080/actuator/loggers/org.springframework.web.util` or
  `http://localhost:8080/actuator/loggers/ca.gforcesoftware.restfulwebservice`
* By using specific logger, I can 'POST' the logger and change the log level to what I want. For example
```
POST http://localhost:8080/actuator/loggers/ca.gforcesoftware.restfulwebservice
Content-Type: application/json

{
  "configuredLevel": "DEBUG"
}

```
Then result will be like this command `GET http://localhost:8080/actuator/loggers/ca.gforcesoftware.restfulwebservice`
``` 
HTTP/1.1 200 
Content-Disposition: inline;filename=f.txt
Content-Type: application/vnd.spring-boot.actuator.v3+json
Transfer-Encoding: chunked
Date: Fri, 15 Nov 2024 17:19:35 GMT

{
  "configuredLevel": "DEBUG",
  "effectiveLevel": "DEBUG"
}
```

### /shutdown Endpoint
* The /shutdown endpoint can be used to gracefully shutdown the application
* This endpoint not enabled by default, You can enable this endpoint by adding
  `management.endpoint.shutdown.enabled=true` to application.properties
* After adding this property, we need to send the HTTP POST request in order to run it.
``` 
POST http://localhost:8080/actuator/shutdown 
```
The result it

``` 
HTTP/1.1 200 
Content-Type: application/vnd.spring-boot.actuator.v3+json
Transfer-Encoding: chunked
Date: Fri, 15 Nov 2024 17:25:45 GMT

{
  "message": "Shutting down, bye..."
}
```




## SpringDoc/Swagger-ui
* __springdoc-openapi__ java library helps to automate the generation of API documentation using Spring Boot projects
* __springdoc-openapi__ java library provides integration between spring-boot and swagger-ui. Automatically generates
  documentation in JSON/YMAL and HTML format APIs.
* This library support
  * OpenAPI 3
  * Spring-boot v3 (JAva 17+)
  * JSR-303 specifically for @NotNull, @Min, @Max and @Size (validation tags)
  * Swagger-ui
  * OAuth 2
* This a community-based project, not maintained by the spring framework contributions

### Development Steps
* Adding  __springdoc-openapi__ Maven dependency
``` 
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.3.0</version>
        </dependency>
```
I will have access using /swagger-ui/index.html `http://localhost:8080/swagger-ui/index.html`
* Defining General API information (Using Annotation)
``` 
@OpenAPIDefinition(
        info = @Info(
                title = "Gargamel Spring Boot Doc",
                description = "Gargamel Spring Boot Doc Description",
                version = "v1.0",
                contact = @Contact(
                        name = "Gavin",
                        email = "Gavin.Hashemi@GForceSoftware.ca",
                        url = "https://www.linkedin.com/in/ghobadh/"

                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.linkedin.com/in/ghobadh/"
                )

        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring Boot User Management Documentation",
                url = "http://localhost:8080/Users"
        )
) 
```
* Customizing Swagger API Documentation with annotations
  In class level I add @Tag
``` 
@Tag(
        name = "CRUD REST APIs for User Resource",
        description = "CRUD REST APIs for User Resource for Create User, Modify User, Delete User and Get All Users"
) 
```

in method level, I need to add these tages
```  
    @Operation(
            summary = "Update User  data REST API",
            description = "Get all User RESTful API is used to get the user from MySQL"

    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "20", description = "HTTP Status 200 get the all user info")
    } )
```
* Customizing Swagger Model Documentation with annotations
  in DTO class level. For example in UserDto.java
```  
@Schema(
        description = "UserDto Model Information"
)
```
In above each field of DTO class, I add this to describe the schema of the class
```  
    @Schema(
            description = "User email"
    )
```

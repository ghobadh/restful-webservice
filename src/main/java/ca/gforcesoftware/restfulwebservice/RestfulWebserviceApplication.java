package ca.gforcesoftware.restfulwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
// if you don't want to set up MySQL you just need to an exclusion like this
// @SpringBootApplication(exclude =  DataSourceAutoConfiguration.class)
@SpringBootApplication
public class RestfulWebserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulWebserviceApplication.class, args);
    }

}

package ca.gforcesoftware.restfulwebservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

// if you don't want to set up MySQL you just need to an exclusion like this
// @SpringBootApplication(exclude =  DataSourceAutoConfiguration.class)
@SpringBootApplication
public class RestfulWebserviceApplication {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(RestfulWebserviceApplication.class, args);
    }

}

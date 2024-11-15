package ca.gforcesoftware.restfulwebservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import jakarta.persistence.Version;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// if you don't want to set up MySQL you just need to an exclusion like this
// @SpringBootApplication(exclude =  DataSourceAutoConfiguration.class)
@SpringBootApplication
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
public class RestfulWebserviceApplication {

  //  @Bean
  //  public ModelMapper modelMapper(){
 //       return new ModelMapper();
  //  }

    public static void main(String[] args) {
        SpringApplication.run(RestfulWebserviceApplication.class, args);
    }

}

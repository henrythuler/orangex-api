package br.com.orangex.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(
                new Info()
                        .title("OrangeX API")
                        .version("v1")
                        .description("OrangeX Social Media API developed with Spring Boot and MongoDB.")
                        .license(new License().name("MIT").url("https://www.mit.edu/~amini/LICENSE.md"))
        )
        .tags(getTags())
        .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
        .components(new Components().addSecuritySchemes("Bearer Authentication", new SecurityScheme().type(SecurityScheme.Type.HTTP).bearerFormat("JWT").scheme("bearer")));
    }

    private List<Tag> getTags(){
        Tag tag1 = new Tag();
        tag1.setName("Authentication");
        tag1.setDescription("Login/Register Routes");

        Tag tag2 = new Tag();
        tag2.setName("Users");
        tag2.setDescription("All users methods routes");

        Tag tag3 = new Tag();
        tag3.setName("Posts");
        tag3.setDescription("All posts methods routes");

        return Arrays.asList(tag1, tag2, tag3);
    }

}

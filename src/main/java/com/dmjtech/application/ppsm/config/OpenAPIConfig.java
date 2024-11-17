package com.dmjtech.application.ppsm.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Dilan Jayaneththi
 * @mailto : ddmdilan@mail.com
 * @created : 6/29/2024, Saturday, 11:23 PM,
 * @project : basic-project
 * @package : com.dmjtech.application.basic_project.config
 **/

@Configuration
public class OpenAPIConfig {
    @Value("${application.name}")
    private String project;
    @Value("${application.version}")
    private String version;

    @Bean
    public OpenAPI initOpenAPI() {
        return new OpenAPI()
                .info(this.info());
    }

    private Info info() {
        return new Info()
                .title(project)
                .version(version)
                .contact(this.contact())
                .description("This microservice is payment project implementation.");
    }

    private Contact contact() {
        Contact contact = new Contact();
        contact.setName("Dilan Madura Jayaneththi");
        contact.email("ddmdilan@gmail.com");
        return contact;
    }
}

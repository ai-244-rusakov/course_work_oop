package ua.opnu.labwork2.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Система управління онлайн-курсами")
                        .version("1.0.0")
                        .description("REST API для платформи управління курсами, студентами та викладачами. " +
                                "Дозволяє створювати курси, модулі, реєструвати студентів на курси та відстежувати їх прогрес " +
                                "Побудовано на базі Spring Boot 3")
                        .contact(new Contact()
                                .name("Кафедра Інформаційних Систем")
                                .email("email@opnu.edu.ua")));
    }
}
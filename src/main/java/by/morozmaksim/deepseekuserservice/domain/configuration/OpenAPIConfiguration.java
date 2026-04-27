package by.morozmaksim.deepseekuserservice.domain.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("API работы с пользователями")               // Заголовок
                .description("Позволяет создать, обновлять, получать и удалять пользователей.") // Описание
                .version("v1.0.0")                          // Версия
                .contact(new Contact()
                        .name("morozmaksim")
                        .email("support@example.com")
                        .url("https://example.com"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("http://springdoc.org"));
    }
}

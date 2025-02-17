package io.github.fabiofavaleiro.libraryapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Library API",
                version = "v1",
                contact = @Contact(
                        name = "Fábio Freitas Assis Valeiro",
                        email = "fabiofavaleiro@gmail.com",
                        url = "Link em manutenção"
                )
        )
)
public class OpenApiConfiguration {
}

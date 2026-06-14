package sv.edu.ues.fia.siplanilla_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SiPlanilla SV API")
                        .version("1.0")
                        .description("Sistema de Gestión de Planillas"));
    }
}
